<template>
  <div style="width: 100%;height: 100vh;overflow:hidden">
    <div class="register">
      <div class="register-content">
        <div >
          <div style="font-size: 30px;font-weight: bold;text-align: center;margin-top: 30px ;padding: 30px">
            欢迎注册

          </div>
          <el-form ref="form" :model="form" size="normal"
                   :rules="rules">
            <el-form-item  style="margin-top: 20px" prop="username">
              <el-input v-model="form.username" style="width: 260px" ></el-input>
              <label>用户名</label>
            </el-form-item>
            <el-form-item style="margin-top: 20px"  prop="password">
              <el-input  style="width: 260px" v-model ="form.password" show-password ></el-input>
              <lable style="color: aqua">密码</lable>
            </el-form-item>
            <el-form-item style="margin-top: 20px" prop="confirm">
              <el-input  style="width: 260px" v-model ="form.confirm" show-password ></el-input>
              <label>确认密码</label>
            </el-form-item >
            <el-form-item style="margin-left: 40px" class="button-container">
              <el-button type="primary" style="width: 80px" class="login-button" @click="back">返回</el-button>
              <el-button type="primary" class="login-button" @click="register">立即注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import request from "../utils/request";
import './css/login.css'
export default {
  name: "Register",
  components:{
  },
  data(){
    return{
      form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
        confirm:[
          {required:true,message:'请确认密码',trigger:'blur'},
        ],
      }
    }
  },
  methods:{
    register(){
      if(this.form.password!==this.form.confirm){
        this.$message({
          type:"error",
          message:"两次密码输入不一致！"
        })
        return
      }
      this.$refs['form'].validate((valid)=>{
        if(valid){
          request.post("/user/register",this.form).then(res=>{
            if(res.code==='0'){
              this.$message({
                type:"success",
                message:"注册成功"
              })
              this.$router.push("/login") //注册成功进行页面跳转
            }else {
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    },back(){
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>
</style>