<template>
  <div
    style="
      width: 900px;
      margin-left: auto;
      margin-right: auto;
      min-height: 480px;
    "
  >
    <div>
      <div style="margin-top: 15px">
        <el-input
          placeholder="请输入内容"
          v-model="search"
          class="input-with-select"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="submit"
          ></el-button>
        </el-input>
      </div>
      <el-row :gutter="21">
        <el-col :span="8" v-for="article in article" :key="article.cid">
          <el-card shadow="hover" style="margin-bottom: 10px; margin-top: 10px">
            <div
              class="article-title"
              v-html="highlight(article.title, search)"
              @click="toarticle(article)"
            ></div>
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
      article: [
        // {
        //   title:
        //     "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
        //   created: 0,
        //   hits: "浏览量",
        //   categories: "所属分类",
        //   status: "已发布",
        // },
      ],
      search: "",
      searchContent: "上海",
    };
  },
  methods: {
    async submit() {
      console.log(this.search);
      if (this.search == "") {
        return this.$message.error("请填写搜索内容！");
      }
      const searchdata = this.search.replace(/\s*/g, "");
      console.log(searchdata);
      this.search = searchdata
      const result = await this.$http.get(`/like/${searchdata}`);
      if (result.data.code == 200) {
        console.log(result.data.obj.length)
        if(result.data.obj.length==0){
         return  this.$message.success("暂无搜索内容！")
        }
        this.article = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    // 跳转页面
    toarticle(val) {
      // console.log(val)
      this.$router.push({ name: "/articledetail", params: val });
    },
    // 参数 source: 原始字符串
    // 参数 keyword: 需要高亮的关键词
    // 返回值：替换之后的高亮字符串
    highlight(source, keyword) {
      // /searchContent/ 正则表达式中的一切内容都会当做字符串使用
      // 这里可以 new RegExp 方式根据字符串创建一个正则表达式
      // RegExp 是原生 JavaScript 的内置构造函数
      // 参数1：字符串，注意，这里不要加 //
      // 参数2：匹配模式，g 全局，i 忽略大小写
      const reg = new RegExp(keyword, "gi");
      return source.replace(
        reg,
        `<span style="color: #3296fa">${keyword}</span>`
      );
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
.hover-date:hover {
  color: #6fa3ef;
}
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
</style>