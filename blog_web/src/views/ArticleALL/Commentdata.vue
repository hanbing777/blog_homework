<template>
  <div class="page-body">
    <el-table :data="tableData" border style="width: 100%" stripe>
      <el-table-column prop="author" label="评论人" width="130">
      </el-table-column>
      <el-table-column fixed prop="content" label="评论内容" width="360">
      </el-table-column>
      <el-table-column label="发布时间" width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.created | dateFormat }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="mail" label="评论人邮箱" width="170">
      </el-table-column>
      <el-table-column prop="url" label="评论人网址" width="200">
      </el-table-column>
      <el-table-column
        prop="status"
        label="评论状态"
        width="90"
        :formatter="statusShow"
      >
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="120">
        <template slot-scope="scope">
          <el-button
            @click="handleClick(scope.row)"
            type="warning"
            size="danger"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage1"
        :page-sizes="[1, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.handleCurrentChange();
  },
  methods: {
    // 修改表格状态:formatter="statusShow"
    statusShow(row, column, cellValue, index) {
      // console.log(row, column, cellValue, index);
      if (cellValue === "approved") {
        return "已发布";
      } else {
        return "未发布";
      }
    },
    handleClick(row) {
      this.$confirm("此操作将永久删除该信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          const result = await this.$http.get(
            `/admin/comments/deleteById/${row.coid}`
          );
          if (result.data.code == 200) {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.handleCurrentChange();
          } else {
            this.$message.error(result.data.messges);
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 分页
    async handleSizeChange(val) {
      ///admin/comments/likeAndPage/{page}/{size}
      // console.log("123",this.currentPage1,this.pageSize)
      this.pageSize = val;
      const result = await this.$http.get(
        `/admin/comments/likeAndPage/${this.currentPage1}/${val}`
      );
      if (result.data.code == 200) {
        this.tableData = result.data.obj.records;
        this.total = result.data.obj.total;
      } else {
        this.$message.error("未获取文章信息");
      }
    },
    //初始时调用
    async handleCurrentChange(val) {
      // /likeAndPage/{page}/{size}
      // ${this.currentPage1}/${this.pageSize}
      const result = await this.$http.get(
        `/admin/comments/likeAndPage/${this.currentPage1}/${this.pageSize}`
      );
      if (result.data.code == 200) {
        this.tableData = result.data.obj.records;
        this.total = result.data.obj.total;
      } else {
        this.$message.error("未获取文章信息");
      }
    },
  },

  data() {
    return {
      currentPage1: 1,
      total: 100, //总数量
      pageSize: 5, //每页数量
      tableData: [
        {
          content:
            "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
          created: "2022-12-10 20:06:01",
          author: "浏览量",
          categories: "所属分类",
          status: "已发布",
          mail: "2773243141@qq.com",
          url: "https://juejin.cn/",
          status: "审核通过",
        },
      ],
    };
  },
};
</script>
<style lang="less" scoped>
.page-body {
  background-color: #ffffff;
}
.pagination-container {
  background-color: #ffffff;
  padding-top: 10px;
  padding-bottom: 10px;
  text-align: center;
}
</style>