package com.lishu.bike.app;

import android.app.Application;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        configUnits();
    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSubunits(Subunits.MM);
    }
}
