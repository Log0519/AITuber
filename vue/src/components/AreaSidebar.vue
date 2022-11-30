<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          background-color="RGB(187,204,233,0.2)"
          active-text-color="RGB(21,182,123,1)"
          default-active="2"
          router="router"
          style="min-width: 150px"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose">
        <el-submenu>
          <el-menu-item index="/area" style="font-size: 24px">商家房间</el-menu-item>
          <el-menu-item index="/area2" style="font-size: 24px">直播平台</el-menu-item>
        </el-submenu>

      </el-menu>
    </el-col>
  </el-row>

</template>

<script>

export default {
  name: "AreaSidebar",
  components:{
  }
}
</script>

<style scoped>

</style>