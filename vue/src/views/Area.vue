<template>
<!--侧边栏-->
  <div>
    <AreaSidebar/>
  </div>
<!--  主体-->
  <div class="back" >
<!--    主要内容-->
  <div  style="margin-top: 10px;margin-left: 20px" >

  <div style="margin-left:570px;font-size: 30px;color:  #b349ef">商家房间列表
    <el-button @click="onDialog()"
               type="primary"
               style="width: 90px;
               height: 30px;
               background-color:#fce889;
               margin-left: 200px;
               font-size: 18px;
               color: #c073e4;
               line-height: 40px"
               round>创建房间
    </el-button>
  </div>
    <!--    房间列表-->
    <Dialog1
        style="z-index: 1"
        title="直播信息"
        :width="720"
        :height="480"
        :content="content"
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
    <div class="homes" style="font-size: 30px;overflow: auto;display:flex; flex-wrap: wrap">
      <div v-for="(d,index) in counter" :key="index">
        <Home
            ref="home"
            :pace="d.pace"
            :home-name=d.hostname
            @delete="onDelete"
        />
      </div>
    </div>
  </div>


  </div>

</template>
<script>
import AreaSidebar from "../components/AreaSidebar.vue";
import {Headset} from "@element-plus/icons";
import Home from "../components/Home.vue";
import Dialog1 from "../components/Dialog1.vue"
export default {
  name: "area",
  components:{
    Headset,
    Home,
    Dialog1,
    AreaSidebar
  },
  data() {
    return {
      count: 0,
      homeName:'默认',
      temp:[],
      counter:[],
      showDialog: false,
      content: '',
      dialogVisible: true,
      input: '',
      inputWords:'',
      flag:true,
      index:1
    };
  },
  created() {
  },
  mounted() {
    this.flag=false;
    this.temp=new Map()
  },
  methods: {
    load () {
      this.count += 2
    },
    onDelete(){
      this.temp.delete(this.$refs.home[0].homeName)
      this.counter=[]
      this.temp.forEach((value,key)=>{
        this.counter.push({"hostname":value})
      })
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
      this.homeName=this.$refs.test.form.homename
      this.pace=this.$refs.test.form.pace
      if(this.temp.get(this.homeName)==null){
        this.temp.set(this.homeName,this.homeName)
        this.counter.push({"hostname":this.homeName,"pace":this.pace})
      }else {
        this.$message.error('房间名称不能重复！');
      }
      this.showDialog = false
      this.$refs.test.form=[]
    }
    ,
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    }
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
