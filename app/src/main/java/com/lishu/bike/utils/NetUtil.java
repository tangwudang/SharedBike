package com.lishu.bike.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 */
public class NetUtil {
	/**
     * 对网络连接状态进行判断
     */
    /*public static boolean isOpenNetwork2(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }

        return false;
    }*/

    public static boolean isOpenNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiNetInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if ((wifiNetInfo != null && wifiNetInfo.isConnected())
                || (mobNetInfo != null && mobNetInfo.isConnected())) {
            // 当前网络是连接的
            return true;
        }

        return false;
    }

    public static String getHttpPath(String urlPath){
        if (urlPath.startsWith("http://") || urlPath.startsWith("https://")
                || urlPath.startsWith("HTTP://") || urlPath.startsWith("HTTPS://")) {
            return urlPath;
        } else {
            return ("http://" + urlPath);
        }
    }
}
