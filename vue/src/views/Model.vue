<template>

    <div class="model-container" style="background-color: #ffffff;
  margin-left: 5%;
  margin-right: 5%;
  width: 90%;
  height: 650px;">
      <!--  侧边栏-->
<!--      <ModelSidebar/>-->
      <!--    主体-->
      <div class="page-background" style="margin-top: 20px;margin-left: 5%;margin-right: 5%">
        <div class="localmodels">
          <div class="user-models-header">
            <p>本地模型</p>
            <el-button class="addModel" type="text">导入模型</el-button>
          </div>

          <ur class="models">
            <li class="model-list">
              <div class="Model-name"></div>
              <div class="Model-type"></div>
            </li>
          </ur>
        </div>

        <div class="insertmodels">
          <el-container class="in-model">
            <p>下载模型</p>
            <ur class="models">
              <li class="model-list">
                <div class="Model-name"></div>
                <div class="Model-type"></div>
              </li>
            </ur>
          </el-container>
        </div>

      </div>


    </div>


</template>


<script>
import './css/Model.css'
import ModelSidebar from "../components/sidebar/ModelSidebar.vue";
export default {
  name: "Model",
  components:{
    ModelSidebar,
  }
}
</script>

<style scoped>
</style>