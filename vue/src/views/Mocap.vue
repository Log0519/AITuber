<template>
  <div>
  <div class="camera_outer">
    <video id="videoCamera" :width="videoWidth" :height="videoHeight" autoplay></video>
    <canvas style="display:none;" id="canvasCamera" :width="videoWidth" :height="videoHeight" ></canvas>
  </div>
  <div>
    <el-button v-if="os" @click="openCamera" size="small">开始</el-button>
    <el-button v-else @click="stopCamera"  size="small">停止</el-button>
  </div>
  </div>
</template>
<script>
export default {
  name: "camera",
  data() {
    return {
      os: false,//控制摄像头开关
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
    // 调用摄像头权限
    openCamera() {
      //必须在model中render后才可获取到dom节点,直接获取无法获取到model中的dom节点
      this.$nextTick(() => {
        const _this = this;
        this.os = false;//切换成关闭摄像头
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
      }
    },
  }
};
</script>


<style lang="scss" scoped>
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

<!--  <body>-->
<!--  &lt;!&ndash; virtual 3d model show here &ndash;&gt;-->
<!--  <div-->
<!--      id="model"-->
<!--      style="-->
<!--                width: 48%;-->
<!--                border: solid 1px #ccc;-->
<!--                border-radius: 10px;-->
<!--                height: fit-content;-->
<!--                top: 10px;-->
<!--                left: 10px;-->
<!--                overflow: hidden;-->
<!--                position: fixed;-->
<!--            "-->
<!--  ></div>-->

<!--  <div id="status" style="position: absolute; bottom: 140px">-->
<!--            <span style="position: absolute; left: 10px; font-size: 12px"-->
<!--            >Render</span-->
<!--            >-->
<!--    <span style="position: absolute; left: 100px; font-size: 12px"-->
<!--    >Mocap</span-->
<!--    >-->
<!--  </div>-->

<!--  <div-->
<!--      id="controller"-->
<!--      style="position: absolute; bottom: 95px; right: 20px"-->
<!--  >-->
<!--    <div-->
<!--        style="-->
<!--                    display: inline-block;-->
<!--                    margin-left: -85px;-->
<!--                    color: #777;-->
<!--                    position: absolute;-->
<!--                "-->
<!--    >-->
<!--      <i-->
<!--          class="mdui-icon material-icons"-->
<!--          style="transform: scale(0.8)"-->
<!--      >videocam</i-->
<!--      ><span style="font-size: 12px; margin-left: 5px">相机视角</span>-->
<!--    </div>-->
<!--    <div-->
<!--        class="targetButton mdui-ripple"-->
<!--        v-bind:class="{'mdui-color-theme':target == 'full'}"-->
<!--        onclick="changeTarget('full')"-->
<!--    >-->
<!--      <div class="my-icon">-->
<!--        <i-->
<!--            class="mdui-icon material-icons"-->
<!--            style="transform: scale(0.8); margin-top: -3px"-->
<!--        >accessibility</i-->
<!--        >-->
<!--      </div>-->
<!--      <span>全身</span>-->
<!--    </div>-->
<!--    <div-->
<!--        class="targetButton mdui-ripple"-->
<!--        v-bind:class="{'mdui-color-theme':target == 'half'}"-->
<!--        onclick="changeTarget('half')"-->
<!--    >-->
<!--      <div class="my-icon">-->
<!--        <i-->
<!--            class="mdui-icon material-icons"-->
<!--            style="transform: scale(1.2); margin-top: 3px"-->
<!--        >accessibility</i-->
<!--        >-->
<!--      </div>-->
<!--      <span>半身</span>-->
<!--    </div>-->
<!--    <div-->
<!--        class="targetButton mdui-ripple"-->
<!--        v-bind:class="{'mdui-color-theme':target == 'face'}"-->
<!--        onclick="changeTarget('face')"-->
<!--    >-->
<!--      <div class="my-icon">-->
<!--        <i-->
<!--            class="mdui-icon material-icons"-->
<!--            style="transform: scale(1.2)"-->
<!--        >person</i-->
<!--        >-->
<!--      </div>-->
<!--      <span>特写</span>-->
<!--    </div>-->
<!--  </div>-->

<!--  <div-->
<!--      style="-->
<!--                position: fixed;-->
<!--                left: calc(50vw - 100px);-->
<!--                bottom: 80px;-->
<!--                width: 200px;-->
<!--                height: 40px;-->
<!--                border-radius: 20px;-->
<!--                background-color: #fffe;-->
<!--            "-->
<!--      class="mdui-shadow-5"-->
<!--      id="loading"-->
<!--  >-->
<!--    <div-->
<!--        class="mdui-spinner mdui-spinner-colorful"-->
<!--        style="margin: 10px 15px; width: 20px; height: 20px"-->
<!--    ></div>-->
<!--    <span-->
<!--        style="-->
<!--                    line-height: 40px;-->
<!--                    position: fixed;-->
<!--                    color: #555;-->
<!--                    font-size: 14px;-->
<!--                    margin-left: -3px;-->
<!--                "-->
<!--    >初始化动作捕捉引擎…</span-->
<!--    >-->
<!--  </div>-->

<!--  <div-->
<!--      id="recording"-->
<!--      style="-->
<!--                position: fixed;-->
<!--                bottom: 20px;-->
<!--                height: 40px;-->
<!--                width: 200px;-->
<!--                background-color: #a40601;-->
<!--                left: calc(50% - 100px);-->
<!--                border-radius: 20px;-->
<!--                text-align: center;-->
<!--                line-height: 40px;-->
<!--                display: none;-->
<!--                color: #fff;-->
<!--            "-->
<!--  >-->
<!--    正在录制，按R键停止-->
<!--  </div>-->

<!--  <canvas-->
<!--      id="background-canvas"-->
<!--      style="position: absolute; top: -99999999px; left: -9999999999px"-->
<!--  ></canvas>-->
<!--  </body>-->
