package com.wing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 16:56
 * Description:
 */
public class TimeHandle {

    public static void main(String[] args) {
        String data = "2018-2-28";
        System.out.println(getDistanceDays(data));

    }
    /**
     * @Author: CuiBo
     * @Description: 判断日期是否过期
     * @Params:
     * @Return:
     * @Date : 2018/3/29 16:58
     */

    public static boolean getDistanceDays(String date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        long days = 0;
        try {
            Date time = df.parse(date);//String转Date
            Date now = new Date();//获取当前时间
            long time1 = time.getTime();
            long time2 = now.getTime();
            long diff = time1 - time2;
            days = diff / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //正数表示在当前时间之后，负数表示在当前时间之前
        if(days <0) return false;
        return true;
    }
}
