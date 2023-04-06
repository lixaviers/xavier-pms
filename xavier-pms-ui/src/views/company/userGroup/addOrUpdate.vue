<template>
  <!-- 添加或修改对话框 -->
  <el-drawer
    :title="title"
    v-model="visible"
    size="600px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="80px">
      <el-form-item label="组名称" prop="groupName">
        <el-input v-model="dataForm.groupName" max="50" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="员工" prop="employeeList">
        <el-input
          v-model="employee"
          @click="handleSelect"
          readonly
          placeholder="请选择员工"
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
        <el-button @click="handleCancel">取 消</el-button>
      </div>
    </template>
    <select-employee ref="selectEmployeeRef" v-model="employeeList" multiple />
  </el-drawer>
</template>

<script setup>
import {
  addOrUpdateUserGroupApi,
  getUserGroupApi
} from '@/api/company/userGroup'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')
const employee = ref('')
const employeeList = ref([])
const selectEmployeeRef = ref(null)

const data = reactive({
  dataForm: {},
  rules: {
    groupName: [{ required: true, message: '请输入', trigger: 'blur' }],
    employeeList: [{ required: true, message: '请选择', trigger: 'change' }]
  }
})

const { dataForm, rules } = toRefs(data)

watch(employeeList, (val) => {
  let str = ''
  if (val && val.length > 0) {
    val.forEach((item) => {
      str += item.nickName + ','
    })
    str = str.substring(0, str.length - 1)
  }
  employee.value = str
  dataForm.value.employeeList = val
})

/**
 * 取消按钮
 */
function handleCancel() {
  visible.value = false
  reset()
}

/**
 * 表单重置
 */
function reset() {
  dataForm.value = {}
  employeeList.value = []
  proxy.resetForm('formRef')
}

/**
 * 选择员工
 */
function handleSelect() {
  selectEmployeeRef.value.init(employeeList.value)
}

/**
 * 提交按钮
 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      addOrUpdateUserGroupApi(dataForm.value).then((response) => {
        proxy.$modal.msgSuccess(`${!dataForm.value.id ? '新增' : '编辑'}成功`)
        visible.value = false
        emits('refreshDataList')
      })
    }
  })
}

/**
 * 初始化
 */
async function init(id) {
  visible.value = true
  if (id) {
    title.value = '编辑员工组'
    dataForm.value = await getUserGroupApi(id)
    employeeList.value = dataForm.value.employeeList
  } else {
    reset()
    title.value = '新员工组'
  }
}

defineExpose({
  init
})
</script>
