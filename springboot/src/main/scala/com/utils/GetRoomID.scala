package com.utils

import java.io.BufferedReader
import java.net.URL
import scala.io.{BufferedSource, Source}

/**
 * @Author Log
 * @Date 2022/12/11 8:25
 *       TODO 
 */
object GetRoomID {
  def main(args: Array[String]): Unit = {

  }
  def record(url: String): String = {
    var newUrl = new URL(url); // 把字符串转换为URL请求地址
    val source: BufferedSource = Source.fromURL(newUrl)
    val br: BufferedReader = source.bufferedReader()
    var line = ""
    line = br.readLine()
    br.close(); // 关闭流
    source.close()
//    {"code":0,"msg":"ok","message":"ok",
    //    "data":{"room_id":12655678,"short_id":52030,"uid":23383532,"need_p2p":0,
    //    "is_hidden":false,"is_locked":false,"is_portrait":false,"live_status":1,
    //    "hidden_till":0,"lock_till":0,"encr
    //    ypted":false,"pwd_verified":false,"live_time":1670691133,
    //    "room_shield":0,"is_sp":0,"special_type":0}}
    println(line.split("room_id\":")(1).split(",\"short_id")(0))
    line.split("room_id\":")(1).split(",\"short_id")(0)
  }
//https://api.live.bilibili.com/room/v1/Room/room_init?id=52030
  def GetBillRoomID(id:String): String ={
    val url = "https://api.live.bilibili.com/room/v1/Room/room_init?id="+id
    val roomid: String = record(url)
    roomid
  }

}
