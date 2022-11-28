<template>

  <div style="margin-left: 250px;display: flex" >
    <div class="window1" >

    </div>
    <div class="window2" >
  <div style="margin: 30px">
      <div class="camera_outer">
    <video id="videoCamera" :width="videoWidth" :height="videoHeight" autoplay></video>
    <canvas style="display:none;" id="canvasCamera" :width="videoWidth" :height="videoHeight" ></canvas>
  </div>
  <div style="margin-top: 10px">
    <el-button type="primary" v-if="os" @click="openCamera" size="small">开始</el-button>
    <el-button type="primary" v-else @click="stopCamera"  size="small">停止</el-button>
    <el-button :disabled="os" type="primary" v-if="record" @click="beginRecord" size="small">录制</el-button>
    <el-button type="primary" v-else @click="stopRecord" size="small">停止</el-button>
  </div>
    </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "camera",
  data() {
    return {
      os: true,//控制摄像头开关
      record:true,
      thisVideo: null,
      thisContext: null,
      thisCancas: null,
      imgSrc: '',
      videoWidth: 500,
      videoHeight: 400,
    };
  },
  created() {
    this.openCamera();
  },
  methods: {
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
        this.os = false;//切换成关闭摄像头
        this.record=true;
        this.thisCancas = document.getElementById('canvasCamera');
        this.thisContext = this.thisCancas.getContext('2d');
        this.thisVideo = document.getElementById('videoCamera');
        // 旧版本浏览器可能根本不支持mediaDevices，我们首先设置一个空对象
        if (navigator.mediaDevices === undefined) {
          navigator.mediaDevices= {}
        }
        // 一些浏览器实现了部分mediaDevices，我们不能只分配一个对象
        // 使用getUserMedia，因为它会覆盖现有的属性。
        // 这里，如果缺少getUserMedia属性，就添加它。
        if (navigator.mediaDevices.getUserMedia === undefined) {
          navigator.mediaDevices.getUserMedia = function (constraints) {
            // 首先获取现存的getUserMedia(如果存在)
            let getUserMedia = navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia;
            // 有些浏览器不支持，会返回错误信息
            // 保持接口一致
            if (!getUserMedia) {
              return Promise.reject(new Error('getUserMedia is not implemented in this browser'))
            }
            // 否则，使用Promise将调用包装到旧的navigator.getUserMedia
            return new Promise(function (resolve, reject) {
              getUserMedia.call(navigator, constraints, resolve, reject)
            })
          }
        }
        const constraints = {
          audio: false,
          video: {width: _this.videoWidth, height: _this.videoHeight, transform: 'scaleX(-1)'}
        };
        navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
          // 旧的浏览器可能没有srcObject
          if ('srcObject' in _this.thisVideo) {
            _this.thisVideo.srcObject = stream
          } else {
            // 避免在新的浏览器中使用它，因为它正在被弃用。
            _this.thisVideo.src = window.URL.createObjectURL(stream)
          }
          _this.thisVideo.onloadedmetadata = function (e) {
            _this.thisVideo.play()
          }
        }).catch(err => {
          this.$message({
            message: '没有开启摄像头权限或浏览器版本不兼容',
            type: 'warning'
          });
        });
      });
    },
    //关闭摄像头
    stopCamera() {
      if (this.thisVideo && this.thisVideo !== null) {
        this.thisVideo.srcObject.getTracks()[0].stop();
        this.os = true;//切换成打开摄像头
        this.record=true;
      }
    },
  }
};
</script>q

<style lang="scss" scoped>
.window1{
  margin: 20px 0px 0px 80px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 560px;
  height: 500px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
//人物视角窗口window2
.window2{
  margin: 20px;
  background-color: rgba(255, 255, 255, 0.27);
  border-radius: 10px;
  border: 1px solid #aaa;
  width: 560px;
  height: 500px;
  box-shadow: inset 0px 0px 10px rgba(255, 255, 255, 0.5), 0px 0px 15px rgba(200, 75, 75, 0.3);
}
.camera_outer{
  position: relative;
  overflow: hidden;
  background-size: 100%;
  video,canvas,.tx_img{
    -moz-transform:scaleX(-1);
    -webkit-transform:scaleX(-1);
    -o-transform:scaleX(-1);
    transform:scaleX(-1);
  }
  .btn_camera{
    position: absolute;
    bottom: 4px;
    left: 0;
    right: 0;
    height: 50px;
    background-color: rgba(0,0,0,0.3);
    line-height: 50px;
    text-align: center;
    color: #ffffff;
  }
  .bg_r_img{
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    top: 0;
  }
  .img_bg_camera{
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    top: 0;
    img{
      width: 100%;
      height: 100%;
    }
    .img_btn_camera{
      position: absolute;
      bottom: 0;
      left: 0;
      right: 0;
      height: 50px;
      line-height: 50px;
      text-align: center;
      background-color: rgba(0,0,0,0.3);
      color: #ffffff;
      .loding_img{
        width: 50px;
        height: 50px;
      }
    }
  }
}
</style>
