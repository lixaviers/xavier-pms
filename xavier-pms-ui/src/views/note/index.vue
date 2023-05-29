<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入"
          clearable
          style="width: 200px"
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
          type="success"
          plain
          icon="Plus"
          @click="handleAddOrUpdate()"
          v-hasPermi="['note:type:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete()"
          v-hasPermi="['note:type:delete']"
          >删除</el-button
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
      <el-table-column label="编号" align="center" prop="id" width="120" />
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="公告类型" align="center" prop="typeName" />
      <el-table-column
        label="开始时间"
        align="center"
        prop="startTime"
        width="160"
      />
      <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        width="160"
      />
      <el-table-column label="排序" align="center" prop="sortNumber" />
      <el-table-column label="状态" align="center" width="80">
        <template #default="scope">
          <el-tag v-if="scope.row.isEnable" type="success">启用</el-tag>
          <el-tag v-else type="danger">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remarks" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      />
      <el-table-column label="操作" width="180" align="center">
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleAddOrUpdate(scope.row.id)"
            v-hasPermi="['note:type:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="danger"
            icon="Delete"
            @click="handleDelete(scope.row.id)"
            v-hasPermi="['note:type:delete']"
            >删除</el-button
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

    <!-- 添加或修改对话框 -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />
  </div>
</template>
<script setup name="Post">
import addOrUpdate from './addOrUpdate.vue'
import {
  queryAnnouncementApi,
  deleteAnnouncementApi
} from '@/api/modules/announcement'

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
  queryParams: {
    pageNo: 1,
    pageSize: 10,
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
  queryAnnouncementApi(queryParams.value)
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
 * 新增/编辑按钮操作
 */
function handleAddOrUpdate(id) {
  addOrUpdateRef.value.init(id)
}

/**
 * 删除按钮操作
 */
function handleDelete(id) {
  const idList = id ? [id] : ids.value
  proxy.$modal
    .confirm('是否确认删除公告？')
    .then(function () {
      return deleteAnnouncementApi(idList)
    })
    .then(() => {
      proxy.$modal.msgSuccess('删除成功')
      getDataList()
    })
    .catch(() => {})
}

onMounted(() => {
  getDataList()
})
</script>
