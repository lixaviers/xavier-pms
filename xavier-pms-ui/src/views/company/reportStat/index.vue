<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="汇报类型" prop="reportType">
        <el-select v-model="queryParams.reportType" placeholder="全部" clearable style="width: 150px">
          <el-option label="日报" :value="1" />
          <el-option label="周报" :value="2" />
          <el-option label="月报" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围">
        <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始" end-placeholder="结束" value-format="YYYY-MM-DD" style="width: 240px" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="getDataList">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getDataList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" stripe border>
      <el-table-column label="序号" type="index" width="60" align="center" />
      <el-table-column label="姓名" align="center" prop="nickName" width="120" />
      <el-table-column label="部门" align="center" prop="deptName" show-overflow-tooltip />
      <el-table-column label="日报" align="center" prop="dailyCount" width="80">
        <template #default="scope">
          <span :style="{ color: scope.row.dailyCount > 0 ? '#67C23A' : '#F56C6C', fontWeight: 600 }">{{ scope.row.dailyCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="周报" align="center" prop="weeklyCount" width="80">
        <template #default="scope">
          <span :style="{ color: scope.row.weeklyCount > 0 ? '#67C23A' : '#F56C6C', fontWeight: 600 }">{{ scope.row.weeklyCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="月报" align="center" prop="monthlyCount" width="80">
        <template #default="scope">
          <span :style="{ color: scope.row.monthlyCount > 0 ? '#67C23A' : '#F56C6C', fontWeight: 600 }">{{ scope.row.monthlyCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合计" align="center" prop="totalCount" width="80">
        <template #default="scope">
          <span style="font-weight: 700; color: #303133">{{ scope.row.totalCount }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup name="ReportStat">
import { reportStatApi } from '@/api/modules/workReport'

const { proxy } = getCurrentInstance()
const loading = ref(false)
const dataList = ref([])
const showSearch = ref(true)
const dateRange = ref(null)

const data = reactive({
  queryParams: { reportType: undefined, startDate: '', endDate: '' }
})
const { queryParams } = toRefs(data)

function getDataList() {
  loading.value = true
  const params = { ...queryParams.value }
  if (dateRange.value) {
    params.startDate = dateRange.value[0]
    params.endDate = dateRange.value[1]
  }
  reportStatApi(params).then(res => {
    dataList.value = res || []
  }).finally(() => { loading.value = false })
}

function resetQuery() {
  dateRange.value = null
  queryParams.value = { reportType: undefined, startDate: '', endDate: '' }
  getDataList()
}

onMounted(() => { getDataList() })
</script>
