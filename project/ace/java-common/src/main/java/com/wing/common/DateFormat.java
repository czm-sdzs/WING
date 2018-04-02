package com.wing.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By: CuiBo
 * Date: 2018/3/5 15:57
 * Description:
 */
public class DateFormat {

    private static Logger LOG = LoggerFactory.getLogger(DateFormat.class);

    /**
     * @Author: CuiBo
     * @Params:
     * @Return:
     * @Date : 2018/3/5 15:57
     */
    public static void getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
        LOG.error("dateString:{}",dateString);

    }


    public static void main(String[] args) {
        getNowDate();
    }
}
