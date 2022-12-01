<template>
  <el-row class="tac">
    <el-col :span="30">
      <el-menu
          router="router"
          background-color="RGB(187,204,233,0.2)"
          active-text-color="RGB(21,182,123,1)"
          class="el-menu-vertical-demo"
          style="min-width: 150px"
          @open="handleOpen"
          @close="handleClose">
          <el-menu-item index="/mocap" style="font-size: 24px">Mocap</el-menu-item>
          <el-menu-item index="/mocap1" style="font-size: 24px">Voice</el-menu-item>
<!--          <el-menu-item index="3" style="font-size: 18px">导航一</el-menu-item>-->
<!--          <el-menu-item index="4" style="font-size: 18px">导航一</el-menu-item>-->
      </el-menu>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "MocapSidebar",
  data(){
    return{
      active:'/mocap'
    }
  },
  methods:{

  }
}
</script>

<style scoped>

</style>