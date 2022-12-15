<template>
  <div
    style="
      width: 1100px;
      margin-left: auto;
      margin-right: auto;
      min-height: 480px;
    "
  >
    <div v-for="sort in sortarticle" :key="sort.date">
      <div style="font-size: 15px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;{{ sort.date }}
      </div>
      <el-row :gutter="21">
        <el-col :span="6" v-for="article in sort.article" :key="article.cid">
          <el-card shadow="hover" style="margin-bottom: 10px; margin-top: 10px">
            <div class="article-title" @click="toarticle(article)">
              {{ article.title }}
            </div>
            <div
              style="text-align: right; padding: 5px"
              @click="toarticle(article)"
            >
              发布于{{ article.created | dateFormat }}
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sortarticle: [
        // {
        //   date: "2022-12月",
        //   article: [
        //     {
        //       title:
        //         "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
        //       created: "2022-12-10 20:06:01",
        //       hits: "浏览量",
        //       categories: "所属分类",
        //       status: "已发布",
        //     },
        //     {
        //       title:
        //         "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
        //       created: "2022-12-10 20:06:01",
        //       hits: "浏览量",
        //       categories: "所属分类",
        //       status: "已发布",
        //     },
        //     {
        //       title:
        //         "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
        //       created: "2022-12-10 20:06:01",
        //       hits: "浏览量",
        //       categories: "所属分类",
        //       status: "已发布",
        //     },
        //     {
        //       title:
        //         "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
        //       created: "2022-12-10 20:06:01",
        //       hits: "浏览量",
        //       categories: "所属分类",
        //       status: "已发布",
        //     },
        //   ],
        // },
      ],
    };
  },
  mounted() {
    this.getArticleSort();
  },
  methods: {
    // 获取页面内容
    async getArticleSort() {
      const result = await this.$http.get("/getDate");
      if (result.data.code == 200) {
        console.log(result.data.obj);
        var adate = result.data.obj;

        for (var i = 0; i < adate.length; i++) {
          // this.sortarticle[i].date = adate[i]
          // this.sortarticle[i].article = await this.getArticle(adate[i])
          var fdata = { date: "", article: [] };
          fdata.date = adate[i];
          fdata.article = await this.getArticle(adate[i]);
          this.sortarticle.push(fdata);
        }
        //  this.sortarticle.push(fdata);
        // this.sortarticle = result.data.obj
      } else {
        this.$message.error(result.data.messges);
      }
    },
    // 获取对应的时间内容
    async getArticle(date) {
      // console.log(date,parseInt(date))
      const result = await this.$http.get(`/getGuiDang/${date}`);
      if (result.data.code == 200) {
        console.log("获取对应的时间内容", result.data.obj);
        return result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    // 跳转页面
    toarticle(val) {
      // console.log(val)
      this.$router.push({ name: "/articledetail", params: val });
    },
  },
};
</script>

<style lang="less" scoped>
.article-title {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-size: 13px;
}
.article-title:hover {
   color: #6fa3ef;
}
.hover-date:hover {
  color: #6fa3ef;
}
</style>