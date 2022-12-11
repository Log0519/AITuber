<template>
  <div id="sse">
    <div id="BillBill_url" hidden>
      <span>BillBill_url:</span>
<!--      wss://titan-ws.pinduoduo.com/-->
<!--      wss://broadcastlv.chat.bilibili.com/sub-->
<!--      https://api.live.bilibili.com/room/v1/Room/room_init?id=52030-->
      <el-input disabled type="text" id="url" value="wss://broadcastlv.chat.bilibili.com/sub" style="width: 40%;margin-left: 10px"> </el-input>
      </div>
    <div style="display: flex;font-size: 16px;margin-top: 5px">
<!--      401854710-->
<!--      1440094-->

      直播房间 url:
      <el-input   placeholder="请输入内容"
                  type="text"
                  id="homeurl"
                  v-model="homeUrl"
                  style="width:60%;
                  margin-left: 10px"></el-input>
    </div>
<!--    直播间的实时消息会发送到flink，经过处理后作为生产者发送到kafka的DanmuSource主题上-->
<!--    先打开kafka消费者，bin/kafka-console-consumer.sh --bootstrap-server hadoop102:9092 --topic DanmuSource-->
    <div>
      <el-button @click="getDanmu()">实时获取</el-button>
      <el-button @click="stopDanmu">停止获取</el-button>
      <el-switch
          style="margin-left: 30px"
          v-model="isWrite"
          @change="writeToMysql()"
          active-text="入库（开启入库需要启动kafka）"
          >
      </el-switch>
    </div>
  </div>


</template>



<script>
import SimpleDateFormat from "three/addons/nodes/core/NodeBuilder";
import {Locale} from "vant";
import request from "../utils/request";
  export default {
    name: "sendDanmuBill",
    components:{
    },
    mounted() {
      window.itemsSend=this.itemsSend
      console.log("homeID"+this.homeID)
      this.isWrite=false
    },
    data() {
      return {
        isWrite:false,
        itemsSend:[],
        temp:[],
        homeID:0,
        homeUrl:'https://live.bilibili.com/697?'
      }
      },
    methods:{
      writeToMysql(){
        console.log("进入writeToMysql方法"+this.isWrite)
        if(this.isWrite===true){
          console.log("正在开启写入mysql数据通道"+this.isWrite)
            request.get("/danmuSource/write",{
              params:{
                flag:this.isWrite
              }
             }).then(res=> {
              console.log("完成开启写入mysql数据通道"+this.isWrite)
            })
        }else {
          console.log("正在关闭写入mysql数据通道"+this.isWrite)
          request.get("/danmuSource/write",{
            params:{
              flag:this.isWrite
            }
          }).then(res=> {
            console.log("完成关闭写入mysql数据通道"+this.isWrite)
          })
        }
      },
    stopDanmu(){
      if(flag===true){
        ws.close()
        flag=false;
        this.$message({
          showClose: true,
          message: '已关闭连接！',
          type: 'success'
        });
      }else {
        console.log("连接未打开")
      }
    },
    getDanmu(){
      var url= document.getElementById("homeurl").value;
      var id =''
      id=url.split("com/")[1].split("?")[0]
      GetBillRoomID(id)
      this.$message({
        showClose: true,
        message: '已连接成功！',
        type: 'success'
      });
    }
    },

  }
  var timer = null;
  var flag=false;
  var ws;

   function GetBillRoomID(id) {
     request.get("/danmuSource/getRoomID",{
      params:{
        id:id
      }
    }).then(res=> {
      console.log("获取到的roomID："+res.data)
       WebSocketTest(res.data)
    })
  }

  function WebSocketTest(roomid) {
  var url = document.getElementById("url").value;
  // var key = document.getElementById("key").value;
  console.log("roomid: " + roomid +
  "\nurl: " + url
  // +
  // "\nkey: " + key
  );
  var json = {
  "uid": 0,
  "roomid": parseInt(roomid), //注意roomid是数字
  "protover": 1,
  "platform": "web",
  "clientver": "1.4.0",
  // "key": key
}
  console.log(JSON.stringify(json));

  if (ws) //防止重复连接
  ws.close()
  // 打开一个 web socket
  ws = new WebSocket(url);
  flag=true;

  // WebSocket连接成功回调
  ws.onopen = function () {
  console.log("WebSocket 已连接上");

  //组合认证数据包 并发送
  ws.send(getCertification(JSON.stringify(json)).buffer);
  //心跳包的定时器
  timer = setInterval(function () { //定时器 注意声明timer变量
  var n1 = new ArrayBuffer(16)
  var i = new DataView(n1);
  i.setUint32(0, 0),  //封包总大小
  i.setUint16(4, 16), //头部长度
  i.setUint16(6, 1), //协议版本
  i.setUint32(8, 2),  // 操作码 2 心跳包
  i.setUint32(12, 1); //就1
  ws.send(i.buffer); //发送
}, 30000)   //30秒
};

  // WebSocket连接关闭回调
  ws.onclose = function () {
  console.log("连接已关闭");
  //要在连接关闭的时候停止 心跳包的 定时器
  if (timer != null)
  clearInterval(timer);
};
  //WebSocket接收数据回调
  ws.onmessage = function (evt) {
  var blob = evt.data;
  //对数据进行解码 decode方法
  decode(blob, function (packet) {
  //解码成功回调
  if (packet.op === 5) {
  //会同时有多个 数发过来 所以要循环
  for (let i = 0; i < packet.body.length; i++) {
  var element = packet.body[i];
  //做一下简单的打印
  console.log(element);//数据格式从打印中就可以分析出来啦
  //cmd = DANMU_MSG 是弹幕
  if (element.cmd === "DANMU_MSG"){
  console.log("uid: " + element.info[2][0]
  + " 用户: " + element.info[2][1]
  + " \n内容: " + element.info[1]
  + " \n时间:" + element.info[9].ts);

    var formats = "yyyy-MM-dd HH:mm:ss";
    var  newDate = new SimpleDateFormat(formats,Locale.CHINA).format(new Date(element.info[9].ts*1000));
    //Fri Dec 09 2022 20:16:02 GMT+0800
    var strings = newDate.toString().split(" ");
    var mm=strings[1];
    var em = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    switch (mm) {
      case em[0]:
        mm = 1;
        break;
      case em[1]:
        mm = 2;
        break;
      case em[2]:
        mm = 3;
        break;
      case em[3]:
        mm = 4;
        break;
      case em[4]:
        mm = 5;
        break;
      case em[5]:
        mm = 6;
        break;
      case em[6]:
        mm = 7;
        break;
      case em[7]:
        mm = 8;
        break;
      case em[8]:
        mm = 9;
        break;
      case em[9]:
        mm = 10;
        break;
      case em[10]:
        mm = 11;
        break;
      case em[11]:
        mm = 12;
        break;
    }
    var finalDate=strings[3]+'-'+mm+'-'+strings[2]+' '+strings[4]
    //itemsSend是另外一个页面用来获取所有弹幕信息的数组，使用unshift可以把后来的数据放在前面
    itemsSend.unshift({'flag':true,'state':"自动",'name': element.info[2][1],'neirong': element.info[1],'time':finalDate,'answer':'新品不打折哟'})
    //itemsSend.push({'flag':true,'state':"自动",'name': element.info[2][1],'neirong': element.info[1],'time':finalDate,'answer':'新品不打折哟'})

    //启动springboot，启动虚拟机上的kafka，可以进行获取弹幕发送到kafka并且写入到aituber下的danmu_bill数据库
    // request.get("/danmuSource/send",{
    //   params: {
    //     name: element.info[2][1],
    //     neirong: element.info[1],
    //     time: element.info[9].ts
    //   }}).then(res=>{
    //   console.log("发送到kafka")
    // })
}
  //cmd = INTERACT_WORD 有人进入直播了
  else if (element.cmd === "INTERACT_WORD") {
  console.log("进入直播: " + element.data.uname);
}
  //还有其他的
}
}
});
};
}
  // 文本解码器
  var textDecoder = new TextDecoder('utf-8');
  // 从buffer中读取int
  const readInt = function (buffer, start, len) {
  let result = 0
  for (let i = len - 1; i >= 0; i--) {
  result += Math.pow(256, len - i - 1) * buffer[start + i]
}
  return result
}
  /**
  * blob blob数据
  * call 回调 解析数据会通过回调返回数据
  */
  function decode(blob, call) {
  let reader = new FileReader();
  reader.onload = function (e) {
  let buffer = new Uint8Array(e.target.result)
  let result = {}
  result.packetLen = readInt(buffer, 0, 4)
  result.headerLen = readInt(buffer, 4, 2)
  result.ver = readInt(buffer, 6, 2)
  result.op = readInt(buffer, 8, 4)
  result.seq = readInt(buffer, 12, 4)
  if (result.op === 5) {
  result.body = []
  let offset = 0;
  while (offset < buffer.length) {
  let packetLen = readInt(buffer, offset + 0, 4)
  let headerLen = 16// readInt(buffer,offset + 4,4)
  let data = buffer.slice(offset + headerLen, offset + packetLen);

  let body = "{}"
  if (result.ver === 2) {
  //协议版本为 2 时  数据有进行压缩 通过pako.js 进行解压
  body = textDecoder.decode(pako.inflate(data));
} else {
  //协议版本为 0 时  数据没有进行压缩
  body = textDecoder.decode(data);
}
  if (body) {
  // 同一条消息中可能存在多条信息，用正则筛出来
  const group = body.split(/[\x00-\x1f]+/);
  group.forEach(item => {
  try {
  result.body.push(JSON.parse(item));
} catch (e) {
  // 忽略非JSON字符串，通常情况下为分隔符
}
});
}
  offset += packetLen;
}
}
  //回调
  call(result);
}
  reader.readAsArrayBuffer(blob);
}

  //组合认证数据包
  function getCertification(json) {
  var bytes = str2bytes(json);  //字符串转bytes
  var n1 = new ArrayBuffer(bytes.length + 16)
  var i = new DataView(n1);
  i.setUint32(0, bytes.length + 16), //封包总大小
  i.setUint16(4, 16), //头部长度
  i.setUint16(6, 1), //协议版本
  i.setUint32(8, 7),  //操作码 7表示认证并加入房间
  i.setUint32(12, 1); //就1
  for (var r = 0; r < bytes.length; r++) {
  i.setUint8(16 + r, bytes[r]); //把要认证的数据添加进去
}
  return i; //返回
}

  //字符串转bytes //这个方法是从网上找的QAQ
  function str2bytes(str) {
  const bytes = []
  let c
  const len = str.length
  for (let i = 0; i < len; i++) {
  c = str.charCodeAt(i)
  if (c >= 0x010000 && c <= 0x10FFFF) {
  bytes.push(((c >> 18) & 0x07) | 0xF0)
  bytes.push(((c >> 12) & 0x3F) | 0x80)
  bytes.push(((c >> 6) & 0x3F) | 0x80)
  bytes.push((c & 0x3F) | 0x80)
} else if (c >= 0x000800 && c <= 0x00FFFF) {
  bytes.push(((c >> 12) & 0x0F) | 0xE0)
  bytes.push(((c >> 6) & 0x3F) | 0x80)
  bytes.push((c & 0x3F) | 0x80)
} else if (c >= 0x000080 && c <= 0x0007FF) {
  bytes.push(((c >> 6) & 0x1F) | 0xC0)
  bytes.push((c & 0x3F) | 0x80)
} else {
  bytes.push(c & 0xFF)
}
}
  return bytes
}

</script>

<style scoped>

</style>