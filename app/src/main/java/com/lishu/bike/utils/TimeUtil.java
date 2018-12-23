package com.lishu.bike.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */

public class TimeUtil {

    public static String getCurDatetime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式2017-02-07 10:20:35

        return df.format(new Date());
    }

    public static String getCurDatetimeByPattern(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式"yyyy年MM月dd日"

        return df.format(new Date());
    }

    /**
     * 获取时间
     */
    public static String getMessageTime(String datetime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date dt = df.parse(datetime);
            //SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日   HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            return formatter.format(dt);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return String.valueOf(date.getTime());
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            //return String.valueOf(sdf.parse(date_str).getTime()/1000);
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // 整数(秒数)转换为时分秒格式(xx:xx:xx)
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 比较2个时间
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String format) {
        DateFormat df1 = new SimpleDateFormat(format);
        DateFormat df2 = new SimpleDateFormat(format);
        try {
            Date dt1 = df1.parse(DATE1);
            Date dt2 = df2.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return -2;
    }
}
