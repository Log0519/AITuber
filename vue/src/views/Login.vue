<template>
  <div style="width: 100%;height: 100vh;overflow:hidden">
    <div class="login">
      <div class="content">
      <div style="color: #ed73f1;font-size: 30px;font-weight: bold;text-align: center;padding: 30px">
      AI数字人
      </div>
      <el-form ref="form" :model="form" size="normal" style="width: 60%;margin-left: 83px"
      :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="margin-left: 14px">
          <el-input  v-model ="form.password" show-password></el-input>
        </el-form-item>

        <el-form-item>
          <el-row style="padding-left: 65px">
            <el-col :span="11"
            ><el-input
                style="width: 100%"
                placeholder="请输入验证码"
                v-model="formLogin.code"
            ></el-input
            ></el-col>
            <el-col :span="10" style="padding-left: 15px">
              <div class="login-code" width="100%" @click="refreshCode">
                <!--验证码组件-->
                <dentify :identifyCode="identifyCode"></dentify></div
              ></el-col>
          </el-row>
        </el-form-item>

        <el-form-item style="margin-left: 80px">
          <el-button type="primary" size="small" @click="login">登 录</el-button>
          <el-button type="primary" size="small" @click="goRegister">注 册</el-button>
          <el-button type="primary" size="small" @click="go">跳过</el-button>
        </el-form-item>
      </el-form>


      </div>
    </div>
  </div>

</template>

<script>

import request from "../utils/request";
import dentify from "../components/dentify.vue";
import './css/login.css'

export default {
  name: "Login",
  components:{
    dentify
  },
  data(){
    return{
      formLogin: {
        code: "", //验证码输入框
      },
      identifyCodes: "1234567890abcdefjhijklinopqrsduvwxyz", //随机串内容,从这里随机抽几个显示验证码
      identifyCode: "", //验证码图片内容
      form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
      }
    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = "";
    //参数：（1）随机串内容。（2）验证码显示位数
    this.makeCode(this.identifyCodes, 4);
  },
  methods: {
    login() {
      if(this.identifyCode === this.formLogin.code)
      {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            request.post("user/login",this.form).then(res => {
              console.log(this.form.username)
              console.log(this.form.password)
              if (res.code === '0') {
                this.$message({
                  type: "success",
                  message: "登录成功"
                })
                sessionStorage.setItem("user", JSON.stringify(res.data))
                this.$router.push("/model") //登陆成功进行页面跳转
              } else {
                this.$message({
                  type: "error",
                  message: res.msg
                })
              }
            })
          }
        })
      }else {
        this.$message({
          type: "error",
          message: "验证码错误"
        })
      }


    }, goRegister() {
      this.$router.push("/register")
    },
    go() {
    this.$router.push("/mocap")
  },
    // 重置验证码
    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    //获取验证码的值
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        //通过循环获取字符串内随机几位
        this.identifyCode +=
            this.identifyCodes[this.randomNum(0, this.identifyCodes.length)];
      }
    },
    //随机数字：用于当角标拿字符串的值
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
  }
}

</script>

<style scoped>

</style>