<template style="position: relative">
  <div class="back" style="display: flex;
   ">
    <!--侧边栏-->

    <!--  主体-->

    <!--    主要内容-->
    <div style="position:absolute; width: 560px;left: 50%">
      <div style="display: flex;width: 560px;height:40px;margin-top: 20px ;align-items: center">
        <div style="font-size: 20px;margin-right: 40px">分辨率</div>
        <el-select size="default" style="margin-right: 30px " v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-button class="myButton" style="border-radius: 10px" size="default" plain>确定</el-button>
      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <div style="font-size: 20px;margin-right: 20px;">语言</div>
        <div>
          <el-radio-group style="margin-left: 40px;" size="default" v-model="radio1">
            <el-radio-button label="简体中文" ></el-radio-button>
            <el-radio-button label="繁体中文"></el-radio-button>
            <el-radio-button label="英语"></el-radio-button>
            <el-radio-button label="日语"></el-radio-button>
          </el-radio-group>
        </div>
      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <div style="font-size: 20px;margin-right: 20px;">帧率</div>
        <div class="block">
          <el-slider
              v-model="value1"
              :step="1"

              max="90"
              style="width: 280px;margin-left: 45px">
          </el-slider>
        </div>
      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <div style="font-size: 20px;margin-right: 20px;">抗锯齿</div>
        <el-switch size="large" style="margin-left: 20px"
                   v-model="switch1"
                   active-color="#13ce66"
                   inactive-color="#ff4949">
        </el-switch>

      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <div style="font-size: 20px;margin-right: 20px;">日志文件</div>
        <el-button type="info" size="default" plain>打开日志</el-button>
      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <el-button type="success" style="width: 120px" size="default">保存设置</el-button>


        <el-button class="el-icon-refresh-right" type="warning" size="default">恢复默认设置</el-button>
      </div>
      <div style="display: flex;height:40px;margin-top: 40px ; align-items: center">
        <el-button class="myButton" style="margin-right: 40px;border-radius: 10px;font-size: 16px;height: 30px;"  plain >重启软件</el-button>
        <el-button  class="myButton2"  style="border-radius: 10px;height: 30px;font-size: 16px">关闭软件</el-button>
      </div>
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
      value1: 0,
      switch1: true,
      options: [{
        value: '选项1',
        label: '432X768'
      }, {
        value: '选项2',
        label: '540X960'
      }, {
        value: '选项3',
        label: '960X540'
      }, {
        value: '选项4',
        label: '768X432'
      }, {
        value: '选项5',
        label: '1920X1080'
      }],
      value: '',
      radio1: '简体中文',
      radio2: '繁体中文',
      radio3: '英语',
      radio4: '日语'
    }
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
  background-color: #ffffff;
  margin-left: 5%;
  margin-right: 5%;
  width: 90%;
  height:100vh;
  color: #49b74b;
}
.myButton{
  color: #72d063;
}
.myButton:hover{
  background: #9aef8d;
  color: #fff;
  border-radius: 10px;
  border-color:  #9aef8d;
  box-shadow: 0 0 5px #b0f6ab,
  0 0 25px #b9fabd,
  0 0 50px #c7fdcf,
  0 0 100px #baffbe;
}

.myButton2{
  color: #e07bcd;
}
.myButton2:hover{
  background-color: #ef9adb;
  color: #ffffff;
  border-radius: 10px;
  border-color:  #9aef8d;
}
</style>