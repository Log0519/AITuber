<template>
<div class="homeBack">
  <div class="information" >
    <div style="font-size: 20px;text-align: center">
      <div class="homeName" style="font-size: 20px;color: #ffa6fe">
        {{ homeName }}
      </div>
    </div>
    <div style="display: flex">
    <div style="font-size: 17px;display: flex">直播平台：
    <div class="pace" style="font-size: 17px;color: #48c54e">
      {{ pace }}
    </div>
    </div>
      <div style="font-size: 17px;display: flex;margin-left: 50px">商店：
        <div class="pace" style="font-size: 17px;color: #48c54e">
          {{isBuy}}
        </div>
      </div>
    </div>
    <div class="state" style="display: flex;font-size: 17px">
      状态：
    <div style="font-size: 17px;color: #8d8f8d">{{state}}</div>
      <div style="margin-left: 100px;font-size: 14px;display: flex">
    创建时间：
        <div style="color:#48c54e ">
          {{nowDate}}
        </div>
      </div>
    </div>
    <div class="moddle" style="display: flex;font-size: 17px">
      模式：
    <div style="font-size: 17px;color: #13da2e">自动</div>
        <div style="display: flex;margin-left: 117px;font-size: 14px;display: flex">网址：
          <div style="color:#48c54e ">
          {{homeUrl}}
          </div>
      </div>
    </div>
    <div style="display: flex">
    <el-button class="myButton2" style="font-size:14px;margin-left: 370px;border-radius: 10px;height: 25px" @click="onDialog()">删除房间</el-button>
  <el-button class="myButton" style="font-size: 14px;border-radius: 10px;height: 25px"  @click="enterHome()">进入房间</el-button>
    </div>
    </div>
</div>
  <DeleteDialog
      ref="delete"
      style="z-index: 1"
      title="提示"
      :width="340"
      :height="100"
      :footer="true"
      :create-time="nowDate"
      :name="homeName"
      cancelText="取消"
      okText="确认"
      switchFullscreen
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
    isBuy: { // 商店
      type: String,
      default: 'false'
    },
    homeUrl: { // 网址
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
    backName() {
      return this.homeName;
    }
  },
  methods:{
    deleteHome(){
      console.log(this.homeName)
      console.log(this.nowDate)
      this.$emit('delete')
    },
    enterHome(){
      this.$emit('enter')
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
  background-color: rgb(216, 252, 195);
  border-radius: 20px;
  border: 3px solid #ffffff;
  width: 620px;
  height: 150px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(75, 200, 96, 0.3);
}
.information{
  margin:10px 10px 10px 10px;
}
.myButton{
  color: #70cb62;
}
.myButton:hover{
  background: #9aef8d;
  color: #fff;
  border-radius: 10px;
  border-color:  #9aef8d;
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