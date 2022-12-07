<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          background-color="RGB(181,255,255，0.1)"
          active-text-color=#ffe68b
          text-color=#ffabbb
          default-active="/otherArea"
          router="router"
          style="min-width: 150px"
  >
        <el-submenu>
          <el-menu-item index="/area" style="font-size: 24px">商家房间</el-menu-item>
          <el-menu-item index="/otherArea" style="font-size: 24px" >直播平台</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>
  </el-row>
</template>
<script>

export default {
  name: "AreaSidebar2",
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