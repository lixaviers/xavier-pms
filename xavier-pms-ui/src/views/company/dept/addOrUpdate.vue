<template>
  <el-dialog
    :title="title"
    v-model="visible"
    width="500px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="80px">
      <el-form-item label="上级部门" prop="parentId">
        <el-tree-select
          v-model="dataForm.parentId"
          :data="dataList"
          :props="{
            value: 'id',
            label: 'deptName',
            children: 'children'
          }"
          value-key="id"
          placeholder="选择上级部门"
          check-strictly
        />
      </el-form-item>
      <el-form-item label="部门名称" prop="deptName">
        <el-input
          v-model="dataForm.deptName"
          placeholder="请输入部门名称"
          maxlength="100"
        />
      </el-form-item>
      <!-- TODO 选择用户 -->
      <el-form-item label="负责人" prop="userId">
        <el-input
          v-model="dataForm.userId"
          placeholder="请输入负责人姓名搜索"
          maxlength="20"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="dataForm.remarks"
          type="textarea"
          placeholder="请输入"
          maxlength="200"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {
  addOrUpdateDepartmentApi,
  getDepartmentApi,
  queryDepartmentApi
} from '@/api/company/dept'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')
const dataList = ref([])

const data = reactive({
  dataForm: {},
  rules: {
    parentId: [
      { required: true, message: '上级部门不能为空', trigger: 'blur' }
    ],
    deptName: [{ required: true, message: '部门名称不能为空', trigger: 'blur' }]
  }
})

const { dataForm, rules } = toRefs(data)

/** 取消按钮 */
function cancel() {
  visible.value = false
  reset()
}
/** 表单重置 */
function reset() {
  dataForm.value = {}
  proxy.resetForm('formRef')
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      addOrUpdateDepartmentApi(dataForm.value).then((response) => {
        proxy.$modal.msgSuccess(`${!dataForm.value.id ? '新增' : '编辑'}成功`)
        visible.value = false
        emits('refreshDataList')
      })
    }
  })
}

/**
 * 查询列表
 */
async function getDataList() {
  const { records } = await queryDepartmentApi({ pageSize: 10000 })
  const list = { id: '0', deptName: '顶级部门', children: [] }
  if (records && records.length > 0) {
    list.children = proxy.handleTree(records, 'id')
  }
  dataList.value = [list]
}

/**
 * 初始化
 */
async function init(id, parentId) {
  visible.value = true
  await getDataList()
  if (id) {
    title.value = '编辑部门'
    dataForm.value = await getDepartmentApi(id)
  } else {
    reset()
    if (parentId) {
      dataForm.value.parentId = parentId
    } else {
      dataForm.value.parentId = '0'
    }
    title.value = '新增部门'
  }
}

defineExpose({
  init
})
</script>
