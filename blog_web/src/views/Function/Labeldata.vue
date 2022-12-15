<template>
  <div class="page-body">
    <!-- 分类列表 -->
    <div>
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span style="color: #6e8cd7">分类列表</span>
        </div>
        <el-form
          :inline="true"
          :model="ruleForm"
          class="demo-form-inline"
          :rules="rules"
          ref="ruleForm"
        >
          <el-form-item label="分类:" prop="name">
            <el-input
              v-model="ruleForm.name"
              placeholder="请输入分类名称"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              style="background-color: #33b86c; color: #fff"
              @click="submitForm('ruleForm')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
        <el-table :data="tableData" border style="width: 100%" stripe>
          <el-table-column type="index" label="序号" width="50">
          </el-table-column>
          <el-table-column prop="name" label="分类名称" width="300">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                @click="handleClickOne(scope.row)"
                type="text"
                size="danger"
                >修改</el-button
              >
              <el-button
                @click="handleClickDelete(scope.row)"
                type="text"
                size="danger"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="修改分类名称" :visible.sync="dialogTableVisible">
          <el-form
            :inline="true"
            :model="ruleForm1"
            class="demo-form-inline"
            :rules="rules"
            ref="ruleForm"
          >
            <el-form-item label="分类:" prop="name">
              <el-input
                v-model="ruleForm1.name"
                placeholder="请输入分类名称"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                style="background-color: #33b86c; color: #fff"
                @click="submitFormOne('ruleForm')"
                >保存</el-button
              >
            </el-form-item>
          </el-form>
        </el-dialog>
      </el-card>
    </div>
    <!-- 标签列表 -->
    <div>
      <el-card class="box-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span style="color: #ec407a">标签列表</span>
        </div>
        <el-form
          :inline="true"
          :model="formInline"
          class="demo-form-inline"
          :rules="rules"
          ref="formInline"
        >
          <el-form-item label="标签:" prop="name">
            <el-input
              v-model="formInline.name"
              placeholder="请输入标签名称"
            ></el-input>
          </el-form-item>
          <el-form-item>
            <el-button
              style="background-color: #ef5350; color: #fff"
              @click="onSubmit('formInline')"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
        <el-table :data="tableData1" border style="width: 100%" stripe>
          <el-table-column type="index" label="序号" width="50">
          </el-table-column>
          <el-table-column prop="name" label="标签名称" width="300">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="text"
                size="danger"
                >修改</el-button
              >
              <el-button
                @click="handleClickDelete1(scope.row)"
                type="text"
                size="danger"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="修改标签名称" :visible.sync="dialogTableVisible1">
          <el-form
            :inline="true"
            :model="formInline1"
            class="demo-form-inline"
            :rules="rules"
            ref="formInline"
          >
            <el-form-item label="标签:" prop="name">
              <el-input
                v-model="formInline1.name"
                placeholder="请输入标签名称"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                style="background-color: #ef5350; color: #fff"
                @click="onSubmit1('formInline')"
                >保存</el-button
              >
            </el-form-item>
          </el-form>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.getCategory();
    this.getTag();
  },
  methods: {
    // 显示分类数据
    async getCategory() {
      const result = await this.$http.get(
        `/admin/metas/getMetasByType/category`
      );
      if (result.data.code == 200) {
        this.tableData = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    // 显示标签数据
    async getTag() {
      const result = await this.$http.get(`/admin/metas/getMetasByType/tag`);
      if (result.data.code == 200) {
        this.tableData1 = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    //修改分类弹框
    handleClickOne(row) {
      this.dialogTableVisible = true;
      let val = JSON.parse(JSON.stringify(row));
      this.ruleForm1 = val;
    },
    // 修改标签弹框
    handleClick(row) {
      this.dialogTableVisible1 = true;
      let val = JSON.parse(JSON.stringify(row));
      this.formInline1 = val;
    },
    // 新建分类（记得清除表单内容）
    async submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/addMetas`,
            this.ruleForm
          );
          if (result.data.code == 200) {
            this.getCategory();
            this.ruleForm = { name: "", type: "category" };
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 修改分类
    submitFormOne(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/updateMetasById`,
            this.ruleForm1
          );
          if (result.data.code == 200) {
            this.getCategory();
            this.ruleForm1 = { name: "", type: "category" };
            this.dialogTableVisible = false;
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 删除分类
    handleClickDelete(row) {
      this.$confirm("此操作将永久删除该分类标签, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async () => {
          const result = await this.$http.get(
            `/admin/metas/deleteMetasById/${row.mid}`
          );
          if (result.data.code == 200) {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.getCategory();
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
    // 新建标签
    onSubmit(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/addMetas`,
            this.formInline
          );
          if (result.data.code == 200) {
            this.getTag();
            this.formInline = { name: "", type: "tag" };
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 修改标签
    onSubmit1(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/updateMetasById`,
            this.formInline1
          );
          if (result.data.code == 200) {
            this.getTag();
            this.formInline = { name: "", type: "tag" };
            this.dialogTableVisible1 = false;
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 删除标签
    handleClickDelete1(row) {
      this.$confirm("此操作将永久删除该分类标签, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(async() => {
          const result = await this.$http.get(
            `/admin/metas/deleteMetasById/${row.mid}`
          );
          if (result.data.code == 200) {
            this.$message({
              type: "success",
              message: "删除成功!",
            });
            this.getTag();
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
  },

  data() {
    return {
      ruleForm: {
        name: "",
        type: "category",
      },
      ruleForm1: {
        name: "",
        type: "category",
      },
      formInline: {
        name: "",
        type: "tag",
      },
      formInline1: {
        name: "",
        type: "tag",
      },
      dialogTableVisible: false,
      dialogTableVisible1: false,
      rules: {
        name: [
          { required: true, message: "请输入内容", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        tags: [
          { required: true, message: "请输入标签名称", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
      tableData: [
        {
          name: "java",
        },
      ],
      tableData1: [
        {
          name: "java",
        },
      ],
    };
  },
};
</script>
<style lang="less" scoped>
.page-body {
  display: flex;
}
.text {
  font-size: 20px;
}

.item {
  margin-bottom: 18px;
}
.clearfix {
  font-size: 20px;
  width: 100%;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 500px;
  margin-right: 20px;
}
</style>