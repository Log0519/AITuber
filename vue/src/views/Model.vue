<template>
<!--  侧边栏-->
  <div >
    <ModelSidebar/>
<!--    主体-->
  </div>
      <div class="page-background" style="width:100%;margin-top: 20px">
        <div class="user-models-header">

          <p>用户模型</p>
          <el-button class="addModel" round>导入模型</el-button>
        </div>
        <ur class="models">
          <li class="model">
            <div class="Model-name"></div>
            <div class="Model-type"></div>
          </li>
        </ur>

        <el-container class="in-model">
          <p>内建模型</p>
          <ur class="models">
            <li class="model">
              <div class="Model-name"></div>
              <div class="Model-type"></div>
            </li>
          </ur>
        </el-container>
      </div>
</template>


<script>
import './css/Model.css'
import ModelSidebar from "../components/ModelSidebar.vue";


export default {
  name: "Model",
  components:{
    ModelSidebar,

  }
}
</script>

<style scoped>

</style>