<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          background-color="RGB(181,255,255，0.1)"
          active-text-color="RGB(111,255,215)"
          default-active="/area"
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
  },
  data() {
    return {

    }
  }
}
</script>

<style scoped>

</style>