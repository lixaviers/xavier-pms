<template>
  <!-- 添加或修改对话框 -->
  <el-dialog
    :title="title"
    v-model="visible"
    width="800px"
    append-to-body
    :close-on-click-modal="false"
  >
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="80px">
      <el-form-item label="标题" prop="title">
        <el-input v-model="dataForm.title" max="100" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="公告类型" prop="announcementTypeId">
        <el-select v-model="dataForm.announcementTypeId" style="width: 100%">
          <el-option
            v-for="item in dataList"
            :key="item.id"
            :label="item.typeName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否启用">
        <el-switch v-model="dataForm.isEnable" />
      </el-form-item>
      <el-form-item label="展示时间" prop="datetimer">
        <el-date-picker
          v-model="dataForm.datetimer"
          type="datetimerange"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          value-format="YYYY-MM-DD HH:mm:ss"
        />
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
      <el-form-item label="内容" prop="content">
        <editor v-model="dataForm.content" :min-height="192" />
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
import editor from '@/components/Editor'
import {
  addOrUpdateAnnouncementApi,
  getAnnouncementApi
} from '@/api/modules/announcement'
import { queryAnnouncementTypeApi } from '@/api/modules/announcementType'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const loading = ref(true)
const title = ref('')
const dataList = ref([])

const data = reactive({
  dataForm: {},
  rules: {
    title: [{ required: true, message: '请输入', trigger: 'blur' }],
    announcementTypeId: [
      { required: true, message: '请选择', trigger: 'change' }
    ],
    sortNumber: [{ required: true, message: '请输入', trigger: 'blur' }],
    datetimer: [{ required: true, message: '请选择', trigger: 'change' }],
    content: [
      { required: true, message: '请输入', trigger: ['blur', 'change'] }
    ]
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
      dataForm.value.startTime = dataForm.value.datetimer[0]
      dataForm.value.endTime = dataForm.value.datetimer[1]
      addOrUpdateAnnouncementApi(dataForm.value).then((response) => {
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
  const { records } = await queryAnnouncementTypeApi({ pageSize: 100 })
  dataList.value = records
}

/**
 * 初始化
 */
async function init(id) {
  visible.value = true
  await getDataList()
  if (id) {
    title.value = '编辑公告'
    dataForm.value = await getAnnouncementApi(id)
    dataForm.value.datetimer = [
      dataForm.value.startTime,
      dataForm.value.endTime
    ]
  } else {
    reset()
    title.value = '新增公告'
  }
}

defineExpose({
  init
})
</script>
