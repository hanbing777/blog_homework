// vue项目的配置文件 覆盖，

module.exports = {
  publicPath: './', // 公共路径 默认为"/"，建议使用"./"相对路径
    lintOnSave: false, // 暂时关闭代码格式检测
    // 开发服务器设置
    devServer: {
      open: true,
      // 设置 npm run serve 启动后的端口号
      port: 8082,
      // 如果你开始了eslint,不要让eslint在页面中遮罩，它错误会在console.log控制台打印
      overlay: false,
      // vue项目代理请求
      proxy: {
      // 规则
      // axios中相对地址开头的字符串  匹配请求uri中的前几位
        '/api': {
        // 把相对地址中的域名 映射到 目标地址中
        // localhost:3000 => https://api.iynn.cn/film/api/v1/
        //   target: 'http://47.99.49.255:8090/',
        //   target: 'http://127.0.0.1:8083/',
          target: 'http://localhost:8083/',
          // target: 'http://47.99.49.255:8091/',
          // 修改host请求的域名为目标域名
          // changeOrigin: false,
          changeOrigin: true,
          // 请求uri和目标uri有一个对应关系
          // 请求/api/login ==> 目标 /v1/api/login
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    },
  }
  