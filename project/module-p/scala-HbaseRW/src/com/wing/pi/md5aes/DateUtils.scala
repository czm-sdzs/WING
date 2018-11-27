package com.wing.pi.md5aes

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

//import com.juanpi.bi.streaming.DateHour
//import org.joda.time.DateTime

/**
  * Created by juanpi on 2015/8/18.
  */
object DateUtils {

  //yyyy-MM-dd HH:mm:ss 时间 转换为时间戳
  def tranTimeToLong(tm:String) :Long={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dt = fm.parse(tm)
    val aa = fm.format(dt)
    val tim: Long = dt.getTime()
    tim
  }

  //时间戳转化为时间
  def tranTimeToString(tm:Long) :String={
    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val tim = fm.format(new Date(tm))
    tim
  }

//  def dateHour(milliseconds: Long):DateHour = {
//    val dateTime = new DateTime(milliseconds)
//    DateHour(dateTime.toString("yyyy-MM-dd"), dateTime.toString("H"))
//  }

//  /**
//    * 返回日期加上gu_id最后一位，作为log文件的保存目录
//    *
//    * @param milliseconds
//    * @param gu_id
//    * @return
//    */
//  def dateGuidPartitions(milliseconds: Long, gu_id: String): String = {
//    val dateTime = new DateTime(milliseconds)
//    val date = dateTime.toString("yyyy-MM-dd")
//    val gu_hex = (gu_id.last).toLower
//    s"date=${date}/gu_hash=${gu_hex}"
//  }

//  /**
//    * 返回 yyyy-MM-dd 格式的日期
//    *
//    * @param milliseconds
//    * @return
//    */
//  def dateStr(milliseconds: Long): String = {
//    val dateTime = new DateTime(milliseconds)
//    dateTime.toString("yyyy-MM-dd")
//  }

//  /**
//    * 接受一个时间戳的参数，返回日期和小时
//    *
//    * @param milliseconds
//    * @return
//    */
//  def dateHourStr(milliseconds: Long): (String, String) = {
//    val dateTime = new DateTime(milliseconds)
//    (dateTime.toString("yyyy-MM-dd"), dateTime.toString("H"))
//  }

  /**
    *
    * @return 返回当前的日期串
    */
  def getDateNow():String={
    val now:Date = new Date()
    val dateFormat:SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dt = dateFormat.format( now )
    dt
  }

  /**
    * 指定日期和间隔天数，返回指定日期前N天的日期 date - N days
    * @param dt
    * @param interval
    * @return
    */
  def getDayStrBefore(dt: Date, interval: Int):String = {
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

    val cal: Calendar = Calendar.getInstance()
    cal.setTime(dt);

    cal.add(Calendar.DATE, - interval)
    val yesterday = dateFormat.format(cal.getTime())
    yesterday
  }
  def getDayBefore(dt: Date, interval: Int):Date = {
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(dt);

    cal.add(Calendar.DATE, - interval)
    cal.getTime()
  }


  /**
    * 指定日期和间隔天数，返回指定日期后N天的日期： date + N days
    * @param dt
    * @param interval
    * @return
    */
  def getDaysLater(dt: Date, interval: Int):String = {
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")

    val cal: Calendar = Calendar.getInstance()
    cal.setTime(dt);

    cal.add(Calendar.DATE, + interval)
    val yesterday = dateFormat.format(cal.getTime())
    yesterday
  }

  def getDaysLater(tm: String, interval: Int):String = {

    val fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dt = fm.parse(tm)
    val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    val cal: Calendar = Calendar.getInstance()
    cal.setTime(dt);

    cal.add(Calendar.DATE, + interval)
    val yesterday = dateFormat.format(cal.getTime())
    yesterday
  }
  /**
    * 2017-01-17  A Week Ago is 2017-01-07
    * @return
    */
  def getWeekAgoDateStr(): String = {
//    val dt: Date = new Date()
//    val dtStr = getDaysBefore(dt, 7)
//    dtStr
    ""
  }

  /**
    *  2017-01-17 A Week Later is  2017-01-21
    * @return
    */
  def getWeekLaterDateStr(): String = {
    val dt: Date = new Date()
    val dtStr = getDaysLater(dt, 7)
    dtStr
  }

  def getYesterday(): String = {
    // Calendar.DATE
//    val dt: Date = new Date()
//    val yesterday = getDaysBefore(dt, 1)
//    return yesterday
    ""
  }



  def main(args: Array[String]) {
    println(getWeekAgoDateStr())
    println(getWeekLaterDateStr())

  }
}