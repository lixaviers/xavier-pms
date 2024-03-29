<template>
  <el-dialog
    title="选择员工"
    v-model="visible"
    width="800px"
    append-to-body
    :close-on-click-modal="false"
    class="select-employee-dialog"
  >
    <el-row>
      <el-col :span="6" class="dept-list">
        <el-input
          v-model="deptName"
          placeholder="请输入部门名称"
          clearable
          prefix-icon="Search"
          style="margin-bottom: 10px"
        />
        <el-tree
          :data="deptList"
          :props="{ label: 'deptName', children: 'children' }"
          :expand-on-click-node="false"
          :filter-node-method="filterNode"
          ref="deptTreeRef"
          node-key="id"
          highlight-current
          @node-click="handleNodeClick"
        />
      </el-col>
      <el-col :span="18" class="employee-container">
        <p>点击左边部门筛选员工，或者点击下面字母筛选</p>
        <p class="mt10">
          <el-button
            link
            v-for="i in 26"
            :key="'py' + i"
            @click="handleQueryPy(String.fromCharCode(96 + i))"
            style="margin-left: 0; margin-right: 5px"
            >{{ String.fromCharCode(64 + i) }}</el-button
          >
        </p>
        <div class="employee-list mt10">
          <div v-if="employeeList && employeeList.length > 0">
            <span
              v-for="(item, index) in employeeList"
              :key="index"
              class="employee-item"
            >
              <el-button @click="handleSelect(item)">{{
                item.nickName
              }}</el-button>
            </span>
          </div>
          <div v-else>暂无员工</div>
        </div>
        <div class="select-list">
          <div class="">
            <span class="fl"
              >已选择<span
                v-if="props.multiple && dataList && dataList.length > 0"
                >({{ dataList.length }})</span
              >
            </span>
            <el-button
              v-if="props.multiple && employeeList.length > 0"
              @click="handleSelectAll"
              class="fr"
              type="primary"
              size="small"
              >全选</el-button
            >
          </div>
          <div class="list-item">
            <span
              v-for="(item, index) in dataList"
              :key="index"
              class="select-detail"
            >
              <el-tag @close="handleDelete(index)" closable>{{
                item.nickName
              }}</el-tag>
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <template #footer>
      <div class="dialog-footer">
        <el-button
          v-if="props.multiple"
          type="primary"
          @click="submitForm"
          auto-insert-space
          >确定</el-button
        >
        <el-button @click="handleCancel">取消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { queryUserApi } from '@/api/modules/user'
import { getDeptListUtil } from '@/utils/xavier'
import { deepClone } from '@/utils'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const props = defineProps({
  modelValue: {
    type: [Object, Array],
    default: []
  },
  // 是否多选
  multiple: {
    type: Boolean,
    default: false
  }
})

const dataList = ref([])
const employeeList = ref([])
const visible = ref(false)
// 部门名称
const deptName = ref('')
// 部门列表
const deptList = ref([])

const data = reactive({
  queryParams: {
    pageNo: 1,
    pageSize: 2000
  }
})

const { queryParams } = toRefs(data)

/**
 * 查询员工列表
 */
function getEmployeeList() {
  queryUserApi(queryParams.value).then((response) => {
    employeeList.value = response.records
  })
}

function handleQueryPy(py) {
  queryParams.value.py = py
  getEmployeeList()
}

/**
 * 节点单击事件
 */
function handleNodeClick(data) {
  queryParams.value.departmentId = data.id
  queryParams.value.py = undefined
  getEmployeeList()
}

/**
 * 通过条件过滤节点
 */
const filterNode = (value, data) => {
  if (!value) return true
  return data.deptName.indexOf(value) !== -1
}

/**
 * 选择员工
 */
function handleSelect(item) {
  if (props.multiple) {
    // 多选
    if (dataList.value && dataList.value.length > 0) {
      let list = dataList.value.filter((obj) => obj.id === item.id)
      if (list && list.length > 0) {
        return
      }
    }
    dataList.value.push(item)
  } else {
    emits('update:modelValue', item) // to update v-model
    visible.value = false
  }
}

/**
 * 全选
 */
function handleSelectAll() {
  employeeList.value.forEach((item) => {
    handleSelect(item)
  })
}

/**
 * 移除选择
 */
function handleDelete(index) {
  dataList.value.splice(index, 1)
}

/**
 * 取消
 */
function handleCancel() {
  visible.value = false
}

/**
 * 确定选择
 */
function submitForm() {
  let data = []
  if (dataList.value && dataList.value.length > 0) {
    dataList.value.forEach((item) => {
      data.push({ id: item.id, nickName: item.nickName })
    })
  }
  emits('update:modelValue', data) // to update v-model
  visible.value = false
  emits('submitForm')
}

/**
 * 根据名称筛选部门树
 */
watch(deptName, (val) => {
  proxy.$refs['deptTreeRef'].filter(val)
})

// watch(
//   props.modelValue,
//   (val) => {
//     console.log(111, val)
//   },
//   { deep: true, immediate: true }
// )

/**
 * 初始化
 */
async function init(value) {
  employeeList.value = []
  if (value) {
    dataList.value = deepClone(value)
  } else {
    dataList.value = []
  }
  visible.value = true
  deptList.value = await getDeptListUtil()
}

defineExpose({
  init
})
</script>

<style lang="scss">
.el-overlay-dialog {
  .el-dialog.select-employee-dialog {
    .el-dialog__header {
      border-bottom: 1px solid #f0f0f0;
    }
    .el-dialog__body {
      padding: 10px 20px 20px;
    }
  }
}
.dept-list {
  border-right: 1px solid #f0f0f0;
}

.employee-container {
  padding-left: 20px;
  .employee-list {
    max-height: 240px;
    overflow-y: auto;
    padding-bottom: 10px;
    .employee-item {
      display: inline-block;
      margin: 5px 5px 0 0;
    }
  }
  .select-list {
    padding-top: 10px;
    border-top: 1px solid #f0f0f0;
    .fl {
      font-weight: 700;
    }

    .list-item {
      clear: both;
      max-height: 240px;
      overflow-y: auto;

      .select-detail {
        display: inline-block;
        margin: 5px 5px 0 0;
      }
    }
  }
}
</style>
