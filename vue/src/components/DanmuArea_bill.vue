<!--<li v-for='item in items' >{{'('+item[0].state+')'}}  {{'---"'+item[1].name+'"---'}} {{item[2].time}}</li>-->
<template>
  <div class="message-page" style="display:flex;">
    <div  class="msg-tit">
      <SendDanmuBill ref="send"/>
      <div class="msg-top">
        <div class="tit-word">
          <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px">历史消息</el-button>
        </div>
        <div class="tit-word" style="margin-left: 30px;">
          <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px">实时告警</el-button>
        </div>
        <div class="tit-word" style="margin-left: 30px;">
          <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px">频繁问题</el-button>
        </div>
        <div class="tit-word" style="margin-left: 30px;">
          <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px">活跃用户</el-button>
        </div>
        <el-button style="margin-left: 75px;font-size: 14px;background-color: rgba(254,249,215,0)" text @click="onDialog()">管理 >></el-button>
      </div>
      <div class="msg-line"></div>
      <div class="marquee-wrap">
        <ul class="marquee-list" :class="{'animate-up': animateUp}" @mouseenter="stopFn" @mouseleave="startFn">
          <li   v-for="(item, index) in items" :key="index">
              <p v-if="item.flag" :style="fontStyle" class="p-word">
                {{'('+item.state+') '}} {{item.time}}  {{' — '+item.name+' ::'}} {{' '+item.neirong}}
                <el-button @click="openChangeWindow(item.neirong,item.answer)" style="max-height: 10px;max-width: 30px;background-color: rgba(254,249,215,0)" text>详情</el-button>
              </p>
            <p v-else :style="fontStyle2" class="p-word">
              {{'('+item.state+') '}} {{item.time}}  {{' — '+item.name+' ::'}} {{' '+item.neirong}}
              <el-button @click="openChangeWindow(item.neirong,item.answer)" style="max-height: 10px;max-width: 30px;color: #e33d60;background-color: rgba(254,249,215,0)" text> 详情</el-button>
            </p>
          </li>
        </ul>
      </div>
    </div>

    <MocapDialog
        style="z-index: 1"
        title="回答设置"
        :width="720"
        :height="480"
        :content="content"
        :footer="true"
        cancelText="取消"
        okText="确认"
        switchFullscreen
        @close="onClose"
        @cancel="onCancel"
        @ok="onConfirm"
        v-show="showDialog"
    />
  </div>


</template>

<script>
import MocapDialog from "./dialog/MocapDialog.vue"
import request from "../utils/request";
import SendDanmuBill from "./SendDanmuBill.vue";

export default {
  name: 'MessagePage',
  components:{
    SendDanmuBill,
    MocapDialog
  },
  data(){
    return{
      //dialog
      showDialog: false,
      content: '',
      dialogVisible: true,
      //dialog
      formLabelWidth: '120px',
      items:[],
      animateUp: false,
      timer: null
    }

  },
  computed: {
    fontStyle(){
      const _site = {
        'color':'#635879',
      }
      return _site
    },
    fontStyle2(){
      const _site = {
        'color':'#dc2958',
      }
      return _site
    },
  },
  created() {
    this.timer = setInterval(this.scrollAnimate, 1300);

  },
  mounted() {
    if(this.$refs.send.itemsSend){
      this.items=this.$refs.send.itemsSend

    }
  },
  methods:{
    getnew(){

    },
    getHis(){
      request.get("/danmuSource/get").then(res=>{
        console.log(res.data)
        console.log(res.data[0])
        this.items=[]
        for (let i = 0; i < res.data.length; i++) {
          var words=res.data[i].split(",")
          this.items.push({'flag':true,'state':"自动",'name': words[0].split("[")[1],'neirong':words[1],'time': words[2].split("]")[0],'answer':'新品不打折哟'})
        }res.data.length()

      })
    },
    //对话框方法
    onDialog () { // 调用Dialog弹出对话框
      this.showDialog = true
    },
    onClose () { // 关闭dialog
      this.showDialog = false
    },
    onCancel () { // “取消”按钮回调
      this.showDialog = false
    },
    onConfirm () { // “确定”按钮回调
      this.showDialog = false
    }
    ,
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    openChangeWindow(name,answer){
      this.$prompt('问题：'+name, '详情', {
        inputValue:answer,
        confirmButtonText: '确认修改',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '修改成功: '
        });
      });
    },
  //   scrollAnimate() {
  //     this.animateUp = false
  //     setTimeout(() => {
  //       this.items.push(this.items[0])
  //       this.items.shift()
  //       this.animateUp = false
  //     }, 190)
  //   },
  //   stopFn(){
  //     clearInterval(this.timer)
  //   },
  //   startFn(){
  //     this.timer = setInterval(this.scrollAnimate, 1300);
  //   }
},
  destroyed() {
    clearInterval(this.timer)
  }
}

</script>

<style scoped lang="less">
.message-page{
  border: 1px solid transparent;
  margin-top: 14px;
  width: 595px;
  height: 310px;
  background: #FFFFFF;
  border-radius: 4px;
  /**/
  .msg-tit{
    padding: 6px 5px 5px 10px;
    .msg-top{
      display: flex;
    }
    .msg-line{
      width: 580px;
      height: 1px;
      background: #EFEFEF;
      margin: 8px 0;

    }
    .marquee-wrap  {
      /*width: 80%;*/
      overflow: auto;
      display:flex;
      flex-wrap: wrap;
      height:170px;
      width: 580px;
      margin: 0 auto;
      .marquee-list {
        /*border: 1px solid red;*/
        li {

          .p-tit{
            font-size: 12px;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            color: #222222;
            height: 22px;
            line-height: 22px;
          }
          .p-word{
            margin-top: 8px;
            font-size: 14px;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            height: 16px;
            line-height: 18px;
            width: 767px;
            white-space: nowrap;
          }

        }
      }
      .animate-up {
        transition: all 0.5s ease-in-out;
        transform: translateY(-58px);
      }
    }
  }
}

</style>