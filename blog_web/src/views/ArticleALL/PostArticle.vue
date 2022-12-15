<template>
  <div>
    <el-card>
      <el-form
        ref="form"
        :model="form"
        label-width="80px"
        :rules="rules"
        :inline="true"
      >
        <el-form-item label="文章标题" style="width: 300px" prop="title">
          <el-input
            v-model="form.title"
            placeholder="请输入文章标题"
          ></el-input>
        </el-form-item>
        <el-form-item label="文章标签" style="width: 300px">
          <el-input v-model="form.tags" placeholder="请输入文章标签"></el-input>
        </el-form-item>
        <el-form-item label="文章分类">
          <el-select v-model="form.categories" placeholder="请选择分类">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
          <!-- <el-input v-model="form.categories" placeholder="请输入文章分类"></el-input> -->
        </el-form-item>
        <!-- 富文本编辑器 -->
        <el-form-item label="文章内容">
          <mavon-editor
            v-model="form.content"
            @imgAdd="$imgAdd"
            style="margin-top: 30px; width: 1200px"
            ref="md"
            :ishljs="true"
          />
        </el-form-item>
        <el-form-item>
          <span class="tool-title">开启评论</span
          ><el-switch v-model="form.allowComment"> </el-switch>
          <span class="tool-title">允许Ping</span
          ><el-switch v-model="form.allowPing"> </el-switch>
          <span class="tool-title">允许订阅</span
          ><el-switch v-model="form.allowFeed"> </el-switch>
          <el-button
            type="primary"
            @click="onSubmit()"
            style="margin-left: 300px"
            >提交</el-button
          >
          <el-button type="warning" @click="onSubmit1()">存为草稿</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import imgUpload from "../../router/upload";
import axios from "axios";
export default {
  data() {
    return {
      options: [
        {
          value: "默认分类",
          label: "默认分类",
        },
      ],
      form: {
        title: "",
        content: "",
        type: "post", //内容类别(page:前台导航条显示页面，post:发布文章)
        tags: "", //标签列表(自己输入)
        categories: "", //分类列表（获取后台分类的值）
        allowComment: true, //boolean是否允许评论
        allowPing: "", //boolean是否允许ping
        allowFeed: "", //boolean允许出现在聚合中（订阅）
        //以下后端自动生成
        status: "", //内容状态(publish:发布,draft：草稿)
        hits: "", //点击次数
        commentsNum: "", //内容所属评论数
        slug: "", //内容缩略名
        authorId: "", //内容所属用户id
        created: "", //内容生成时间
        modified: "", //内容更改时间
      },
      rules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.GetCategory();
  },
  methods: {
    async GetCategory() {
      const result = await this.$http.get(
        `/admin/metas/getMetasByType/category`
      );
      if (result.data.code == 200) {
        console.log(result.data.obj);
        var categories = result.data.obj;

        var i = 0;
        for (i; i < categories.length; i++) {
          console.log(categories[i].name);
          var category = {
            value: categories[i].name,
            label: categories[i].name,
          };
          this.options.push(category);
        }
      } else {
        this.$message.error("未获取分类信息");
      }
    },
    onSubmit() {
      this.$refs.form.validate(async (valid) => {
        this.form.status = "publish";
        if (!valid) return this.$message.error("表单验证失败！");
        console.log(this.form);
        const result = await this.$http.post(
          "/admin/contents/addContents",
          this.form
        );
        console.warn(result);
        if (result.data.code === 200) {
          this.$message.success("文章发布成功！");
          this.$router.push("/articledata");
          this.form = {
            title: "",
            content: "",
            type: "post",
            tags: "",
            categories: "",
            allowComment: true,
            allowPing: true,
            allowFeed: true,
            status: "",
            hits: "",
            commentsNum: "",
            slug: "",
            authorId: "",
            created: "",
            modified: "",
          };
        } else {
          this.$message.error(result.data.messges);
        }
      });
    },
    // 保存草稿
    onSubmit1() {
      this.$refs.form.validate(async (valid) => {
        this.form.status = "draft";
        if (!valid) return this.$message.error("表单验证失败！");
        console.log(this.form);
        const result = await this.$http.post(
          "/admin/contents/addContents",
          this.form
        );
        console.warn(result);
        if (result.data.code === 200) {
          this.$message.success("文章发布成功！");
          this.$router.push("/articledata");
          this.form = {
            title: "",
            content: "",
            type: "post",
            tags: "",
            categories: "",
            allowComment: true,
            allowPing: true,
            allowFeed: true,
            status: "",
            hits: "",
            commentsNum: "",
            slug: "",
            authorId: "",
            created: "",
            modified: "",
          };
        } else {
          this.$message.error(result.data.messges);
        }
      });
    },
    // 封装上传文件请求
    doupload() {
      return axios.defaults.baseURL + "/file/upload";
    },
    // 上传封面图片成功回调函数
    handleAvatarSuccess(data) {
      console.log("上传图片成功", data.obj);
      this.form.mainImg = data.obj;
    },
    // 上传封面文章图片之前的回调函数
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 5;
      console.log(file);
      // 实例化一个阅读器对象
      var reader = new FileReader();
      reader.readAsDataURL(file);
      var that = this;
      reader.onload = function () {
        //回显给上方图片中
        that.mainImg = this.result;
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
    // 上传markdown图片路径调用函数
    imgUpload(url, params) {
      return axios({
        method: "post",
        headers: {
          "Content-Type": "multipart/form-data",
        },
        url: `${url}`,
        data: params,
      });
    },
    $imgAdd(pos, $file) {
      // 第一步.将图片上传到服务器.
      var formdata = new FormData();
      formdata.append("file", $file);
      this.imgUpload("/admin/attach/upFile", formdata).then((res) => {
        if (res.data.code == 200) {
          console.log("上传图片", res);
          this.$nextTick(function () {
            console.log(res.data.obj.url);
            this.$refs.md.$img2Url(pos, res.data.obj.url);
          });
        }
      });
    },
  },
};
</script>

<style lang="less" scoped>
.card {
  height: 87px;
  background-image: radial-gradient(
    circle 248px at center,
    #16d9e3 0%,
    #30c7ec 47%,
    #46aef7 100%
  );
  line-height: 87px;
  color: #fff;
  font-size: 29px;
  letter-spacing: -1px;
  font-weight: 600;
}
.avatar-uploader-icon {
  border: 1px dashed #d9d9d9 !important;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9 !important;
  border-radius: 6px !important;
  position: relative !important;
  overflow: hidden !important;
}
.avatar-uploader .el-upload:hover {
  border: 1px dashed #d9d9d9 !important;
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 300px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 300px;
  height: 178px;
  display: block;
}
.tool-title {
  font-size: 15px;
  padding: 10px;
  margin: 10px;
}
</style>