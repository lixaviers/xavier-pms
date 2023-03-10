<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="工号" prop="employeeNumber">
        <el-input
          v-model="queryParams.employeeNumber"
          placeholder="请输入"
          clearable
          style="width: 120px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入"
          clearable
          style="width: 120px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入"
          clearable
          style="width: 150px"
          @keyup.enter="handleQuery"
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
      <el-col :span="1.5">
        <el-button
          :disabled="multiple"
          type="success"
          plain
          icon="Check"
          @click="handleApproval()"
          v-hasPermi="['system:post:add']"
          >审批通过</el-button
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
      border
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="工号"
        align="center"
        prop="employeeNumber"
        width="120"
      />
      <el-table-column label="姓名" align="center" prop="nickName" />
      <el-table-column label="手机号" align="center" prop="mobile" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="性别" prop="gender" align="center" width="80" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      />
      <el-table-column
        label="操作"
        width="150"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="success"
            icon="Check"
            @click="handleApproval(scope.row.id)"
            v-hasPermi="['system:post:edit']"
            >审批通过</el-button
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
  </div>
</template>
<script setup>
import { queryUserApprovalApi, approvalUserApi } from '@/api/system/user'

const { proxy } = getCurrentInstance()

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)

const data = reactive({
  queryParams: {
    pageNo: 1,
    pageSize: 20,
    postName: undefined,
    status: undefined
  }
})

const { queryParams } = toRefs(data)

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  queryUserApprovalApi(queryParams.value)
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
 * 多选框选中数据
 */
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/**
 * 审批通过操作
 */
function handleApproval(id) {
  const idList = id ? [id] : ids.value
  proxy.$modal
    .confirm('是否确认审批通过？')
    .then(function () {
      return approvalUserApi(idList)
    })
    .then(() => {
      proxy.$modal.msgSuccess('审批成功')
      getDataList()
    })
    .catch(() => {})
}

onMounted(() => {
  getDataList()
})
</script>
