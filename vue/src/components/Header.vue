<template>
  <div style="position: relative; margin: 0px auto; width: auto;height: 60px;justify-content: center;align-content: center;display: block;box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)" >
    <div class="button-container" style="margin: auto;width: 380px;display: block" >
      <el-button type="primary" @click="goModel" style="width: 100px; margin-right: 20px; height: 40px; background-color:white;margin-top: 10px; font-size: 18px; color: #181818; line-height: 40px" round>模型库</el-button>
      <el-button type="primary" @click="goMocap" style="width: 100px; margin-right: 20px;  height: 40px; background-color:white; margin-top: 10px; font-size: 18px; color: #181818; line-height: 40px" round>动作捕捉</el-button>
      <el-button type="primary" @click="goHome" style="width: 100px;  height: 40px; background-color:white; margin-top: 10px; font-size: 18px; color: #181818; line-height: 40px" round>设置</el-button>
    </div>
    <el-col :span="12" style="position: absolute;right: 20px;top: 5px">
      <div class="demo-basic--circle">
        <div class="block" onclick=""><el-avatar :size="50" :src="circleUrl"></el-avatar></div>
      </div>
    </el-col>
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
    goHome(){
      this.$router.push("/home")
    },
    goModel(){
      this.$router.push("/model")
    },
    goMocap(){
      this.$router.push("/model")
    },
  }
}

</script>


<style scoped>

</style>
