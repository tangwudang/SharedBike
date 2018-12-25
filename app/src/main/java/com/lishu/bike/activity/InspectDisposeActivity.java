package com.lishu.bike.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.lishu.bike.R;
import com.lishu.bike.adapter.LivePictureGridAdapter;
import com.lishu.bike.app.BaseApplication;
import com.lishu.bike.entity.LivePictureEntity;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.TakePhotoListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.InspectImageModel;
import com.lishu.bike.model.InspectTypeModel;
import com.lishu.bike.utils.LocationService;
import com.lishu.bike.utils.LogUtil;
import com.lishu.bike.utils.SystemUtil;
import com.lishu.bike.utils.TimeUtil;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.AmountView;
import com.lishu.bike.widget.NoScrollGridView;
import com.lishu.bike.widget.SpinerPopWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InspectDisposeActivity extends BaseActivity implements View.OnClickListener, AmountView.OnAmountChangeListener {
    //车辆数量
    private AmountView mobikeNum, ofoNum, helloNum;
    private int mobikeCount, ofoCount, helloCount;
    //违规类型
    private TextView violationType;
    private SpinerPopWindow mSpinerPopWindow;
    //private List<InspectTypeModel.InspectTypeBean> typeList;
    private int violationTypeId = -1;
    //处理内容
    private EditText disposeContent;
    //现场照片
    private NoScrollGridView mPictrueGridview;
    private ArrayList<LivePictureEntity> imageList = new ArrayList<>();
    private int imageListSize = 0;//用户总共拍的照片张数
    private int uploadedImageSize = 0;//当前已经成功上传的照片张数
    private LivePictureGridAdapter mGridviewAdapter;
    private File cameraFile;
    private String uploadedFileNames;//已上传的图片名称，由服务器返回
    //定位
    private TextView location_addr;
    private String locationAddress;
    private LocationService locationService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inspect);

        initView();
        initEvent();

        getViolationTypes();
        startBaiduLocation();
    }

    private void initView() {
        setTopTitle("巡检上报");
        //车辆数量
        mobikeNum = findViewById(R.id.mobikeNum);
        ofoNum = findViewById(R.id.ofoNum);
        helloNum = findViewById(R.id.helloNum);
        //违规类型
        violationType = findViewById(R.id.violationType);
        mSpinerPopWindow = new SpinerPopWindow(this, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InspectTypeModel.InspectTypeBean inspectTypeBean = (InspectTypeModel.InspectTypeBean) parent.getItemAtPosition(position);
                violationType.setText(inspectTypeBean.getParamName());
                violationTypeId = inspectTypeBean.getId();
                mSpinerPopWindow.dismiss();
            }
        });
        //处理内容
        disposeContent = findViewById(R.id.disposeContent);
        //现场照片
        mPictrueGridview = findViewById(R.id.picture_gridview);
        initGridViewData();//初始化GridView，默认添加“+图片”
        mGridviewAdapter = new LivePictureGridAdapter(this);
        mPictrueGridview.setAdapter(mGridviewAdapter);
        mGridviewAdapter.setData(imageList);
        //定位
        location_addr = findViewById(R.id.location_addr);
    }

    private void initEvent() {
        mobikeNum.setOnAmountChangeListener(this);
        ofoNum.setOnAmountChangeListener(this);
        helloNum.setOnAmountChangeListener(this);
        violationType.setOnClickListener(this);
        mSpinerPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTextImage(R.drawable.icon_down);
            }
        });
        mGridviewAdapter.setPhotoListener(new TakePhotoListener() {
            @Override
            public void takePhoto() {
                selectPicFromCamera();
            }
        });
        findViewById(R.id.submit).setOnClickListener(this);
        findViewById(R.id.location_button_layout).setOnClickListener(this);
    }

    @Override
    public void onAmountChange(View view, int amount) {
        switch (view.getId()) {
            case R.id.mobikeNum:
                mobikeCount = amount;
                LogUtil.d("mobikeNum = " + mobikeCount);
                break;
            case R.id.ofoNum:
                ofoCount = amount;
                LogUtil.d("ofoNum = " + ofoCount);
                break;
            case R.id.helloNum:
                helloCount = amount;
                LogUtil.d("helloNum = " + helloCount);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.violationType:
                mSpinerPopWindow.setWidth(violationType.getWidth());
                mSpinerPopWindow.showAsDropDown(violationType);
                setTextImage(R.drawable.icon_up);
                break;
            case R.id.submit:
                if (TextUtils.isEmpty(disposeContent.getText().toString())) {
                    ToastUtil.showShort("请填写处理内容");
                } else {
                    imageListSize = imageList.size();
                    if (imageListSize <= 1) {
                        ToastUtil.showShort("请拍摄至少1张现场照片");
                    } else {
                        showAlways("上传中，请稍后...");
                        for (int i = 0; i < imageListSize; i++) {
                            if (imageList.get(i).getType() == 0) {
                                sendPicture(imageList.get(i).getIcon());
                            }
                        }
                    }
                }
                break;
            case R.id.location_button_layout:
                //开始定位
                startBaiduLocation();
                break;
        }
    }

    private void sendPicture(String imgPath) {
        HttpLoader.addInspectImage(-1, imgPath, new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    hide();
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    hide();
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                InspectImageModel inspectImageModel = (InspectImageModel) model;
                if (inspectImageModel != null) {
                    uploadedFileNames = inspectImageModel.getInspectImageName() + ",";
                }
                uploadedImageSize++;
                if (uploadedImageSize == imageListSize) {//说明所有图片都已成功上传到服务器
                    hide();
                    submitInspectReport();
                }
            }
        });
    }

    private void submitInspectReport() {
        HttpLoader.addInspect(mobikeCount, ofoCount, helloCount, violationTypeId,
                disposeContent.getText().toString(), locationAddress, TimeUtil.getCurDatetime(),
                uploadedFileNames.substring(0, uploadedFileNames.length() - 2), new HttpBase.IResponseListener() {
                    @Override
                    public void onResponse(BaseModel model) {
                        if (model == null) {
                            ToastUtil.showShort(R.string.please_check_network);
                            return;
                        }
                        if (!model.success()) {
                            ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                            return;
                        }

                        ToastUtil.showShort("提交成功！");
                    }
                });
    }

    private void getViolationTypes() {
        HttpLoader.getDictionaryTypes(new HttpBase.IResponseListener() {
            @Override
            public void onResponse(BaseModel model) {
                if (model == null) {
                    hide();
                    ToastUtil.showShort(R.string.please_check_network);
                    return;
                }
                if (!model.success()) {
                    hide();
                    ToastUtil.showShort(getString(R.string.get_data_fail) + model.getResMsg());
                    return;
                }

                List<InspectTypeModel.InspectTypeBean> inspectTypeList = ((InspectTypeModel) model).getDataList();
                if (inspectTypeList != null) {
                    mSpinerPopWindow.setData(inspectTypeList);
                }
            }
        });
    }

    private void initGridViewData() {
        LivePictureEntity livePictureEntity = new LivePictureEntity();
        livePictureEntity.setType(1);
        livePictureEntity.setIcon("xx");
        imageList.add(livePictureEntity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 99) {
            if (cameraFile != null && cameraFile.exists()) {
                try {
                    //1、得到新拍的照片
                    String sendFileLocalPath = cameraFile.getAbsolutePath();
                    LogUtil.d("sendFileLocalPath=" + sendFileLocalPath);
                    //2.1、压缩图片
                    //2.2、加入List
                    LivePictureEntity livePictureEntity = new LivePictureEntity();
                    livePictureEntity.setType(0);
                    livePictureEntity.setIcon(sendFileLocalPath);
                    imageList.add(imageList.size() - 1, livePictureEntity);
                    //3、更新GridView显示
                    mGridviewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过照相得到照片
     */
    protected void onRequestPermissionsDenied(int requestCode) {
        if (requestCode == 22) {

        }
    }

    public void selectPicFromCamera() {
        if (!(SystemUtil.selfPermissionGranted(mContext, Manifest.permission.CAMERA)
                && SystemUtil.selfPermissionGranted(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            ActivityCompat.requestPermissions(InspectDisposeActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 22);
            return;
        }

        if (!(android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED))) {
            Toast.makeText(getApplicationContext(), "SD卡不存在，不能拍照",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/bike");
        if (!dir.exists()) {
            dir.mkdir();
        }
        cameraFile = new File(dir, System.currentTimeMillis() + ".jpg");
        LogUtil.d("CleanReportActivity cameraFile1 = " + cameraFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(this, "com.lishu.bike.fileprovider", cameraFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, apkUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        }
        startActivityForResult(intent, 99);
    }

    @Override
    protected void onStop() {
        stopBaiduLocation();
        super.onStop();
    }

    private void startBaiduLocation(){
        locationService = ((BaseApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
    }

    private void stopBaiduLocation(){
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                int errorCode = location.getLocType();
                locationAddress = location.getAddrStr();
                if(locationAddress != null) {
                    location_addr.setText(locationAddress);
                }else{
                    location_addr.setText("定位失败，请保持网络畅通，重新定位！");
                }

                //LogUtil.d( "getAddrStr = " + locationAddress);
                //LogUtil.d("getAddress = " +location.getAddress().address);
                LogUtil.d("定位返回码：" +  errorCode);
            }

            stopBaiduLocation();
        }
    };

    /**
     * 给TextView右边设置图片
     */
    private void setTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
        violationType.setCompoundDrawables(null, null, drawable, null);
    }
}
