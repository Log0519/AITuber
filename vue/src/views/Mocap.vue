<template>
<!--侧边栏-->
  <div>
    <MocapSidebar/>
  </div>
<!--  主体-->
  <div class="body" style="display: flex" >
    <div class="window1" >
      <el-select id="1" v-model="value" placeholder="选择模型" style="width: 100px;margin:10px 30px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <div class="window2" >
  <div style="margin: 1px">
    <div style="margin: 0px 0px 0px 10px" class="camera_outer">
        <embed :src="src" width=650px height=500px />
    </div>

  <div style="margin-top: 10px;margin-left: 20px">
    <el-button  v-if="os" @click="openCamera" size="large" style="font-size: 19px">开始面捕</el-button>
    <el-button  v-else @click="stopCamera"  size="large" style="font-size: 19px">停止面捕</el-button>
    <el-button :disabled="os"  v-if="record" @click="beginRecord" size="large" style="font-size: 19px">录制</el-button>
    <el-button  v-else @click="stopRecord" size="large" style="font-size: 19px">停止</el-button>
  </div>
    </div>
    </div>
  </div>
</template>
<script>
import MocapSidebar from "../components/MocapSidebar.vue";

export default {
  name: "mocap",
  components:{
    MocapSidebar
  },
  data() {
    return {
      os: true,//控制摄像头开关
      record:true,
      videoWidth: 500,
      videoHeight: 400,
      src:''
    };
  },
  created() {
  },
  mounted() {
  },
  methods: {
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
        this.src=''

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
  width: 660px;
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
