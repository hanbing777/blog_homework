<template>
  <el-card class="box-card">
    <div class="about">
      <el-row
        style="
          width: 100%;
          margin-left: auto;
          margin-right: auto;
          position: relative;
        "
      >
        <el-col :span="6">
          <div
            class="function-card"
            style="background-color: #29b6f6 !important; display: flex"
          >
            <div class="function-card-icon"></div>
            <div
              style="margin-left: 130px; margin-top: auto; margin-bottom: auto"
            >
              <div class="function-card-title">发表了</div>
              <div class="function-card-number">{{ count.contentsCount }}</div>
              <div class="function-card-title">篇文章</div>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div
            class="function-card"
            style="background-color: #7e57c2 !important; display: flex"
          >
            <span class="function-card-icon"></span>
            <div
              style="margin-left: 130px; margin-top: auto; margin-bottom: auto"
            >
              <div class="function-card-title">收到了</div>
              <div class="function-card-number">{{ count.commentsCount }}</div>
              <div class="function-card-title">条留言</div>
            </div>
          </div></el-col
        >
        <el-col :span="6">
          <div
            class="function-card"
            style="background-color: #33b86c !important; display: flex"
          >
            <span class="function-card-icon"></span>
            <div
              style="margin-left: 130px; margin-top: auto; margin-bottom: auto"
            >
              <div class="function-card-title">上传了</div>
              <div class="function-card-number">{{ count.fileCount }}</div>
              <div class="function-card-title">个附件</div>
            </div>
          </div></el-col
        >
        <el-col :span="6"
          ><div
            class="function-card"
            style="background-color: #6e8cd7 !important; display: flex"
          >
            <span class="function-card-icon"></span>
            <div
              style="margin-left: 130px; margin-top: auto; margin-bottom: auto"
            >
              <div class="function-card-title">友链了</div>
              <div class="function-card-number">{{ count.linkCount }}</div>
              <div class="function-card-title">个好友</div>
            </div>
          </div></el-col
        >
      </el-row>
      <!-- 下面一部分 -->
      <el-row style="margin-top: 20px">
        <el-col :span="8"
          ><el-card class="box-card-data" shadow="hover">
            <div slot="header" class="clearfix">
              <span>最新文章</span>
            </div>
            <div v-for="o in article" :key="o.cid" class="text item">
              {{ o.title }}
            </div>
          </el-card></el-col
        >
        <el-col :span="8">
          <el-card class="box-card-data" shadow="hover">
            <div slot="header" class="clearfix">
              <span>最新留言</span>
            </div>
            <div v-for="m in tip" :key="m.coid" class="text">
              <div class="item" style="color: #337ab7; font-size: 15px">
                {{ m.author }}
              </div>
              <span style="padding: 10px">于{{ m.created | dateFormat }}</span>
              <div class="item" style="color: #337ab7; font-size: 10px">
                {{ m.content }}
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card-data" shadow="hover">
            <div slot="header" class="clearfix">
              <span>系统日志</span>
            </div>
            <div
              v-for="n in log"
              :key="n.id"
              class="text item"
              style="font-size: 14px"
            >
              {{ n.created | dateFormat}}=>{{ n.action }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>
<script>
import * as echarts from "echarts";
export default {
  name: "PageCount",
  data() {
    return {
      count: { number: 5 },
      article: [
        { title: 111 },
        {
          title:
            "数组初始化之后，该数组的长度是不可变的（可通过数组的 length 属性访问数组的长度）。Java 中的数组必须经过初始化（为数组对象的元素分配内存空间，并为每个数组元素指定初始值）才可使用。",
        },
      ],
      tip: [
        {
          user: 1234658,
          time: "2022-12-10 13:13:07",
          content:
            "数组初始化之后，该数组的长度是不可变的（可通过数组的 length 属性访问数组的长度）。",
        },
        {
          user: 1234658,
          time: "2022-12-10 13:13:07",
          content:
            "数组初始化之后，该数组的长度是不可变的（可通过数组的 length 属性访问数组的长度）。",
        },
      ],
      log: [
        { time: "2022-12-10 13:13:07", data: "登录后台" },
        {
          time: "2022-12-10 13:13:07",
          data: "数组初始化之后，该数组的长度是不可变的（可通过数组的 length 属性访问数组的长度）。",
        },
      ],
    };
  },
  mounted() {
    this.GetArticle();
    this.GetComment();
    this.GetLog();
    this.GetAllCount()
    this.$nextTick(function () {});
  },
  methods: {
    // 最新文章
    async GetArticle() {
      const result = await this.$http.get("/likeAndPage");
      // console.log(result)
      // 只提取前10条数据
      this.article = result.data.obj.splice(0,10).reverse();
    },
    // 最新留言
    async GetComment() {
      const result = await this.$http.get("/admin/comments/listAll");
      // console.log(result);
      this.tip = result.data.obj.splice(0,10).reverse();
    },
    // 系统日志
    async GetLog() {
      const result = await this.$http.get("/logs/GetLogs");
      console.log(result);
      this.log = result.data.obj.splice(0,10);
    },
    // 系统日志
    async GetAllCount() {
      const result = await this.$http.get("/admin/metas/getCount");
      console.log(result);
      this.count = result.data.obj;
    },
  },
};
</script>

<style lang="less" scoped>
image {
  width: 100%;
  height: 100%;
}
.box-card {
  width: 95%;
  height: 95%;
}
.function-card {
  width: 280px;
  height: 110px;
  margin-right: 10px;
  margin-left: 10px;
}
.function-card-icon {
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 100%;
  font-size: 30px;
  height: 60px;
  line-height: 60px;
  margin-left: 20px;
  text-align: center;
  margin-top: auto;
  margin-bottom: auto;
  width: 60px;
}
.function-card-title {
  padding-top: 2px;
  color: #eee;
  font-size: 15px;
  text-align: right;
}
.function-card-number {
  color: #fff;
  font-size: 25px;
  font-weight: 600;
  text-align: right;
}
// 三个卡片
.text {
  margin-bottom: 18px;
  border-bottom: 1px solid #ddd;
}
.item {
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.clearfix {
  font-size: 18px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.box-card-data {
  margin-right: 15px;
}
</style>
