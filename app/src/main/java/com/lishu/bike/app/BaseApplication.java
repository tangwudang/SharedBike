package com.lishu.bike.app;

import android.app.Application;
import android.content.Context;

import com.lishu.bike.constant.AppConfig;
import com.lishu.bike.constant.UserPreferences;

import org.xutils.x;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class BaseApplication extends Application {
    private static Context applicationContext;

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
        if (AppConfig.IS_DEBUG) {
            // 是否输出debug日志, 开启debug会影响性能.
            x.Ext.setDebug(true);
        }
    }

    public static Context getAppContext() {
        return applicationContext;
    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                // 将 dp 的支持关闭, 彻底隔离修改 density 所造成的不良影响
                .setSupportDP(false)
                // 如果关闭对 sp 的支持, 在布局时就应该使用副单位填写字体的尺寸
                // 如果开启 sp, 对其他三方库控件影响不大, 也可以不关闭对 sp 的支持, 这里我就继续开启 sp
                //.setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
    }
}