<template>
  <div
    style="
      width: 900px;
      margin-left: auto;
      margin-right: auto;
      min-height: 480px;
    "
  >
    <el-card>
      <div style="font-size: 15px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;友情链接
      </div>
      <div style="font-size: 15px; margin: 10px">
        <el-row :gutter="20">
          <el-col :span="6" v-for="link in links" :key="link.mid"
            ><a :href="link.slug" target="_blank"
              ><i class="el-icon-lock" style="color: #ecae0f"></i>&nbsp;&nbsp;
              {{ link.name }}</a
            ></el-col
          >
        </el-row>
      </div>
      <div style="font-size: 15px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;链接须知
      </div>
      <div style="margin-left: 20px">
        <ul style="margin: 10px; font-size: 14px" class="ul">
          <li>请确定贵站可以稳定运营</li>
          <li>原创博客优先，技术类博客优先，设计、视觉类博客优先</li>
          <li>经常过来访问和评论，眼熟的</li>
        </ul>
      </div>
      <div style="font-size: 15px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;基本信息
      </div>
      <div style="margin-left: 20px">
        <ul style="margin: 10px; font-size: 14px" class="ul">
          <li>网站名称：Blog</li>
          <li>
            网站地址：
            <a href="http://cjj200275.gitee.io/atext/" target="_blank"
              >http://cjj200275.gitee.io/atext/</a
            >
          </li>
          <li>我的邮箱:XXX@gmail.com</li>
        </ul>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      links: [
        {
          mid: 6,
          name: "my github",
          slug: "https://github.com/hanbing777",
          type: "link",
          description:
            "https://cravatar.cn/avatar/b4dfd72ba6ce524a1715bbbd79c1921a?s=256&d=mm",
          sort: 1,
          parent: 0,
        },
      ],
    };
  },
  mounted() {
    this.getLinks();
  },
  methods: {
    // 显示分类数据
    async getLinks() {
      const result = await this.$http.get(`/admin/metas/getMetasByType/link`);
      if (result.data.code == 200) {
        this.links = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
  },
};
</script>

<style lang="less" scoped>
.hover-date:hover {
  color: #6fa3ef;
}
a {
  text-decoration: none;
  color: black;
  font-size: 15px;
}
a:hover {
  text-decoration: none;
  color: #ecae0f;
}

.ul li {
  padding: 10px;
}
</style>