<template>
  <div style="width: 900px; margin-left: auto; margin-right: auto;min-height:480px;">
    <el-card>
      <div class="about">about my blog</div>
      <div style="font-size: 18px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;Blog
      </div>
      <div style="margin-left: 20px">
        <ul style="margin: 10px; font-size: 15px" class="ul">
          <li>前端:Vue + element-ui</li>
          <li>后端:SpringBoot + MybatisPlus + MySQL</li>
          <li>Markdown编辑器:mavon-editor</li>
        </ul>
      </div>
      <div style="font-size: 18px" class="hover-date">
        <span style="color: #6fa3ef">#</span>&nbsp;&nbsp;感谢
      </div>
      <div style="margin-left: 20px">
        <ul style="margin: 10px; font-size: 15px" class="ul">
          <li>XXX</li>
        </ul>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {};
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
  font-family: "KaiTi";
}
.about {
  font: 400 40px "Times New Roman", Georgia, Serif;
  color: #88d3ce;
}
</style>