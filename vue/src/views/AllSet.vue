<template>
<!--侧边栏-->
  <div>
    <AllSetSidebar/>
  </div>
<!--  主体-->
  <div class="back" >
<!--    主要内容-->
  <div  style="margin-top: 10px;margin-left: 20px">
  <div style="text-align: center;font-size: 30px">设置</div>

  </div>



  </div>

</template>
<script>
import AllSetSidebar from "../components/sidebar/AllSetSidebar.vue";
import {Headset} from "@element-plus/icons";

export default {
  name: "allSet",
  components:{
    Headset,
    AllSetSidebar
  },
  data() {
    return {

    };
  },
  created() {
  },
  mounted() {

  },
  methods: {


  }
};
</script>

<style lang="scss" scoped>
.back{
  margin: 20px 0px 0px 20px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 1340px;
  height: 600px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
</style>
