package com.log
import com.utils.TimeUtils.TimeStampToDate
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.configuration.Configuration
import org.apache.flink.connector.jdbc.{JdbcConnectionOptions, JdbcSink, JdbcStatementBuilder}
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, KafkaConsumer}
import org.apache.spark.api.java.function.MapFunction

import java.sql.{Connection, DriverManager, PreparedStatement}
import java.util
import java.util.{Collections, Properties}


/**
 * @Author Log
 * @Date 2022/12/5 10:38
 *       TODO 
 */
case class danmu(name:String,neirong:String,time:String)
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
//    stream.addSink(JdbcSink.sink(
//      "INSERT INTO danmu_bill(name,neirong,time) VALUES (?,?,?)",//定义写入Mysql的语句
//      new JdbcStatementBuilder[String] {
//        override def accept(t: PreparedStatement, u:String): Unit = {
//          t.setString(1,u.split("用户：")(1).split("\t")(0))
//          t.setString(2,u.split("内容：")(1).split("\t")(0))
//          t.setString(3,u.split("time：")(1))
//        }
//      },
//      new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
//        .withUrl("jdbc:mysql://localhost:3306/aituber")
//        .withDriverName("com.mysql.jdbc.driver")
//        .withUsername("root")
//        .withPassword("fuxiaoluo")
//        .build()
//    ))

    env.execute()
  }
  def GetDanmu(sink: MyRichSinkToMySQL): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    //构建ETL管道
    //从Kafka读取数据,并且写入mysql
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","hadoop102:9092")
    val stream: DataStream[String] = env.addSource(new FlinkKafkaConsumer[String]("DanmuSource", new SimpleStringSchema(), properties))

    //用户：主子是墨鱼丸子-	内容：飘逸了	time：2022-12-09 12:28:16
    val danmuStream: DataStream[danmu] = stream.map(data => {
      val words: Array[String] = data.split("\t")
      danmu(words(0).split("用户：")(1), words(1).split("内容：")(1), words(2).split("time：")(1))
    })
    danmuStream.addSink(sink)
    env.execute()
  }
  class MyRichSinkToMySQL extends RichSinkFunction[danmu] {
    //预编译
    //mysql连接配置
    var conn: Connection =_
    //插入语句
    var insertTmp: PreparedStatement =_
//    //更新语句
//    var updateTmp: PreparedStatement =_

    //重写open方法
    override def open(parameters: Configuration): Unit = {
      //实现连接配置
      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/aituber","root","fuxiaoluo")
      //实现插入语句，在invoke中确实具体插入的数据内容
      insertTmp=conn.prepareStatement("insert into danmu_bill (name,neirong,time) values (?,?,?)")
      //实现更新语句，在invoke中确实具体插入的数据内容
    // updateTmp=conn.prepareStatement("update danmu_bill set name = ? where id = ? ")
    }

    //实现invoke方法，执行具体的逻辑操作
    override def invoke(value: danmu, context: SinkFunction.Context): Unit = {
      //执行更新操作
//      updateTmp.setString(1,value.name)
//      updateTmp.setString(2,value.neirong)
//      updateTmp.setString(3,value.time)
//      updateTmp.execute()

      //判断更新条数，如果为0，说明该不存在，即执行插入操作
   //   if (updateTmp.getUpdateCount == 0 ){
        insertTmp.setString(1,value.name)
        insertTmp.setString(2,value.neirong)
        insertTmp.setString(3,value.time)
        insertTmp.execute()
      //}
    }

    //关闭所有连接
    override def close(): Unit = {
      insertTmp.close()
      //updateTmp.close()
      conn.close()
    }
  }
}
