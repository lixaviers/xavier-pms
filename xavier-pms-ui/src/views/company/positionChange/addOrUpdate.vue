<template>
  <!-- 职位/职称变动对话框 -->
  <el-dialog
    title="办理变动"
    v-model="visible"
    width="550px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="100px">
      <el-form-item label="员工">
        <el-input :model-value="employeeName" disabled />
      </el-form-item>
      <el-form-item label="变动类型" prop="changeType">
        <el-select v-model="dataForm.changeType" placeholder="请选择" style="width: 100%" @change="handleChangeType">
          <el-option
            v-for="item in changeTypeList"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="变动前">
        <el-input :model-value="beforeName" disabled />
      </el-form-item>
      <el-form-item label="变动后" prop="afterId">
        <el-select v-model="dataForm.afterId" placeholder="请选择" style="width: 100%" filterable>
          <el-option
            v-for="item in afterOptions"
            :key="item.id"
            :value="item.id"
            :label="item.postName || item.titleName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="变动日期" prop="changeDate">
        <el-date-picker
          v-model="dataForm.changeDate"
          type="date"
          placeholder="请选择"
          value-format="YYYY-MM-DD"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="变动原因" prop="changeReason">
        <el-input
          v-model="dataForm.changeReason"
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
  </el-dialog>
</template>

<script setup>
import { addPositionChangeApi } from '@/api/modules/positionChange'
import { queryPostApi } from '@/api/modules/post'
import { queryProfessionalTitleApi } from '@/api/modules/professionalTitle'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const employeeName = ref('')
const beforeName = ref('')
const afterOptions = ref([])

// 变动类型选项
const changeTypeList = [
  { value: 1, label: '职位变动' },
  { value: 2, label: '职称变动' }
]

const data = reactive({
  dataForm: {
    userId: undefined,
    changeType: undefined,
    beforeId: undefined,
    afterId: undefined,
    changeDate: undefined,
    changeReason: undefined,
    remarks: undefined
  },
  rules: {
    changeType: [{ required: true, message: '变动类型不能为空', trigger: 'change' }],
    afterId: [{ required: true, message: '请选择变动后', trigger: 'change' }],
    changeDate: [{ required: true, message: '变动日期不能为空', trigger: 'change' }]
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
    changeType: undefined,
    beforeId: undefined,
    afterId: undefined,
    changeDate: undefined,
    changeReason: undefined,
    remarks: undefined
  }
  employeeName.value = ''
  beforeName.value = ''
  afterOptions.value = []
  proxy.resetForm('formRef')
}

/**
 * 变动类型切换
 */
async function handleChangeType(val) {
  dataForm.value.afterId = undefined
  if (val === 1) {
    // 职位变动
    const { records } = await queryPostApi({ pageSize: 10000 })
    afterOptions.value = records
  } else if (val === 2) {
    // 职称变动
    const { records } = await queryProfessionalTitleApi({ pageSize: 10000 })
    afterOptions.value = records
  }
}

/**
 * 提交按钮
 */
function submitForm() {
  proxy.$refs['formRef'].validate((valid) => {
    if (valid) {
      addPositionChangeApi(dataForm.value).then(() => {
        proxy.$modal.msgSuccess('操作成功')
        visible.value = false
        emits('refreshDataList')
      })
    }
  })
}

/**
 * 初始化
 * @param {Object} row 员工行数据
 * @param {Number} changeType 变动类型（1-职位变动 2-职称变动），从员工列表页直接传入
 */
async function init(row, changeType) {
  visible.value = true
  reset()
  if (row) {
    dataForm.value.userId = row.id
    employeeName.value = `${row.nickName}（${row.employeeNumber}）`
    // 从员工列表页直接传入变动类型
    if (changeType) {
      dataForm.value.changeType = changeType
      if (changeType === 1) {
        beforeName.value = row.postName || '-'
        const { records } = await queryPostApi({ pageSize: 10000 })
        afterOptions.value = records
      } else if (changeType === 2) {
        beforeName.value = row.titleName || '-'
        const { records } = await queryProfessionalTitleApi({ pageSize: 10000 })
        afterOptions.value = records
      }
    }
  }
}

defineExpose({
  init
})
</script>
