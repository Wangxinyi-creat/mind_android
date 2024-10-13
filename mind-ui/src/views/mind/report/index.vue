<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="85px">
      <el-form-item label="测评表ID" prop="assessmentId">
        <el-input
          v-model="queryParams.assessmentId"
          placeholder="请输入测评表ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="测评表名称" prop="assessmentName">
        <el-input
          v-model="queryParams.assessmentName"
          placeholder="请输入测评表名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="reportList">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="报告ID" align="center" prop="reportId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="测评表ID" align="center" prop="assessmentId" />
      <el-table-column label="测评表名称" align="center" prop="assessmentName" />
      <el-table-column label="测评结果" align="center" prop="result" />
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
    addReport,
    delReport,
    getReport,
    listReport,
    updateReport
  } from "@/api/mind/report";

  export default {
  name: "Report",
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
      // 测试报告表格数据
      reportList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        assessmentId: null,
        assessmentName: null,
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询测试报告列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows;
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>
