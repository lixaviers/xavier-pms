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
      <el-form-item label="类型名称" prop="typeName">
        <el-input v-model="dataForm.typeName" max="100" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="排序" prop="sortNumber">
        <el-input-number
          v-model="dataForm.sortNumber"
          :min="1"
          :max="999999"
          :precision="0"
          placeholder="请输入"
          :controls="false"
          style="width: 100%"
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
        <el-button type="primary" @click="submitForm" auto-insert-space
          >确定</el-button
        >
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {
  addOrUpdateAnnouncementTypeApi,
  getAnnouncementTypeApi
} from '@/api/modules/announcementType'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')

const data = reactive({
  dataForm: {},
  rules: {
    typeName: [{ required: true, message: '请输入', trigger: 'blur' }],
    sortNumber: [{ required: true, message: '请输入', trigger: 'blur' }]
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
      addOrUpdateAnnouncementTypeApi(dataForm.value).then((response) => {
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
    title.value = '编辑类型'
    dataForm.value = await getAnnouncementTypeApi(id)
  } else {
    reset()
    title.value = '新增类型'
  }
}

defineExpose({
  init
})
</script>
