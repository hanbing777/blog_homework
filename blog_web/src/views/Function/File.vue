<template>
  <div>
    <el-card>
      <div class="box">
        <div class="filter-container">
          <el-button type="primary" class="butT" @click="openSave()"
            >添加</el-button
          >
          <!-- <el-button type="danger" plain @click="deletequery()"
            >批量删除</el-button
          > -->
        </div>
        <el-table
          size="small"
          current-row-key="id"
          :data="dataList"
          stripe
          highlight-current-row
          :row-class-name="tableRowClassName"
          @selection-change="handleSelectionChange"
        >
          <!-- <el-table-column type="selection" width="55"></el-table-column> -->
          <el-table-column
            type="index"
            align="center"
            label="序号"
          ></el-table-column>
          <el-table-column
            prop="fname"
            label="文件名称"
            align="center"
          ></el-table-column>
          <el-table-column
            prop="ftype"
            label="文件类型"
            align="center"
          ></el-table-column>
          <el-table-column label="时间" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.created | dateFormat }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <a
                :href="'/api/downFile/' + scope.row.id"
                download
                @click.stop
              >
              <!-- <el-button type="primary" size="mini" @click="updateBrandTest(scope.row)">下载</el-button> -->
              <el-button
                type="primary"
                size="mini"
                >下载</el-button
              >
              </a>
              <el-button
                size="mini"
                type="danger"
                @click="deleteBrand(scope.row)"
                style="margin-left: 30px"
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
        <!-- 新增标签弹层 -->
        <div class="add-form">
          <el-dialog title="新增文件" :visible.sync="dialogFormVisible">
            <el-form
              :model="ruleForm"
              ref="ruleForm"
              label-width="100px"
              class="demo-ruleForm"
              :rules="rules"
            >
              <el-form-item label="上传文件">
                <el-upload
                  class="upload-demo"
                  drag
                  action="/api/admin/attach/upFile"
                  :on-success="handleAvatarSuccess"
                  :before-upload="beforeAvatarUpload"
                  :headers="headers"
                >
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">
                    将文件拖到此处，或<em>点击上传</em>
                  </div>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <!-- <el-button type="primary" @click="submitForm('ruleForm')"
                  >立即上传</el-button
                > -->
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </div>
      </div></el-card
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      headers: {
        Authorization:
          localStorage.getItem("tokenHead") +
          " " +
          localStorage.getItem("token"),
        Accept: "application/json,text/plain,*/*",
      },
      dataList: [
        {
          id: 1,
          fname: "类别测试数据",
          ftype: "书名测试数据",
          download: 1,
          fkey: "下载路径",
        },
        {
          id: 1,
          bookname: "1类别测试数据",
          name: "1书名测试数据",
          download: 1,
          path: "1下载路径",
        },
      ], //当前页要展示的分页列表数据
      currentPage1: 1,
      total: 100, //总数量
      pageSize: 5, //每页数量
      // 批量删除
      deletedata: [],
      dialogFormVisible: false, //增加表单是否可见
      ruleForm: {
        bookname: "",
        name: "", //文件名
        download: 0,
        path: "", //下载路径
      },
      rules: {
        bookname: [
          { required: true, message: "请重新上传文件", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.handleCurrentChange();
  },

  methods: {
    // 分页
    async handleSizeChange(val) {
      //${this.currentPage1}/${val}
      // console.log("123",this.currentPage1,this.pageSize)
      this.pageSize = val;
      const result = await this.$http.get(
        `/admin/attach/likeAndPage/${this.currentPage1}/${val}`
      );
      if (result.data.code == 200) {
        this.tableData = result.data.obj.records;
        this.total = result.data.obj.total;
      } else {
        this.$message.error("未获取信息");
      }
    },
    //初始时调用
    async handleCurrentChange(val) {
      // /likeAndPage/{page}/{size}
      // ${this.currentPage1}/${this.pageSize}
      const result = await this.$http.get(
        `/admin/attach/likeAndPage/${this.currentPage1}/${this.pageSize}`
      );
      if (result.data.code == 200) {
        this.dataList = result.data.obj.records;
        this.total = result.data.obj.total;
      } else {
        this.$message.error("未获取信息");
      }
    },
    // 下载
    async updateBrandTest(data) {
      const result = await this.$http.get(`/downFile/${data.id}`);
    },
    // 弹出添加窗口
    openSave() {
      this.dialogFormVisible = true;
      this.resetForm();
    },
    // 上传文件
    handleAvatarSuccess(res, file) {
      console.log(res, file);
      // 提醒上传文件成功的文字

      if (res.code == 200) {
        this.$message.success(res.messges);
        this.handleCurrentChange();
        this.dialogFormVisible = false;
        this.resetForm("ruleForm");
      } else {
        this.$message.error(res.messges);
      }
    },
    beforeAvatarUpload(file) {
      console.log(file);
      // this.$message.success("上传文件成功")
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 10MB!");
      }
      return isLt2M;
    },
    //添加(废弃)
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          console.log(this.ruleForm);
          // 第一步.将图片上传到服务器.
          var formdata = new FormData();
          formdata.append("file", $file);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 删除
    deleteBrand(data) {
      this.$confirm("此操作将永久删除该信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          const result = await this.$http.get(
            `/admin/attach/deleteById/${data.id}`
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
    // 箭头
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    handleSelectionChange(val) {
      this.mutlipleSelection = val;
      // console.log(val);
      this.deletedata = val;
    },
    // 批量删除（废弃）
    deletequery() {
      var newData = [];
      var that = this;
      console.log(newData);
      for (var i = 0; i < this.deletedata.length; i++) {
        newData.push(this.deletedata[i].id);
      }
    },
  },
};
</script>

<style>
</style>