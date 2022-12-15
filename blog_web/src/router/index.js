import Vue from "vue";
import VueRouter from "vue-router";
Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    // component:()=>import('@/views/login')
    redirect: "/front",
  },
  {
    path: "/login",
    name: "/login",
    component: () => import("@/views/login"),
  },
  {
    path: "/front",
    name: "/front",
    component: () => import("@/views/frontpage/page.vue"),
    children: [
      {
        path: "/",
        name: "/fronthome",
        component: () => import("@/views/frontpage/index.vue"),
      },
      {
        path: "/sort",
        name: "/sort",
        component: () => import("@/views/frontpage/Sort.vue"),
      },
      {
        path: "/frontlink",
        name: "/frontlink",
        component: () => import("@/views/frontpage/frontlink.vue"),
      },
      {
        path: "/about",
        name: "/about",
        component: () => import("@/views/frontpage/about.vue"),
      },
      {
        path: "/select",
        name: "/select",
        component: () => import("@/views/frontpage/select.vue"),
      },
      {
        path: "/articledetail",
        name: "/articledetail",
        component: () => import("@/views/frontarticle/page.vue"),
      },
      {
        path: "/text",
        name: "/text",
        component: () => import("@/views/frontarticle/text.vue"),
      },
      {
        path: "/textone",
        name: "/textone",
        component: () => import("@/views/frontarticle/textone.vue"),
      },
      {
        path: "/texttwo",
        name: "/texttwo",
        component: () => import("@/views/frontarticle/texttwo.vue"),
      },
    ],
  },
  {
    path: "/admin",
    name: "/admin",
    component: () => import("@/views/page"),
    children: [
      {
        path: "/",
        name: "/adminhome",
        component: () => import("@/views/page/home.vue"),
      },
      {
        path: "/home",
        name: "/home",
        component: () => import("@/views/page/home.vue"),
      },
      // 管理员页面
      {
        path: "/edituser",
        name: "/edituser",
        // 编辑个人资料
        component: () => import("@/views/Function/editdetail.vue"),
      },
      {
        path: "/changepwd",
        name: "/changepwd",
        // 修改密码
        component: () => import("@/views/Function/changepassword.vue"),
      },
      // 文章管理
      {
        path: "/post",
        name: "/post",
        component: () => import("@/views/ArticleALL/PostArticle.vue"),
      },
      {
        path: "/articledata",
        name: "/articledata",
        component: () => import("@/views/ArticleALL/Articledata.vue"),
      },
      {
        path: "/editdata",
        name: "/editdata",
        component: () => import("@/views/ArticleALL/Editdata.vue"),
      },
      {
        path: "/commentdata",
        name: "/commentdata",
        component: () => import("@/views/ArticleALL/Commentdata.vue"),
      },
      // 功能设置
      {
        path: "/labeldata",
        name: "/labeldata",
        component: () => import("@/views/Function/Labeldata.vue"),
      },
      {
        path: "/file",
        name: "/file",
        component: () => import("@/views/Function/File.vue"),
      },
      {
        path: "/links",
        name: "/links",
        component: () => import("@/views/Function/Links.vue"),
      },
      // 系统设置
      {
        path: "/setting",
        name: "/setting",
        // component: () => import("@/views/Setting"),
        component: () => import("@/views/Setting/page.vue"),
      },
    ],
  },
];
const routers = new VueRouter({ routes });
routers.beforeEach(async (to, from, next) => {
  // console.log('to',to)
  // console.log('from',from)
  // console.log('next',next)
  // next();
  var userinfo = JSON.parse(localStorage.getItem("userdata"));
  console.log(userinfo);
  if (userinfo) {
    // 登录之后，不能去登录、注册页面
    if (to.path === "/login") {
      next("/front");
      // next()
    } else {
      next();
    }
  } else {
    const toPath = to.path;
    if (
      toPath.indexOf("/admin") !== -1 ||
      toPath.indexOf("/home") !== -1 ||
      toPath.indexOf("/edituser") !== -1 ||
      toPath.indexOf("/changepwd") !== -1 ||
      toPath.indexOf("/post") !== -1 ||
      toPath.indexOf("/articledata") !== -1 ||
      toPath.indexOf("/editdata") !== -1 ||
      toPath.indexOf("/commentdata") !== -1 ||
      toPath.indexOf("/labeldata") !== -1 ||
      toPath.indexOf("/file") !== -1 ||
      toPath.indexOf("/links") !== -1 ||
      toPath.indexOf("/labeldata") !== -1 ||
      toPath.indexOf("/setting") !== -1 
    ) {
      next("/login?redirect=" + toPath);
      // next()
    } else {
      next();
    }
  }
});
export default routers;
