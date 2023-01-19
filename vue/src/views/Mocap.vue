<template>
<!--侧边栏-->
<!--  <div>-->
<!--    <MocapSidebar/>-->
<!--  </div>-->
<!--  主体-->
  <div class="back">
  <div v-if="pace==='哔哩哔哩'" class="body" style="display: flex;width: 98%;margin-left: 1%" >
    <div class="window1" style="display: flex">
      <div class="window1-left" style="width: 100%">
        <div style="margin: 10px;display: flex;font-size: 18px;color:#49b74b">房间名称：
          <div style="color: #e291ea">
          {{homeName}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 14px;color: #49b74b">平台：
          <div style="color: #e291ea">
            {{pace}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 13px;color: #49b74b">创建时间：
          <div style="color: #e291ea">
            {{createTime}}
          </div>
        </div>
      <div style="margin: 10px;color: #49b74b">

        ⭐模型：
        <div class="list" style="background-color: #aaecab;margin-top:10px;width: 98%;height: 170px;
        margin-left: 1%;border-radius: 20px;overflow: auto">
        <div class="his-body" style="margin-left: 10px;color: #2cb43d;display: flex; flex-wrap: wrap">
        <div v-for="(d,index) in phoCounter" :key="index">
          <!--                photo组件-->
          <PhoItem
              :src="d.src"
              @click="modelCode=d.code;"
              :name="d.name"
          >
          </PhoItem>

        </div>
        </div>
        </div>
      </div>
        <SendDanmuBill style="color: #f2f2f2;margin-left: 20px">

        </SendDanmuBill>
      </div>
      <!--    右    >-->

    </div>


    <div class="window2" >
  <div style="margin: 1px">

<!--    主播房间的时候-->
    <div v-if="src===''" style="margin: 10px 0px 0px 10px" class="camera_outer">

        <div class="tuberHome" style="width:865px;height:490px;margin-bottom: 5px;display: flex">

          <danmuArea_bill2
            style="font-size: 16px;margin-left: 10px;
            background-color: rgba(236,223,253,0.45);
            color: #635773"
            :homeurl="homeUrl"
        />
<!--          商品列表-->
          <div v-if="isBuy==='开启'">
          <div v-if="isOpenBuy" style="background-color: rgba(15,57,145,0.6);width: 300px;height: 450px;margin-left: 100px;
              border-radius: 10px">
            <div style="display: flex">

            <div style="margin-left: 10px;margin-top: 10px;color: #40a9ff;font-family: 幼圆,serif">商品</div>
              <button class="closeOpenBuy" style="height: 20px;margin-left: 120px;padding-bottom: 5px;
              width: 40px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isGuanli=true;isOpenBuy=false">添加</button>
              <button class="closeOpenBuy" style="height: 20px;margin-left: 10px;padding-bottom: 5px;
              width: 40px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isShanchu=true;isOpenBuy=false">删除</button>
              <button class="closeOpenBuy" style="height: 20px;margin-left: 10px;padding-bottom: 5px;
              width: 20px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isOpenBuy=false">—</button>
            </div>
            <div class="goodsList" style="height: 380px;width: 280px;
            background-color: #5694c7;margin-top: 10px;margin-left: 10px;margin-right: 10px;padding-left: 8px;
            border-radius: 5px;display: flex; flex-wrap: wrap;overflow: auto">

                <div style="height: 10px"></div>
                <div v-for="(d,index) in BuyCounter" :key="index">
                  <!--                goods组件 商品-->
                  <div>
                    <button class="closeOpenBuy" style="height: 20px;margin-left: 15px;padding-bottom: 5px;
              width: 40px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="this.src=d.src;goMv()">视频</button>
                  <goods style="margin: 5px;border-radius: 5px;cursor: pointer"
                         :goods-name="d.name"
                         :goods-price="d.price"
                         @click="dname=d.name;photoInit()"
                  ></goods>

                  </div>
<!--                  商品预览-->
                  <ScenePhotoDialog
                      ref="photo"
                      style="z-index: 10000"
                      title="预览"
                      :footer="true"
                      :photos="this.photos"
                      switchFullscreen
                      :goods-name="dname"
                      :switch-fullscreen="false"
                      @close="onCloseScene"
                      @ok="onConfirmScene"
                      v-show="isOpenScenePho"
                  />
              </div>
            </div>
          </div>

          <!--          删除列表-->
          <div v-if="isShanchu" style="background-color: rgba(15,57,145,0.6);width: 300px;height: 450px;margin-left: 100px;
              border-radius: 10px">
            <div style="display: flex">

              <div style="margin-left: 10px;margin-top: 10px;color: #40a9ff">商品</div>
              <button class="closeOpenBuy" style="height: 20px;margin-left: 120px;padding-bottom: 5px;
              width: 70px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="myDelete()">删除选中</button>
              <button class="closeOpenBuy" style="height: 20px;margin-left: 10px;padding-bottom: 5px;
              width: 40px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isShanchu=false;isOpenBuy=true">取消</button>
            </div>
            <div class="goodsDeleteList" style="height: 380px;width: 280px;
            background-color: #5694c7;margin-top: 10px;margin-left: 10px;margin-right: 10px;
            border-radius: 5px;display: flex; flex-wrap: wrap;overflow: auto;padding-left: 8px">


                <div style="height: 10px"></div>
                <div v-for="(d,index) in BuyCounter" :key="index">
                  <!--                goods组件 商品-->
                  <el-checkbox></el-checkbox>
                  <goods style="margin-top: 0px;margin-left:5px;margin-right:5px;border-radius: 5px"
                         :goods-name="d.name"
                         :goods-price="d.price"

                  ></goods>


              </div>
            </div>
          </div>

<!--          商店页面-->
          <div v-if="isGuanli" style="background-color: rgba(15,57,145,0.6);width: 300px;height: 450px;margin-left: 100px;
              border-radius: 10px">
            <div style="display: flex">

              <div style="margin-left: 10px;margin-top: 10px;color: #40a9ff">商品</div>


              <button class="closeOpenBuy" style="height: 20px;margin-left: 190px;padding-bottom: 5px;
              width: 40px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isOpenBuy=true;isGuanli=false">返回</button>
            </div>
            <div class="list" style="height: 380px;width: 280px;
            background-color: #5694c7;margin-top: 10px;margin-left: 10px;
            border-radius: 5px">
              <div style="height: 30px"></div>
              <div style="margin-left: 20px">
                <div style="display: flex;color: #f2f2f2">
                  名称：
                  <div style="width: 60px">
                  <el-input></el-input>
                  </div>
                </div>
                <div style="display: flex;margin-top: 30px;color: #f2f2f2">
                  价格：
                  <div style="width: 60px">
                    <el-input></el-input>
                  </div>
                </div>

                <button class="closeOpenBuy" style="height: 30px;margin-left: 140px;padding-bottom: 5px;
              width: 60px;margin-top: 30px;background-color: #74ace7;font-size: 16px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px">添加</button>
              </div>
              <div style="height: 5px"></div>
            </div>
          </div>


          <div v-else>
            <button class="closeOpenBuy" style="height: 20px;margin-left:370px;padding-bottom: 2px;
              width: 20px;margin-top: 10px;background-color: #74ace7;font-size: 8px;
              border-color: #40a9ff;color: #f2f2f2;border-radius: 5px" @click="isOpenBuy=true">十</button>
          </div>
          </div>
          <div v-if="isBuy==='关闭'">

          </div>

        </div>


    </div>
<!--    播放视频的时候-->
    <div v-else style="margin: 10px 0px 0px 10px" class="camera_outer">
      <div class="tuberHome" style="width:865px;height:490px;margin-bottom: 5px">
        <div hidden>
        <danmuArea_bill2
            style="font-size: 16px;margin-left: 10px;
            background-color: rgba(236,223,253,0.45);
            color: #635773"
            :homeurl="homeUrl"
        />
        </div>
        <embed :src="src" width=865px height=490px />
      </div>

    </div>


  <div style="margin-top: 20px;margin-left: 20px;display: flex">

    <div style="color: #49b74b">
   视频地址:
    </div>
    <el-input   placeholder="请输入地址"

                v-model="srcTemp"
                style="width:30%;height: 30px;
                  margin-left: 10px;margin-right: 20px"
                clearable>

    </el-input>
    <el-button class="myButton" v-if="os" @click="goMv"  style="font-size: 19px;border-radius: 10px;height: 30px">播放视频</el-button>
    <el-button  class="myButton"  v-else @click="stopMv"  style="font-size: 19px;border-radius: 10px;height: 30px">关闭视频</el-button>
    <el-button   class="myButton" v-if="!isCatch" @click="openCamera" style="font-size: 19px;border-radius: 10px;height: 30px">开始捕捉</el-button>
    <el-button    class="myButton" v-else @click="stopCamera"   style="font-size: 19px;border-radius: 10px;height: 30px">停止捕捉</el-button>
  </div>
    <div>
      <el-button class="myButton" style="margin-left: 750px;margin-top: 30px;border-radius: 10px;height: 30px" @click="backArea">返回列表</el-button>
    </div>
    </div>
    </div>
  </div>




  <div v-else class="body" style="display: flex" >
    <div class="window1" style="display: flex">
      <div class="window1-left" style="width: 100%">
        <div style="margin: 10px;display: flex;font-size: 18px;color: #49b74b">房间名称：
          <div style="color:  #e291ea">
            {{homeName}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 14px;color: #49b74b">平台：
          <div style="color:  #e291ea">
            {{pace}}
          </div>
        </div>
        <div style="margin: 10px;display: flex;font-size: 13px;color: #49b74b">创建时间：
          <div style="color:  #e291ea">
            {{createTime}}
          </div>
        </div>
        <div style="margin: 10px">
          ⭐模型：
          <div class="list" style="background-color:  #aaecab;margin-top:10px;width: 98%;height: 170px;
        margin-left: 1%;border-radius: 20px;overflow: auto">
            <div class="his-body" style="margin-left: 10px;color: #2cb43d;display: flex; flex-wrap: wrap">
              <div v-for="(d,index) in phoCounter" :key="index">
                <!--                photo组件-->
                <PhoItem
                    :src="d.src"
                    @click="modelCode=d.code;"
                    :name="d.name"
                >
                </PhoItem>

              </div>
            </div>
          </div>
        </div>
        <danmuArea_bill
            style="font-size: 16px;margin-left:1%;
            background-color: rgba(236,223,253,0.45);
            color: #635773;width: 98%"
            :homeurl="homeUrl"
        />
      </div>
      <!--    右    >-->

    </div>


    <div class="window2" >
      <div style="margin: 1px">

        <!--    主播房间的时候-->
        <div v-if="src===''" style="margin: 10px 0px 0px 10px" class="camera_outer">

          <div class="tuberHome" style="width:865px;height:490px;margin-bottom: 5px">

          </div>
        </div>
        <!--    播放视频的时候-->
        <div v-else style="margin: 10px 0px 0px 10px" class="camera_outer">
          <div class="tuberHome" style="width:865px;height:490px;margin-bottom: 5px">
            <embed :src="src" width=865px height=490px />
          </div>

        </div>


        <div style="margin-top: 20px;margin-left: 20px;display: flex">

          <div style="color:#49b74b">
            视频地址:
          </div>
          <el-input   placeholder="请输入地址"

                      v-model="srcTemp"
                      style="width:30%;height: 30px;
                  margin-left: 10px;margin-right: 20px"
                      clearable>

          </el-input>
          <el-button class="myButton" v-if="os" @click="goMv"  style="font-size: 19px;border-radius: 10px;height: 30px">播放视频</el-button>
          <el-button  class="myButton"  v-else @click="stopMv"  style="font-size: 19px;border-radius: 10px;height: 30px">关闭视频</el-button>
          <el-button   class="myButton" v-if="!isCatch" @click="openCamera" style="font-size: 19px;border-radius: 10px;height: 30px">开始捕捉</el-button>
          <el-button    class="myButton" v-else @click="stopCamera"   style="font-size: 19px;border-radius: 10px;height: 30px">停止捕捉</el-button>

        </div>
        <div>
          <el-button class="myButton" style="margin-left: 750px;margin-top: 30px;border-radius: 10px;height: 30px" @click="backArea">返回列表</el-button>
        </div>
      </div>
    </div>
  </div>




  <MocapWindowDialog
      :hidden="flag"
      :model-code="modelCode"
      :style="tuberStyle"
      :width="1020"
      :height="380"
      :content="content"
      :footer="false"
      cancelText="取消"
      okText="确认"
      switchFullscreen
      @close="onClose"
      @cancel="onCancel"
      @ok="onConfirm"
      v-show="showDialog"
  />
</div>
</template>


<script>
import MocapSidebar from "../components/sidebar/MocapSidebar.vue";
import DanmuArea_bill from "../components/DanmuArea_bill.vue";
import DanmuArea_bill2 from "../components/DanmuArea_bill2.vue";
import SendDanmuBill from "../components/SendDanmuBill.vue";
import {useRoute, useRouter} from "vue-router/dist/vue-router";
import MocapWindowDialog from "../components/dialog/MocapWindowDialog.vue";
import PhoItem from "../components/PhoItem.vue";
import goods from "../components/goods.vue";
import ScenePhotoDialog from "../components/dialog/ScenePhotoDialog.vue";
import request from "../utils/request";




export default {
  name: "mocap",
  components:{
    SendDanmuBill,
    MocapSidebar,
    DanmuArea_bill,
    DanmuArea_bill2,
    MocapWindowDialog,
    PhoItem,
    goods,
    ScenePhotoDialog,
  },
  data() {
    return {
      isBuy:'',
      modelCode:'3',
      isCatch:true,
      os: true,//控制摄像头开关
      record:true,
      videoWidth: 500,
      videoHeight: 400,
      src:'',
      inputWords:'',
      homeName:'',
      pace:'',
      isGuanli:false,
      left:'947px',
      srcTemp:'./public/vedio/back.mp4',
      // srcTemp:'./public/vedio/back.mp4',
      createTime:'',
      homeUrl:'',
      showDialog:true,
      isOpenScenePho:false,
      model:'hiyori',
      isShanchu:false,
      isOpenBuy:false,
      dname:'',
      phoCounter:[{"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":'/public/pho/redgirl.png',"code":'3',"name":'redgirl'},
        {"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":'/public/pho/fangcao.png',"code":'2',"name":'fangcao'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
        {"src":"/public/pho/hiyori.png","code":'1',"name":'hiyori'},
      ],
      BuyCounter:[
          {'name':"西瓜",'price':'2元/斤','src':'/vedio/back.mp4'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
        {'name':"西瓜",'price':'2元/斤'},{'name':"西兰花",'price':'3元/斤'},{'name':"番茄",'price':'4元/斤'},
      ],
      photos:[]
    };
  },
  created() {
    const router = useRouter()
    console.log(router.currentRoute.value.query)
    this.homeName=router.currentRoute.value.query.homeName
    this.createTime=router.currentRoute.value.query.createTime
    this.pace=router.currentRoute.value.query.pace
    this.homeUrl=router.currentRoute.value.query.homeUrl
    this.isBuy=router.currentRoute.value.query.isBuy

  },
  mounted() {

  },
  computed:{
    tuberStyle(){
      var site = {
        'z-index': '1',
        'margin-left': this.left,
        'margin-top': '292px'
      }
      return site
    },
  },
  methods: {
    photoInit(){
      this.photos=[]
      request.get("/goodsPhotos/init",{
        params: {
          name:this.dname
        }
      }).then(res => {
        if (res.code === '0') {
          for (let i = 0; i < res.data.length; i++) {
            this.photos.push({"src":res.data[i].photourl})
          }
          this.isOpenScenePho=true
        } else {
          console.log("错误")
        }
      })
    },
    myInit(){

    },
    stopMv(){
      this.src=""
      this.os=true
      this.left='947px'
    },
    openScenePho(){
      this.isOpenScenePho=true
    },
    goMv(){
      this.left='1200px'
      this.src=this.srcTemp
     //this.src="./public/vedio/back.mp4"
      this.os=false
    },
    //对话框方法
    onDialog () { // 调用Dialog弹出对话框
      this.showDialog = true
    },
    onClose () { // 关闭dialog
      this.showDialog = false
    },
    onCloseScene () { // 关闭dialog
      this.isOpenScenePho = false
    },
    onConfirmScene(){
      this.isOpenScenePho = false
    },
    onCancel () { // “取消”按钮回调
      this.showDialog = false
    },
    onConfirm () { // “确定”按钮回调
      this.showDialog = false
    },
    backArea(){
      this.$router.push("/area")
    },
    ChangeToVoice(){
      console.log(this.inputWords)
    },
    beginRecord(){
      this.record=false;
      },
    stopRecord(){
      this.record=true;
    },

    // 调用摄像头权限
    openCamera() {
      //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
      this.$nextTick(() => {
        const _this = this;
        this.isCatch=true
        this.record=true;
      });
      this.showDialog=true


    },
    //关闭摄像头
    stopCamera() {
        this.showDialog=false
        this.isCatch=false
        this.record=true;
    },

  }

};





</script>

<style lang="scss" scoped>
.back{
  background-color: #ffffff;
  margin-left: 5%;
  margin-right: 5%;
  width: 90%;
  height: 100vh;
}
.window1{
  border-radius: 10px;
  width: 35%;
  height: 640px;
}
//人物视角窗口window2
.window2{
  margin-left: 1%;

  border-radius: 10px;
  width: 60%;
  height: 640px;
}

.camera_outer{
    /*-moz-transform:scaleX(-1);
    -webkit-transform:scaleX(-1);
    -o-transform:scaleX(-1);
    transform:scaleX(-1);*/
}
.tuberHome{
  background-image: url("/pho/homeBack02.gif");
  //background-image: url("/pho/gifback4.gif");
  //background-image: url("/vedio/noVoice.mp4");
  background-size: cover;
}
.closeOpenBuy{
  cursor: pointer;  /*鼠标悬停变小手*/

}
.guanli{
  cursor: pointer;
}
.goodsDeleteList::-webkit-scrollbar{
 display: none;
}
.goodsList::-webkit-scrollbar{
 display: none;
}
.list::-webkit-scrollbar{
  display: none;
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
</style>
