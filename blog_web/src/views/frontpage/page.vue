<template>
  <div style="background-color: rgb(239, 239, 239)">
    <!-- 背景图片 -->
    <div class="background-image" style="display: flex">
      <div style="margin-top: 15px; margin-left: 15%">
        <img
          src="@/assets/images/logo.png"
          width="80px"
          height="50px;"
          style="vertical-align: middle"
        />
      </div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        text-color="#000"
        active-text-color="#0066ff"
        @select="handleSelect"
      >
        <el-menu-item index="front">首页</el-menu-item>
        <el-menu-item index="sort">归档</el-menu-item>
        <el-menu-item index="frontlink">友链</el-menu-item>
        <el-menu-item index="about">关于</el-menu-item>
        <el-menu-item index="admin">后台管理</el-menu-item>
        <el-menu-item index="select"><i class="el-icon-search"></i>搜索</el-menu-item>
      </el-menu>
    </div>
    <!-- 内容区 -->
    <el-main style="min-height: 300px;margin-top:80px;z-index:-1;">
      <!-- 路由占位符 -->
      <router-view></router-view>
    </el-main>
    <!-- 底部 -->
    <div
      style="
        height: 120px;
        background: #accbee;
        line-height: 120px;
        color: #fff;
        text-align:center;
      "
    >
      @2022&nbsp;&nbsp;版权所有 My Blog
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
      imgurl:
        "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
      activeIndex: "1",
      Showinfo: true, //显示登录或者注册或者退出
    };
  },
  mounted() {},
  beforeDestroy() {},
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key);
    },
  },
};
</script>

<style lang="less" scoped>
image {
  width: 100%;
  height: 100%;
}
.background-image {
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
  height: 80px;
  top: 0px;
  right: 0px;
  width: 100%;
  position: fixed;
  z-index: 100;
}
.el-menu-demo {
  position: fixed;
  top: 0px;
  right: 0px;
  background-color: transparent;
  border-bottom: none;
  height: 80px;
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
  z-index: 100;
}
.el-submenu__title:hover,
.el-menu-item:focus,
.el-menu-item:hover {
  background-color: transparent !important;
}
</style>