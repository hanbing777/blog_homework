<template>
  <div id="app">
    <!-- 路由出口 -->
    <router-view v-if="isRouterAlive" />
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      isRouterAlive: true,
    };
  },
  provide() {
    return {
      reload: this.reload,
    };
  },
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function () {
        this.isRouterAlive = true;
      });
    },
  },
  mounted() {
  },
};
</script>

<style>
html {
  overflow-x: auto;
  overflow-y: scroll;
}
body,
dl,
dt,
dd,
ul,
ol,
li,
pre,
form,
fieldset,
input,
p,
blockquote,
th,
td {
  font-weight: 400;
  margin: 0;
  padding: 0;
}
h1,
h2,
h3,
h4,
h4,
h5 {
  margin: 0;
  padding: 0;
}
body {
  /* background-color:#FFFFFF; */
  color: #666666;
  font-family: Helvetica, Arial, sans-serif;
  font-size: 12px;
  padding: 0 10px;
  text-align: left;
}
select {
  font-size: 12px;
}
table {
  border-collapse: collapse;
}
fieldset,
img {
  border: 0 none;
}
fieldset {
  margin: 0;
  padding: 0;
}
fieldset p {
  margin: 0;
  padding: 0 0 0 8px;
}
legend {
  display: none;
}
address,
caption,
em,
strong,
th,
i {
  font-style: normal;
  font-weight: 400;
}
table caption {
  margin-left: -1px;
}
hr {
  border-bottom: 1px solid #ffffff;
  border-top: 1px solid #e4e4e4;
  border-width: 1px 0;
  clear: both;
  height: 2px;
  margin: 5px 0;
  overflow: hidden;
}
caption,
th {
  text-align: left;
}
q:before,
q:after,
blockquote:before,
blockquote:after {
  content: ””;
}
</style>
