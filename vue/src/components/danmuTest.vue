<!--<li v-for='item in items' >{{'('+item[0].state+')'}}  {{'---"'+item[1].name+'"---'}} {{item[2].time}}</li>-->
<template>
  <div class="message-page">
    <div class="msg-tit">
      <div class="msg-top">
        <div class="tit-word">消息通知</div>
        <div class="tit-word" style="margin-left: 30px">实时告警</div>
        <el-button style="margin-left: 340px" @click="openSetWindow">回复管理</el-button>
      </div>
      <div class="msg-line"></div>
      <div class="marquee-wrap">
        <ul class="marquee-list" :class="{'animate-up': animateUp}" @mouseenter="stopFn" @mouseleave="startFn">
          <li   v-for="(item, index) in items" :key="index">
              <p v-if="item.flag" :style="fontStyle" class="p-word">
              {{'('+item.state+')'}}  {{'---"'+item.name+'"---'}} {{item.time}}
                <el-button @click="openChangeWindow(item.name,item.answer)" style="max-height: 10px;max-width: 30px;background-color: rgba(254,249,215,0)" text>详情</el-button>
              </p>
            <p v-else :style="fontStyle2" class="p-word">
              {{'('+item.state+')'}}  {{'---"'+item.name+'"---'}} {{item.time}}
              <el-button @click="openChangeWindow(item.name,item.answer)" style="max-height: 10px;max-width: 30px;color: #e33d60;background-color: rgba(254,249,215,0)" text> 详情</el-button>
            </p>
          </li>
        </ul>
      </div>
    </div>

  </div>

</template>

<script>
export default {
  name: 'MessagePage',
  data(){
    return{
      dialogVisible: false,
      formLabelWidth: '120px',
      items:[
          {'flag':false,'state':'未解决','name': "今天天气怎么样",'time':"2022-12-1 12:08:06",'answer':''},
          {'flag':true,'state':"自动",'name': "预计多久发售呢",'time':"2022-12-1 12:12:53",'answer':'12月份就会发售'},
          {'flag':true,'state':"自动",'name': "这个商品打折吗",'time':"2022-12-1 12:14:41",'answer':'新品不打折哟'},
          {'flag':false,'state':"未解决",'name': "疫情期间可以发货吗",'time':"2022-12-1 12:25:26",'answer':''},
          {'flag':true,'state':"自动",'name': "双十一活动吗",'time':"2022-12-1 12:46:13",'answer':'双十一活动暂时还没确定'},
          {'flag':true,'state':"自动",'name': "商品有保险吗",'time':"2022-12-1 13:16:41",'answer':'有的'},
          {'flag':true,'state':"自动",'name': "这个有其他颜色的吗",'time':"2022-12-1 13:17:32",'answer':'这个有红色和白色'},
          {'flag':false,'state':"未解决",'name': "这个销量怎么样子",'time':"2022-12-1 13:17:59",'answer':''},
          {'flag':true,'state':"自动",'name': "七天包退换吗",'time':"2022-12-1 13:18:41",'answer':'是的'},
      ],
      animateUp: false,
      timer: null
    }

  },
  computed: {
    fontStyle(){
      const _site = {
        'color':'#7b6988',
      }
      return _site
    },
    fontStyle2(){
      const _site = {
        'color':'#cb365b',
      }
      return _site
    },
  },
  created() {
    this.timer = setInterval(this.scrollAnimate, 1300);
  },
  methods:{
    openChangeWindow(name,answer){
      this.$prompt('问题：'+name, '已设置的自动回复', {
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
    openSetWindow(){
      this.$prompt('修改', '已设置的自动回复', {
        inputType:'ew',
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        this.$message({
          type: 'success',
          message: '修改成功'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消修改'
        });
      });
    },
    scrollAnimate() {
      this.animateUp = false
      setTimeout(() => {
        this.items.push(this.items[0])
        this.items.shift()
        this.animateUp = false
      }, 190)
    },
    stopFn(){
      clearInterval(this.timer)
    },
    startFn(){
      this.timer = setInterval(this.scrollAnimate, 1500);
    }
  },
  destroyed() {
    clearInterval(this.timer)
  }
}
</script>

<style scoped lang="less">
.message-page{
  border: 1px solid transparent;
  margin-top: 14px;
  width: 595px;
  height: 200px;
  background: #FFFFFF;
  border-radius: 4px;
  overflow: hidden;
  /**/
  .msg-tit{
    padding: 6px 5px 5px 10px;
    .msg-top{
      display: flex;
    }
    .msg-line{
      width: 580px;
      height: 1px;
      background: #EFEFEF;
      margin: 8px 0;

    }
    .marquee-wrap  {
      /*width: 80%;*/
      height:150px;
      margin: 0 auto;
      overflow: hidden;
      .marquee-list {
        /*border: 1px solid red;*/
        li {
          .p-tit{
            font-size: 12px;
            font-family: PingFangSC-Regular, PingFang SC;
            color: #222222;
            height: 22px;
            line-height: 22px;
          }
          .p-word{
            margin-top: 8px;
            font-size: 14px;
            font-family: PingFangSC-Regular, PingFang SC;

            height: 16px;
            line-height: 18px;
            width: 767px;
            overflow: hidden;
            text-overflow: ellipsis;
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


}
</style>