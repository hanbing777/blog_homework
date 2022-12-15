<template>
  <el-container class="home_container">
    <el-header>
      <div class="box1">
        <img
          src="@/assets/images/logo.png"
          width="50px"
          height="50px;"
          style="vertical-align: middle"
        />
        <span> My Blog</span>
      </div>
      <div class="box2">
        <div>
          <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link"
              >个人中心<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :command="composeValue('cd1', 'front')"
                >网站首页</el-dropdown-item
              >
              <el-dropdown-item :command="composeValue('cd1', 'edituser')"
                >修改资料</el-dropdown-item
              >
              <el-dropdown-item :command="composeValue('cd2', 'changepwd')"
                >修改密码</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <div class="box22" @click="logout">
          <span>退出登录</span>
          <i class="iconfont icon-guanbi"></i>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-aside width="210px">
        <!-- 侧边栏菜单区域 -->
        <el-menu router unique-opened>
          <!-- <el-submenu index="1"> -->
          <el-menu-item index="home" v-show="show3">
            <!-- 一级菜单 -->
            <!-- <template slot="title"> -->
            <i class="el-icon-s-home" style="margin-left: 0px"></i>
            <span>仪表盘</span>
            <!-- </template> -->
          </el-menu-item>
          <!-- 文章管理 -->
          <el-submenu index="1" v-show="show1">
            <template slot="title">
              <i class="el-icon-s-grid"></i>
              <span>文章管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="post">发布文章</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
              <el-menu-item index="articledata">文章管理</el-menu-item>
            </el-menu-item-group>
            <!-- <el-menu-item-group>
              <el-menu-item index="Pagedata">页面管理</el-menu-item>
            </el-menu-item-group> -->
            <el-menu-item-group>
              <el-menu-item index="commentdata">评论管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <!-- 功能设置 -->
          <el-submenu index="2" v-show="show1">
            <template slot="title">
              <i class="el-icon-s-grid"></i>
              <span>功能设置</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="labeldata">分类/标签</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
              <el-menu-item index="file">文件管理</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
              <el-menu-item index="links">友链管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <!-- 系统设置 -->
          <el-menu-item index="setting" v-show="show2">
            <i class="el-icon-setting"></i>
            <span slot="title">系统设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <!-- 内容区 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "page",
  data() {
    return {
      options: [
        {
          value: "person",
          label: "个人中心",
          children: [
            {
              value: "psw",
              label: "重置密码",
            },
          ],
        },
      ],
      cd1: "reset_psw",
      // show1:false,
      // show2:false,
      // show3:false
      show1: true,
      show2: true,
      show3: true,
    };
  },
  created() {
    // 派发actions;//组件挂载完毕，可以向服务器发送请求
    // this.$store.dispatch('getuserinfo')
  },
  mounted() {
    // if(this.$store.state.user.useringo)
    // var userinfo = JSON.parse(localStorage.getItem('Userinfo'))
    // var arole = userinfo.role
    // console.log('arole',arole)
    // var role = arole.role
    // console.log('role',role)
    // if(role ==='用户'){
    //   this.show1 = true
    // }
    // else if(role ==='驾驶员'){
    //   this.show2 = true
    // }else{
    //   this.show3 = true
    // }
  },
  methods: {
    handleCommand(command) {
      //   this.$router.push(this.cd1)
      console.log(command);
      this.$router.push(command.row);
    },
    logout() {
      // userlogout
      alert("确认退出登录?");
      // this.$store.dispatch('userlogout')
      // 清空token
      localStorage.removeItem("userdata");
      window.sessionStorage.clear();
      // 跳转到登录页
      this.$router.push("/login");
      // console.log(JSON.parse(window.sessionStorage.getItem('user')))
    },
    composeValue(item, row) {
      return {
        button: item,
        row: row,
      };
    },
  },
};
</script>

<style lang="less" scoped>
.home_container {
  height: 100%;
  background: #f5f5f5;
}

.el-header {
  background: #ecf5ff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 0px !important;
  .box1 {
    // background: #eeeeee;
    span {
      font-size: 25px;
      margin-left: 0px;
      font-size: 28px;
      color: #2f353f;
      line-height: 50px;
    }
  }
  .box2 {
    display: flex;
    justify-content: space-between;
    align-items: center;
    div {
      display: flex;
      align-items: center;
      padding: 0 0 0 20px;
    }
    .box22 {
      span {
        padding-right: 6px;
        font-size: 20px;
        // cursor: pointer;
      }
      i {
        font-size: 20px !important;
      }
    }
    .box22:hover {
      color: #409eff;
      cursor: pointer;
    }
  }
}

.el-aside {
  background-color: #ffffff;
  .el-menu {
    border-right: none;
    margin-left: 0px;
    .el-submenu__title {
      font-size: 20px;
    }
  }
}

.el-main {
  position: relative;
}

.el-main::after {
  content: "";
  // background: url(https://img1.baidu.com/it/u=2477871273,3251970349&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500) no-repeat;
  background-color: #f5f5f5;
  background-size: cover;
  // background-attachment: fixed;
  // background-size: contain;
  opacity: 0.7;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  position: absolute;
  z-index: -1;
}

.iconfont {
  margin-right: 10px;
}

.el-dropdown-link {
  cursor: pointer;
  font-size: 20px;
}
.el-icon-arrow-down {
  font-size: 12px;
}

.icon3 {
  margin-right: 0px;
}
.el-avatar {
  cursor: pointer;
}

.isLook {
  display: block;
  // display: none;
}
</style>
