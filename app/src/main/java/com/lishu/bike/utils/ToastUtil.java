package com.lishu.bike.utils;

import android.content.Context;
import android.widget.Toast;

import com.lishu.bike.app.BaseApplication;

/**
 * Toast工具类（如需设置显示位置，另外定制）
 */
public class ToastUtil {
    private static Toast toast;//实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

    public static void showShort(String msg) {
        if(BaseApplication.getAppContext() != null){
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    public static void showShort(int msg) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }


    public static void showLong(String msg) {
        if(BaseApplication.getAppContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    public static void showLong(int msg) {
        if(BaseApplication.getAppContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_LONG);
            } else {
                toast.setText(msg);
                toast.setDuration(Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, CharSequence msg, int duration) {
        if(BaseApplication.getAppContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getAppContext(), msg, duration);
            } else {
                toast.setText(msg);
                toast.setDuration(duration);
            }
            toast.show();
        }
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int msg, int duration) {
        if(BaseApplication.getAppContext() != null) {
            if (toast == null) {
                toast = Toast.makeText(BaseApplication.getAppContext(), msg, duration);
            } else {
                toast.setText(msg);
                toast.setDuration(duration);
            }
            toast.show();
        }
    }

}
