package com.wing.pi.tools.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By: CuiBo
 * Date: 2018/8/3 11:51
 * Description:
 */
public class DTime {
    public static void main(String[] args) {
        System.out.println(getTimeString(getYestardayZero()));
        getNowSDate();
    }

    /**
     * @Author: CuiBo
     * @Description: 获取当前时间
     * @Params: 
     * @Return: “yyyy-MM-dd HH:mm:ss”
     * @Date : 2018/8/3 11:54
     */
     
    public static String getNowSDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        return dateString;
    }
    /**
     *
     * @return 2108-11-11 00:00:00
     */
    public static Date getYestardayZero(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getTimeString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
