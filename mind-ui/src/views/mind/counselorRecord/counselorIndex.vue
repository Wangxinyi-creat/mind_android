<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="咨询师ID" prop="counselorId">
        <el-input
          v-model="queryParams.counselorId"
          placeholder="请输入咨询师ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="counselorRecordList">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="记录ID" align="center" prop="recordId"/>
      <el-table-column label="姓名" align="center" prop="name"/>
      <el-table-column label="学院" align="center" prop="college"/>
      <el-table-column label="班级" align="center" prop="collegeClass"/>
      <el-table-column label="联系方式" align="center" prop="phone"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="send(scope.row)"
          >回复
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize" s
      @pagination="getList"
    />

    <!-- 添加或修改评论对话框 -->
    <el-dialog :title="title" :lock-scroll="false" :visible.sync="open" width="550px" append-to-body>
      <div style="height:600px;overflow: auto;scrollbar-width: none;" ref="scrollWrapper">
        <div v-for="(item,index) in messageList" :key="index">
          <el-row :gutter="12">
            <el-col :span="18" v-if="counselorId != item.senderId">
              <div class="grid-content bg-purple">
                {{ item.messageContent }}
              </div>
            </el-col>
            <el-col :span="18" :offset="6" v-if="counselorId == item.senderId">
              <div class="grid-content bg-right">
                {{ item.messageContent }}
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-form ref="form" :model="form" label-width="0px">
          <el-form-item label="" prop="messageContent">
            <el-input v-model="form.messageContent" placeholder="请输入消息内容"/>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="submit">发 送</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  addCounselorRecord,
  delCounselorRecord,
  getCounselorRecord,
  listCounselorRecord,
  updateCounselorRecord
} from "@/api/mind/counselorRecord";
import {getCounselorByUserId} from "@/api/mind/counselor";
import {addMessage, getNextMessage, listAllMessage} from "@/api/mind/message";

export default {
  name: "CounselorRecord",
  dicts: ['mind_activity_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表单参数
      form: {},
      // 咨询师预约记录表格数据
      counselorRecordList: [],
      // 弹出层标题
      title: "",
      counselorId: null,
      // 是否显示弹出层
      open: false,
      messageList: [],
      lastMessageId: 0,
      // 预约状态时间范围
      daterangeAppointmentTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    this.getCounselorInfo();
  },
  methods: {
    /** 查询咨询师预约记录列表 */
    getList() {
      this.queryParams.counselorId = this.counselorId
      if (this.queryParams.counselorId == null) {
        return
      }
      this.loading = true;
      listCounselorRecord(this.queryParams).then(response => {
        this.counselorRecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getCounselorInfo();
      // this.queryParams.counselorId = userInfo
      this.queryParams.pageNum = 1;
    },
    getCounselorInfo() {
      getCounselorByUserId().then(response => {
        if (response.data != null) {
          this.counselorId = response.data.counselorId;
        }
        this.loading = false;
        this.getList();
      });
    },
    scrollToBottom() {
      const wrapper = this.$refs.scrollWrapper;
      wrapper.scrollTop = wrapper.scrollHeight;
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeAppointmentTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    cancel() {
      this.messageList = [];
      this.open = false
      this.reset();
      // this.scrollToBottom();
    },
    reset() {
      this.form = {
        senderId: null,
        recipientId: null,
        recordId: null,
        messageContent: null
      };
      this.resetForm("form");
    },
    getLastMessage() {
      getNextMessage(this.lastMessageId, this.form.recordId).then(res => {
        this.messageList = this.messageList.concat(res.data);
        if (this.messageList.length > 0) {
          this.lastMessageId = this.messageList[this.messageList.length - 1].messageId
        }
        this.form.messageContent = ""
        this.scrollToBottom();
      })
    },
    submit() {
      addMessage(this.form).then(res => {
        this.getLastMessage()
      })
    },
    send(row) {
      this.form.recordId = row.recordId
      this.form.senderId = this.counselorId
      this.form.recipientId = row.userId
      listAllMessage(this.form).then(response => {
        this.messageList = response.data;
        if (this.messageList.length > 0) {
          this.lastMessageId = this.messageList[this.messageList.length - 1].messageId
        }
        this.open = true
      })
    }
  }
};
</script>
<style>
.el-row {
  margin-bottom: 20px;
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-right {
  background: #95ec69;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  padding: 12px;
  display: flex;
  align-items: center;
  border-radius: 4px;
  vertical-align: middle;
  min-height: 36px;
  text-align: center;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.parent::-webkit-scrollbar {
  width: 0;
}

/* 隐藏水平滚动条 */
.parent::-webkit-scrollbar {
  height: 0;
}
</style>
