<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          background-color="RGB(181,255,255，0.1)"
          active-text-color="RGB(111,255,215)"
          default-active="1"
          class="el-menu-vertical-demo"
          style="min-width: 150px"
          @open="handleOpen"
          @close="handleClose">
        <el-submenu>
            <el-menu-item index="1" style="font-size: 24px">本地模型</el-menu-item>
          <el-menu-item index="2" style="font-size: 24px">下载模型</el-menu-item>
        </el-submenu>

      </el-menu>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "ModelSidebar"
}
</script>

<style scoped>

</style>