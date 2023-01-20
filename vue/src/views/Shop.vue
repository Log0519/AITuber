<template>
  <!--  侧边栏-->
  <div >
<div class="back">
    <div style="text-align: center;color: #f2f2f2;font-size: 24px;">商店管理
    </div>
    <div style="margin-left: 1%">
      <el-button style="margin-left: 1%;background-color: #fce889;color: #c073e4" @click="flag=0" >商品</el-button>
      <el-button style="margin-left: 1%;background-color: #fce889;color: #c073e4"  @click="flag=1">设置</el-button>
    </div>

    <div v-if="flag===0">
      <div style="margin-left: 1%;margin-top: 10px">
        商品：
        <br>

      </div>
    </div>

    <div v-if="flag===1">
      <div style="margin-left: 1%;margin-top: 10px">
       设置
      </div>
    </div>


  </div>
</div>





</template>

<script>
export default {
  name: "DatasView",
data(){
    return{
      flag:0,
    }
}
}
</script>

<style scoped>
.back{

  background-color: #ffffff;
  margin-left: 5%;
  margin-right: 5%;
  width: 90%;
  height: 100vh;
}
</style>