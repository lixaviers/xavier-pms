<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="部门名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入部门名称"
          clearable
          style="width: 200px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
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
          @click="handleAddOrUpdate()"
          v-hasPermi="['system:dept:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-if="isExpandAll"
          type="info"
          plain
          icon="Sort"
          @click="toggleExpandAll"
          >折叠</el-button
        >
        <el-button v-else type="info" plain icon="Sort" @click="toggleExpandAll"
          >展开</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getDataList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      border
      :data="dataList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
    >
      <el-table-column label="部门编号" align="center" prop="id" width="120" />
      <el-table-column prop="deptName" label="部门名称" />
      <el-table-column prop="nickName" label="负责人" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="200"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleAddOrUpdate(scope.row.id)"
            v-hasPermi="['system:dept:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="success"
            icon="Plus"
            @click="handleAddOrUpdate(null, scope.row.id)"
            v-hasPermi="['system:dept:add']"
            >新增</el-button
          >
          <el-button
            v-if="!scope.row.children"
            link
            type="danger"
            icon="Delete"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['system:dept:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改对话框 -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />
  </div>
</template>

<script setup name="Dept">
import addOrUpdate from './addOrUpdate.vue'
import { queryDepartmentApi, deleteDepartmentApi } from '@/api/company/dept'

const { proxy } = getCurrentInstance()
const addOrUpdateRef = ref()

// 数据列表
const dataList = ref([])
const loading = ref(true)
const showSearch = ref(true)
const isExpandAll = ref(true)
const refreshTable = ref(true)

const data = reactive({
  queryParams: {
    deptName: undefined,
    pageSize: 10000
  }
})

const { queryParams } = toRefs(data)

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  queryDepartmentApi(queryParams.value).then((response) => {
    dataList.value = proxy.handleTree(response.records, 'id')
    loading.value = false
  })
}

/** 搜索按钮操作 */
function handleQuery() {
  getDataList()
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm('queryRef')
  handleQuery()
}
/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false
  isExpandAll.value = !isExpandAll.value
  nextTick(() => {
    refreshTable.value = true
  })
}

/**
 * 新增/编辑按钮操作
 */
function handleAddOrUpdate(id, parentId) {
  addOrUpdateRef.value.init(id, parentId)
}

/** 删除按钮操作 */
function handleDelete(id) {
  proxy.$modal
    .confirm('是否确认删除部门?')
    .then(function () {
      return deleteDepartmentApi(id)
    })
    .then(() => {
      getDataList()
      proxy.$modal.msgSuccess('删除成功')
    })
    .catch(() => {})
}

onMounted(() => {
  getDataList()
})
</script>
