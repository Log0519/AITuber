package com.utils

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

/**
 * @Author Log
 * @Date 2022/11/22 22:33
 *       TODO 
 */
object TimeUtils {
  def TimeStampToDate(timestamp:Long):String= {
    val length: Int = timestamp.toString.length

    if (length== 13){
      var formats = "yyyy-MM-dd HH:mm:ss";
      var  date = new SimpleDateFormat(formats,Locale.CHINA).format(new Date(timestamp));
      date;
    }else{
      var formats = "yyyy-MM-dd HH:mm:ss";
      var  date = new SimpleDateFormat(formats,Locale.CHINA).format(new Date(timestamp*1000));
      date;
    }


  }
}
