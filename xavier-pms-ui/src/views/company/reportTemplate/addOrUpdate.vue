<template>
  <el-dialog v-model="visible" :title="isEdit ? '编辑模板' : '新增模板'" width="620px" destroy-on-close :close-on-click-modal="false" @close="handleClose">
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="100px">
      <el-form-item label="模板名称" prop="templateName">
        <el-input v-model="dataForm.templateName" placeholder="请输入模板名称" maxlength="100" />
      </el-form-item>
      <el-form-item label="汇报类型" prop="reportType">
        <el-select v-model="dataForm.reportType" placeholder="请选择汇报类型" style="width: 100%" :disabled="isEdit">
          <el-option label="日报" :value="1" />
          <el-option label="周报" :value="2" />
          <el-option label="月报" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="内容模板">
        <div class="template-editor">
          <div class="template-header">
            <span class="template-tip">定义汇报的章节结构，每行一个章节</span>
            <el-button type="primary" link size="small" @click="addSection">+ 添加章节</el-button>
          </div>
          <div class="section-list">
            <div class="section-item" v-for="(sec, idx) in sections" :key="idx">
              <el-input v-model="sec.label" placeholder="章节名称" style="flex: 1" />
              <el-button type="danger" link @click="sections.splice(idx, 1)"><el-icon><Delete /></el-icon></el-button>
            </div>
          </div>
        </div>
      </el-form-item>
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="默认模板">
            <el-switch v-model="dataForm.isDefault" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="启用状态">
            <el-switch v-model="dataForm.isEnable" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="备注">
        <el-input v-model="dataForm.remark" type="textarea" :rows="2" placeholder="请输入备注" maxlength="500" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" @click="handleSubmit">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import { Delete } from '@element-plus/icons-vue'
import { addReportTemplateApi, getReportTemplateApi } from '@/api/modules/reportTemplate'

const emit = defineEmits(['refreshDataList'])
const visible = ref(false)
const isEdit = ref(false)
const formRef = ref(null)
const sections = ref([{ key: 'work_summary', label: '工作总结', type: 'textarea' }])

const dataForm = ref(getDefaultForm())

const rules = {
  templateName: [{ required: true, message: '请输入模板名称', trigger: 'blur' }],
  reportType: [{ required: true, message: '请选择汇报类型', trigger: 'change' }]
}

function getDefaultForm() {
  return { id: null, templateName: '', reportType: undefined, contentTemplate: '', isDefault: false, isEnable: true, remark: '' }
}

function addSection() {
  const key = `section_${Date.now()}`
  sections.value.push({ key, label: '', type: 'textarea' })
}

async function init(id) {
  visible.value = true
  isEdit.value = !!id
  if (id) {
    const res = await getReportTemplateApi(id)
    dataForm.value = { ...res }
    // 解析contentTemplate
    if (res.contentTemplate) {
      try {
        sections.value = JSON.parse(res.contentTemplate)
      } catch (e) {
        sections.value = [{ key: 'work_summary', label: '工作总结', type: 'textarea' }]
      }
    }
  } else {
    dataForm.value = getDefaultForm()
    sections.value = [
      { key: 'work_summary', label: '工作总结', type: 'textarea' },
      { key: 'work_plan', label: '工作计划', type: 'textarea' },
      { key: 'issues', label: '问题与建议', type: 'textarea' }
    ]
  }
}

function handleClose() {
  visible.value = false
  dataForm.value = getDefaultForm()
}

async function handleSubmit() {
  try { await formRef.value.validate() } catch (e) { return }
  // 校验章节
  const validSections = sections.value.filter(s => s.label.trim())
  if (validSections.length === 0) {
    ElMessage.warning('请至少添加一个章节')
    return
  }
  dataForm.value.contentTemplate = JSON.stringify(validSections)
  await addReportTemplateApi(dataForm.value)
  ElMessage.success(isEdit.value ? '编辑成功' : '新增成功')
  handleClose()
  emit('refreshDataList')
}

defineExpose({ init })
</script>

<style scoped lang="scss">
.template-editor {
  width: 100%;
  border: 1px solid #dcdfe6;
  border-radius: 6px;
  padding: 12px;
}

.template-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.template-tip {
  font-size: 12px;
  color: #909399;
}

.section-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.section-item {
  display: flex;
  gap: 8px;
  align-items: center;
}
</style>
