<template>
  <div>
    <el-card class="box_card">
      <div slot="header" class="clearfix" style="text-align: left">
        <span style="color: #6e8cd7; font-size: 20px">修改个人资料</span>
      </div>
      <!-- 表单 -->
      <el-form
        class="login_form"
        :model="loginForm"
        :rules="loginFormRules"
        ref="loginFormRef"
        style="width: 400px"
      >
        <el-form-item prop="username">
          <el-input
            prefix-icon="iconfont icon-BasicIcons-all-"
            placeholder="请输入账号"
            v-model="loginForm.username"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item prop="screen_name">
          <el-input
            prefix-icon="iconfont icon-BasicIcons-all-"
            placeholder="请输入用户名"
            v-model="loginForm.screen_name"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
            prefix-icon="el-icon-message"
            placeholder="请输入邮箱"
            v-model="loginForm.email"
          ></el-input>
        </el-form-item>
        <el-form-item class="form_button">
          <el-button
            style="background: #6e8cd7; color: #fff; margin-left: 50px"
            round
            class="btn1"
            @click="login('loginFormRef')"
            >提交<i class="el-icon-right"></i>
          </el-button>
          <el-button
            @click="resetForm('loginFormRef')"
            style="margin-left: 50px"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  mounted() {
    console.log(JSON.parse(localStorage.getItem("userdata")))
    this.loginForm = JSON.parse(localStorage.getItem("userdata"));
  },
  data() {
    return {
      loginForm: {
        username: "12",
        screen_name: "",
        email: "",
      },
      loginFormRules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          // { message: '长度最多不超过 12 个字符', trigger: 'blur' }
        ],
        screen_name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请重新输入邮箱地址", trigger: "blur" },
          { type: "email", message: "请输入正确的邮箱地址", trigger: ["blur"] },
          // { max: 10, message: '长度最多不超过 10 个字符', trigger: 'blur' }
        ],
      },
    };
  },
  methods: {
    login() {
      this.$refs.loginFormRef.validate(async (valid) => {
        if (!valid) return this.$message.error("表单验证失败！");
        console.log(this.loginForm);
        var user = this.loginForm;
        console.log(user);
        const result = await this.$http.post(
          "/admin/users/updateUserNameAndEmail",
          this.loginForm
        );
        console.log("login", result);
        if (result.data.code === 200) {
          this.$message.success("修改成功");
          localStorage.setItem("userdata", JSON.stringify(result.data.obj));
          console.log(
            "localstorage",
            JSON.parse(localStorage.getItem("userdata"))
          );
        } else {
          this.$message.error(result.data.messges);
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style lang="less" scoped>
.box_card {
  width: 550px;
}

.psdForm {
  padding-right: 30px;
}

.resetBtn {
  margin-left: 30px;
}
.btn1 {
  width: 150px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 100px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
// .avatar-uploader .el-upload:hover {
// //   border-color: #409eff;
// }
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
  border-radius: 100px;
}
</style>
