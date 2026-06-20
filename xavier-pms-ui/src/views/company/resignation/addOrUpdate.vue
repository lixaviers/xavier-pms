<template>
  <!-- 办理离职对话框 -->
  <el-dialog
    title="办理离职"
    v-model="visible"
    width="550px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="100px">
      <el-form-item label="员工" prop="userId">
        <el-input
          :model-value="employeeName"
          :disabled="!!fromEmployee"
          placeholder="请选择员工"
          readonly
          @click="handleSelectEmployee"
        >
          <template #suffix>
            <el-icon v-if="!fromEmployee" class="el-input__icon" style="cursor: pointer"><Search /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item label="离职类型" prop="resignationType">
        <el-select v-model="dataForm.resignationType" placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in resignationTypeList"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="离职日期" prop="resignationDate">
        <el-date-picker
          v-model="dataForm.resignationDate"
          type="date"
          placeholder="请选择"
          value-format="YYYY-MM-DD"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="离职原因" prop="resignationReason">
        <el-input
          v-model="dataForm.resignationReason"
          type="textarea"
          placeholder="请输入"
          maxlength="500"
        />
      </el-form-item>
      <el-form-item label="交接人" prop="handoverUserId">
        <el-input
          v-model="dataForm.handoverUserName"
          placeholder="请选择交接人"
          readonly
          @click="handleSelectHandover"
        >
          <template #suffix>
            <el-icon class="el-input__icon" style="cursor: pointer"><Search /></el-icon>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item label="交接事项" prop="handoverRemark">
        <el-input
          v-model="dataForm.handoverRemark"
          type="textarea"
          placeholder="请输入"
          maxlength="500"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="dataForm.remarks"
          type="textarea"
          placeholder="请输入"
          maxlength="500"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="submitForm" auto-insert-space>确定</el-button>
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>

    <select-employee ref="selectEmployeeRef" v-model="handoverUser" @update:modelValue="onHandoverSelected" />
    <select-employee ref="selectResignEmployeeRef" v-model="resignEmployee" @update:modelValue="onResignEmployeeSelected" />
  </el-dialog>
</template>

<script setup>
import { addResignationApi } from '@/api/modules/resignation'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const employeeName = ref('')
const handoverUser = ref({})
const resignEmployee = ref({})
const selectEmployeeRef = ref(null)
const selectResignEmployeeRef = ref(null)
const fromEmployee = ref(false)

// 离职类型选项
const resignationTypeList = [
  { value: 1, label: '主动离职' },
  { value: 2, label: '辞退' },
  { value: 3, label: '合同到期' },
  { value: 4, label: '其他' }
]

const data = reactive({
  dataForm: {
    userId: undefined,
    resignationType: undefined,
    resignationDate: undefined,
    resignationReason: undefined,
    handoverUserId: undefined,
    handoverUserName: undefined,
    handoverRemark: undefined,
    remarks: undefined
  },
  rules: {
    userId: [{ required: true, message: '请选择员工', trigger: 'change' }],
    resignationType: [{ required: true, message: '离职类型不能为空', trigger: 'change' }],
    resignationDate: [{ required: true, message: '离职日期不能为空', trigger: 'change' }]
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
    userId: undefined,
    resignationType: undefined,
    resignationDate: undefined,
    resignationReason: undefined,
    handoverUserId: undefined,
    handoverUserName: undefined,
    handoverRemark: undefined,
    remarks: undefined
  }
  employeeName.value = ''
  handoverUser.value = {}
  resignEmployee.value = {}
  fromEmployee.value = false
  proxy.resetForm('formRef')
}

/**
 * 选择交接人
 */
function handleSelectHandover() {
  selectEmployeeRef.value.init()
}

/**
 * 选择离职员工（从离职列表页新增时）
 */
function handleSelectEmployee() {
  if (fromEmployee.value) return
  selectResignEmployeeRef.value.init()
}

/**
 * 离职员工选择回调
 */
function onResignEmployeeSelected(item) {
  if (item && item.id) {
    dataForm.value.userId = item.id
    employeeName.value = `${item.nickName}（${item.employeeNumber}）`
  }
}

/**
 * 交接人选择回调
 */
function onHandoverSelected(item) {
  if (item && item.id) {
    dataForm.value.handoverUserId = item.id
    dataForm.value.handoverUserName = item.nickName
  }
}

/**
 * 提交按钮
 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      addResignationApi(dataForm.value).then(() => {
        proxy.$modal.msgSuccess('操作成功')
        visible.value = false
        emits('refreshDataList')
      })
    }
  })
}

/**
 * 初始化
 * @param {Object|undefined} row 员工行数据（从员工列表页传入时直接填充）
 */
function init(row) {
  visible.value = true
  reset()
  if (row) {
    fromEmployee.value = true
    dataForm.value.userId = row.id
    employeeName.value = `${row.nickName}（${row.employeeNumber}）`
  } else {
    fromEmployee.value = false
  }
}

defineExpose({
  init
})
</script>
