<template>
  <div class="page-body">
    <el-row>
      <el-col
        :span="8"
        v-for="article in article"
        :key="article.cid"
        style=""
      >
        <el-card
          :body-style="{ padding: '0px' }"
          style="margin: 20px; min-height: 336.68px"
        >
          <img
            :src="article.articleimg"
            class="image"
            style="height: 260px"
            @click="toarticle(article)"
          />
          <div style="padding: 14px" @click="toarticle(article)">
            <div class="article-title">{{ article.title }}</div>
            <div class="bottom clearfix" style="text-align: right">
              <div
                style="width: 100px; float: right; font-size: 15px"
                class="article-categories"
              >
                <i class="el-icon-paperclip"></i>&nbsp;&nbsp;{{
                  article.categories
                }}
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage1"
        :page-size="pageSize"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentPage1: 1,
      total: 100, //总数量
      pageSize: 6, //每页数量
      article: [
        {
          title: "yiyiyi",
          categories: "java",
          articleimg: require("../../assets/img/rand/1.jpg"),
        },
        // { title: "yiyiyi", categories: "java",articleimg:'../../assets/img/rand/1.jpg'},
        { title: "《疯狂 Java 突破程序员基本功的 16 课》 读书笔记" },
        {
          title: "《Java 多线程编程核心技术》学习笔记及总结",
          categories: "HashMap、Hashtable、HashSet 和 ConcurrentHashMap 的比较",
        },
        {
          title:
            "HashMap、Hashtable、HashSet 和 ConcurrentHashMap 的比较HashMap、Hashtable、HashSet 和 ConcurrentHashMap 的比较",
          categories: "java",
        },
        {
          title: "Pyspider框架 —— Python爬虫实战之爬取 V2EX 网站帖子",
          categories: "java",
        },
        { title: "Pyspider框架 —— Python爬虫实战之爬取 V2EX 网站帖子记" },
      ],
    };
  },
  methods: {
    toaddimg() {
      // console.log(this.article)
      const imgall = [
        {
          url: "https://img0.baidu.com/it/u=4153189400,1084324485&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800",
        },
        {
          url: "https://img0.baidu.com/it/u=2027708053,1507644202&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        },
        {
          url: "https://img1.baidu.com/it/u=4163343134,3072943796&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800",
        },
        {
          url: "https://img1.baidu.com/it/u=3797799325,246455253&fm=253&fmt=auto&app=138&f=JPEG?w=658&h=370",
        },
        {
          url: "https://img1.baidu.com/it/u=3658862117,761662498&fm=253&fmt=auto&app=138&f=JPEG?w=660&h=495",
        },
        {
          url: "https://img2.baidu.com/it/u=112746732,575090313&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800",
        },
        {
          url: "https://img2.baidu.com/it/u=3172842374,833415216&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=281",
        },
      ];
      for (var i = 0; i < this.article.length; i++) {
        var d = imgall.length + 1;
        this.article[i].articleimg = imgall[i % d].url;
        // console.log(i % d, imgall.length);
      }
    },
    // 分页
    async handleSizeChange(val) {
      const result = await this.$http.get(
        `/likeAndPage/${this.currentPage1}/${this.pageSize}`
      );
      if (result.data.code == 200) {
        this.article = result.data.obj.records;
        this.total = result.data.obj.total;
        this.toaddimg();
      } else {
        this.$message.error("未获取文章信息");
      }
    },
    //初始时调用
    async handleCurrentChange(val) {
      // console.log(`2当前页: ${val}`);
      // console.log("123",this.currentPage1,this.pageSize)
      const result = await this.$http.get(
        `/likeAndPage/${this.currentPage1}/${this.pageSize}`
      );
      if (result.data.code == 200) {
        this.article = result.data.obj.records;
        this.total = result.data.obj.total;
        this.toaddimg();
      } else {
        this.$message.error("未获取文章信息");
      }
    },
    // 跳转页面
    toarticle(val) {
      // console.log(val)
      this.$router.push({ name: "/articledetail", params: val });
    },
  },
  mounted() {
    this.handleCurrentChange();
    // this.toaddimg();
  },
};
</script>

<style lang="less" scoped>
.page-body {
  width: 900px;
  margin-left: auto;
  margin-right: auto;
}
.image {
  width: 100%;
  height: 100%;
}
// 多行文本溢出处理
.article-title {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-size: 15px;
  font-weight: 600px;
}
.article-categories {
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  color: #83d2f7;
}
// 分页
.pagination-container {
  background-color: #ffffff;
  padding-top: 10px;
  padding-bottom: 10px;
  text-align: center;
}
</style>