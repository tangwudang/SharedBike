package com.lishu.bike.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.lishu.bike.R;
import com.lishu.bike.widget.photo.PhotoView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class PhotoViewActivity extends BaseActivity {
    private PhotoView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        initView();
        initEvent();
    }

    private void initView() {
        setTopTitle("图片查看");

        photo = findViewById(R.id.image);
    }

    private void initEvent() {
        /*photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoViewActivity.this.finish();
            }
        });*/

        ImageOptions options = new ImageOptions.Builder()
                .setConfig(Bitmap.Config.RGB_565)
                .setFadeIn(true)
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setCircular(true)
                .setLoadingDrawableId(R.drawable.img_hellobike)
                .setFailureDrawableId(R.drawable.img_hellobike)
                .setUseMemCache(true)
                .build();

        x.image().bind(photo, getIntent().getStringExtra("imageUrl"), options);
    }
}
