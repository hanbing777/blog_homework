<template>
  <div style="display: flex">
    <div>
      <el-card shadow="hover">
        <div
          style="
            background: #33b86c;
            color: #fff;
            font-size: 20px;
            padding: 10px;
            width: 500px; ;
          "
        >
          全局设置
        </div>
        <el-form
          :model="ruleForm"
          class="demo-form-inline"
          :rules="rules"
          ref="ruleForm"
          style="margin: 10px; width: 350px"
          label-width="120px"
        >
          <el-form-item label="站点名称:">
            <el-input
              v-model="ruleForm.siteTitle"
              placeholder="请输入站点名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="站点描述:">
            <el-input
              v-model="ruleForm.siteDescription"
              placeholder="请输入站点描述"
            ></el-input>
          </el-form-item>
          <el-form-item label="站点关键词">
            <el-input
              v-model="ruleForm.siteKeywords"
              placeholder="请输入站点关键词"
              maxlength="30"
              show-word-limit
            ></el-input>
          </el-form-item>
          <el-form-item label="博客主题:">
            <el-select v-model="ruleForm.siteTheme" placeholder="请选择分类">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              style="background-color: #33b86c; color: #fff"
              @click="submitForm('ruleForm')"
              >保存设置</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div style="margin-left: 50px">
      <el-card shadow="hover">
        <div
          style="
            background: #212121;
            color: #fff;
            font-size: 20px;
            padding: 10px;
            width: 500px;
          "
        >
          个性化设置
        </div>
        <el-form
          :model="ruleForm1"
          class="demo-form-inline"
          :rules="rules"
          ref="ruleForm1"
          style="margin: 10px; width: 350px"
          label-width="120px"
        >
          <el-form-item label="微博账号:">
            <el-input
              v-model="ruleForm1.social_weibo"
              placeholder="请输入微博账号"
            ></el-input>
          </el-form-item>
          <el-form-item label="知乎账号:">
            <el-input
              v-model="ruleForm1.social_zhihu"
              placeholder="请输入知乎账号"
            ></el-input>
          </el-form-item>
          <el-form-item label="Github账号:">
            <el-input
              v-model="ruleForm1.social_github"
              placeholder="请输入Github账号"
            ></el-input>
          </el-form-item>
          <el-form-item label="Twitter账号:">
            <el-input
              v-model="ruleForm1.social_twitter"
              placeholder="请输入Twitter账号"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              style="background-color: #212121; color: #fff"
              @click="submitForm1('ruleForm1')"
              >保存设置</el-button
            >
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        siteTitle: "",
        siteDescription: "",
        siteKeywords: "",
        siteTheme: "默认主题",
      },
      ruleForm1: {
        social_github: "",
        social_twitter: "",
        social_weibo: "",
        social_zhihu: "",
      },
      options: [
        {
          value: "默认主题",
          label: "默认主题",
        },
      ],
      rules: {
        name: [
          { required: true, message: "请输入链接标题", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        tags: [
          { required: true, message: "请输入标签名称", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    // 获取信息
    async getData() {
      const result = await this.$http.get(
        `/admin/options/getAll`
      );
      if (result.data.code == 200) {
        this.ruleForm = result.data.obj;
        this. ruleForm1 = result.data.obj;
      } else {
        this.$$message.error(result.data.messges);
      }
    },
    // 全局设置
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert(this.ruleForm);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 个性化设置
    submitForm1(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert(this.ruleForm1);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
</style>