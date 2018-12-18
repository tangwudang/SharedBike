package com.lishu.bike.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.lishu.bike.R;
import com.lishu.bike.utils.ImageUtil;
import com.lishu.bike.utils.ToastUtil;
import com.lishu.bike.widget.photo.PhotoView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;

public class PhotoViewActivity extends BaseActivity {
    private PhotoView photo;
    private String localPath;
    private String remotePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        localPath = getIntent().getExtras().getString("localPath");
        remotePath = getIntent().getExtras().getString("remotePath");

        initView();
    }

    private void initView() {
        setTopTitle("图片查看");

        photo = findViewById(R.id.image);

        //本地存在，直接显示本地的图片
        if ((!TextUtils.isEmpty(localPath)) && new File(localPath).exists()) {
            ImageUtil.bindImageView(photo, localPath, R.drawable.ic_default_photo);
        } else if (!TextUtils.isEmpty(remotePath)) { //去服务器下载图片
            ImageOptions options = new ImageOptions.Builder()
                    .setConfig(Bitmap.Config.RGB_565)
                    .setFadeIn(true)
                    .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                    .setLoadingDrawableId(R.drawable.ic_default_photo)
                    .setFailureDrawableId(R.drawable.ic_default_photo)
                    .setUseMemCache(true)
                    .build();

            x.image().bind(photo, remotePath, options);
        } else {
            photo.setImageResource(R.drawable.ic_default_photo);
            ToastUtil.showShort("找不到该图片的存放路径，或已被清理");
        }
    }

}
