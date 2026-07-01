<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="模板名称" prop="templateName">
        <el-input v-model="queryParams.templateName" placeholder="请输入" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="汇报类型" prop="reportType">
        <el-select v-model="queryParams.reportType" placeholder="全部" clearable style="width: 150px">
          <el-option label="日报" :value="1" />
          <el-option label="周报" :value="2" />
          <el-option label="月报" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="success" plain icon="Plus" @click="handleAddOrUpdate()">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()">删除</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getDataList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="模板名称" align="center" prop="templateName" show-overflow-tooltip />
      <el-table-column label="汇报类型" align="center" prop="reportTypeDesc" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.reportType === 1 ? 'primary' : scope.row.reportType === 2 ? 'success' : 'warning'" size="small">{{ scope.row.reportTypeDesc }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="默认模板" align="center" prop="isDefault" width="90">
        <template #default="scope">
          <el-tag :type="scope.row.isDefault ? 'success' : 'info'" size="small">{{ scope.row.isDefault ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="isEnable" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.isEnable ? 'success' : 'danger'" size="small">{{ scope.row.isEnable ? '启用' : '禁用' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createUserName" width="100" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip />
      <el-table-column label="操作" width="80" align="center" fixed="right">
        <template #default="scope">
          <el-dropdown @command="(cmd) => handleCommand(cmd, scope.row)">
            <el-button link type="primary" icon="More" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="edit" icon="Edit">修改</el-dropdown-item>
                <el-dropdown-item command="delete" icon="Delete">删除</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNo" v-model:limit="queryParams.pageSize" @pagination="getDataList" />

    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />
  </div>
</template>

<script setup name="ReportTemplate">
import addOrUpdate from './addOrUpdate.vue'
import { queryReportTemplateApi, deleteReportTemplateApi } from '@/api/modules/reportTemplate'

const { proxy } = getCurrentInstance()
const addOrUpdateRef = ref()
const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)

const data = reactive({
  queryParams: { pageNo: 1, pageSize: 10, templateName: undefined, reportType: undefined }
})
const { queryParams } = toRefs(data)

function getDataList() {
  loading.value = true
  queryReportTemplateApi(queryParams.value).then(res => {
    dataList.value = res.records
    total.value = res.total
  }).finally(() => { loading.value = false })
}

function handleQuery() {
  queryParams.value.pageNo = 1
  getDataList()
}

function resetQuery() {
  proxy.resetForm('queryRef')
  handleQuery()
}

function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id)
  single.value = selection.length !== 1
  multiple.value = !selection.length
}

function handleAddOrUpdate(id) {
  addOrUpdateRef.value.init(id)
}

function handleCommand(command, row) {
  if (command === 'edit') handleAddOrUpdate(row.id)
  else if (command === 'delete') handleDelete(row.id)
}

function handleDelete(id) {
  const idList = id ? [id] : ids.value
  proxy.$modal.confirm('是否确认删除该模板？').then(() => {
    return deleteReportTemplateApi(idList[0])
  }).then(() => {
    proxy.$modal.msgSuccess('删除成功')
    getDataList()
  }).catch(() => {})
}

onMounted(() => { getDataList() })
</script>
