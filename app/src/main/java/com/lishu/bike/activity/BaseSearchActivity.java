package com.lishu.bike.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.DatePicker;
import android.widget.EditText;

import com.lishu.bike.R;
import com.lishu.bike.widget.MyDatePickerDialog;

import java.util.Calendar;

public class BaseSearchActivity extends BaseActivity{
    private Calendar calender;
    protected final int COUNT_PER_PAGE = 10;//每页条数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calender = Calendar.getInstance();
    }

    /**
     * 时间选择对话框
     * @param type:1、开始时间 2、结束时间
     */
    protected void showDatePickerDialog(final int type) {
        new MyDatePickerDialog(BaseSearchActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        //修改时间显示，例如 7月8日，显示为 07月08日
                        String myMonth, myDay;
                        if(monthOfYear < 10){
                            myMonth = "0" + monthOfYear;
                        }else{
                            myMonth = "" + monthOfYear;
                        }
                        if(dayOfMonth < 10){
                            myDay = "0" + dayOfMonth;
                        }else{
                            myDay = "" + dayOfMonth;
                        }
                        if (type == 0) {
                            //begin_time_ev.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                            EditText beginTime = findViewById(R.id.begin_time_ev);
                            if(beginTime != null){
                                beginTime.setText("" + year + monthOfYear + dayOfMonth);
                            }
                        } else if (type == 1) {
                            //end_time_ev.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                            EditText endTime = findViewById(R.id.end_time_ev);
                            if(endTime != null){
                                endTime.setText("" + year + monthOfYear + dayOfMonth);
                            }
                        }
                    }
                },
                calender.get(Calendar.YEAR), // 传入年份
                calender.get(Calendar.MONTH), // 传入月份
                calender.get(Calendar.DAY_OF_MONTH) // 传入天数
        ).show();
    }
}
