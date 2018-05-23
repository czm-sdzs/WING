package com.wing.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By: CuiBo
 * Date: 2018/3/29 16:56
 * Description:
 */
public class TimeHandle {

    public static void main(String[] args) {
//        String data = "2018-2-28";
//        System.out.println(getDistanceDays(data));

//        getCurrenYMD();
        getToday();
//        test(getBefore15Days()y,)

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


    /**
     * @Author: CuiBo
     * @Description: 根据小时和分钟比较时间
     * @Params:
     * current_hour == hour && current_minute > minute; true
     * current_hour == hour && current_hour == minute; true
     * current_hour == hour && current_hour < minute; false
     * current_hour < hour; false
     * @Return:
     * @Date : 2018/3/23 15:09
     */

    public static boolean compareTime(int hour,int minute){
        Calendar calendar = Calendar.getInstance();
        int current_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int current_minute = calendar.get(Calendar.MINUTE);
        System.out.println("定义小时："+hour);
        System.out.println("定义分钟："+minute);
        System.out.println("current_hour "+current_hour);
        System.out.println("current_minute "+current_minute);
        if((current_hour < hour) || (current_hour == hour && current_minute < minute)){ // 未到查询时间
            return false;
        }
        return true;
    }


    public static void getCurrenYMD(){
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(date));
    }




    /**
     *
     * 获取给定时间前几分钟的时间
     * @author zhaoeryuan 2016年11月30日 下午6:08:56
     * @since 1.0.0.000
     * @return
     */
    public static Date getBefore15Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -14);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        return calendar.getTime();
    }


    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
        return calendar.getTime();
    }


//    public static void test((),)
//    public List<BamResultLog> setZeroToNull2(final long beginTime, final long endTime, final List<BamResultLog> logList) {
//        List<BamResultLog> result = new ArrayList<BamResultLog>();
//        long loopUnit = 24*60*60*1000;
//        if(logList.size() <=0) {
//            for(long i= beginTime; i < endTime; ) {
//                BamResultLog dto = new BamResultLog();
//                dto.setCollectTime(new Date(i));
//                dto.setCollectValue("0");
//                result.add(dto);
//                i=i+loopUnit;
//            }
//            return result;
//        }
//
//        BamResultLog dto = null;
//        for(long i= beginTime; i < endTime; ) {
//            boolean isExist =false;
//            inner:for(BamResultLog log : logList) {
//                if(log.getCollectTime().getTime() ==i) {
//                    isExist = true;
//                    dto = log;
//                    break inner;
//                }
//
//            }
//            if(!isExist) {
//                dto= new BamResultLog();
//                dto.setCollectTime(new Date(i));
//                dto.setCollectValue("0");
//
//            }
//            result.add(dto);
//            i=i+loopUnit;
//        }
//        return result;
//    }

}
