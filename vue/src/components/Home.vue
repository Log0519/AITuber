<template>
<div class="homeBack">
  <div class="information" >
    <div style="font-size: 20px;text-align: center">
      房间
    </div>
  <div style="font-size: 17px;display: flex">房间名称：
  <div class="homeName" style="font-size: 17px;color: #c657ff">
    {{ homeName }}
  </div>
  </div>
    <div style="font-size: 17px;display: flex">直播平台：
    <div class="pace" style="font-size: 17px;color: #c657ff">
      {{ pace }}
    </div>
    </div>
    <div class="state" style="display: flex;font-size: 17px">
      状态：
    <div style="font-size: 17px;color: #8d8f8d">{{state}}</div>
      <div style="margin-left: 100px">
    创建时间：{{nowDate}}
      </div>
    </div>
    <div class="moddle" style="display: flex;font-size: 17px">
      模式：
    <div style="font-size: 17px;color: #13da2e">自动</div>
        <div style="display: flex;margin-left: 117px">剩余时间：  <CountDown :endTime='endTime'/>
      </div>
    </div>
    <div style="display: flex">


    <el-button type="danger" style="font-size:5px;margin-left: 370px" @click="onDialog()">删除房间</el-button>
  <el-button style="background-color: rgba(238,236,255,0);font-size: 16px" text>进入房间>></el-button>
    </div>
    </div>
</div>
  <DeleteDialog
      style="z-index: 1"
      title="提示"
      :width="340"
      :height="100"
      :footer="true"
      cancelText="取消"
      okText="确认"
      switchFullscreen
      ref="test"
      @close="onClose"
      @cancel="onCancel"
      @ok="onConfirm"
      v-show="showDialog"
  />
</template>

<script>
import DeleteDialog from "./dialog/DeleteDialog.vue";
import CountDown from "./CountDown.vue";
export default {
  name: "home",
  components:{
    DeleteDialog,
    CountDown
  },
  data(){
    return {
      showDialog:false,
      state:"未开始"
    }
  },
  props: {
    homeName: { // 标题
      type: String,
      default: ''
    },
    pace: { // 标题
      type: String,
      default: ''
    },
    endTime: { // 结束时间
      type: String,
      default: ''
    },
    nowDate: { // 创建时间
      type: String,
      default: ''
    },
  },
  methods:{
    deleteHome(){
      this.$emit('delete')
    },
    //对话框方法
    onDialog () { // 调用Dialog弹出对话框
      this.showDialog = true
    },
    onClose () { // 关闭dialog
      this.showDialog = false
    },
    onCancel () { // “取消”按钮回调
      this.showDialog = false
    },
    onConfirm () { // “确定”按钮回调
      this.deleteHome()
      this.showDialog = false
    }
    ,
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    }
  },
}
</script>

<style scoped>
.homeBack{
  margin: 20px 0px 0px 20px;
  background-color: rgb(248, 240, 148);
  border-radius: 20px;
  border: 3px solid #d3a6ff;
  width: 582px;
  height: 170px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
.information{
  margin:10px 10px 10px 10px;
}
</style>