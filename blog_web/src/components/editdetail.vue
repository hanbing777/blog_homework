<template>
  <div>
    <el-card class="box_card">
      <div slot="header" class="clearfix" style="text-align:left;">
        <span>修改个人资料</span>
      </div>
      <!-- :data="{ userId: user.eid, status: userStatus }" -->
      <el-upload
        class="avatar-uploader"
        :action="doupload()"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
        style="display: inline-block; width: 200px; margin-left: 0px"
      >
        <!-- 上传成功后展示的图片 -->
        <img v-if="loginForm.avatar" :src="loginForm.avatar" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
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
        <el-form-item prop="nickname">
          <el-input
            prefix-icon="iconfont icon-BasicIcons-all-"
            placeholder="请输入用户名"
            v-model="loginForm.nickname"
          ></el-input>
        </el-form-item>
        <el-form-item prop="phone">
          <el-input
            prefix-icon="el-icon-phone-outline"
            placeholder="请输入手机号"
            v-model="loginForm.phone"
          ></el-input>
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
import axios from "axios";
export default {
  mounted(){
    this.loginForm = JSON.parse(localStorage.getItem('userdata'))
  },
  data() {
    return {
      imageUrl:
        "https://img1.baidu.com/it/u=2820222784,2850106904&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1665075600&t=57bf9c1de8fcdd94ce92c892682a1fe3",
      loginForm: {
        username: "",
        nickname:"",
        phone: "",
        avatar:""
      },
      loginFormRules: {
        username: [
          { required: true, message: "请输入账号", trigger: "blur" },
          // { message: '长度最多不超过 12 个字符', trigger: 'blur' }
        ],
        nickname: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请重新输入手机号码", trigger: "blur" },
          {
            pattern:
              /^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\d{8}$/,
            message: "请输入有效的手机号码",
          },
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
        const result = await this.$http.put(
          "/user/infoModify",
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
          this.$message.error(result.data.message);
          // location.reload();
        }
        // const res = await this.$http.get('login', { params: this.loginForm })
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 封装上传文件请求
    doupload() {
      return axios.defaults.baseURL + "/file/upload";
    },
    // 成功上传之后的回调函数
    handleAvatarSuccess(res,) {
      this.loginForm.avatar = res.obj
      console.log(res);
    },
    beforeAvatarUpload(file) {
      // const isJPG = file.type == "image/png";
      const isLt2M = file.size / 1024 / 1024 < 5;
      console.log(file);
      // 实例化一个阅读器对象
      var reader = new FileReader();
      // 读取文件的路径，没有返回值,结果在reader.result里
      reader.readAsDataURL(file);
      // 读取需要时间，读完后再修改图片路径
      var that = this;
      reader.onload = function () {
        //回显给上方图片中
        that.loginForm.avatar = this.result;
      };
      if (
        file.type == "image/png" ||
        file.type == "image/jpg" ||
        file.type == "image/jpeg" ||
        file.type == "image/JPG"
      ) {
        // this.$message.error("上传头像图片格式不正确");
      } else {
        this.$message.error("上传头像图片格式不正确");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 5MB!");
      }
      return isLt2M;
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
