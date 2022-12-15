<template>
  <div class="page">
      <div v-for="(content,item) in contents" :key="item">
          <!-- <div>{{content.user.name}}</div> -->
          <div style="margin-left:10px;margin-top:10px;display:flex;">
              <!-- 用户头像 -->
              <el-avatar :size="50" :src="content.user.imgurl"></el-avatar>
              <!-- 用户名 -->
              <div class="name">{{content.user.name}}</div>
          </div>
          <!-- 评论内容 -->
          <div style="border-bottom:#CACCCF solid 1px;width:90%;margin-left:5%;">
              <div class="fcomment">{{content.commentcontent}}</div>
              <el-button type="text" style="margin-top:10px;margin-left:80px;" @click="fcomment(content)">删除</el-button>
              <div class="time">{{content.gmtCreate}}</div>
              <div class = "rrusercontent" v-for="(rrusercontent,index) in content.responseList" :key="index">
                  <div class = "zcomment">
                      <el-avatar :size="50" :src="rrusercontent.respedUser.imgurl" style="margin-left:100px;margin-top:10px;"></el-avatar>
                      <div class = "left">
                    <div class="rrusername" style="display:flex;margin-left:180px;margin-top:-40px;">
                        <span class = "van-ellipsis span">{{rrusercontent.respedUser.name}}</span>
                        <span class = "van-ellipsis span center">回复</span>
                        <!-- <span class = "van-ellipsis span right">{{content.user.name}}</span> -->
                        <span class = "van-ellipsis span right">{{rrusercontent.respUser.name}}</span>
                    </div>
                    <!--子评论回复内容 -->
                    <div class = "content" style="margin-left:200px;margin-top:10px;">{{rrusercontent.content}}</div>
                    <!-- 时间 -->
                    <el-button type="text" style="margin-top:10px;margin-left:200px;" @click="zcomment(rrusercontent)">删除</el-button>
                    <div class="time" style="margin-left:250px;margin-bottom:10px;">{{rrusercontent.gmtCreate}}</div>
                  </div>
              </div>
          </div>
      </div>
      </div>
  </div>
</template>

<script>
export default {
    name:'commenlist',
    // inject:['refresh'],
    props:{
        contents:{
            type:Array
        }
    },
    methods:{
        fcomment(content){
            this.$confirm('此操作将永久删除该评论, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(async() => {
                    alert("点击删除父评论")
                    console.log(content)
                    const result = await this.$http.post('',content)
                    if(result.data.code===200){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }else{
                        this.$message.error('未获取路径');
                    }
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
                });
        },
        zcomment(content){
            this.$confirm('此操作将永久删除该评论, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
                }).then(async () => {
                    alert("点击删除子评论")
                    console.log(content)
                    const result = await this.$http.post('',content)
                    if(result.data.code===200){
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }else{
                        this.$message.error('未获取路径');
                    }
                }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });          
                });
        }
    }

}
</script>

<style lang="less" scoped>
.page{
    border:#DDDDDD solid 1px;
    width:90%;
    margin-top:10px;
    // height:50px;
    margin-left:5%;
    border-radius:10px;
}
.name{
    margin-top:15px;
    margin-left:20px;
    font-size:20px;
    font-weight: 600;
}
.fcomment{
    // border-bottom:#CACCCF solid 1px;
    width:90%;
    overflow: hidden;
    margin-left:5%;
    font-size:15px;
    margin-left:80px;
}
.time{
    margin-left:120px;
    font-size:12px;
    color: #707070;
    margin-top:-30px;
}
.rrusername{
    span{
        margin-left:20px;
        height:20px;
        line-height:20px;
    }
}
</style>