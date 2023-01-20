


<template>
  <div class="header-container">
    <div class="left" >

      <el-col :span="12">
        <div class="demo-basic--circle" style="margin-top: 5px;margin-left: 10px">
          <div class="block"><el-avatar :size="50" :src="circleUrl"></el-avatar></div>
        </div>
      </el-col>

    </div>
    <div class="button-container" >
      <div class="inner-container">
        <el-button class="model" type="text" @click="goModel"  >模型</el-button>
        <!--      <el-button type="primary" @click="goMocap" style="width: 100px; height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>捕捉</el-button>-->
        <el-button class="area" type="text" @click="goArea">房间</el-button>
        <el-button class="shop" type="text" @click="goShop">商店管理</el-button>
        <el-button class="settings" type="text" @click="goAllSet" >设置</el-button>
        <!--      <el-button type="primary" @click="goNoMocap" style="width: 100px;  height: 40px; background-color:#fdea78; margin-top: 10px; font-size: 18px; color: #c175df; line-height: 40px" round>无捕</el-button>-->
        <el-button class="dataView" type="text" @click="goDatasView"  >数据面板</el-button>

      </div>

    </div>
    <div class="right">
      <el-button class="myButton"  style="margin-left: 80%;font-size: 12px;height: 25px;width: 60px;border-radius: 10px;margin-top: 20px" @click="returnLogin"  round>退出登录</el-button>
    </div>

  </div>

</template>

<script>
import ArrowDown from "@element-plus/icons/lib/ArrowDown";
import './css/header.css';
export default {
  name: "Header",
  components: { ArrowDown,
  },
  data(){
    return {
      nowUser:"now",
      circleUrl: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.nga.178.com%2Fattachments%2Fmon_202212%2F25%2F-9lddQ2r-eb62KtT1kSdi-ek.jpg&refer=http%3A%2F%2Fimg.nga.178.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1676689952&t=86804aabe636b700265d26ea5aff5411",
      sizeList: ["large", "medium", "small"],
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
    goShop(){
      this.$router.push("/shop")
    },
    goDatasView(){
      this.$router.push("/datasView")
    },
  }
}
</script>


<style scoped>
.myButton:hover{
  background: #9aef8d;
  color: #fff;
  border-radius: 20px;
  border-color:  #9aef8d;
  box-shadow: 0 0 5px #b0f6ab,
  0 0 25px #b9fabd,
  0 0 50px #c7fdcf,
  0 0 100px #baffbe;
}
</style>
