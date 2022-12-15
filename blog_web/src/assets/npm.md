# windows+cmd命令大全

1.win键+R 输入cmd打开控制台

2.在任意文件夹下面打开，按住shift+鼠标右键点击，在此处打开命令行窗口

##### 3.（1）盘符切换: *盘名+：（c:）*

##### （2）查看当前目录下的所有文件： *dir*

##### 切换目录： ***cd 需要进入的目录名***

##### 返回上一级目录： *cd …*

##### 跨盘进入目录: *cd /d 盘名：*

##### 返回根目录： cd\

**创建文件夹**：*md 文件夹名*

**移除文件夹**： *rd 文件夹名*

**移除文件夹**： *rd 文件夹名*

**删除文件**： del 文件名

##### 打开应用

- **calc:** *计算器*
- **mspaint**: *画图*
- **notepad:** *记事本*

##### 清屏与退出

- **清屏：** *cls*
- **退出**：*exit*

# NPM - Node Package Manager - Node 包管理器

1.模块化:是指解决一个复杂问题时，自顶向下逐层把系统划分成若干模块的过程。对于整个系统来说，模块是可组 合、分解和更换的单元。

2.编程领域中的模块化，就是遵守固定的规则，把一个大文件拆成独立并互相依赖的多个小模块。 把代码进行模块化拆分的好处： ① 提高了代码的复用性 ② 提高了代码的可维护性 ③ 可以实现按需加载。

3.模块化规范就是对代码进行模块化的拆分与组合时，需要遵守的那些规则。

4.Node.js 中的第三方模块又叫做包。 就像电脑和计算机指的是相同的东西，第三方模块和包指的是同一个概念，只不过叫法不同。

5.. 为什么需要包 由于 Node.js 的内置模块仅提供了一些底层的 API，导致在基于内置模块进行项目开发的时，效率很低。 包是基于内置模块封装出来的，提供了更高级、更方便的 API，极大的提高了开发效率。 包和内置模块之间的关系，类似于 jQuery 和 浏览器内置 API 之间的关系。

包的语义化版本规范 包的版本号是以“**点分十进制**”形式进行定义的，总共有三位数字，例如 2.24.0 其中每一位数字所代表的的含义如下： 第1位数字：大版本 第2位数字：功能版本 第3位数字：Bug修复版

**6.从 https://www.npmjs.com/ 网站上搜索自己所需要的包** 

​	**从 https://registry.npmjs.org/ 服务器上下载自己需要的**

###### 	**切换 npm 的下包镜像源**:

```
//查看当前的下包镜像源
npm config get registry
//将镜像源切换为淘宝镜像源
npm config set registry= https://registry.npm.taobao.org/
//检查镜像源是否下载成功
npm config get registry
```

为了更方便的切换下包的镜像源，我们可以安装 **nrm** 这个小工具，利用 nrm 提供的终端命令，可以快速查看和切换下 包的镜像源。

```
npm i nrm -g
nrm ls//查看所有可用的镜像源
nrm use taobao//切换镜像源
```

7.

```
npm -v 命令，来查看自己电脑上所安装的 npm 包管理工具的版本号
npm version
```

```
npm install 包名  简写为  npm i 包名
npm i --legacy-peer-deps//可能是npm 版本的问题
npm uninstall 包名  卸载指定的包(自动从 package.json 的 dependencies 中移除掉)
全局包后面加上 -g
```

- 如果某些包只在项目开发阶段会用到，在项目上线之后不会用到，则建议把这些包记录到 devDependencies 节点中。 与之对应的，如果某些包在开发和项目上线之后都需要用到，则建议把这些包记录到 dependencies 节点中。

  ```
  npm install 包名 --save-dev 
  npm i 包名 -D
  ```

- **node_modules** 文件夹用来存放所有已安装到项目中的包。

- **package-lock.json** 配置文件用来记录 node_modules 目录下的每一个包的下载信息。

默认情况下，使用 npm install 命令安装包的时候，会自动安装最新版本的包。如果需要安装指定版本的包，可以在包 名之后，通过 @ 符号指定具体的版本

```
npm i 包名@版本号      
```

**注意：**今后在项目开发中，一定要把 node_modules 文件夹，添加到 .gitignore 忽略文件中。

- 项目包：那些被安装到项目的 node_modules 目录中的包。

- 项目包又分为两类，分别是：

  **开发依赖包**（被记录到 devDependencies 节点中的包，只在开发期间会用到）  **核心依赖包**（被记录到 dependencies 节点中的包，在开发期间和项目上线之后都会用到。

  ```
  npm i 包名 -D //开发依赖包
  npm i 包名   //核心依赖包
  ```

  

- npm 包管理工具提供了一个快捷命令，可以在执行命令时所处的目录中，快速创建 package.json 这个包管理 配置文件：

  ```
  npm init -y
  npm init 
  npm init -yes
  ```

  注意： ① 上述命令只能在英文的目录下成功运行！所以，项目文件夹的名称一定要使用英文命名，不要使用中文，不能出现空格。

   ② 运行 npm install 命令安装包的时候，npm 包管理工具会自动把包的名称和版本号，记录到 package.json 中

8.在清楚了包的概念、以及如何下载和使用包之后，接下来，我们深入了解一下包的内部结构。 一个规范的包，它的组成结构，必须符合以下 3 点要求： ① 包必须以单独的目录而存在 ② 包的顶级目录下要必须包含 package.json 这个包管理配置文件 ③ package.json 中必须包含 name，version，main 这三个属性，分别代表包的名字、版本号、包的入口。 注意：以上 3 点要求是一个规范的包结构必须遵守的格式，关于更多的约束，可以参考如下网址： https://yarnpkg.com/zh-Hans/docs/package-json

9.i5ting_toc 是一个可以把 md 文档转为 html 页面的小工具，使用步骤如下：

```
npm install -g i5ting_toc
i5ting_toc -f 要转换的md文件路径 -o
```

# git常用命令

##### 1.**本地仓库与远程仓库未关联**

```
// 代码上传环境初始化
git init    // git 初始化命令，会创建本地仓库。
git status    // 查看仓库状态。

// 将代码上传到本地仓库
git add .    
git commit -m "更新内容说明"

// 拉取远程仓库代码
git remote add origin 远程仓库地址    // 将本地仓库与远程仓库关联。
git pull origin 远程分支名 --allow-unrelated-histories    // 强行拉取，无视仓库关联性。
git push    // 如果用用户名和密码方式还要追加 -u origin 分支名，不推荐。

// 如果想让本地仓库换关联，需要取消关联，解决报错：fatal: remote origin already exists.
git remote rm origin 

```

##### 2.本地仓库与远程仓库已关联

```
git clone 远程仓库地址
git add .
git commit -m "更新内容说明"
git pull    // 推荐追加 --rebase
git push

```

##### 3.拉取

```
git pull origin master
```

https://blog.csdn.net/m0_45234510/article/details/120181503
