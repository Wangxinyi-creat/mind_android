<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发送者ID" prop="senderId">
        <el-input
          v-model="queryParams.senderId"
          placeholder="请输入发送者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接收者ID" prop="recipientId">
        <el-input
          v-model="queryParams.recipientId"
          placeholder="请输入接收者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="inboxList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="信件ID" align="center" prop="messageId"/>
      <el-table-column label="发送者ID" align="center" prop="senderId"/>
      <el-table-column label="接收者ID" align="center" prop="recipientId"/>
      <el-table-column label="信件内容" align="center" prop="messageContent"/>
      <el-table-column label="是否匿名" align="center" prop="anonymous">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mind_type_yes_no" :value="scope.row.anonymous"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.senderId != 1 && scope.row.reply != 1"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['mind:inbox:edit']"
          >回复
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改心晴信箱对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="信件内容">
          <el-input
            type="textarea"
            v-model="form.messageContent" :min-height="300"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addInbox,
  delInbox,
  getInbox,
  listInbox,
  updateInbox
} from "@/api/mind/inbox";

export default {
  name: "Inbox",
  dicts: ['mind_type_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 心晴信箱表格数据
      inboxList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        senderId: null,
        recipientId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        messageContent: [
          {required: true, message: "信件内容不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询心晴信箱列表 */
    getList() {
      this.loading = true;
      listInbox(this.queryParams).then(response => {
        this.inboxList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        messageId: null,
        senderId: null,
        recipientId: null,
        messageContent: null,
        reply: null,
        anonymous: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.form.senderId = 1
      this.form.recipientId = row.senderId
      this.form.reply = 1
      this.open = true;
      this.title = "回复心晴信箱";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addInbox(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
  }
};
</script>
