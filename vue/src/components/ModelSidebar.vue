<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          background-color="RGB(187,204,233,0.2)"
          active-text-color="RGB(21,182,123,1)"
          default-active="1"
          class="el-menu-vertical-demo"
          style="min-width: 150px"
          @open="handleOpen"
          @close="handleClose">
        <el-submenu>
            <el-menu-item index="1" style="font-size: 24px">1</el-menu-item>
          <el-menu-item index="2" style="font-size: 24px">2</el-menu-item>
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