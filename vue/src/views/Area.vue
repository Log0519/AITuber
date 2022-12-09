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
    <BusinessDialog
        style="z-index: 1"
        title="直播信息"
        :width="720"
        :height="480"
        :content="content"
        :footer="true"
        cancelText="取消"
        okText="立即创建"
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
            :now-date="d.nowDate"
            :end-time="d.endTime"
            :pace="d.pace"
            :home-name=d.hostname
            @delete="onDelete"
            @enter="onEnter"
        />
      </div>
    </div>
  </div>


  </div>

</template>
<script>
import AreaSidebar from "../components/sidebar/BusinessAreaSidebar.vue";
import {Headset} from "@element-plus/icons";
import Home from "../components/Home.vue";
import BusinessDialog from "../components/dialog/BusinessDialog.vue"
export default {
  name: "area",
  components:{
    Headset,
    Home,
    BusinessDialog,
    AreaSidebar
  },
  data() {
    return {
      count: 0,
      homeName:'',
      temp:[],
      counter:[],
      showDialog: false,
      content: '',
      dialogVisible: true,
      input:'',
      inputWords:'',
      flag:true,
      endYear:"",
      endMo:"",
      endDay:"",
      index:1,
      nowDate:''
    };
  },
  created() {
  },
  mounted() {
    this.flag=false;
    this.temp=new Map()
  },
  methods: {
    getNowDate() {
      //获取当前时间
      var _this = this;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth()+1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf = new Date().getMinutes()<10 ? '0'+new Date().getMinutes() : new Date().getMinutes();
      let ss = new Date().getSeconds()<10 ? '0'+new Date().getSeconds() : new Date().getSeconds();
      _this.gettime = yy+'-'+mm+'-'+dd+' '+hh+':'+mf+':'+ss;
      return _this.gettime
    },
    load () {
      this.count += 2
    },
    onDelete(){
      this.temp.delete(this.$refs.home[0].homeName)
      this.counter=[]
      this.temp.forEach((value,key)=>{
        this.counter.push({"hostname":value.toString().split(",")[0],
          "pace":value.toString().split(",")[1],
          "endTime":value.toString().split(",")[2],
          "nowDate":value.toString().split(",")[3]
        })
      })
    },
    onEnter(){
      this.$router.push("/mocap")
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
      this.nowDate=this.getNowDate()
      this.pace=this.$refs.test.form.pace
      this.endYear=this.$refs.test.form.date1.toString().split(" ")[3]
      this.endMo=this.$refs.test.form.date1.toString().split(" ")[1]
      this.endDay=this.$refs.test.form.date1.toString().split(" ")[2]
      this.endTime2=this.$refs.test.form.date2.toString().split(" ")[4]
      this.endTime=this.endYear+"-"+this.endMo+"-"+this.endDay+" "+this.endTime2
      console.log(this.endTime)
      console.log(this.nowDate)

      if(this.homeName===""){
        this.$message.error('房间名称不能为空！');
        return
      }
      if(this.pace===""){
        this.$message.error('请选择直播平台！');
        return
      }
      if(this.endTime==="undefined-undefined-undefined undefined"){
        this.$message.error('请选择结束时间！');
        return
      }
      if(this.endTime<=this.nowDate){
        this.$message.error('结束时间必须大于现在时间！');
        return
      }
      if(this.temp.get(this.homeName)==null){
        this.temp.set(this.homeName,this.homeName+","+this.pace+","+this.endTime+","+this.nowDate)
        this.counter.push({"hostname":this.homeName,"pace":this.pace,"endTime":this.endTime,"nowDate":this.nowDate})
      }else {
        this.$message.error('房间名称不能重复！');
      }
      this.endYear=""
      this.endMo=""
      this.endDay=""
      this.showDialog = false
      this.endTime=""
      this.$refs.test.form.homename=""
      this.$refs.test.form.pace=""
      this.$refs.test.form.date1='2002-13-14 20:20:12'
      this.$refs.test.form.date2='2002-13-14 20:20:12'
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
