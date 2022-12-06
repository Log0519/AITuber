<template>
  <div style="position: relative; margin: 0px auto; width: auto;height: 60px;justify-content: center;align-content: center;display: block;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)" >
    <div class="button-container" style="margin: auto;width: 100%;display: block;margin-left: 450px" >
      <el-button type="primary" @click="goModel" style="width: 100px; height: 40px; background-color:#fdea78;margin-top: 10px; font-size: 18px; color: #c26cbd; line-height: 40px" round>模型</el-button>
      <el-button type="primary" @click="goMocap" style="width: 100px; height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>捕捉</el-button>
      <el-button type="primary" @click="goArea" style="width: 100px;  height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>房间</el-button>
      <el-button type="primary" @click="goAllSet" style="width: 100px;  height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>设置</el-button>
      <el-button type="primary" @click="goNoMocap" style="width: 100px;  height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>无捕</el-button>
      <el-button type="primary" @click="goDatasView" style="width: 100px;  height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>数据面板</el-button>
      <el-button type="primary" @click="returnLogin" style="width: 80px; margin-left: 280px; height: 30px;background-color:#fdea78; margin-top: 10px; font-size: 15px; color: #bf74de; line-height: 40px" round>退出登录</el-button>
    </div>

  </div>

</template>

<script>
import ArrowDown from "@element-plus/icons/lib/ArrowDown";
export default {

  name: "Header",
  components: { ArrowDown,
  },
  data(){
    return {
      nowUser:"now"
    }
  },

  methods:{
    goAllSet(){
      this.$router.push("/allSet")
    },
    goModel(){
      this.$router.push("/model")
    },
    goMocap(){
      this.$router.push("/mocap")
    },
    goNoMocap(){
      this.$router.push("/mocapNo")
    },
    goArea(){
      this.$router.push("/area")
    },
    returnLogin(){
      this.$router.push("/login")
    },
    goDatasView(){
      this.$router.push("/datasView")
    },
  }
}

</script>


<style scoped>

</style>
