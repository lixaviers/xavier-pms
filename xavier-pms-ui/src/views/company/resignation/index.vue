<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="姓名" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工号" prop="employeeNumber">
        <el-input
          v-model="queryParams.employeeNumber"
          placeholder="请输入"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="离职类型" prop="resignationType">
        <el-select
          v-model="queryParams.resignationType"
          placeholder="请选择"
          clearable
          style="width: 200px"
        >
          <el-option
            v-for="item in resignationTypeList"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          :loading="loading"
          type="primary"
          icon="Search"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Plus"
          @click="handleAdd()"
          v-hasPermi="['company:resignation:add']"
          >新增</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getDataList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="dataList"
      stripe
    >
      <el-table-column label="工号" align="center" prop="employeeNumber" width="120" />
      <el-table-column label="姓名" align="center" prop="nickName" />
      <el-table-column label="部门" align="center" prop="deptName" show-overflow-tooltip />
      <el-table-column label="职位" align="center" prop="postName" show-overflow-tooltip />
      <el-table-column label="离职类型" align="center" prop="resignationTypeDesc" width="100" />
      <el-table-column label="离职日期" align="center" prop="resignationDate" width="120" />
      <el-table-column label="离职原因" prop="resignationReason" show-overflow-tooltip />
      <el-table-column label="交接人" align="center" prop="handoverUserName" width="100" />
      <el-table-column label="交接事项" prop="handoverRemark" show-overflow-tooltip />
      <el-table-column label="备注" prop="remarks" show-overflow-tooltip />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      />
      <el-table-column
        label="操作"
        width="80"
        align="center"
        fixed="right"
      >
        <template #default="scope">
          <el-dropdown @command="(cmd) => handleCommand(cmd, scope.row)">
            <el-button link type="primary" icon="More" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="detail" icon="View">详情</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNo"
      v-model:limit="queryParams.pageSize"
      @pagination="getDataList"
    />

    <!-- 离职详情对话框 -->
    <el-dialog
      title="离职详情"
      v-model="detailVisible"
      width="550px"
      append-to-body
    >
      <el-descriptions :column="1" border>
        <el-descriptions-item label="工号">{{ detailData.employeeNumber }}</el-descriptions-item>
        <el-descriptions-item label="姓名">{{ detailData.nickName }}</el-descriptions-item>
        <el-descriptions-item label="部门">{{ detailData.deptName }}</el-descriptions-item>
        <el-descriptions-item label="职位">{{ detailData.postName }}</el-descriptions-item>
        <el-descriptions-item label="离职类型">{{ detailData.resignationTypeDesc }}</el-descriptions-item>
        <el-descriptions-item label="离职日期">{{ detailData.resignationDate }}</el-descriptions-item>
        <el-descriptions-item label="离职原因">{{ detailData.resignationReason || '-' }}</el-descriptions-item>
        <el-descriptions-item label="交接人">{{ detailData.handoverUserName || '-' }}</el-descriptions-item>
        <el-descriptions-item label="交接事项">{{ detailData.handoverRemark || '-' }}</el-descriptions-item>
        <el-descriptions-item label="备注">{{ detailData.remarks || '-' }}</el-descriptions-item>
        <el-descriptions-item label="创建时间">{{ detailData.createTime }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <!-- 办理离职对话框 -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />
  </div>
</template>
<script setup name="Resignation">
import { queryResignationApi, getResignationApi } from '@/api/modules/resignation'
import addOrUpdate from './addOrUpdate.vue'

const { proxy } = getCurrentInstance()

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const detailVisible = ref(false)
const detailData = ref({})
const addOrUpdateRef = ref()

// 离职类型选项
const resignationTypeList = [
  { value: 1, label: '主动离职' },
  { value: 2, label: '辞退' },
  { value: 3, label: '合同到期' },
  { value: 4, label: '其他' }
]

const data = reactive({
  queryParams: {
    pageNo: 1,
    pageSize: 10,
    nickName: undefined,
    employeeNumber: undefined,
    resignationType: undefined
  }
})

const { queryParams } = toRefs(data)

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  queryResignationApi(queryParams.value)
    .then((response) => {
      dataList.value = response.records
      total.value = response.total
    })
    .finally(() => {
      loading.value = false
    })
}

/**
 * 搜索按钮操作
 */
function handleQuery() {
  queryParams.value.pageNo = 1
  getDataList()
}

/**
 * 重置按钮操作
 */
function resetQuery() {
  proxy.resetForm('queryRef')
  handleQuery()
}

/**
 * 新增按钮操作
 */
function handleAdd() {
  addOrUpdateRef.value.init()
}

/**
 * 操作命令分发
 */
async function handleCommand(command, row) {
  if (command === 'detail') {
    detailData.value = await getResignationApi(row.id)
    detailVisible.value = true
  }
}

onMounted(() => {
  getDataList()
})
</script>
