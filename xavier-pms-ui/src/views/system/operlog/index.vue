<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="操作名" prop="operateName">
        <el-input
          v-model="queryParams.operateName"
          placeholder="请输入"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请求地址" prop="requestUrl">
        <el-input
          v-model="queryParams.requestUrl"
          placeholder="请输入"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="请求方法" prop="requestMethod">
        <el-select
          v-model="queryParams.requestMethod"
          placeholder="请选择"
          clearable
          style="width: 120px"
        >
          <el-option label="GET" value="GET" />
          <el-option label="POST" value="POST" />
          <el-option label="PUT" value="PUT" />
          <el-option label="DELETE" value="DELETE" />
        </el-select>
      </el-form-item>
      <el-form-item label="结果码" prop="resultCode">
        <el-input
          v-model="queryParams.resultCode"
          placeholder="请输入"
          clearable
          style="width: 120px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户IP" prop="userIp">
        <el-input
          v-model="queryParams.userIp"
          placeholder="请输入"
          clearable
          style="width: 160px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作时间">
        <el-date-picker
          v-model="dateRange"
          style="width: 240px"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
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
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getDataList"
      ></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" border>
      <el-table-column label="编号" align="center" prop="id" width="80" />
      <el-table-column
        label="操作名"
        align="center"
        prop="operateName"
        width="160"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="请求方法"
        align="center"
        prop="requestMethod"
        width="100"
      >
        <template #default="scope">
          <el-tag
            :type="methodTagType(scope.row.requestMethod)"
            effect="plain"
            >{{ scope.row.requestMethod }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column
        label="请求地址"
        align="center"
        prop="requestUrl"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="结果码"
        align="center"
        prop="resultCode"
        width="80"
      >
        <template #default="scope">
          <el-tag v-if="scope.row.resultCode === 200" type="success"
            >{{ scope.row.resultCode }}</el-tag
          >
          <el-tag v-else type="danger">{{ scope.row.resultCode }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="结果提示"
        align="center"
        prop="resultMsg"
        width="160"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="用户IP" align="center" prop="userIp" width="140" />
      <el-table-column
        label="执行时长"
        align="center"
        prop="duration"
        width="100"
      >
        <template #default="scope">
          {{ scope.row.duration }} ms
        </template>
      </el-table-column>
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        width="180"
      />
      <el-table-column
        label="操作"
        width="80"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="View"
            @click="handleDetail(scope.row)"
            >详情</el-button
          >
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

    <!-- 详情对话框 -->
    <el-dialog title="操作日志详情" v-model="detailOpen" width="1000px" append-to-body>
      <el-descriptions :column="2" border>
        <el-descriptions-item label="编号">{{ detailData.id }}</el-descriptions-item>
        <el-descriptions-item label="操作名">{{ detailData.operateName }}</el-descriptions-item>
        <el-descriptions-item label="请求方法">{{ detailData.requestMethod }}</el-descriptions-item>
        <el-descriptions-item label="结果码">{{ detailData.resultCode }}</el-descriptions-item>
        <el-descriptions-item label="用户IP">{{ detailData.userIp }}</el-descriptions-item>
        <el-descriptions-item label="执行时长">{{ detailData.duration }} ms</el-descriptions-item>
        <el-descriptions-item label="开始时间" :span="2">{{ detailData.startTime }}</el-descriptions-item>
        <el-descriptions-item label="请求地址" :span="2">{{ detailData.requestUrl }}</el-descriptions-item>
        <el-descriptions-item label="Java方法" :span="2">{{ detailData.javaMethod }}</el-descriptions-item>
        <el-descriptions-item label="方法参数" :span="2">
          <pre class="method-args">{{ detailData.javaMethodArgs }}</pre>
        </el-descriptions-item>
        <el-descriptions-item label="结果提示" :span="2">{{ detailData.resultMsg }}</el-descriptions-item>
        <el-descriptions-item label="链路追踪ID" :span="2">{{ detailData.traceId }}</el-descriptions-item>
        <el-descriptions-item label="浏览器UA" :span="2">{{ detailData.userAgent }}</el-descriptions-item>
      </el-descriptions>
      <template #footer>
        <el-button @click="detailOpen = false">关 闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Operlog">
import { queryOperateLogApi } from '@/api/modules/operlog'

const { proxy } = getCurrentInstance()

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const total = ref(0)
const dateRange = ref([])

// 详情相关
const detailOpen = ref(false)
const detailData = ref({})

const data = reactive({
  queryParams: {
    pageNo: 1,
    pageSize: 10,
    operateName: undefined,
    requestUrl: undefined,
    requestMethod: undefined,
    resultCode: undefined,
    userIp: undefined
  }
})

const { queryParams } = toRefs(data)

/** 请求方法标签类型 */
function methodTagType(method) {
  const map = { GET: '', POST: 'success', PUT: 'warning', DELETE: 'danger' }
  return map[method] || 'info'
}

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  const params = { ...queryParams.value }
  // 处理日期范围
  if (dateRange.value && dateRange.value.length === 2) {
    params.createTimeFrom = dateRange.value[0]
    params.createTimeTo = dateRange.value[1]
  }
  queryOperateLogApi(params)
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
  dateRange.value = []
  proxy.resetForm('queryRef')
  handleQuery()
}

/**
 * 查看详情
 */
function handleDetail(row) {
  detailData.value = row
  detailOpen.value = true
}

onMounted(() => {
  getDataList()
})
</script>

<style scoped>
.method-args {
  margin: 0;
  white-space: pre-wrap;
  word-break: break-all;
  max-height: 200px;
  overflow-y: auto;
  font-size: 12px;
  line-height: 1.5;
}
</style>
