package com.log

import com.utils.SparkInIt
import org.apache.spark.sql.SparkSession

/**
 * @Author Log
 * @Date 2022/11/26 16:00
 *       TODO 
 */
object login {
def login(username:String,password:String): Unit ={
  val spark: SparkSession = SparkInIt.init()
  val df = spark.read
    .format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/cardemo?serverTimezone=UTC")
    .option("dbtable", "weather_demo_user")
    .option("user", "root")
    .option("password", "fuxiaoluo")
    .load()

  df.createOrReplaceTempView(("tempView"))
  spark.sql(
    s"""
      |select
      |count(*)
      |from tempView
      |where username=${username} and password=${password}
      |""".stripMargin)

}
}
