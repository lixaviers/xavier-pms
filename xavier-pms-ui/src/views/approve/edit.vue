<template>
  <!-- 添加或修改对话框 -->
  <el-drawer
    :title="title"
    v-model="visible"
    size="800px"
    append-to-body
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    @close="handleCancel"
    class="xavier-audit"
  >
    <div>
      <v-form-render
        :form-json="formJson"
        :form-data="formData"
        :option-data="optionData"
        ref="vformRenderRef"
      />
    </div>
    <div v-if="id">
      <h4 class="mb20">审批进程</h4>
      <el-table border :data="processList" :span-method="cellMerge">
        <el-table-column prop="title" label="节点名称" width="80" />
        <el-table-column label="审批人">
          <template #default="scope">
            <div
              v-for="(item, index) in scope.row.detailList"
              :key="index"
              class="mr5 fl"
            >
              <employee-card v-model="item.userId" :label="item.nickName" />
            </div>
          </template>
        </el-table-column>
        <el-table-column label="审批结果" width="95" align="center">
          <template #default="scope">
            <el-tag v-if="scope.row.auditStatus === 4" type="success"
              >已通过</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 3" type="warning"
              >审批中</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 2" type="success"
              >已提交</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 7" type="info"
              >自动抄送</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 1" type="info"
              >待处理</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 6" type="danger"
              >已撤回</el-tag
            >
            <el-tag v-else-if="scope.row.auditStatus === 5" type="danger"
              >已拒绝</el-tag
            >
          </template>
        </el-table-column>
        <el-table-column prop="remarks" label="审批意见" />
        <el-table-column prop="dealTime" label="审批时间" width="180" />
      </el-table>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button
          v-if="!id"
          type="primary"
          @click="submitForm"
          :loading="loading"
          auto-insert-space
          >确定</el-button
        >
        <el-button
          v-if="optButton && optButton.handler"
          type="primary"
          icon="Check"
          @click="handelAudit(2)"
          :loading="loading"
          >办理</el-button
        >
        <el-button
          v-if="optButton && optButton.audit"
          type="primary"
          icon="Check"
          @click="handelAudit(1)"
          :loading="loading"
          >同意</el-button
        >
        <el-button
          v-if="optButton && optButton.audit"
          icon="Close"
          type="danger"
          @click="handelAudit(0)"
          :loading="loading"
          >拒绝</el-button
        >
        <el-button
          v-if="optButton && optButton.revocation"
          icon="Back"
          type="danger"
          @click="handelRevocation"
          :loading="loading"
          >撤回</el-button
        >
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script setup>
import { ElMessageBox } from 'element-plus'
import { getApprovalApi } from '@/api/modules/approval'
import {
  addAuditFormApi,
  getAuditFormApi,
  getAuditFormProcessApi,
  revocationAuditFormApi,
  auditAuditFormApi
} from '@/api/modules/auditForm'
const { proxy } = getCurrentInstance()
const emits = defineEmits()

const visible = ref(false)
const title = ref('')
const loading = ref(false)
// 审批单id
const id = ref()
// 审批id
const approvalId = ref()
// 操作按钮
const optButton = ref(null)
const formJson = ref()
const formData = ref({})
const optionData = ref({})
const vformRenderRef = ref(null)
// 审批流程
const processList = ref([])
const spanArr = ref([])
const pos = ref(0)

const cellMerge = ({ row, column, rowIndex, columnIndex }) => {
  if (columnIndex === 0) {
    const _row = spanArr.value[rowIndex]
    const _col = _row > 0 ? 1 : 0
    return {
      rowspan: _row,
      colspan: _col
    }
  }
}

/**
 * 初始化
 */
async function init(auditId, appId) {
  approvalId.value = appId
  if (auditId) {
    title.value = '审批详情'
    id.value = auditId
    const res = await getAuditFormApi(auditId)
    optButton.value = res.button
    visible.value = true
    nextTick(() => {
      vformRenderRef.value.setFormJson(JSON.parse(res.form))
      vformRenderRef.value.setFormData(JSON.parse(res.formData))
      setTimeout(() => {
        vformRenderRef.value.disableForm()
      }, 10)

      getProcessList()
    })
  } else {
    title.value = '发起申请'
    const res = await getApprovalApi(appId)
    visible.value = true
    nextTick(() => {
      vformRenderRef.value.setFormJson(JSON.parse(res.form))
      vformRenderRef.value.enableForm()
    })
  }
}

/**
 * 获取审批进程列表
 */
function getProcessList() {
  getAuditFormProcessApi(id.value).then((res) => {
    processList.value = res

    spanArr.value = []
    pos.value = 0
    for (var i = 0; i < res.length; i++) {
      if (i === 0) {
        spanArr.value.push(1)
        pos.value = 0
      } else {
        // 判断当前元素与上一个元素是否相同
        if (res[i].id === res[i - 1].id) {
          spanArr.value[pos.value] += 1
          spanArr.value.push(0)
        } else {
          spanArr.value.push(1)
          pos.value = i
        }
      }
    }
  })
}

function submitForm() {
  vformRenderRef.value
    .getFormData()
    .then((formData) => {
      loading.value = true
      const params = {
        approvalId: approvalId.value,
        formData: JSON.stringify(formData)
      }
      addAuditFormApi(params)
        .then(() => {
          proxy.$modal.msgSuccess('提交成功')
          visible.value = false
          emits('refreshDataList')
        })
        .finally(() => {
          loading.value = false
        })
    })
    .catch(() => {})
}

function handleCancel() {
  vformRenderRef.value.resetForm()
  visible.value = false
  processList.value = []
  id.value = undefined
}

/**
 * 撤回审批单
 */
function handelRevocation() {
  ElMessageBox.prompt('撤回理由', {
    confirmButtonText: '撤回',
    cancelButtonText: '取消',
    inputType: 'textarea',
    inputPlaceholder: '请输入',
    inputPattern: /^.{1,100}$/,
    inputErrorMessage: '请输入'
  })
    .then(({ value }) => {
      const data = { id: id.value, remarks: value }
      revocationAuditFormApi(data).then(() => {
        proxy.$modal.msgSuccess('撤回成功')
        emits('refreshDataList')
        init(id.value)
      })
    })
    .catch(() => {})
}
/**
 * 审批审批单
 */
function handelAudit(type) {
  let desc = '拒绝'
  let pass = false
  if (type === 1) {
    desc = '同意'
    pass = true
  } else if (type === 2) {
    desc = '办理'
    pass = true
  }
  ElMessageBox.prompt(`${desc}理由`, {
    confirmButtonText: desc,
    cancelButtonText: '取消',
    inputType: 'textarea',
    inputPlaceholder: '请输入',
    inputPattern: /^.{1,100}$/,
    inputErrorMessage: '请输入'
  })
    .then(({ value }) => {
      const data = { id: id.value, remarks: value, pass: pass }
      auditAuditFormApi(data).then(() => {
        proxy.$modal.msgSuccess(`${desc}成功`)
        emits('refreshDataList')
        init(id.value)
      })
    })
    .catch(() => {})
}

defineExpose({
  init
})
</script>

<style lang="scss">
.xavier-audit {
  .el-drawer__header {
    margin-bottom: 0;
  }
}
</style>
