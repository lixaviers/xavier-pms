<template>
  <!-- 添加或修改对话框 -->
  <el-dialog
    :title="title"
    v-model="visible"
    width="500px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="80px">
      <el-form-item label="车辆名称" prop="carName">
        <el-input v-model="dataForm.carName" max="50" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="车牌号码" prop="carNumber">
        <el-input v-model="dataForm.carNumber" max="10" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="所属员工" prop="userId">
        <el-select
          v-model="dataForm.userId"
          style="width: 100%"
          filterable
          clearable
          placeholder="请选择"
        >
          <el-option
            v-for="item in employeeList"
            :key="item.id"
            :value="item.id"
            :label="item.nickName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否启用" prop="isEnable">
        <el-switch v-model="dataForm.isEnable" />
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
        <el-button type="primary" @click="submitForm" auto-insert-space
          >确定</el-button
        >
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { addOrUpdateCarApi, getCarApi } from '@/api/modules/car'
import { queryUserApi } from '@/api/modules/user'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')
const employeeList = ref([])

const data = reactive({
  dataForm: {},
  rules: {
    CarName: [{ required: true, message: '请输入', trigger: 'blur' }]
  }
})

const { dataForm, rules } = toRefs(data)

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
  dataForm.value = {
    isEnable: true
  }
  proxy.resetForm('formRef')
}

/**
 * 提交按钮
 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      addOrUpdateCarApi(dataForm.value).then((response) => {
        proxy.$modal.msgSuccess(`${!dataForm.value.id ? '新增' : '编辑'}成功`)
        visible.value = false
        emits('refreshDataList')
      })
    }
  })
}

/**
 * 查询员工列表
 */
async function getEmployeeList() {
  const { records } = await queryUserApi({ pageSize: 10000 })
  employeeList.value = records
}

/**
 * 初始化
 */
async function init(id) {
  visible.value = true
  getEmployeeList()
  if (id) {
    title.value = '编辑车辆'
    dataForm.value = await getCarApi(id)
  } else {
    reset()
    title.value = '新增车辆'
  }
}

defineExpose({
  init
})
</script>
