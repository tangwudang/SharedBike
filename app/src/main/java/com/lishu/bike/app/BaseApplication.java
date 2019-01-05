package com.lishu.bike.app;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.lishu.bike.constant.AppConfig;
import com.lishu.bike.constant.UserPreferences;
import com.lishu.bike.utils.LocationService;

import org.xutils.x;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class BaseApplication extends Application {
    private static Context applicationContext;
    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();
        // 初始化AutoSize
        configUnits();
        // 初始化Preferences
        UserPreferences.getInstance().init(this);
        // 初始化xUtils
        x.Ext.init(this);
        //if (AppConfig.IS_DEBUG) {
            // 是否输出debug日志, 开启debug会影响性能.
            x.Ext.setDebug(false);
        //}
        // 百度地图初始化
        locationService = new LocationService(getApplicationContext());
        SDKInitializer.initialize(this);
        // 百度地图支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        //SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                // 将 dp 的支持关闭, 彻底隔离修改 density 所造成的不良影响
                .setSupportDP(false)
                // 如果关闭对 sp 的支持, 在布局时就应该使用副单位填写字体的尺寸
                // 如果开启 sp, 对其他三方库和系统控件影响不大, 也可以不关闭对 sp 的支持
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
    }
}
