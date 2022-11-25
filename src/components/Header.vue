<template>
         <el-button type="primary" @click="goHome">主页</el-button>
         <el-button type="primary" @click="goHome">主页</el-button>
         <el-button type="primary" @click="goHome">主页</el-button>
         <el-button type="primary" @click="goHome">主页</el-button>


</template>

<script>
import ArrowDown from "@element-plus/icons/lib/ArrowDown";
export default {

  name: "Header",
  components: { ArrowDown,
  },
  data(){
    return {
      nowUser:"now"
    }
  },
  methods:{
    goHome(){
      this.$router.push("/home")
    },
  }
}

</script>


<style scoped>

</style>
