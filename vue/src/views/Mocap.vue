<template>
<!--侧边栏-->
<!--  <div>-->
<!--    <MocapSidebar/>-->
<!--  </div>-->
<!--  主体-->
  <div class="body" style="display: flex" >
    <div class="window1" style="display: flex">
      <div class="window1-left" style="width: 36%">
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
      <div style="margin: 20px">
        ⭐模型：
      <el-select id="1" v-model="value" placeholder="选择模型" style="width: 100px;margin:10px 0px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        >
        </el-option>
      </el-select>
      </div>
        <!--    左-->
        <div style="display: flex;margin: 20px">
          <div style="font-size: 16px">
            <div style="font-size: 20px">⭐声音:</div>
            <div style="margin-top: 20px">1、文字转语音：
              <el-input
                  style="margin: 10px"
                  placeholder="请输入内容"
                  type="textarea"
                  :autosize="{ minRows: 4, maxRows: 8}"
                  v-model="inputWords"
                  >
              </el-input>
              <el-button style="margin-left: 115px" @click="ChangeToVoice">转化</el-button>
            </div>
            <div>2、导入音频：
              <el-button style="margin: 10px">导入</el-button>
            </div>
            <div>3、实时传入：
              <el-select style="margin: 10px"></el-select>
            </div>
          </div>

        </div>

      </div>
      <!--    右    >-->
      <div class="window1-right" style="width: 76%">
        <div class="window-model" style="width: 300px;height: 270px"></div>
        <danmuArea_bill
            style="font-size: 16px;
            background-color: rgba(236,223,253,0.45);
            color: #635773"
            :homeurl="homeUrl"
        />
      </div>
    </div>


    <div class="window2" >
  <div style="margin: 1px">
    <div style="margin: 0px 0px 0px 10px" class="camera_outer">
        <embed :src="src" width=650px height=435px />

    </div>


  <div style="margin-top: 0px;margin-left: 20px">
    <el-button  v-if="os" @click="openCamera" size="large" style="font-size: 19px">开始捕捉</el-button>
    <el-button  v-else @click="stopCamera"  size="large" style="font-size: 19px">停止捕捉</el-button>
    <el-button :disabled="os"  v-if="record" @click="beginRecord" size="large" style="font-size: 19px">录制</el-button>
    <el-button  v-else @click="stopRecord" size="large" style="font-size: 19px">停止</el-button>
  </div>
    <div>
      <el-button style="margin-left: 550px;margin-top: 60px" size="large" @click="backArea">返回列表</el-button>
    </div>
    </div>
    </div>
  </div>


</template>


<script>
import MocapSidebar from "../components/sidebar/MocapSidebar.vue";
import DanmuArea_bill from "../components/DanmuArea_bill.vue";
import SendDanmuBill from "../components/SendDanmuBill.vue";
import {useRoute, useRouter} from "vue-router/dist/vue-router";

export default {
  name: "mocap",
  components:{
    SendDanmuBill,
    MocapSidebar,
    DanmuArea_bill,
  },
  data() {
    return {
      os: true,//控制摄像头开关
      record:true,
      videoWidth: 500,
      videoHeight: 400,
      src:'',
      inputWords:'',
      homeName:'',
      pace:'',
      createTime:'',
      homeUrl:''
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
        this.os = false;//切换成关闭摄像头
        this.src="./src/views/mocap.html"
        this.record=true;

      });
    },
    //关闭摄像头
    stopCamera() {
        this.os = true;//切换成打开摄像头
        this.record=true;
        this.src='-'
    },

  }
};
</script>

<style lang="scss" scoped>
.window1{
  margin: 20px 0px 0px 20px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 815px;
  height: 600px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
//人物视角窗口window2
.window2{
  margin: 20px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 660px;
  height: 600px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
.camera_outer{
    -moz-transform:scaleX(-1);
    -webkit-transform:scaleX(-1);
    -o-transform:scaleX(-1);
    transform:scaleX(-1);
}
</style>
