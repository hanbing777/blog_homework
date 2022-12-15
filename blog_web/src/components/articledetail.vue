<template>
  <div style="background-color: #efefef; text-align: left">
    <!-- 背景图片 -->
    <div class="background-image">
      <!-- 文章内容 -->
       <el-card
          style="
            width: 90%;
            min-height: 400px;
            height: auto !important;
            margin-bottom: 20px;
          "
        >
        <div>
          <el-page-header @back="goBack" :content="myBlog.title"> </el-page-header></div>
        <div style="height: 20px"></div>
        <div class="contentBox" v-html="myBlog.content"></div>
        <!-- 评论内容 -->
        <el-card
          style="
            width: 80%;
            min-height: 200px;
            height: auto !important;
            margin-left: 10%;
            margin-top: 2vh;
            margin-bottom: 20px;
          "
        >
          <div style="font-size: 25px; font-weight: 500">
            <i class="el-icon-chat-line-round" style="color: #4facfe"></i
            >&nbsp;&nbsp;评论
          </div>
          <!-- emoj -->
          <!-- 评论窗口 -->
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            class="demo-ruleForm"
            style="margin-top: 1vh"
          >
            <el-form-item label="" prop="desc">
              <el-input
                :rows="4"
                type="textarea"
                v-model="ruleForm.content"
                placeholder="留下你的评论吧！"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >提交</el-button
              >
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
          </el-form>
          <!-- 评论的展示 -->
          <div style="margin-left: 5vh; margin-right: 10vh">
            <div
              v-for="(comment, index) in comment"
              :key="index"
              style="border-bottom: #d9d9d9 solid 1px"
            >
              <div style="display: flex">
                <el-avatar :size="45" :src="comment.user.avatar"></el-avatar>
                <span class="comment-username">{{
                  comment.user.nickname
                }}</span>
                <span
                  style="
                    color: #409eff;
                    padding-top: 15px;
                    margin-left: 10px;
                    font-size: 10px;
                  "
                  @click="deletecommentone(comment)"
                  >删除</span
                >
                <span class="replay" @click="replycommentone(comment)"
                  >回复</span
                >
              </div>
              <div class="comment-content">{{ comment.content }}</div>
              <!-- 对评论的回复 -->
              <div
                v-for="(recomment, item) in comment.recomment"
                :key="item"
                style="
                  margin-left: 10vh;
                  margin-top: 2vh;
                  border-top: #d9d9d9 solid 1px;
                "
              >
                <!-- 评论回复的内容 -->
                <div style="display: flex; margin-top: 1vh">
                  <el-avatar
                    :size="45"
                    :src="recomment.replyUser.avatar"
                  ></el-avatar>
                  <span class="comment-username">{{
                    recomment.replyUser.nickname
                  }}</span>
                  <span
                    @click="replycommenttwo(comment)"
                    style="
                      color: red;
                      margin-left: 2vh;
                      margin-top: 15px;
                      font-size: 10px;
                    "
                    >回复</span
                  >
                  <span
                    style="
                      color: #409eff;
                      padding-top: 15px;
                      margin-left: 10px;
                      font-size: 10px;
                    "
                    @click="deletecomment(recomment)"
                    >删除</span
                  >
                </div>
                <div class="comment-content recomment">
                  {{ recomment.content }}
                </div>
              </div>
            </div>
          </div>
          <!--  -->
        </el-card>

        <!-- 对评论的回复 -->
        <el-dialog title="回复" :visible.sync="dialogFormVisible">
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            class="demo-ruleForm"
            style="margin-top: 1vh"
          >
            <el-form-item label="" prop="desc">
              <el-input
                type="textarea"
                v-model="form1.desc"
                placeholder="留下你的评论吧！"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('ruleForm')">取 消</el-button>
            <el-button type="primary" @click="submitForm1('ruleForm')"
              >确 定</el-button
            >
          </div>
        </el-dialog>
        <!-- 对回复的回复 -->
        <el-dialog title="回复" :visible.sync="dialogFormVisible1">
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            class="demo-ruleForm"
            style="margin-top: 1vh"
          >
            <el-form-item label="" prop="desc">
              <el-input
                type="textarea"
                v-model="form1.desc"
                placeholder="留下你的评论吧！"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="resetForm('ruleForm')">取 消</el-button>
            <el-button type="primary" @click="submitForm2('ruleForm')"
              >确 定</el-button
            >
          </div>
        </el-dialog>
       </el-card>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.articledetail(this.$route.query.id);
    this.commentone(this.$route.query.id);
  },
  data() {
    return {
      // 文章详情
      myBlog: {
        id: 0,
        content: "",
        title: "",
      },
      ruleForm: {
        content: "",
      },
      rules: {
        content: [
          { required: true, message: "留下你的评论吧！", trigger: "blur" },
        ],
      },
      //  评论
      comment: [
        {
          imgurl:
            "https://t7.baidu.com/it/u=3165657288,4248157545&fm=193&f=GIF",
          username: "12354",
          content: "1234564",
          recomment: [
            {
              imgurl:
                "https://t7.baidu.com/it/u=3165657288,4248157545&fm=193&f=GIF",
              username: "对评论的回复",
              content: "对评论的回复",
            },
          ],
        },
        {
          imgurl:
            "https://t7.baidu.com/it/u=3165657288,4248157545&fm=193&f=GIF",
          username: "12354",
          content: "1234564",
          recomment: [
            {
              imgurl:
                "https://t7.baidu.com/it/u=3165657288,4248157545&fm=193&f=GIF",
              username: "对评论的回复1",
              content: "对评论的回复1",
            },
          ],
        },
      ],
      //   回复评论
      dialogFormVisible: false,
      dialogFormVisible1: false,
      form1: {
        desc: "",
      },
      formLabelWidth: "100px",
      placeholder: "留下你的评论吧",
      content: "",
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    // 显示文章详情
    async articledetail(id) {
      const result = await this.$http.get(`/article/show?id=${id}`);
      if (result) {
        this.myBlog = result.data.obj;
      } else {
        this.$message.error("获取文章数据失败！");
      }
    },
    // 展示一级评论
    async commentone(id) {
      const result = await this.$http.get(`/ac/list1?aid=${id}`);
      // console.log(result);
      this.comment = result.data;
      var i = 0;
      for (i; i < this.comment.length; i++) {
        const res = await this.commenttwo(this.comment[i].id);
        this.comment[i].recomment = res;
      }
      this.$forceUpdate();
    },
    //展示二级评论
    async commenttwo(id) {
      const result = await this.$http.get(`/ac/list2?cid=${id}`);
      // console.log(result.data, "1");
      return result.data;
    },
    // 对文章的回复
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const articleComment = {
            aid: this.$route.query.id,
            uid: JSON.parse(localStorage.getItem("userdata")).id,
            content: this.ruleForm.content,
            pid: 0,
            reply_uid: 0,
          };
          // const articleComment = {
          //   aid: 1,
          //   uid: JSON.parse(localStorage.getItem("userdata")).id,
          //   content: this.ruleForm.content,
          // };
          const result = await this.$http.post("/ac/addAc", articleComment);
          this.$message.success(result.data.message);
          this.commentone(this.$route.query.id);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      // this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
      this.dialogFormVisible1 = false;
    },
    // 对一级评论的弹出框
    replycommentone(comment) {
      this.dialogFormVisible = true;
      this.form1.comment = comment;
    },
    // 对评论的回复
    submitForm1(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          console.log(this.form1);
          const articleComment = {
            pid: this.form1.comment.id, //被回复评论id
            replyUid: JSON.parse(localStorage.getItem("userdata")).id, //写评论的人
            uid: this.form1.comment.user.id, //被回复人
            content: this.form1.desc, //二级评论内容
            aid: Number(this.$route.query.id), //文章id
          };
          const result = await this.$http.post("/ac/addAc", articleComment);
          this.dialogFormVisible = false;
          this.$message.success(result.data.message);
          this.commentone(this.$route.query.id);
          this.form1.desc = "";
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 对二级评论的弹出框
    replycommenttwo(recomment) {
      this.dialogFormVisible1 = true;
      this.form1.recomment = recomment;
    },
    // 对回复的回复
    submitForm2(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          console.log(this.form1);
          const articleComment = {
            pid: this.form1.recomment.id, //被回复评论id
            replyUid: JSON.parse(localStorage.getItem("userdata")).id, //写评论的人
            uid: this.form1.recomment.user.id, //被回复人
            content: this.form1.desc, //二级评论内容
            aid: Number(this.$route.query.id), //文章id
          };
          const result = await this.$http.post("/ac/addAc", articleComment);
          this.dialogFormVisible1 = false;
          this.$message.success(result.data.message);
          this.commentone(this.$route.query.id);
          this.form1.desc = "";
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 删除一级评论
    deletecommentone(comment) {
      this.$alert("确定删除该评论吗？", "", {
        confirmButtonText: "确定",
        callback: async (action) => {
          // this.$message({
          //   type: 'info',
          //   message: `action: ${ action }`
          // });
          console.log(comment.id);
          const result = await this.$http.delete(
            "/ac/delete1?cid=" + comment.id
          );
          if (result.data.code == 200) {
            this.$message.success(result.data.message);
            this.commentone(this.$route.query.id);
          } else {
            this.$message.error(result.data.message);
          }
        },
      });
    },
    // 删除二级评论
    deletecomment(comment) {
      this.$alert("确定删除该评论吗？", "", {
        confirmButtonText: "确定",
        callback: async (action) => {
          // this.$message({
          //   type: 'info',
          //   message: `action: ${ action }`
          // });
          console.log(comment);
          const result = await this.$http.delete(
            "/ac/delete?cid=" + comment.id
          );
          if (result.data.code == 200) {
            this.$message.success(result.data.message);
            this.commentone(this.$route.query.id);
          } else {
            this.$message.error(result.data.message);
          }
        },
      });
    },
  },
};
</script>

<style lang="less" scoped>
image {
  width: 100%;
  height: 100%;
}
// 评论的样式
.comment-username {
  font-size: 20px;
  height: 30px;
  line-height: 30px;
  padding-top: 10px;
  margin-left: 2vh;
}
.replay {
  margin-left: 20px;
  padding-top: 10px;
  color: red;
}
.comment-content {
  margin-left: 8vh;
  font-size: 18px;
}
.recomment {
  margin-top: 5px;
  color: #4c4948;
}
</style>