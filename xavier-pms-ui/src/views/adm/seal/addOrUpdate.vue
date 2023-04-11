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
      <el-form-item label="印章名称" prop="sealName">
        <el-input v-model="dataForm.sealName" max="100" placeholder="请输入" />
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="handleCancel">取 消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { addOrUpdateSealApi, getSealApi } from '@/api/adm/seal'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')

const data = reactive({
  dataForm: {},
  rules: {
    sealName: [{ required: true, message: '请输入', trigger: 'blur' }]
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
      addOrUpdateSealApi(dataForm.value).then((response) => {
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
    title.value = '编辑印章'
    dataForm.value = await getSealApi(id)
  } else {
    reset()
    title.value = '新增印章'
  }
}

defineExpose({
  init
})
</script>
