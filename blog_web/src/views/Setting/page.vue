<template>
  <div style="">
    <div>
      <el-card shadow="hover">
        <div
          style="
            background: #33b86c;
            color: #fff;
            font-size: 20px;
            padding: 10px;
            width: 800px; ;
          "
        >
          全局设置
        </div>
        <el-table :data="tableData" style="width: 820px" stripe>
          <el-table-column prop="description" label="描述" width="180">
          </el-table-column>
          <el-table-column prop="name" label="名称" width="180">
          </el-table-column>
          <el-table-column prop="value" label="内容"> </el-table-column>
          <el-table-column fixed="right" label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click="handleOpen(scope.row)" type="text" size="small"
                >修改</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
          <el-form :model="ruleForm">
            <el-form-item label="描述:" :label-width="formLabelWidth">
              <el-input
                v-model="ruleForm.description"
                autocomplete="off"
                maxlength="30"
                show-word-limit
                type="textarea"
                placeholder="请输入描述"
              ></el-input>
            </el-form-item>
            <el-form-item label="名称:" :label-width="formLabelWidth">
              <el-input
                v-model="ruleForm.name"
                autocomplete="off"
                maxlength="90"
                show-word-limit
                type="textarea"
                placeholder="请输入名称"
              ></el-input>
            </el-form-item>
            <el-form-item label="内容:" :label-width="formLabelWidth">
              <el-input
                v-model="ruleForm.value"
                autocomplete="off"
                maxlength="90"
                show-word-limit
                type="textarea"
                placeholder="请输入内容(链接地址等)"
              ></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleClick()">确 定</el-button>
          </div>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          value: "2016-05-02",
          name: "王小虎",
          description: "上海市普陀区金沙江路 1518 弄",
        },
      ],
      formLabelWidth: "130px",
      ruleForm: {
        value: "",
        name: "",
        description: "",
      },
      options: [
        {
          value: "默认主题",
          label: "默认主题",
        },
      ],
      dialogFormVisible: false,
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
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    // 获取信息
    async getData() {
      const result = await this.$http.get(`/admin/options/getAll`);
      if (result.data.code == 200) {
        this.tableData = result.data.obj;
      } else {
        this.$message.error(result.data.messges);
      }
    },
    // 全局设置
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert(this.ruleForm);
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 打开弹框
    async handleOpen(val) {
      this.dialogFormVisible = true;

      let row = JSON.parse(JSON.stringify(val));
      this.ruleForm = row;
    },
    // 提交修改
    async handleClick() {
      const result = await this.$http.post(
        `/admin/options/updateByName`,
        this.ruleForm
      );
      if (result.data.code == 200) {
        this.getData();
        this.ruleForm = {
          value: "",
          name: "",
          description: "",
        };
        this.dialogFormVisible = false;
      } else {
        this.$message.error(result.data.messges);
      }
    },
  },
};
</script>

<style>
</style>