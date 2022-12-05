package com.log
import com.utils.TimeUtils.TimeStampToDate
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer
/**
 * @Author Log
 * @Date 2022/12/5 10:38
 *       TODO 
 */
object DanmuKafka {
  def SendDanmu(info: String): Unit ={
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val strings: Array[String] = info.split(",")
    val timeStamp: String = strings(2)
    val time: String = TimeStampToDate(timeStamp.toLong)
    val newInfo:String="用户："+strings(0)+"\t内容："+strings(1)+"\ttime："+time
    val stream: DataStream[String] = env.fromElements(newInfo)
    //数据写入Kafka
    stream.addSink(new FlinkKafkaProducer[String]("hadoop102:9092","DanmuSource",new SimpleStringSchema()))
    print("完成")
    env.execute()
  }
}
