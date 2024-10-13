<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间" prop="activityTime">
        <el-date-picker clearable
                        v-model="queryParams.activityTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约状态" prop="appointmentStatus">
        <el-select v-model="queryParams.appointmentStatus" placeholder="请选择预约状态" clearable>
          <el-option
            v-for="dict in dict.type.mind_activity_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mind:article:add']"
        >新增
        </el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="activityList">
      <el-table-column label="活动ID" align="center" prop="activityId"/>
      <el-table-column label="活动名称" align="center" prop="activityName"/>
      <el-table-column label="日期" align="center" prop="activityTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center" prop="activityTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityTime, '{h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地点" align="center" prop="location"/>
      <el-table-column label="活动总结" align="center" prop="details"/>
      <el-table-column label="预约状态" align="center" prop="appointmentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mind_activity_status" :value="scope.row.appointmentStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="人数限制" align="center" prop="capacity"/>
      <el-table-column label="当前人数" align="center" prop="capacityNow"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.appointmentStatus == 0"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleStart(scope.row)"
                     v-hasPermi="['mind:activity:edit']"
          >开始活动
          </el-button>
          <el-button v-if="scope.row.appointmentStatus == 1"
                     size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleEnd(scope.row)"
                     v-hasPermi="['mind:activity:remove']"
          >结束活动
          </el-button>
          <el-button
                     size="mini"
                     type="text"
                     icon="el-icon-delete"
                     @click="handleDelete(scope.row)"
                     v-hasPermi="['mind:activity:remove']"
          >删除活动
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

    <!-- 添加或修改校园心理活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名称"/>
        </el-form-item>
        <el-form-item label="时间" prop="activityTime">
          <el-date-picker
            clearable
            v-model="form.activityTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="13:30:00"
            placeholder="请选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入地点"/>
        </el-form-item>
        <el-form-item label="人数限制" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入人数限制"/>
        </el-form-item>
        <el-form-item label="当前人数" prop="capacityNow">
          <el-input v-model="form.capacityNow" placeholder="请输入当前人数"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改校园心理活动对话框 -->
    <el-dialog title="总结" :visible.sync="openEnd" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="总结" prop="activityName">
          <el-input v-model="form.details" placeholder="请输入活动总结"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleEndSubmit">确 定</el-button>
        <el-button @click="cancelEnd">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addActivity,
  delActivity,
  getActivity,
  listActivity,
  updateActivity
} from "@/api/mind/activity";
import {getArticle} from "@/api/mind/article";

export default {
  name: "Activity",
  dicts: ['mind_activity_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 校园心理活动表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openEnd: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        activityName: null,
        activityTime: null,
        location: null,
        details: null,
        appointmentStatus: null,
      },
      // 表单参数
      form: {
        pageNum: 1,
        pageSize: 10,
        activityName: null,
        activityTime: null,
        location: null,
        details: null,
        appointmentStatus: null,
      },
      // 表单校验
      rules: {
        activityName: [
          {required: true, message: "活动名称不能为空", trigger: "blur"}
        ],
        activityTime: [
          {required: true, message: "时间不能为空", trigger: "blur"}
        ],
        location: [
          {required: true, message: "地点不能为空", trigger: "blur"}
        ],
        details: [
          {required: true, message: "详情不能为空", trigger: "blur"}
        ],
        appointmentStatus: [
          {required: true, message: "预约状态不能为空", trigger: "change"}
        ],
        capacity: [
          {required: true, message: "人数限制不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询校园心理活动列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancelEnd() {
      this.openEnd = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        activityId: null,
        activityName: null,
        activityTime: null,
        location: null,
        details: null,
        appointmentStatus: null,
        capacity: null,
        capacityNow: null,
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
    handleStart(row) {
      this.reset();
      const id = row.activityId

      getActivity(id).then(response => {
        this.form = response.data;
        this.form.appointmentStatus = "1"
        updateActivity(this.form).then(res => {
          this.$modal.msgSuccess("成功");
          this.open = false;
          this.getList();
        });
      });
    },
    handleEnd(row) {
      this.reset();
      const id = row.activityId
      getActivity(id).then(response => {
        this.form = response.data;
        this.form.appointmentStatus = "2"
        this.openEnd = true;
      });
    },
    handleEndSubmit() {
      updateActivity(this.form).then(response => {
        this.$modal.msgSuccess("成功");
        this.openEnd = false;
        this.getList();
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加校园心理活动";
    },
    handleDelete(row) {
      const activityIds = row.activityId || this.ids;
      this.$modal.confirm('是否确认删除校园心理活动编号为"' + activityIds + '"的数据项？').then(function() {
        return delActivity(activityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != null) {
            updateActivity(this.form).then(response => {
              this.$modal.msgSuccess("成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActivity(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
  }
};
</script>
