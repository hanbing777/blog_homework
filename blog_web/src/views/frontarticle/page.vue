<template>
  <div>
    <div class="article-title">{{ article.title }}</div>
    <div class="article-tags">
      &nbsp;&nbsp;发布于{{
        article.created | dateFormat
      }}&nbsp;&nbsp;/&nbsp;&nbsp;{{
        article.categories
      }}&nbsp;&nbsp;/&nbsp;&nbsp;{{
        article.commentsNum
      }}条评论&nbsp;&nbsp;/&nbsp;&nbsp;{{ article.hits }}浏览
    </div>
    <mavon-editor
        v-if="zuJianWD"
        :editable="false"
        :value="zuJianWD"
        :subfield="false"
        :defaultOpen="'preview'"
        :toolbarsFlag="false"
        :scrollStyle="true"
        :ishljs="true"
        :navigation="true"
        @navigationToggle="onAddUrl"
        style="z-index: 10; width: 80%; margin-left: 10%; min-height: 200px"
    />
    <!-- 发布评论 -->
    <div
        style="
        background: #fff;
        width: 80%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 10px;
      "
    >
      <div
          style="
          background: #accbee;
          color: #fff;
          font-size: 20px;
          padding: 1%;
          width: 98%;
          margin-left: auto;
          margin-right: auto;
        "
      >
        发布评论
      </div>
      <el-form
          :inline="true"
          :model="ruleForm"
          class="demo-form-inline"
          :rules="rules"
          ref="ruleForm"
          style="margin: 10px; width: 78%; margin-left: auto; margin-right: auto"
      >
        <el-form-item label="用户名:" prop="author">
          <el-input
              v-model="ruleForm.author"
              placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="mail">
          <el-input
              v-model="ruleForm.mail"
              placeholder="请输入邮箱地址"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="ruleForm.url" placeholder="请输入网址"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              style="background-color: #6e8cd7; color: #fff"
              @click="submitForm('ruleForm')"
          >提交
          </el-button
          >
        </el-form-item>
        <br/>
        <el-form-item label="评论内容">
          <VueEmoji ref="emoji" @input="onInput" :value="ruleForm.content"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="clearTextarea">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 遍历评论 -->
    <div
        style="
        width: 80%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 10px;
        background: #fff;
      "
    >
      <el-card v-for="comment in comment" :key="comment.cid">
        <el-row>
          <el-col :span="2">
            <el-avatar
                :size="50"
                src="https://img2.baidu.com/it/u=3969710885,433730833&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            ></el-avatar
            >
          </el-col>
          <el-col :span="1"></el-col>
          <el-col
              :span="18"
              style="height: 50px; line-height: 50px; font-size: 15px"
          >{{ comment.author }}
          </el-col
          >
        </el-row>
        <div style="padding: 10px; font-size: 14px">{{ comment.content }}</div>
        <div style="padding: 10px">{{ comment.created | dateFormat }}</div>
      </el-card>
    </div>
  </div>
</template>

<script>
import VueEmoji from "emoji-vue";

export default {
  components: {
    VueEmoji,
  },
  data() {
    return {
      zuJianWD: "",
      // zuJianWD:
      //   "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n" +
      //   "```html <h1>123</h1> ```\n ### 12356\n```html\n<span>13246</span>\n```\n" +
      //   "```html <h1>123</h1> ```\n #### 12356\n```html\n<span>13246</span>\n```\n" +
      //   "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n" +
      //   "```html <h1>123</h1> ```\n ## 二二二\n```html\n<span>13246</span>\n```\n",
      article: {
        title: "",
        // content: "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n",
        content: "",
        type: "post", //内容类别(page:前台导航条显示页面，post:发布文章)
        tags: "", //标签列表(自己输入)
        categories: "默认分类", //分类列表（获取后台分类的值）
        allowComment: true, //boolean是否允许评论
        allowPing: "", //boolean是否允许ping
        allowFeed: "", //boolean允许出现在聚合中（订阅）
        //以下后端自动生成
        status: "", //内容状态(publish:发布,draft：草稿)
        hits: "1", //点击次数
        commentsNum: "1", //内容所属评论数
        slug: "", //内容缩略名
        authorId: "", //内容所属用户id
        created: 1496815277, //内容生成时间
        modified: "", //内容更改时间
      },
      content: "",
      ruleForm: {
        author: "",
        mail: "",
        url: "",
        content: "",
      },
      rules: {
        author: [
          {required: true, message: "请输入用户名", trigger: "blur"},
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        mail: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        content: [
          {required: true, message: "请输入评论内容", trigger: "blur"},
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
      comment: [
        // {
        //   author: "111",
        //   mail: "111",
        //   url: "111",
        //   content: "111",
        //   created: "2022-12-11",
        //   cid: 1,
        // },
      ],
    };
  },
  watch: {
    zuJianWD() {
      this.onAddUrl();
    },
  },
  mounted() {
    this.getArticlePublish(this.$route.params.cid);
    this.showComment(this.$route.params.cid);
    this.onAddUrl();
    console.log(this.$route.params);
  },
  methods: {
    onAddUrl() {
      this.$nextTick(function () {
        let _aList = document.querySelectorAll(".v-note-navigation-content a");
        for (let i = 0; i < _aList.length; i++) {
          let _aParent = _aList[i].parentNode;
          let _a = _aParent.firstChild;
          if (!_a.id) continue; // 把不属于导航中的a标签去掉，否则会报错
          let _text = _aParent.lastChild;
          let text = _text.textContent;
          _a.href = "#" + _a.id;
          _a.innerText = text;
          _aParent.removeChild(_text);
          console.log(_aList[i]);
        }
        // console.log(_aList);
      });
    },
    // 获取发布文章详情
    async getArticlePublish(aid) {
      console.log("aid", aid);
      const result = await this.$http.get(`/article/${aid}`);
      if (result.data.code == 200) {
        this.article = result.data.obj.contents;
        this.zuJianWD = result.data.obj.contents.content;
        this.comment = result.data.obj.comments;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    //发布评论
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        console.log(this.ruleForm);
        if (valid) {
          if (this.ruleForm.content == "") {
            return this.$message.error("请输入评论内容！");
          }
          this.ruleForm.cid = this.$route.params.cid;
          const result = await this.$http.post(`/addComments`, this.ruleForm);
          if (result.data.code == 200) {
            this.$message.success(result.data.messges);
            this.showComment(this.$route.params.cid);
            this.ruleForm = {
              author: "",
              mail: "",
              url: "",
              content: "",
            };
            this.$refs.emoji.clear();
          } else {

            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 展示评论
    async showComment(cid) {
      this.getArticlePublish(this.$route.params.cid);
      // console.log(cid);
    },
    onInput(event) {
      //event.data contains the value of the textarea
      console.log(event);
      this.ruleForm.content = event.data;
    },
    clearTextarea() {
      this.$refs.emoji.clear();
      this.ruleForm.content = "";
    },
  },
};
</script>

<style lang="less" scoped>
.article-title {
  width: 76%;
  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
  font-size: 20px;
  color: black;
  padding: 2%;
}

.article-tags {
  width: 79%;
  margin-left: auto;
  margin-right: auto;
  padding: 0.5%;
  color: black;
  background-color: #ffffff;
  font-size: 15px;
}

.emoji-vue-wraper emoji-wysiwyg-editor {
  max-height: 350px;
}
</style>