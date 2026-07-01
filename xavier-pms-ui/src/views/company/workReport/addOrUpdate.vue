<template>
  <el-dialog v-model="visible" :title="isEdit ? '编辑汇报' : `填写${typeLabel}`" width="700px" destroy-on-close :close-on-click-modal="false" @close="handleClose">
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="90px" class="report-form">
      <el-form-item label="标题" prop="title">
        <el-input v-model="dataForm.title" placeholder="请输入汇报标题" maxlength="200" show-word-limit />
      </el-form-item>

      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="汇报类型" prop="reportType">
            <el-select v-model="dataForm.reportType" placeholder="请选择" style="width: 100%" :disabled="isEdit" @change="handleTypeChange">
              <el-option label="日报" :value="1" />
              <el-option label="周报" :value="2" />
              <el-option label="月报" :value="3" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="汇报日期" prop="reportDate">
            <el-date-picker v-model="dataForm.reportDate" type="date" placeholder="请选择" value-format="YYYY-MM-DD" style="width: 100%" :disabled-date="disabledDate" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="使用模板">
        <el-select v-model="dataForm.templateId" placeholder="选择模板（可选）" clearable style="width: 100%" @change="handleTemplateChange">
          <el-option v-for="t in templateList" :key="t.id" :label="t.templateName" :value="t.id" />
        </el-select>
      </el-form-item>

      <el-divider content-position="left">汇报内容</el-divider>

      <el-form-item label="工作总结">
        <el-input v-model="dataForm.workSummary" type="textarea" :rows="4" placeholder="请输入工作总结" />
      </el-form-item>
      <el-form-item label="工作计划">
        <el-input v-model="dataForm.workPlan" type="textarea" :rows="4" placeholder="请输入工作计划" />
      </el-form-item>
      <el-form-item label="问题与建议">
        <el-input v-model="dataForm.issues" type="textarea" :rows="3" placeholder="请输入问题与建议" />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button @click="handleSaveDraft">存草稿</el-button>
        <el-button type="primary" @click="handleSubmit">提交</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { addWorkReportApi, getWorkReportApi, submitWorkReportApi } from '@/api/modules/workReport'
import { queryReportTemplateApi } from '@/api/modules/reportTemplate'

const emit = defineEmits(['refreshDataList'])
const visible = ref(false)
const isEdit = ref(false)
const formRef = ref(null)
const templateList = ref([])

const dataForm = ref(getDefaultForm())

const rules = {
  title: [{ required: true, message: '请输入汇报标题', trigger: 'blur' }],
  reportType: [{ required: true, message: '请选择汇报类型', trigger: 'change' }],
  reportDate: [{ required: true, message: '请选择汇报日期', trigger: 'change' }]
}

const typeLabels = { 1: '日报', 2: '周报', 3: '月报' }
const typeLabel = computed(() => typeLabels[dataForm.value.reportType] || '汇报')

function getDefaultForm() {
  return {
    id: null, title: '', reportType: 1, templateId: null, reportDate: '',
    content: '', workSummary: '', workPlan: '', issues: '', status: 1
  }
}

function disabledDate(time) {
  // 不允许选择未来日期
  return time.getTime() > Date.now()
}

async function init(id, reportType) {
  visible.value = true
  isEdit.value = !!id

  // 加载模板列表
  try {
    const res = await queryReportTemplateApi({ pageNo: 1, pageSize: 100, reportType, isEnable: true })
    templateList.value = res.records || []
  } catch (e) { templateList.value = [] }

  if (id) {
    const res = await getWorkReportApi(id)
    dataForm.value = {
      id: res.id, title: res.title, reportType: res.reportType,
      templateId: res.templateId, reportDate: res.reportDate,
      content: res.content || '', workSummary: res.workSummary || '',
      workPlan: res.workPlan || '', issues: res.issues || '', status: res.status
    }
  } else {
    dataForm.value = {
      ...getDefaultForm(),
      reportType: reportType || 1,
      reportDate: new Date().toISOString().substring(0, 10)
    }
  }
}

function handleTypeChange(val) {
  dataForm.value.templateId = null
  // 重新加载对应类型的模板
  queryReportTemplateApi({ pageNo: 1, pageSize: 100, reportType: val, isEnable: true }).then(res => {
    templateList.value = res.records || []
  })
}

function handleTemplateChange(templateId) {
  if (!templateId) return
  const tpl = templateList.value.find(t => t.id === templateId)
  if (tpl && tpl.contentTemplate) {
    // 根据模板结构初始化内容（这里简化处理，保留现有文本字段）
  }
}

function handleClose() {
  visible.value = false
  dataForm.value = getDefaultForm()
}

async function handleSaveDraft() {
  try { await formRef.value.validate() } catch (e) { return }
  dataForm.value.status = 1
  await addWorkReportApi(dataForm.value)
  ElMessage.success('已保存草稿')
  handleClose()
  emit('refreshDataList')
}

async function handleSubmit() {
  try { await formRef.value.validate() } catch (e) { return }
  await ElMessageBox.confirm('提交后不可撤回，确认提交？', '提示', {
    confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
  })
  // 先保存
  dataForm.value.status = 2
  await addWorkReportApi(dataForm.value)
  // 如果是新增，需要拿到id后再提交
  if (!dataForm.value.id) {
    // 保存后再提交
    ElMessage.success('提交成功')
  } else {
    await submitWorkReportApi(dataForm.value.id)
    ElMessage.success('提交成功')
  }
  handleClose()
  emit('refreshDataList')
}

defineExpose({ init })
</script>

<style scoped lang="scss">
.report-form {
  :deep(.el-divider__text) {
    font-weight: 600;
    color: #4e5969;
    font-size: 14px;
  }
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}
</style>
