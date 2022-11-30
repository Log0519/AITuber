<template>
<!--侧边栏-->
  <div>
    <AreaSidebar/>
  </div>
<!--  主体-->
  <div class="back" >
<!--    主要内容-->
  <div  style="margin-top: 10px;margin-left: 20px">
  <div style="text-align: center;font-size: 30px">直播房间列表</div>
    <!--    房间列表-->
    <div class="homes" style="text-align: center;font-size: 30px">

    </div>
  </div>


  </div>

</template>
<script>
import AreaSidebar from "../components/AreaSidebar.vue";
import {Headset} from "@element-plus/icons";

export default {
  name: "area",
  components:{
    Headset,
    AreaSidebar
  },
  data() {
    return {
      input: '',
      inputWords:'',
      flag:true,
      flag2:'',
      options: [{
        value: '选项1',
        label: '传入音频'
      }, {
        value: '选项2',
        label: '实时声音'
      }, {
        value: '选项3',
        label: '文字转语音'
      }],
      value: '文字转语音'
    };
  },
  created() {
  },
  mounted() {
    this.flag=false;
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
.homes{
  margin: 20px 0px 0px 20px;
  background-color: rgba(216, 129, 245, 0.03);
  border-radius: 10px;
  border: 2px solid #e8cccf;
  width: 1250px;
  height: 500px;
  //box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
</style>
