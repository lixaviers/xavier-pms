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
      <el-table :data="processList">
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
            <div
              v-if="
                scope.row.approvalMoreType && scope.row.detailList.length > 1
              "
              style="clear: both"
            >
              <div v-if="scope.row.approvalMoreType === 'one'" class="clearfix">
                1人提交即可
              </div>
              <div v-else class="clearfix">需所有人通过</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="审批结果" width="80">
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
          </template>
        </el-table-column>
        <el-table-column prop="remarks" label="审批意见" />
        <el-table-column label="审批时间" width="180">
          <template #default="scope">
            <span
              v-if="scope.row.auditStatus === 4 || scope.row.auditStatus === 2"
              >{{ scope.row.detailList[0].updateTime }}</span
            >
          </template>
        </el-table-column>
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
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script setup>
import { getApprovalApi } from '@/api/modules/approval'
import {
  addAuditFormApi,
  getAuditFormApi,
  getAuditFormProcessApi
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
const formJson = ref()
const formData = ref({})
const optionData = ref({})
const vformRenderRef = ref(null)
// 审批流程
const processList = ref([])

/**
 * 初始化
 */
async function init(auditId, appId) {
  approvalId.value = appId
  if (auditId) {
    title.value = '审批详情'
    id.value = auditId
    const res = await getAuditFormApi(auditId)
    visible.value = true
    nextTick(() => {
      vformRenderRef.value.setFormJson(JSON.parse(res.form))
      vformRenderRef.value.setFormData(JSON.parse(res.formData))
      setTimeout(() => {
        vformRenderRef.value.disableForm()
      }, 10)

      getAuditFormProcessApi(auditId).then((res) => {
        processList.value = res
      })
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
