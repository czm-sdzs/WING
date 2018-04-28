package com.wing.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 16:59
 * Description: 时间处理工具类
 */
public class TimeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtil.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @Author: CuiBo
     * @Description: 判断日期是否过期
     * @Params:
     * @Return:
     * @Date : 2018/3/29 16:58
     */

    public static boolean getDistanceDays(String date) {
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
