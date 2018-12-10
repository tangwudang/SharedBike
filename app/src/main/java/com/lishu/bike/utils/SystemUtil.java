package com.lishu.bike.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.PermissionChecker;

public class SystemUtil {

    public static boolean selfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (getTargetSdkVersion(context) >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                result = context.checkSelfPermission(permission)
                        == PackageManager.PERMISSION_GRANTED;
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                result = PermissionChecker.checkSelfPermission(context, permission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
        }

        return result;
    }

    private static int getTargetSdkVersion(Context context) {
        int version = 0;

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            version = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context cxt) {
        try {
            PackageManager manager = cxt.getPackageManager();
            PackageInfo info = manager.getPackageInfo(cxt.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取版本名称
     */
    public static String getVersionName(Context cxt) {
        try {
            PackageManager manager = cxt.getPackageManager();
            PackageInfo info = manager.getPackageInfo(cxt.getPackageName(), 0);
            return "V" + info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "V1.0";
        }
    }

    /**
     * 手机系统的版本
     */
    public static String getPhoneOsRelease() {
        String version = "android";
        try {
            version += android.os.Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 终端型号
     */
    public static String getPhoneModel() {
        String model = "";
        try {
            model += android.os.Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 终端品牌
     */
    public static String getPhoneBrand() {
        String brand = "";
        try {
            brand += android.os.Build.BRAND;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

}
