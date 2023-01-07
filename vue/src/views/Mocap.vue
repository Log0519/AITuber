<template>
<!--侧边栏-->
<!--  <div>-->
<!--    <MocapSidebar/>-->
<!--  </div>-->
<!--  主体-->
  <div class="body" style="display: flex" >
    <div class="window1" style="display: flex">
      <div class="window1-left" style="width: 100%">
        <div style="margin: 10px;display: flex;font-size: 18px">房间名称：
          <div style="color: #b44aef">
          {{homeName}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 14px">平台：
          <div style="color: #b44aef">
            {{pace}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 13px">创建时间：
          <div style="color: #b44aef">
            {{createTime}}
          </div>
        </div>
      <div style="margin: 10px">

        ⭐模型：
        <div class="list" style="background-color: #f5eac0;margin-top:10px;width: 560px;height: 170px;
        margin-left: 20px;border-radius: 20px;overflow: auto">
        <div class="his-body" style="margin-left: 10px;color: #2cb43d;display: flex; flex-wrap: wrap">
        <div v-for="(d,index) in phoCounter" :key="index">
          <!--                photo组件-->
          <PhoItem
              :src="d.src"
              @click="modelCode=d.code;"
              :name="d.name"
          >
          </PhoItem>

        </div>
        </div>
        </div>
      </div>
        <!--    左-->
<!--        <div style="display: flex;margin: 20px">-->
<!--          <div style="font-size: 16px">-->
<!--            <div style="font-size: 20px">⭐声音:</div>-->
<!--            <div style="margin-top: 20px">1、文字转语音：-->
<!--              <el-input-->
<!--                  style="margin: 10px"-->
<!--                  placeholder="请输入内容"-->
<!--                  type="textarea"-->
<!--                  :autosize="{ minRows: 4, maxRows: 8}"-->
<!--                  v-model="inputWords"-->
<!--                  >-->
<!--              </el-input>-->
<!--              <el-button style="margin-left: 115px" @click="ChangeToVoice">转化</el-button>-->
<!--            </div>-->
<!--            <div>2、导入音频：-->
<!--              <el-button style="margin: 10px">导入</el-button>-->
<!--            </div>-->
<!--            <div>3、实时传入：-->
<!--              <el-select style="margin: 10px"></el-select>-->
<!--            </div>-->
<!--          </div>-->

<!--        </div>-->

          <danmuArea_bill
              style="font-size: 16px;margin-left: 10px;
            background-color: rgba(236,223,253,0.45);
            color: #635773"
              :homeurl="homeUrl"
          />
      </div>
      <!--    右    >-->

    </div>


    <div class="window2" >
  <div style="margin: 1px">

<!--    主播房间的时候-->
    <div v-if="src===''" style="margin: 0px 0px 0px 10px" class="camera_outer">
      <div  style="width:850px;height:500px;background-color: #fff3f3;margin-bottom: 5px">
        <div style="background-color: #ffc5ef;color: #d7629c;height: 30px;text-align: center">
        主播房间
        </div>
        </div>
    </div>
<!--    播放视频的时候-->
    <div v-else style="margin: 0px 0px 0px 10px" class="camera_outer">

        <embed :src="src" width=850px height=500px />
    </div>


  <div style="margin-top: 0px;margin-left: 20px">
    <el-button  v-if="os" @click="goMv" size="large" style="font-size: 19px">播放视频</el-button>
    <el-button  v-else @click="stopMv"  size="large" style="font-size: 19px">关闭视频</el-button>
    <el-button  v-if="!isCatch" @click="openCamera" size="large" style="font-size: 19px">开始捕捉</el-button>
    <el-button  v-else @click="stopCamera"  size="large" style="font-size: 19px">停止捕捉</el-button>
  </div>
    <div>
      <el-button style="margin-left: 750px;margin-top: 30px" size="large" @click="backArea">返回列表</el-button>
    </div>
    </div>
    </div>
  </div>


  <MocapWindowDialog
      :model-code="modelCode"
      style="z-index: 1;margin-left: 947px;margin-top: 292px"
      :width="1020"
      :height="480"
      :content="content"
      :footer="false"
      cancelText="取消"
      okText="确认"
      switchFullscreen
      @close="onClose"
      @cancel="onCancel"
      @ok="onConfirm"
      v-show="showDialog"
  />


</template>


<script>
import MocapSidebar from "../components/sidebar/MocapSidebar.vue";
import DanmuArea_bill from "../components/DanmuArea_bill.vue";
import SendDanmuBill from "../components/SendDanmuBill.vue";
import {useRoute, useRouter} from "vue-router/dist/vue-router";
import MocapWindowDialog from "../components/dialog/MocapWindowDialog.vue";
import PhoItem from "../components/PhoItem.vue";



export default {
  name: "mocap",
  components:{
    SendDanmuBill,
    MocapSidebar,
    DanmuArea_bill,
    MocapWindowDialog,
    PhoItem
  },
  data() {
    return {
      modelCode:'3',
      isCatch:true,
      os: true,//控制摄像头开关
      record:true,
      videoWidth: 500,
      videoHeight: 400,
      src:'',
      inputWords:'',
      homeName:'',
      pace:'',
      createTime:'',
      homeUrl:'',
      showDialog:true,
      model:'hiyori',
      phoCounter:[{"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":'/public/pho/redgirl.png',"code":'3',"name":'redgirl'},
        {"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
      ],
    };
  },
  created() {
    const router = useRouter()
    console.log(router.currentRoute.value.query)
    this.homeName=router.currentRoute.value.query.homeName
    this.createTime=router.currentRoute.value.query.createTime
    this.pace=router.currentRoute.value.query.pace
    this.homeUrl=router.currentRoute.value.query.homeUrl
  },
  mounted() {

  },
  methods: {
    stopMv(){
      this.src=""
      this.os=true
    },
    goMv(){
     this.src="./public/vedio/back.mp4"
      this.os=false
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
    },
    backArea(){
      this.$router.push("/area")
    },
    ChangeToVoice(){
      console.log(this.inputWords)
    },
    beginRecord(){
      this.record=false;
      },
    stopRecord(){
      this.record=true;
    },

    // 调用摄像头权限
    openCamera() {
      //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
      this.$nextTick(() => {
        const _this = this;
        this.isCatch=true
        this.record=true;
      });
      this.showDialog=true


    },
    //关闭摄像头
    stopCamera() {
        this.showDialog=false
        this.isCatch=false
        this.record=true;
    },

  }

};





</script>

<style lang="scss" scoped>
.window1{
  margin: 10px 0px 0px 10px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 615px;
  height: 640px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
//人物视角窗口window2
.window2{
  margin: 10px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 890px;
  height: 640px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
.camera_outer{
    /*-moz-transform:scaleX(-1);
    -webkit-transform:scaleX(-1);
    -o-transform:scaleX(-1);
    transform:scaleX(-1);*/
}
</style>
