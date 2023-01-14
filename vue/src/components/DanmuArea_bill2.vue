<!--<li v-for='item in items' >{{'('+item[0].state+')'}}  {{'---"'+item[1].name+'"---'}} {{item[2].time}}</li>-->
<template>

    <div  class="msg-tit">
      <div hidden>
      <SendDanmuBill ref="send"
      :home-url="homeurl"

      />
      </div>
      <div class="msg-top">
        <div class="tit-word">
          <!--          控制点击变色-->
          <div v-if="windowFlag===0">
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px;color: #c073e4"
                       @click="windowFlag=0"
            >弹幕</el-button>
          </div>
          <div v-else>
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px"
                       @click="windowFlag=0"
            >弹幕</el-button>
          </div>
        </div>

        <div class="tit-word">
          <!--          控制点击变色-->
          <div v-if="windowFlag===1">
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px;color: #c073e4"
                       @click="windowFlag=1"
            >告警</el-button>
          </div>
          <div v-else>
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px"
                       @click="windowFlag=1"
            >告警</el-button>
          </div>
        </div>

        <div class="tit-word">
          <!--          控制点击变色-->
          <div v-if="windowFlag===2">
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px;color: #c073e4"
                       @click="windowFlag=2"
            >频繁</el-button>
          </div>
          <div v-else>
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px"
                       @click="windowFlag=2"
            >频繁</el-button>
          </div>
        </div>

          <div class="tit-word" >
          <!--          控制点击变色-->
          <div v-if="windowFlag===3">
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px;color: #c073e4"
                       @click="windowFlag=3"
            >活跃</el-button>
          </div>
          <div v-else>
            <el-button text style="background-color: rgba(254,249,215,0);font-size: 16px"
                       @click="windowFlag=3"
            >活跃</el-button>
          </div>
          </div>



      </div>



      <div class="msg-line"></div>


      <div v-if="windowFlag===0" class="marquee-wrap">
        <ul class="marquee-list" :class="{'animate-up': animateUp}">
          <li   v-for="(item, index) in items" :key="index">
<!--            flag=true 弹幕消息-->
            <div v-if="item.flag===true" :style="fontStyle" class="p-word">

              <img src="/pho/fangcao.png" style="min-block-size: 20px;border-radius: 50%">
              <div style="color: #aa00ff;margin-left: 5px">
                {{item.name+'：'}}
              </div>
              <div style="color: #e0fff1">
                {{' '+item.neirong}}
              </div>
              <div style="color: #5379da">
                {{' - '+item.time}}
              </div>

            </div>

<!--           flag=enter 进入房间-->
            <p v-else-if="item.flag==='enter'" :style="fontStyle2" class="p-word">

                {{"欢迎 "+item.name}} {{item.neirong}}

            </p>
            <!--           flag=gift 送出礼物-->
            <p v-else-if="item.flag==='gift'" :style="fontStyle2" class="p-word">

              {{"谢谢 "+item.name}} {{'送出的"'+item.neirong+'"'}}

            </p>
          </li>
        </ul>
      </div>

      <!--      实时告警-->
      <div v-else-if="windowFlag===1"  class="marquee-wrap" >
        <div style="height: 100px;width: 50px;background-color: #1890ff">
        </div>
      </div>

      <!--      频繁弹幕-->
      <div v-else-if="windowFlag===2"  class="marquee-wrap" >
        <ul class="marquee-list" :class="{'animate-up': animateUp}">
          <li   v-for="(item, index) in danmus" :key="index">
            <div style="color: #8450e0;display: flex" class="p-word">
              <div style="color: #c073e4;margin-left: 5px">
                {{item.name}}
              </div>
              {{'：'+item.danmuCount}} {{' — '+item.time}}
            </div>
          </li>
        </ul>
      </div>


      <!--      活跃用户-->
      <div v-else-if="windowFlag===3"  class="marquee-wrap" >
        <ul class="marquee-list" :class="{'animate-up': animateUp}">
          <li   v-for="(item, index) in users" :key="index">
            <div style="color: #8450e0;display: flex" class="p-word">
              <img src="/pho/fangcao.png" style="min-block-size: 20px;border-radius: 50%">
              <div style="color: #c073e4;margin-left: 5px">
                {{item.name}}
              </div>
              {{'：'+item.userCount}} {{' — '+item.time}}
              <!--              {{' '+item.count}}-->
              <!--              <el-button @click="openChangeWindow(item.neirong,item.answer)"-->
              <!--                         style="max-height: 10px;max-width: 30px;-->
              <!--                         background-color: rgba(254,249,215,0);-->
              <!--                         padding-top: 8px-->
              <!--                          " text>详情</el-button>-->
            </div>
          </li>
        </ul>

      </div>




    </div>

    <MocapDialog
        style="z-index: 1"
        title="回答设置"
        :width="720"
        :height="480"
        :content="content"
        :footer="true"
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
import MocapDialog from "./dialog/MocapDialog.vue"
import request from "../utils/request";
import SendDanmuBill from "./SendDanmuBill.vue";

export default {
  name: 'MessagePage',
  components:{
    SendDanmuBill,
    MocapDialog
  },
  data(){
    return{
      //dialog
      showDialog: false,
      content: '',
      dialogVisible: true,
      //dialog
      formLabelWidth: '120px',
      items:[],
      users:[],
      danmus:[],
      animateUp: false,
      timer: null,
      windowFlag:0
    }

  },
  props: {
    homeurl: { // 地址
      type: String,
      default: 'https://live.bilibili.com/697?'
    },
  },
  computed: {
    fontStyle(){
      const _site = {
        'color':'#635879',
        'display':'flex'
      }
      return _site
    },
    fontStyle2(){
      const _site = {
        'color':'#d93660',
        'display':'flex'
      }
      return _site
    },
  },
  created() {
    this.timer = setInterval(this.scrollAnimate, 1300);

  },
  mounted() {
    if(this.$refs.send.itemsSend){
      this.items=this.$refs.send.itemsSend
      this.users=this.$refs.send.itemsSend2
      this.danmus=this.$refs.send.activeDanmu
    }
  },
  methods:{
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
      this.showDialog = false
    }
    ,
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    openChangeWindow(name,answer){
      this.$prompt('问题：'+name, '详情', {
        inputValue:answer,
        confirmButtonText: '确认修改',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '修改成功: '
        });
      });
    },

},
  destroyed() {
    clearInterval(this.timer)
  }
}

</script>

<style scoped lang="less">

  .msg-tit{
    padding: 6px 5px 5px 10px;
    .msg-top{
      display: flex;
    }
    .msg-line{
      width: 230px;
      height: 1px;
      background: #EFEFEF;
      margin: 8px 0;

    }
    .marquee-wrap::-webkit-scrollbar {
      display: none;
    }
    .marquee-wrap  {
      /*width: 80%;*/
      overflow: auto;
      flex-wrap: wrap;
      height:357px;

      margin: 0 auto;
      .marquee-list {
        /*border: 1px solid red;*/
        word-break: break-all;
        word-wrap: break-word;
        li {

          .p-tit{
            font-size: 6px;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            color: #222222;
            height: 22px;
            line-height: 22px;
          }
          .p-word{
            margin-top: 8px;
            font-size: 7px;
            font-weight: 10;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            height: 17px;
            line-height: 18px;
            width: 217px;
            white-space: nowrap;
          }

        }
      }
      .animate-up {
        transition: all 0.5s ease-in-out;
        transform: translateY(-58px);
      }
    }

    .streamingWarning  {
      /*width: 80%;*/
      overflow: auto;
      display:flex;
      flex-wrap: wrap;
      height:407px;
      width: 230px;
      margin: 0 auto;
      .marquee-list {
        /*border: 1px solid red;*/
        li {

          .p-tit{
            font-size: 12px;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            color: #222222;
            height: 22px;
            line-height: 22px;
          }
          .p-word{
            margin-top: 8px;
            font-size: 14px;
            font-family: PingFangSC-Regular, PingFang SC,serif;
            height: 16px;
            line-height: 18px;
            width: 767px;
            white-space: nowrap;
          }

        }
      }
      .animate-up {
        transition: all 0.5s ease-in-out;
        transform: translateY(-58px);
      }
    }
  }

</style>