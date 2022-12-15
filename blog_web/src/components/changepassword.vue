<template>
    <div>
        <el-card class="box_card">
            <div slot="header" class="clearfix">
                <span>重置密码</span>
            </div>
            <el-form
        class="login_form"
        :model="loginForm"
        :rules="loginFormRules"
        ref="loginFormRef"
        style="width: 300px"
      >
        <el-form-item prop="password">
          <el-input
            prefix-icon="iconfont icon-suoding"
            placeholder="请输入新密码"
            :type="pwdType"
            v-model="loginForm.password"
          >
          </el-input>
          <div class="eye" @click="isClosed">
            <i :class="eyes ? icon1 : icon2"></i>
          </div>
        </el-form-item>
        <el-form-item prop="subpassword">
          <el-input
            prefix-icon="iconfont icon-suoding"
            placeholder="请输入确认密码"
            :type="pwdType"
            v-model="loginForm.subpassword"
          >
          </el-input>
          <div class="eye" @click="isClosed">
            <i :class="eyes ? icon1 : icon2"></i>
          </div>
        </el-form-item>
        <el-form-item class="form_button">
          <el-button type="primary" round class="btn1" @click="login"
            >提交<i class="el-icon-right"></i>
          </el-button>
        </el-form-item>
      </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
  name: "changepassord",
  inject: ["reload"],
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.loginForm.password !== "") {
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.loginForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      codeurl: "http://101.43.104.20:8182/captcha",
      loginForm: {
        subpassword: "",
        password: "",
      },
      pwdType: "password",
      eyes: true,
      icon1: "iconfont icon-hide",
      icon2: "iconfont icon-browse",
      loginFormRules: {
        password: [{ validator: validatePass, trigger: "blur" }],
        subpassword: [{ validator: validatePass2, trigger: "blur" }],
      },
    };
  },
  mounted() {},
  methods: {
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return this.$message.error("表单验证失败！");
        console.log(this.loginForm);
        this.loginForm.id = JSON.parse(localStorage.getItem('userdata')).id
        const result = await this.$http.post(
          "/user/pwdModify",
          this.loginForm
        );
        console.log("login", result);
        if (result.data.code === 200) {
          this.$message.success("修改成功");
          localStorage.removeItem("userdata")
          this.$router.push("/login");
        } else {
          this.$message.error(result.data.message);
          location.reload();
        }
      });
    },
    isClosed() {
      this.pwdType = this.pwdType === "password" ? "text" : "password";
      this.eyes = !this.eyes;
    },
  },
};
</script>


<style lang="less" scoped>
.box_card {
    width: 600px;
}

.psdForm {
    padding-right: 30px;
}

.resetBtn {
    margin-left: 30px;
}
.password{
  margin-left:10px;
}
.eye {
  margin-left: 320px;
  margin-top: -40px;
}
</style>
