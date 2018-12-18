package com.lishu.bike.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.lishu.bike.R;
import com.lishu.bike.adapter.LivePictureGridAdapter;
import com.lishu.bike.entity.LivePictureEntity;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;

import me.jessyan.autosize.utils.LogUtils;

public class TaskDisposeActivity extends BaseActivity {
    private int taskId;
    private EditText disposeContent;
    private Button submit;
    //现场照片
    private GridView mPictrueGridview;
    private LayoutInflater mInflater;
    private ArrayList<LivePictureEntity> imageList = new ArrayList<>();
    private int imageListSize = 0;//用户总共拍的照片张数
    private int uploadedImageSize = 0;//当前已经成功上传的照片张数
    private StringBuilder imageURLs;
    private LivePictureGridAdapter mGridviewAdapter;
    private File cameraFile;
    private String sendFileLocalPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_dispose);

        taskId = getIntent().getIntExtra("taskId", -1);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle("任务处理");
        disposeContent = findViewById(R.id.disposeContent);
        //现场照片
        mPictrueGridview = findViewById(R.id.picture_gridview);
        initGridViewData();//初始化GridView，默认添加“+图片”
        mGridviewAdapter = new LivePictureGridAdapter(this);
        mPictrueGridview.setAdapter(mGridviewAdapter);
        mGridviewAdapter.setData(imageList);
    }

    private void initEvent() {
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(disposeContent.getText().toString())) {
                    ToastUtil.showShort("请填写处理内容");
                } else {
                    imageListSize = imageList.size();
                    if (imageListSize <= 1) {
                        ToastUtil.showShort("请拍摄至少1张现场照片");
                    } else {
                        showAlways("上传中，请稍后...");
                        imageURLs = new StringBuilder();
                        for (int i = 0; i < imageListSize; i++) {
                            if (imageList.get(i).getType() == 0) {
                                sendPicture(imageList.get(i).getIcon());
                            }
                        }
                    }
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
        if(resultCode == RESULT_OK && requestCode == 99){
            if (cameraFile != null && cameraFile.exists()){
                try {
                    //1、得到新拍的照片
                    sendFileLocalPath = cameraFile.getAbsolutePath();
                    LogUtils.d("sendFileLocalPath="+sendFileLocalPath);

                    //2、加入userList
                    LivePictureEntity livePictureEntity;
                    livePictureEntity = new LivePictureEntity();
                    livePictureEntity.setType(0);
                    livePictureEntity.setIcon(sendFileLocalPath);
                    LogUtils.d("imageList.size()="+imageList.size());
                    imageList.add(imageList.size() - 1, livePictureEntity);
                    //3、更新GridView显示
                    mGridviewAdapter.notifyDataSetChanged();

                    //4、压缩图片
                    /*Message msg = new Message();
                    msg.what = MESSAGE_COMPRESS_IMAGE;
                    Bundle bundle = new Bundle();
                    bundle.putString("path", sendFileLocalPath);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过照相得到照片
     */
    public void selectPicFromCamera() {
        if (!(android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED))) {
            LogUtils.d("CleanReportActivity SD卡不存在，不能拍照");
            Toast.makeText(getApplicationContext(), "SD卡不存在，不能拍照",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/bike");
        if(!dir.exists()){
            dir.mkdir();
        }
        cameraFile = new File(dir, System.currentTimeMillis() + ".jpg");
        LogUtils.d("CleanReportActivity cameraFile1 = " + cameraFile);
        LogUtils.d("CleanReportActivity cameraFile2 = " + cameraFile.toString());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        startActivityForResult(intent, 99);
    }

    private void sendPicture(String imgPath) {
        HttpLoader.addTaskDisposeImage(taskId, imgPath, new HttpBase.IResponseListener() {
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
                uploadedImageSize++;
                if(uploadedImageSize == imageListSize){//说明所有图片都已成功上传到服务器
                    hide();
                    submitTaskDispose();
                }
            }
        });
    }

    private void submitTaskDispose() {
        HttpLoader.addTaskDisposeImage(taskId, disposeContent.getText().toString(), new HttpBase.IResponseListener() {
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
}
