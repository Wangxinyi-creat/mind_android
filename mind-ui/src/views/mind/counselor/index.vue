<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="85px">
      <el-form-item label="咨询师姓名" prop="counselorName">
        <el-input
          v-model="queryParams.counselorName"
          placeholder="请输入咨询师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['mind:counselor:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mind:counselor:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mind:counselor:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="counselorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="咨询师ID" align="center" prop="counselorId"/>
      <el-table-column label="用户ID" align="center" prop="userId"/>
      <el-table-column label="图片地址" align="center" prop="picUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.picUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="咨询师姓名" align="center" prop="counselorName"/>
      <el-table-column label="咨询师等级" align="center" prop="counselorLevel"/>
      <el-table-column label="资格证" align="center" prop="education"/>
      <el-table-column label="擅长" align="center" prop="speciality"/>
      <el-table-column label="可预约时间" align="center" prop="availableTime1" width="180"/>
      < label="可预约时间" align="center" prop="availableTime2" width="180" />
      <el-table-column label="可预约星期" align="center" prop="availableWeek">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.mind_week_type"
                    :value="scope.row.availableWeek ? scope.row.availableWeek.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mind:counselor:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mind:counselor:remove']"
          >删除
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

    <!-- 添加或修改咨询师对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID"/>
        </el-form-item>
        <el-form-item label="图片地址" prop="picUrl">
          <image-upload v-model="form.picUrl"/>
        </el-form-item>
        <el-form-item label="姓名" prop="counselorName">
          <el-input v-model="form.counselorName" placeholder="请输入咨询师姓名"/>
        </el-form-item>
        <el-form-item label="资格证" prop="education">
          <el-input v-model="form.education" placeholder="请输入资格证"/>
        </el-form-item>
        <el-form-item label="擅长" prop="speciality">
          <el-input v-model="form.speciality" placeholder="请输入擅长"/>
        </el-form-item>
        <el-form-item label="上午">
          <el-time-select size="mini"
            :picker-options="{
               start: '09:00',
               step: '01:00',
               end: '12:00',
           }"
            v-model="form.availableTime1[0]"
            placeholder="请选择可预约时间">
          </el-time-select>
          <el-time-select size="mini"
            :picker-options="{
               start: '09:00',
               step: '01:00',
               end: '12:00',
               minTime: form.availableTime1[0]
           }"
            v-model="form.availableTime1[1]"
            placeholder="请选择可预约时间">
          </el-time-select>
        </el-form-item>
        <el-form-item label="下午">
          <el-time-select size="mini"
            :picker-options="{
               start: '13:00',
               step: '01:00',
               end: '18:00',
           }"
            v-model="form.availableTime2[0]"
            placeholder="请选择可预约时间">
          </el-time-select>
          <el-time-select size="mini"
            :picker-options="{
               start: '13:00',
               step: '01:00',
               end: '18:00',
               minTime:form.availableTime2[0]
           }"
            v-model="form.availableTime2[1]"
            placeholder="请选择可预约时间">
          </el-time-select>
        </el-form-item>
        <el-form-item label="可预约星期" prop="availableWeek">
          <el-checkbox-group v-model="form.availableWeek">
            <el-checkbox
              v-for="dict in dict.type.mind_week_type"
              :key="dict.value"
              :label="dict.value">
              {{ dict.label }}
            </el-checkbox>
          </el-checkbox-group>
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
  addCounselor,
  delCounselor,
  getCounselor,
  listCounselor,
  updateCounselor
} from "@/api/mind/counselor";

export default {
  name: "Counselor",
  dicts: ['mind_week_type'],
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
      // 咨询师表格数据
      counselorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        counselorName: null,
      },
      // 表单参数
      form: {
        availableTime1: ["09:00", "12:00"],
        availableTime2: ["13:00", "18:00"]
      },
      // 表单校验
      rules: {
        picUrl: [
          { required: true, message: "图片地址不能为空", trigger: "blur" }
        ],
        userId: [
          {required: true, message: "用户ID不能为空", trigger: "blur"}
        ],
        counselorName: [
          {required: true, message: "咨询师姓名不能为空", trigger: "blur"}
        ],
        counselorLevel: [
          {required: true, message: "咨询师等级不能为空", trigger: "blur"}
        ],
        education: [
          {required: true, message: "资格证不能为空", trigger: "blur"}
        ],
        speciality: [
          {required: true, message: "擅长不能为空", trigger: "blur"}
        ],
        availableTime1: [
          {required: true, message: "上午可预约时间不能为空", trigger: "blur"}
        ],
        availableTime2: [
          {required: true, message: "下午可预约时间不能为空", trigger: "blur"}
        ],
        availableWeek: [
          {required: true, message: "可预约星期不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询咨询师列表 */
    getList() {
      this.loading = true;
      listCounselor(this.queryParams).then(response => {
        this.counselorList = response.rows;
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
        counselorId: null,
        userId: null,
        counselorName: null,
        counselorLevel: null,
        education: null,
        speciality: null,
        picUrl: null,
        availableTime1: ["09:00", "12:00"],
        availableTime2: ["13:00", "18:00"],
        availableWeek: [],
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.counselorId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加咨询师";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const counselorId = row.counselorId || this.ids
      getCounselor(counselorId).then(response => {
        this.form = response.data;
        this.form.availableWeek = this.form.availableWeek.split(",");
        this.form.availableTime1 = this.form.availableTime1.split("-");
        this.form.availableTime2 = this.form.availableTime2.split("-");
        this.open = true;
        this.title = "修改咨询师";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.availableWeek = this.form.availableWeek.join(",");
          console.log(this.form.availableTime1)
          this.form.availableTime1 = this.form.availableTime1.join("-");
          this.form.availableTime2 = this.form.availableTime2.join("-");
          if (this.form.counselorId != null) {
            updateCounselor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCounselor(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const counselorIds = row.counselorId || this.ids;
      this.$modal.confirm('是否确认删除咨询师编号为"' + counselorIds + '"的数据项？').then(function () {
        return delCounselor(counselorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mind/counselor/export', {
        ...this.queryParams
      }, `counselor_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
