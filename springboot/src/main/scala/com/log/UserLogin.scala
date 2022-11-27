package com.log

import com.utils.SparkInIt
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * @Author Log
 * @Date 2022/11/26 16:00
 *       TODO 
 */
object UserLogin {
  def main(args: Array[String]): Unit = {
    login("root111","123456")
  }
def login(username:String,password:String):Boolean = {
  val spark: SparkSession = SparkInIt.init()
  val df = spark.read
    .format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/db1?serverTimezone=UTC")
    .option("dbtable", "weather_demo_user")
    .option("user", "root")
    .option("password", "fuxiaoluo")
    .load()

  df.createOrReplaceTempView(("tempView"))
  val frame: DataFrame = spark.sql(
    s"""
       |select
       |*
       |from tempView
       |where username='${username}' and password='${password}'
       |""".stripMargin)

  if(frame.count()==1){
    true
  }else{
    false
  }
}
}
