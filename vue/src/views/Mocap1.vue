<template>
<!--侧边栏-->
  <div>
    <MocapSidebar/>
  </div>
<!--  主体-->
  <div class="back" >
<!--    主要内容-->
  <div  style="margin-top: 10px;margin-left: 20px">
<!--    声音控制-->
    <div style="text-align: center;font-size: 30px">声音控制</div>
<!--    左-->
    <div style="display: flex;margin: 20px">
    <div style="font-size: 16px;width: 50%">
      <div style="font-size: 20px">来源:</div>
      <div style="width: 50%;margin-top: 20px">⭐文字转语音：
      <el-input
          style="margin: 10px"
          placeholder="请输入内容"
          type="textarea"
          :autosize="{ minRows: 4, maxRows: 8}"
          v-model="inputWords"
          :disabled="flag">
      </el-input>
        <el-button style="margin-left: 270px">转化</el-button>
      </div>
      <div style="width: 50%;margin: 10px">⭐导入音频：
        <el-button>导入</el-button>
      </div>
      <div style="width: 50%;margin: 10px">⭐实时传入：
        <el-select></el-select>
      </div>
    </div>
      <!--右-->
        <div style="font-size: 16px;width: 50%">音源:
          <el-select id="1" v-model="value" placeholder="请选择" style="width: 100px">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
          <el-input
              placeholder="请输入内容"
              v-model="input"
              :disabled="flag">
          </el-input>
        </div>

      </div>
  </div>



  </div>

</template>
<script>
import MocapSidebar from "../components/MocapSidebar.vue";
import {Headset} from "@element-plus/icons";

export default {
  name: "mocap",
  components:{
    Headset,
    MocapSidebar
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
</style>
