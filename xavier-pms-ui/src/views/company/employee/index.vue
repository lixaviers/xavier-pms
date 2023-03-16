<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            prefix-icon="Search"
            style="margin-bottom: 10px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptList"
            :props="{ label: 'deptName', children: 'children' }"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="deptTreeRef"
            node-key="id"
            highlight-current
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
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
          <el-table-column
            label="性别"
            prop="gender"
            align="center"
            width="80"
          />
          <el-table-column label="部门名称" prop="deptName" align="center" />
          <el-table-column label="职位名称" prop="postName" align="center" />
          <el-table-column label="职称" prop="titleName" align="center" />
          <el-table-column
            label="创建时间"
            align="center"
            prop="createTime"
            width="180"
          />
          <el-table-column label="操作" width="150" align="center">
          </el-table-column>
        </el-table>

        <pagination
          v-show="total > 0"
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getDataList"
        />
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { queryUserApi } from '@/api/company/user'
import { queryDepartmentApi } from '@/api/company/dept'

const { proxy } = getCurrentInstance()

const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
// 部门名称
const deptName = ref('')
// 部门列表
const deptList = ref([])

const data = reactive({
  queryParams: {
    pageNo: 1,
    pageSize: 20
  }
})

const { queryParams } = toRefs(data)

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  queryUserApi(queryParams.value)
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
  queryParams.value.departmentId = undefined
  proxy.$refs.deptTreeRef.setCurrentKey(null)
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

/**
 * 查询部门列表
 */
async function getDeptList() {
  loading.value = true
  const response = await queryDepartmentApi(queryParams.value)
  deptList.value = proxy.handleTree(response.records, 'id')
}
/**
 * 节点单击事件
 */
function handleNodeClick(data) {
  queryParams.value.departmentId = data.id
  handleQuery()
}

/**
 * 通过条件过滤节点
 */
const filterNode = (value, data) => {
  if (!value) return true
  return data.deptName.indexOf(value) !== -1
}

/**
 * 根据名称筛选部门树
 */
watch(deptName, (val) => {
  proxy.$refs['deptTreeRef'].filter(val)
})

onMounted(async () => {
  await getDeptList()
  getDataList()
})
</script>
