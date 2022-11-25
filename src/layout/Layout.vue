<template>
  <div>
    <!--    头部-->
    <Header/>

    <!--    主体-->
    <div style="display: flex;">
      <!--      内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>
<script>

import Header from "../components/Header.vue";
import HomeView from "../views/HomeView.vue";

export default {
  name:"Layout",
  components:{
    Header,
    HomeView

  }
}
</script>

<style scoped>

</style>