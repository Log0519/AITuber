package com.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @Author Log
 * @Date 2022/10/17 19:24
 *       TODO 
 */

object SparkInIt {
  def init():SparkSession  ={
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
    val spark: SparkSession = new SparkSession.Builder().config(sparkConf).enableHiveSupport().getOrCreate()
    spark
  }

}
