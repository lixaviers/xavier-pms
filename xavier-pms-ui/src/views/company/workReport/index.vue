<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="汇报类型" prop="reportType">
        <el-select v-model="queryParams.reportType" placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option label="日报" :value="1" />
          <el-option label="周报" :value="2" />
          <el-option label="月报" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入" clearable style="width: 200px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="全部" clearable style="width: 120px">
          <el-option label="草稿" :value="1" />
          <el-option label="已提交" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="success" plain icon="Plus" @click="handleAddOrUpdate()">填写汇报</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getDataList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" stripe>
      <el-table-column label="标题" align="center" prop="title" show-overflow-tooltip />
      <el-table-column label="汇报类型" align="center" prop="reportTypeDesc" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.reportType === 1 ? 'primary' : scope.row.reportType === 2 ? 'success' : 'warning'" size="small">{{ scope.row.reportTypeDesc }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="汇报人" align="center" prop="nickName" width="100" />
      <el-table-column label="部门" align="center" prop="deptName" show-overflow-tooltip />
      <el-table-column label="汇报日期" align="center" prop="reportDate" width="120" />
      <el-table-column label="状态" align="center" prop="statusDesc" width="80">
        <template #default="scope">
          <el-tag :type="scope.row.status === 2 ? 'success' : 'info'" size="small">{{ scope.row.statusDesc }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" />
      <el-table-column label="操作" width="80" align="center" fixed="right">
        <template #default="scope">
          <el-dropdown @command="(cmd) => handleCommand(cmd, scope.row)">
            <el-button link type="primary" icon="More" />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="view" icon="View">查看</el-dropdown-item>
                <el-dropdown-item command="edit" icon="Edit">编辑</el-dropdown-item>
                <el-dropdown-item v-if="scope.row.status === 1" command="submit" icon="Check">提交</el-dropdown-item>
                <el-dropdown-item command="delete" icon="Delete">删除</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNo" v-model:limit="queryParams.pageSize" @pagination="getDataList" />

    <!-- 新增/编辑弹窗 -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />

    <!-- 详情弹窗 -->
    <el-dialog v-model="detailVisible" title="汇报详情" width="700px" destroy-on-close>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="标题">{{ detail.title }}</el-descriptions-item>
        <el-descriptions-item label="汇报类型">{{ detail.reportTypeDesc }}</el-descriptions-item>
        <el-descriptions-item label="汇报人">{{ detail.nickName }}</el-descriptions-item>
        <el-descriptions-item label="部门">{{ detail.deptName }}</el-descriptions-item>
        <el-descriptions-item label="汇报日期">{{ detail.reportDate }}</el-descriptions-item>
        <el-descriptions-item label="状态">
          <el-tag :type="detail.status === 2 ? 'success' : 'info'" size="small">{{ detail.statusDesc }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="模板" v-if="detail.templateName">{{ detail.templateName }}</el-descriptions-item>
        <el-descriptions-item label="提交时间" v-if="detail.submitTime">{{ detail.submitTime }}</el-descriptions-item>
      </el-descriptions>

      <el-divider content-position="left">汇报内容</el-divider>
      <div v-if="detail.workSummary" style="margin-bottom: 16px">
        <div style="font-weight: 600; color: #303133; margin-bottom: 6px">工作总结</div>
        <div style="color: #606266; white-space: pre-wrap; line-height: 1.6">{{ detail.workSummary }}</div>
      </div>
      <div v-if="detail.workPlan" style="margin-bottom: 16px">
        <div style="font-weight: 600; color: #303133; margin-bottom: 6px">工作计划</div>
        <div style="color: #606266; white-space: pre-wrap; line-height: 1.6">{{ detail.workPlan }}</div>
      </div>
      <div v-if="detail.issues">
        <div style="font-weight: 600; color: #303133; margin-bottom: 6px">问题与建议</div>
        <div style="color: #606266; white-space: pre-wrap; line-height: 1.6">{{ detail.issues }}</div>
      </div>

      <!-- 评论 -->
      <el-divider content-position="left">评论 ({{ detail.comments?.length || 0 }})</el-divider>
      <div v-if="detail.comments && detail.comments.length > 0" style="margin-bottom: 16px">
        <div v-for="c in detail.comments" :key="c.id" style="display: flex; gap: 10px; margin-bottom: 12px">
          <el-avatar :size="28" style="background: #409EFF; color: #fff; flex-shrink: 0">{{ c.nickName?.substring(0, 1) }}</el-avatar>
          <div style="flex: 1">
            <div style="display: flex; gap: 10px; margin-bottom: 4px">
              <span style="font-size: 13px; font-weight: 600; color: #303133">{{ c.nickName }}</span>
              <span style="font-size: 12px; color: #c0c4cc">{{ c.createTime?.substring(0, 16) }}</span>
            </div>
            <div style="font-size: 14px; color: #606266; line-height: 1.6">{{ c.content }}</div>
          </div>
        </div>
      </div>
      <el-input v-model="commentContent" placeholder="写评论..." @keyup.enter="handleComment">
        <template #append>
          <el-button @click="handleComment" :disabled="!commentContent.trim()">发送</el-button>
        </template>
      </el-input>
    </el-dialog>
  </div>
</template>

<script setup name="WorkReport">
import addOrUpdate from './addOrUpdate.vue'
import { queryWorkReportApi, getWorkReportApi, deleteWorkReportApi, submitWorkReportApi, addCommentApi } from '@/api/modules/workReport'

const { proxy } = getCurrentInstance()
const addOrUpdateRef = ref()
const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const detailVisible = ref(false)
const detail = ref({})
const commentContent = ref('')

const data = reactive({
  queryParams: { pageNo: 1, pageSize: 10, reportType: 1, title: undefined, status: undefined }
})
const { queryParams } = toRefs(data)

function getDataList() {
  loading.value = true
  queryWorkReportApi(queryParams.value).then(res => {
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
  queryParams.value.reportType = 1
  handleQuery()
}

function handleAddOrUpdate(id) {
  addOrUpdateRef.value.init(id, queryParams.value.reportType)
}

async function handleView(row) {
  const res = await getWorkReportApi(row.id)
  detail.value = res
  commentContent.value = ''
  detailVisible.value = true
}

function handleSubmit(id) {
  proxy.$modal.confirm('确认提交该汇报？提交后不可撤回。').then(() => {
    return submitWorkReportApi(id)
  }).then(() => {
    proxy.$modal.msgSuccess('提交成功')
    getDataList()
  }).catch(() => {})
}

function handleDelete(id) {
  proxy.$modal.confirm('是否确认删除该汇报？').then(() => {
    return deleteWorkReportApi(id)
  }).then(() => {
    proxy.$modal.msgSuccess('删除成功')
    getDataList()
  }).catch(() => {})
}

function handleCommand(command, row) {
  if (command === 'view') handleView(row)
  else if (command === 'edit') handleAddOrUpdate(row.id)
  else if (command === 'submit') handleSubmit(row.id)
  else if (command === 'delete') handleDelete(row.id)
}

async function handleComment() {
  if (!commentContent.value.trim()) return
  await addCommentApi({ reportId: detail.value.id, content: commentContent.value })
  proxy.$modal.msgSuccess('评论成功')
  commentContent.value = ''
  const res = await getWorkReportApi(detail.value.id)
  detail.value = res
}

onMounted(() => { getDataList() })
</script>
