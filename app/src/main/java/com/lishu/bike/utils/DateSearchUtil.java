package com.lishu.bike.utils;

import com.lishu.bike.listener.DateSearchListener;

public class DateSearchUtil {

    public static void searchByDate(String beginDate, String endDate, DateSearchListener listener){
        if (StringUtil.isEmpty(beginDate)) {
            if (StringUtil.isEmpty(endDate)) {
                listener.searchByDefaultDate();
            } else {
                ToastUtil.showShort("开始时间不能为空");
            }
        } else if (StringUtil.isEmpty(endDate)) {
            ToastUtil.showShort("结束时间不能为空");
        } else {
            int result = TimeUtil.compareDate(beginDate, endDate, "yyyy/MM/dd");
            if (result == 1) {//大于
                ToastUtil.showShort("开始时间不能大于结束时间");
            } else if (result == -1 || result == 0) {//小于或者等于
                listener.searchByChooseDate(beginDate.replace("/", ""), endDate.replace("/", ""));
            }
        }
    }
}
