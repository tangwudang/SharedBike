package com.lishu.bike.utils;

import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

public class ImageUtil {

    /*public static void bindImageView(ImageView imgView, Uri uri, int defaultResId){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setFailureDrawableId(defaultResId)
                .setLoadingDrawableId(defaultResId)
                .setUseMemCache(true)
                .build();

        x.image().bind(imgView, uri.toString(), imageOptions);
    }*/

    public static void bindImageView(ImageView imgView, String path, int defaultResId){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setFailureDrawableId(defaultResId)
                .setLoadingDrawableId(defaultResId)
                .setUseMemCache(true)
                .build();

        x.image().bind(imgView, path, imageOptions);
    }

    /*public static void setUserAvatar(ImageView imgView, Uri uri, int defaultResId){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setFailureDrawableId(defaultResId)
                .setLoadingDrawableId(defaultResId)
                .setUseMemCache(true)
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setCircular(true)
                .build();

        x.image().bind(imgView, uri.toString(), imageOptions);
    }*/

    public static void setUserAvatar(ImageView imgView, String path, int defaultResId){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setFailureDrawableId(defaultResId)
                .setLoadingDrawableId(defaultResId)
                .setUseMemCache(true)
                .setCrop(true)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setCircular(true)
                .build();

        x.image().bind(imgView, path, imageOptions);
    }

}
