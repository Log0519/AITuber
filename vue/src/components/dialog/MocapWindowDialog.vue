<template>
  <div class="m-dialog-mask" style="overflow:hidden;" >
    <div class="m-dialog" :style="`width: ${dialogWidth}; height: ${dialogHeight};`">
      <div class="m-dialog-content">

        <svg @click="onClose" class="u-close" viewBox="64 64 896 896" data-icon="close" aria-hidden="true" focusable="false"><path d="M563.8 512l262.5-312.9c4.4-5.2.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 824.9A7.95 7.95 0 0 0 203 838h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z"></path></svg>
        <div class="m-dialog-header">
        </div>
<!--        商家-->


           <embed src="src/components/live2d/index.html" width=650px height=435px />

      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Dialog1',
  props: {
    title: { // 标题
      type: String,
      default: '提示'
    },
    content: { // 内容
      type: String,
      default: ''
    },
    width: { // 宽度，默认640
      type: Number,
      default: 640
    },
    height: { // 高度，默认480
      type: Number,
      default: 480
    },
    switchFullscreen: { // 是否允许切换全屏（允许后右上角会出现一个按钮）
      type: Boolean,
      default: false
    },
    cancelText: { // 取消按钮文字
      type: String,
      default: '取消'
    },
    okText: { // 确认按钮文字
      type: String,
      default: '确定'
    },
    footer: { // 是否显示底部按钮，默认显示
      type: Boolean,
      default: true
    }
  },
  data () {
    return {
      form: {
        homename:'',
        homeId:'',
        name: '',
        pace:'',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      value1:false,
      value2:true,
      fullScreen: false,
    }
  },
  computed: {
    dialogWidth () {
      if (this.fullScreen) {
        return '100%'
      } else {
        return this.width + 'px'
      }
    },
    dialogHeight () {
      if (this.fullScreen) {
        return '100vh'
      } else {
        return this.height + 'px'
      }
    }
  },
  methods: {
    onFullScreen () {
      this.fullScreen = !this.fullScreen
    },
    onClose () {
      this.fullScreen = false
      this.$emit('close')
    },
    onCancel () {
      this.$emit('cancel')
    },
    onConfirm () {
      this.$emit('ok')

    }
  }
}
</script>
<style lang="less" scoped>

.m-dialog-mask {
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 600px;
  height: 500px;
  z-index: 10000;
  background: rgba(0,0,0,0);
  .m-dialog {
    position: relative;
    top: 40%;
    transform: translateY(-50%);
    -ms-transform: translateY(-50%);; /* IE 9 */
    -webkit-transform: translateY(-50%); /* Safari and Chrome */
    margin: 0 auto;
    transition: all .3s ease;
    .m-dialog-content {
      position: relative;
      background: rgba(24, 66, 108, 0);
      border-radius: 4px;
      box-shadow: 0 4px 12px rgba(211, 158, 158, 0.1);
      .u-screen {
        .u-close();
        right: 64px;
      }
      .u-close {
        width: 16px;
        height: 16px;
        position: absolute;
        top: 19px;
        right: 24px;
        fill: rgba(0,0,0,.45);
        cursor: pointer;
        transition: fill .3s;
        &:hover {
          fill: rgba(0,0,0,.75);
        }
      }
      .m-dialog-header {
        padding: 16px 24px;
        color: rgba(0,0,0,.65);
        .u-head {
          margin: 0;
          color: rgba(0,0,0,.85);
          font-weight: 500;
          font-size: 18px;
          line-height: 22px;
          word-wrap: break-word;
        }
      }
      .m-dialog-body {
        padding: 24px;
        font-size: 18px;
        line-height: 1.5;
        word-wrap: break-word;
        overflow: auto;
        transition: all .3s;
      }
      .m-dialog-footer {
        padding: 10px 16px;
        text-align: right;
        border-top: 1px solid #e8e8e8;
        .u-cancel {
          height: 32px;
          line-height: 32px;
          padding: 0 15px;
          font-size: 18px;
          border-radius: 4px;
          color: rgba(0,0,0,.65);
          background: #fff;
          border: 1px solid #d9d9d9;
          cursor: pointer;
          transition: all .3s cubic-bezier(.645,.045,.355,1);
          &:hover {
            color: #40a9ff;
            border-color: #40a9ff;
          }
          &:focus {
            color: #096dd9;
            border-color: #096dd9;
          }
        }
        .u-confirm {
          margin-left: 8px;
          height: 32px;
          line-height: 32px;
          padding: 0 15px;
          font-size: 18px;
          border-radius: 4px;
          background: #1890ff;
          border: 1px solid #1890ff;
          color: #fff;
          transition: all .3s cubic-bezier(.645,.045,.355,1);
          cursor: pointer;
          &:hover {
            color: #fff;
            background: #40a9ff;
            border-color: #40a9ff;
          }
          &:focus {
            background: #096dd9;
            border-color: #096dd9;
          }
        }
      }
    }
  }
}
</style>