import Vue from "vue";
import App from "./App.vue";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
// 引入路由相关组件
import router from "./router";
// 引入仓库进行注册
import store from "./store";
// 引入axios
import axios from "axios";
// import './style/md.css'
import "./style/md/css/index.css";

import mavonEditor from "mavon-editor";
import "mavon-editor/dist/css/index.css";
import VueEmoji from "emoji-vue";
// import "mavon-editor/dist/markdown/github-markdown.min.css";
// import "./style/github-markdown.min.css.map"
Vue.use(mavonEditor);
//
Vue.prototype.$http = axios;
axios.defaults.baseURL = "/api";
// axios.defaults.baseURL = 'http://1/01.43.104.20:8182'
axios.defaults.timeout = 10000;
// 加载全局样式
import "./style/index.less";
import "./assets/font/iconfont.css";
import commenlist from "@/components/commenlist";
import Video from "video.js";
import "video.js/dist/video-js.css";
Vue.prototype.$videos = Video;
Vue.component(commenlist.name, commenlist);
Vue.use(ElementUI);
Vue.config.productionTip = false;
Vue.filter("dateFormat", function (originVal) {
  // 10位数字的需要乘以1000,13位数字不需要
  const dt = new Date(originVal*1000);
  const y = dt.getFullYear();
  const m = (dt.getMonth() + 1 + "").padStart(2, "0");
  const d = (dt.getDate() + "").padStart(2, "0");
  const hh = (dt.getHours() + "").padStart(2, "0");
  const mm = (dt.getMinutes() + "").padStart(2, "0");
  const ss = (dt.getSeconds() + "").padStart(2, "0");
  //时间格式年月日、时分秒
  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`;
});

axios.defaults.withCredentials = true; // session跨域

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");

// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
  (config) => {
    if (localStorage.getItem("token")) {
      const tokenHead = localStorage.getItem("tokenHead");
      const token = localStorage.getItem("token");
      config.headers.Authorization =
        tokenHead + " " + localStorage.getItem("token");
      // config.headers.token = "123"
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
