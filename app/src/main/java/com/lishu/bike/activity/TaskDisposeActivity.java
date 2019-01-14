package com.lishu.bike.activity;

import android.Manifest;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.lishu.bike.R;
import com.lishu.bike.adapter.LivePictureGridAdapter;
import com.lishu.bike.entity.LivePictureEntity;
import com.lishu.bike.http.HttpBase;
import com.lishu.bike.http.HttpLoader;
import com.lishu.bike.listener.TakePhotoListener;
import com.lishu.bike.model.BaseModel;
import com.lishu.bike.model.TaskImageModel;
import com.lishu.bike.task.Upgrade;
import com.lishu.bike.utils.LogUtil;
import com.lishu.bike.utils.SystemUtil;
import com.lishu.bike.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;

public class TaskDisposeActivity extends BaseActivity {
    private int taskId;
    private EditText disposeContent;
    //现场照片
    private GridView mPictrueGridview;
    private ArrayList<LivePictureEntity> imageList = new ArrayList<>();
    private int imageListSize = 0;//用户总共拍的照片张数
    private int uploadedImageSize = 0;//当前已经成功上传的照片张数
    private LivePictureGridAdapter mGridviewAdapter;
    private File cameraFile;
    private String uploadedFileNames = "";//已上传的图片名称，由服务器返回

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
        mGridviewAdapter.setPhotoListener(new TakePhotoListener() {
            @Override
            public void takePhoto() {
                checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        });
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
                    String sendFileLocalPath = cameraFile.getAbsolutePath();
                    LogUtil.d("sendFileLocalPath="+sendFileLocalPath);
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

    protected void onRequestPermissionsGranted(int requestCode) {
        if (requestCode == 100) {
            checkPermissions(new String[]{Manifest.permission.CAMERA}, 101);
        }else if(requestCode == 101){
            selectPicFromCamera();
        }
    }

    /**
     * 通过照相得到照片
     */
    public void selectPicFromCamera() {
        /*if (!(SystemUtil.selfPermissionGranted(mContext, Manifest.permission.CAMERA)
                && SystemUtil.selfPermissionGranted(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            return;
        }*/

        if (!(android.os.Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED))) {
            Toast.makeText(getApplicationContext(), "SD卡不存在，不能拍照",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/bike");
        if(!dir.exists()){
            dir.mkdir();
        }
        cameraFile = new File(dir, System.currentTimeMillis() + ".jpg");
        LogUtil.d("CleanReportActivity cameraFile1 = " + cameraFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(this, "com.lishu.bike.fileprovider", cameraFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, apkUri);
        }else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        }
        startActivityForResult(intent, 99);
    }

    //{"resCode":"000000","resMsg":"操作处理成功","taskResponseImageName":"/uploadfiles/taskResponse/1/1545963059913.jpg"}
    private void sendPicture(String imgPath) {
        HttpLoader.addTaskDisposeImage(imgPath, new HttpBase.IResponseListener() {
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
                TaskImageModel taskImageModel = (TaskImageModel) model;
                if (taskImageModel != null) {
                    uploadedFileNames += taskImageModel.getTaskResponseImageName() + ",";
                }

                uploadedImageSize++;
                if(uploadedImageSize == (imageListSize - 1)){//说明所有图片都已成功上传到服务器
                    hide();
                    submitTaskDispose();
                }
            }
        });
    }

    private void submitTaskDispose() {
        HttpLoader.addTaskResponse(taskId, disposeContent.getText().toString(),
                uploadedFileNames.substring(0, uploadedFileNames.length() - 1), new HttpBase.IResponseListener() {
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
                startActivity(new Intent(TaskDisposeActivity.this, TaskListActivity.class));
                //finish();
            }
        });
    }
}
