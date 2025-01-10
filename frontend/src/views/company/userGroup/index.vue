<template>
  <div class="app-container">
    <div v-if="total > 0">
      <el-form
        :model="queryParams"
        ref="queryRef"
        :inline="true"
        v-show="showSearch"
      >
        <el-form-item label="组名称" prop="groupName">
          <el-input
            v-model="queryParams.groupName"
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
            v-hasPermi="['company:userGroup:add']"
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
            v-hasPermi="['company:userGroup:delete']"
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
        <el-table-column label="组名称" align="center" prop="groupName" />
        <el-table-column label="员工" align="center" min-width="200">
          <template #default="scope">
            <span
              v-for="item in scope.row.employeeList"
              :key="item.id"
              class="pr5"
            >
              <el-tag>{{ item.nickName }}</el-tag>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="备注" prop="remarks" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          width="180"
        />
        <el-table-column
          label="操作"
          width="180"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template #default="scope">
            <el-button
              link
              type="primary"
              icon="Edit"
              @click="handleAddOrUpdate(scope.row.id)"
              v-hasPermi="['company:userGroup:edit']"
              >修改</el-button
            >
            <el-button
              link
              type="danger"
              icon="Delete"
              @click="handleDelete(scope.row.id)"
              v-hasPermi="['company:userGroup:delete']"
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
    </div>

    <div v-else>
      <el-row>
        <el-col :span="12">
          <h1 style="margin-top: 40px">员工组</h1>
          <p class="mt20" style="color: var(--el-color-info)">
            你可以创建员工组，并将任意成员添加至一个或多个员工组内。通过和其他功能结合，可以实现更灵活的员工管理。
          </p>
          <el-button
            class="mt20"
            type="success"
            plain
            icon="Plus"
            @click="handleAddOrUpdate()"
            v-hasPermi="['company:userGroup:add']"
            >新增员工组</el-button
          >
        </el-col>
        <el-col :span="12"
          ><el-empty description="TODO 选择员工图片" />
        </el-col>
      </el-row>
    </div>
    <!-- 添加或修改对话框 -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList" />
  </div>
</template>
<script setup name="userGroup">
import addOrUpdate from './addOrUpdate.vue'
import { queryUserGroupApi, deleteUserGroupApi } from '@/api/modules/userGroup'

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
  queryUserGroupApi(queryParams.value)
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
    .confirm('是否确认删除员工组？')
    .then(function () {
      return deleteUserGroupApi(idList)
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
