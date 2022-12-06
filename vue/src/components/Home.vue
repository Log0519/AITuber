<template>
<div class="homeBack">
  <div class="information" >
    <div style="font-size: 20px;text-align: center">
      房间
    </div>
  <div style="font-size: 17px;display: flex">名称：
  <div class="homeName" style="font-size: 17px;color: #c657ff">
    {{ homeName }}
  </div>
  </div>
    <div class="state" style="display: flex;font-size: 17px">
      状态：
    <div style="font-size: 17px;color: #2dd91a">进行中</div>
      <div style="margin-left: 163px">
        已自动回复:
      </div>
    </div>
    <div class="moddle" style="display: flex;font-size: 17px">
      模式：
    <div style="font-size: 17px;color: #13da2e">自动</div>
      <div style="margin-left: 180px">
        当前告警:
      </div>
    </div>
    <div style="display: flex">
    <el-button type="danger" style="font-size:5px;margin-left: 270px" @click="deleteHome()">删除房间</el-button>
  <el-button style="background-color: rgba(238,236,255,0);font-size: 16px" text>进入房间>></el-button>
    </div>
    </div>
</div>
</template>

<script>
export default {
  name: "home",
  props: {
    homeName: { // 标题
      type: String,
      default: '默认'
    },
  },
  methods:{
    deleteHome(){
      this.$emit('delete')
    }
  },
}
</script>

<style scoped>
.homeBack{
  margin: 20px 0px 0px 20px;
  background-color: rgb(248, 240, 148);
  border-radius: 20px;
  border: 3px solid #d3a6ff;
  width: 500px;
  height: 150px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
.information{
  margin:10px 10px 10px 10px;
}
</style>