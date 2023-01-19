<template>
  <!--  侧边栏-->
  <div >
<div class="back">

    <div style="text-align: center;color: #f2f2f2;font-size: 24px;">数据中心
    </div>
    <div style="margin-left: 1%">
      <el-button style="margin-left: 1%;background-color: #1f9de7;color: #f2f2f2" @click="flag=0" >房间数据</el-button>
      <el-button style="margin-left: 1%;background-color: #1f9de7;color: #f2f2f2"  @click="flag=1">商品数据</el-button>
      <el-button style="margin-left: 1%;background-color: #1f9de7;color: #f2f2f2"  @click="flag=2">使用数据</el-button>
      <el-button style="margin-left: 1%;background-color: #1f9de7;color: #f2f2f2"  @click="flag=3">后台管理</el-button>
    </div>

    <div v-if="flag===0">
      <div style="margin-left: 1%;margin-top: 10px">
        房间列表：
        <br>
        共10个房间
      </div>
    </div>

    <div v-if="flag===1">
      <div style="margin-left: 1%;margin-top: 10px">
        商品数据：
      </div>
    </div>

    <div v-if="flag===2">
      <div style="margin-left: 1%;margin-top: 10px">
        使用数据：

      </div>
    </div>

    <div v-if="flag===3">
      <div style="margin-left: 1%;margin-top: 10px">
        后台管理：
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