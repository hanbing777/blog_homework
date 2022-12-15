<template>
  <div class="page-body">
    <el-card>
      <div
        style="
          background: #6e8cd7;
          color: #fff;
          font-size: 20px;
          padding: 10px;
          width: 90.3%;
        "
      >
        友链管理
      </div>
      <el-form
        :inline="true"
        :model="ruleForm"
        class="demo-form-inline"
        :rules="rules"
        ref="ruleForm"
        style="margin: 10px"
      >
        <el-form-item label="链接标题:" prop="name">
          <el-input
            v-model="ruleForm.name"
            placeholder="请输入链接标题"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="ruleForm.slug"
            placeholder="请输入链接地址"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="ruleForm.description"
            placeholder="请输入链接描述"
            maxlength="90"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="ruleForm.sort"
            placeholder="请输入链接排序（大于0）"
            type="number"
            :min="1"
            oninput="value=value.replace(/^0|[^0-9]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            style="background-color: #6e8cd7; color: #fff"
            @click="submitForm('ruleForm')"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
      <el-table :data="tableData" border style="width: 92%" stripe>
        <el-table-column type="index" width="50" label="序号">
        </el-table-column>
        <el-table-column prop="name" label="链接名称" width="360">
        </el-table-column>
        <el-table-column prop="slug" label="链接地址" width="250">
        </el-table-column>
        <el-table-column prop="description" label="链接描述" width="250">
        </el-table-column>
        <el-table-column prop="sort" label="排序" width="50"> </el-table-column>
        <el-table-column fixed="right" label="操作" width="150">
          <template slot-scope="scope">
            <el-button size="small" @click="handleClick(scope.row)"
              >编辑</el-button
            >
            <el-button
              type="danger"
              size="small"
              @click="handleClickDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="修改友链信息" :visible.sync="dialogTableVisible">
      <el-form
        :model="ruleForm1"
        class="demo-form-inline"
        :rules="rules"
        ref="ruleForm"
        style="margin: 10px"
        label-width="100px"
      >
        <el-form-item label="链接标题:" prop="name">
          <el-input
            v-model="ruleForm1.name"
            placeholder="请输入链接标题"
          ></el-input>
        </el-form-item>
        <el-form-item label="链接地址:">
          <el-input
            v-model="ruleForm1.slug"
            placeholder="请输入链接地址"
          ></el-input>
        </el-form-item>
        <el-form-item label="链接描述:">
          <el-input
            v-model="ruleForm1.description"
            placeholder="请输入链接描述"
            maxlength="90"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item label="链接排序:">
          <el-input
            v-model="ruleForm1.sort"
            placeholder="请输入链接排序（大于0）"
            type="number"
            :min="1"
            oninput="value=value.replace(/^0|[^0-9]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            style="background-color: #6e8cd7; color: #fff"
            @click="submitFormOne('ruleForm')"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
// 注意清除表单的值
export default {
  mounted() {
    this.getLinks();
  },
  methods: {
    // 显示分类数据
    async getLinks() {
      const result = await this.$http.get(`/admin/metas/getMetasByType/link`);
      if (result.data.code == 200) {
        this.tableData = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    handleClick(row) {
      this.$nextTick(() => {
        this.dialogTableVisible = true;
        let val = JSON.parse(JSON.stringify(row));
        this.ruleForm1 = val;
      });
    },
    // 新建友链
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/addMetas`,
            this.ruleForm
          );
          if (result.data.code == 200) {
            this.getLinks();
            this.ruleForm = {
              name: "",
              slug: "",
              description: "",
              sort: "",
              type: "link",
            };
          } else {
            this.$message.error(result.data.messges);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 修改友链
    submitFormOne(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const result = await this.$http.post(
            `/admin/metas/updateMetasById`,
            this.ruleForm1
          );
          if (result.data.code == 200) {
            this.getLinks();
            this.dialogTableVisible = false;
            this.ruleForm = {
              name: "",
              slug: "",
              description: "",
              sort: "",
              type: "link",
            };
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
      this.$confirm("此操作将永久删除该友链信息, 是否继续?", "提示", {
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
            this.getLinks();
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
        slug: "",
        description: "",
        sort: "",
        type: "link",
      },
      ruleForm1: {
        name: "",
        slug: "",
        description: "",
        sort: "",
        type: "link",
      },
      dialogTableVisible: false,
      rules: {
        name: [
          { required: true, message: "请输入链接标题", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        tags: [
          { required: true, message: "请输入标签名称", trigger: "blur" },
          // { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
      },
      tableData: [
        {
          name: "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
          slug: "https://github.com",
          description: "https://github.com",
          sort: "1",
        },
        {
          name: "上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄",
          slug: "https://github.com",
          description: "https://github.com",
          sort: "2",
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