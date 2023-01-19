<template>
  <!--  侧边栏-->
  <div >
<div class="back">
  <div style="width: 96%;margin-left: 2%;
  height: 96%;margin-top: 0.5%;border: solid;border-color: #78f1fc;border-width: 6px">
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

  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  margin-left: 1%;
  width: 98%;
  height: 650px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
</style>