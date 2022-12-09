package com.log

import cn.hutool.core.collection.CollUtil.newArrayList
import com.utils.SparkInIt
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}

import java.util

/**
 * @Author Log
 * @Date 2022/12/9 14:06
 *       TODO 
 */
object getDanmu {
  def Bill(): util.ArrayList[String] = {
    val spark: SparkSession = SparkInIt.init()
    val df = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3306/aituber?serverTimezone=UTC")
      .option("dbtable", "danmu_bill")
      .option("user", "root")
      .option("password", "fuxiaoluo")
      .load()

    df.createOrReplaceTempView(("tempView"))

    val rdd: RDD[Row] = spark.sql(
      """
        |select * from tempView
        |""".stripMargin).rdd
    var list: util.ArrayList[String] = newArrayList()
    val rows: Array[Row] = rdd.collect()
    rows.foreach(
      data => {
        list.add(data.toString())
      })
    list
      }
}
