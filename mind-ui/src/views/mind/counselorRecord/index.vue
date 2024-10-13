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
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="recordId" />
      <el-table-column label="咨询师ID" align="center" prop="counselorId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="学院" align="center" prop="college" />
      <el-table-column label="班级" align="center" prop="collegeClass" />
      <el-table-column label="联系方式" align="center" prop="phone" />


    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

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
      // 咨询师预约记录表格数据
      counselorRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
    this.getList();
  },
  methods: {
    /** 查询咨询师预约记录列表 */
    getList() {
      this.loading = true;
      listCounselorRecord(this.queryParams).then(response => {
        this.counselorRecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeAppointmentTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>
