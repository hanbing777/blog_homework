<template>
  <div class="login_container">
    <div class="loginForm_box">
      <div class="title_box"><span>Login In</span></div>
      <el-form
        class="login_form"
        :model="loginForm"
        :rules="loginFormRules"
        ref="loginFormRef"
      >
        <el-form-item prop="username">
          <el-input
            prefix-icon="iconfont icon-BasicIcons-all-"
            placeholder="请输入用户名"
            v-model="loginForm.username"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            prefix-icon="iconfont icon-suoding"
            placeholder="请输入密码"
            :type="pwdType"
            v-model="loginForm.password"
          >
          </el-input>
          <div class="eye" @click="isClosed">
            <i :class="eyes ? icon1 : icon2"></i>
          </div>
        </el-form-item>
        <el-form-item prop="code" style="width: 200px">
          <el-input
            prefix-icon="el-icon-postcard"
            placeholder="请输入验证码"
            v-model="loginForm.code"
          ></el-input>
        </el-form-item>
        <el-image
          style="
            width: 120px;
            height: 40px;
            margin-left: 210px;
            margin-top: -70px !important;
            position: absolute;
          "
          :src="captchUrl"
          fit="fit"
          @click="getcode()"
        ></el-image>
        <el-form-item class="form_button">
          <el-checkbox v-model="checked">记住我</el-checkbox>
          <el-button
            type="primary"
            round
            class="btn1"
            @click="submitForm('loginFormRef')"
            style="margin-left: 50px; background: #88d3ce; border: none"
            >登录<i class="el-icon-right"></i>
          </el-button>
        </el-form-item>
        <!-- <div class="sign_up" @click="jumpHome"><span>新用户注册</span></div> -->
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "login",
  inject: ["reload"],
  data() {
    return {
      captchUrl: "api/captcha?time=" + new Date(),
      checked: false,
      loginForm: {
        username: "",
        password: "",
        code: "",
        ip: "127.0.0.1",
      },
      pwdType: "password",
      eyes: true,
      icon1: "iconfont icon-hide",
      icon2: "iconfont icon-browse",
      loginFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          // { message: '长度最多不超过 12 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          // { max: 10, message: '长度最多不超过 10 个字符', trigger: 'blur' }
        ],
        code: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { max: 4, message: "长度最多不超过4个字符", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    this.getcode();
  },
  methods: {
    // 切换验证码
    async getcode() {
      this.captchUrl = "api/captcha?time=" + new Date();
    },
    //登录
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          console.log(this.loginForm);
          const result = await this.$http.post("/login", this.loginForm);
          console.log(result.data);
          if (result.data.code == 200) {
            this.$message.success(result.data.messges);
            localStorage.setItem("tokenHead", result.data.obj.token.tokenHead);
            localStorage.setItem("token", result.data.obj.token.token);
            const res = await this.$http.get("/admin/users/getUserInfo");
            console.log(res.data);
            localStorage.setItem("userdata", JSON.stringify(res.data.obj));
            console.log(
              "用户信息1",
              JSON.parse(localStorage.getItem("userdata"))
            );
            this.$router.push("/admin");
            this.loginForm = {
              username: "",
              password: "",
              code: "",
              ip: "127.0.0.1",
            };
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    jumpHome() {
      // this.$router.push("/register");
    },
    isClosed() {
      this.pwdType = this.pwdType === "password" ? "text" : "password";
      this.eyes = !this.eyes;
    },
  },
};
</script>

<style lang="less" scoped>
.login_container {
  height: 100%;
  // background: url('https://img1.baidu.com/it/u=1490431314,2325918058&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500') no-repeat;
  background-size: cover;
  //  background: linear-gradient(to bottom right,#f1f0f0, #6699CC, #b8b6b6);
  background: linear-gradient(to bottom right, #99cccc, #fff);
}

.loginForm_box {
  width: 450px;
  height: 360px;
  background-color: #fff;
  border-radius: 5px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.title_box {
  width: 100%;
  height: 85px;
  line-height: 85px;
  // background: green;
  text-align: center;
  > span {
    font: 400 40px "Times New Roman", Georgia, Serif;
    color: #88d3ce;
  }
}

.login_form {
  width: 100%;
  padding: 0 60px;
  box-sizing: border-box;
  position: absolute;
  bottom: 0;
  > .el-form-item {
    margin-bottom: 30px;
  }
}

.form_button {
  display: flex;
  // justify-content: center;
}

.sign_up {
  position: absolute;
  right: 17px;
  bottom: 11px;
  color: #909399;
  cursor: pointer;
}

.eye {
  position: absolute;
  bottom: -2px;
  right: 10px;
}
.select {
  width: 330px;
  margin-top: -10px;
  margin-bottom: 20px;
}
.btn1 {
  width: 100px;
}
.code {
  width: 200px;
  margin-top: -50px;
}
</style>
