<template>


      <div class="page-background">


        <div class="user-models-header">
          <p>用户模型</p>
          <el-button class="addModel" round>导入模型</el-button>
        </div>
        <ur class="models">
          <li class="model">
            <div class="Model-name">Ashtra</div>
            <div class="Model-type">VRM</div>
          </li>
        </ur>

        <el-container class="in-model">
          <p>内建模型</p>
          <ur class="models">
            <li class="model">
              <div class="Model-name">Ashtra</div>
              <div class="Model-type">VRM</div>
            </li>
          </ur>
        </el-container>

      </div>





</template>


<script>
import './css/Model.css'


export default {
  name: "Model"
}
</script>

<style scoped>

</style>