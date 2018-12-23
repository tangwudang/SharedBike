package com.lishu.bike.widget;

import android.app.DatePickerDialog;
import android.content.Context;

/**
 * 在4.1 4.2 4.4 版本下DatePickerDialog的onDateSet的回调方法会执行两次
 * 只需写一个子类继承DatePickerDialog，然后在里面重写父类的onStop()方法, 注释掉super.onStop()
 */
public class MyDatePickerDialog extends DatePickerDialog {

    public MyDatePickerDialog(Context context, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public MyDatePickerDialog(Context context, int theme, OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, DatePickerDialog.THEME_HOLO_LIGHT, callBack, year, monthOfYear, dayOfMonth);
    }

    @Override
    protected void onStop() {
        //super.onStop();
    }
}
