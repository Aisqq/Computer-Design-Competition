<template>
  <div class="login" >
    <div class="overlay"></div>

    <div :style="style1" class="title" >
      <img  src="../../image/logo.png"  class="logo" alt="logo"/>
    </div>

    <div :style="style2" class="bigbox" alt="bigbox">

      <div class="login-form">

        <!--loginform是表单的数据 获取表单输入的信息
         prop对提交数据，必须与字段标识一致
        :rules表单验证规则
         ref获取form组件-->

        <div  class="loginbox">

          <el-form :model="loginForm" status-icon :rules="rules" ref="ruleForm" label-width="60px" label-suffix="" class="item">

            <el-form-item label="用户名"  prop="username" style="padding:1vh">
              <el-input  type="text" v-model="loginForm.userName" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item class="passwordbox"label="密码" prop="password" style="padding:0.5vh">
              <el-input type="password" v-model="loginForm.userPwd" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button @click="submitForm('ruleForm')">登录</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>

          </el-form>
        </div>
      </div>


    </div>


  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  data() {//只要输入就通过
    var validateUser = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }

    };

    return {
      loginForm: {   //表单存储对象
        userName: '',
        userPwd: ''
      },
      rules: {  //验证规则
        userName: [
          { validator: validateUser, trigger: 'blur' }
        ],
        userPwd: [
          { validator: validatePass, trigger: 'blur' }
        ]
      },

      style1:{
        position: 'absolute',
        zIndex: 2,
      },
      style2:{
        position: 'absolute',
        zIndex: 1,
      }

    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log('submit!');
          request.post('/user/login',this.loginForm).then(res=> {
            if(res.code === 'success') {
              this.$notify.success("登录成功");
              this.$router.push('/back/map');
            }else{
              this.$message.error(res.msg);
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>


<style>
.login{
  background: url("../../image/background.png");
  background-size: cover; /* 背景图片覆盖整个元素 */
  background-position: center; /* 背景图片居中 */
  height: 100vh; /* 高度占满整个视口高度 */
  width: 100vw; /* 宽度占满整个视口宽度 */
  position: fixed; /* 定位为固定，这样背景就会固定在视口中 */
  top: 0; /* 顶部对齐 */
  left: 0; /* 左边对齐 */
}
.overlay{
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.1);
}

.el-form-item label{
  color:black;
  font-weight:bold;
}

.logo{
  margin-top: 190px;
  padding-left:45vw;
  width: 10%;
  height: 10%;
}


.bigbox{
  padding: 10vh;
  width: 300px;
  left:700px;
  top: 250px;
  opacity: 1;
  background: rgba(66,153, 181, 0.8);
  border: 3px solid rgba(50, 185, 196, 1);
}


</style>
