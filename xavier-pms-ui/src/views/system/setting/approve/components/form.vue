<template>
  <div class="approve-form">
    <el-drawer
      v-model="visible"
      size="600px"
      :title="dataForm.title"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
    >
      <el-form
        ref="formRef"
        :model="dataForm"
        :rules="rules"
        label-position="top"
      >
        <el-form-item
          v-if="dataForm.approvalType === 'start'"
          label="谁可以提交"
        >
          <el-select v-model="dataForm.submitType" style="width: 100%">
            <el-option label="全员" value="all" />
          </el-select>
        </el-form-item>

        <!-- 审批 -->
        <div v-if="dataForm.approvalType === 'approval'">
          <el-form-item label="审批方式">
            <el-radio-group v-model="dataForm.approvalMode">
              <el-radio label="manual">人工审批</el-radio>
              <el-radio label="autoPass">自动通过</el-radio>
            </el-radio-group>
          </el-form-item>
          <div v-if="dataForm.approvalMode === 'manual'">
            <div v-if="dataForm.itemList && dataForm.itemList.length > 0">
              <app-choose
                v-for="(item, index) in dataForm.itemList"
                :key="index"
                title="审批人"
                :dataForm="item"
                @handelDelete="handelDelete('approval', index)"
              />
            </div>
            <div
              v-if="!dataForm.itemList || dataForm.itemList.length < 5"
              class="mt10 mb10"
            >
              <el-button
                @click="handelAdd('approval')"
                icon="Plus"
                link
                type="primary"
                >添加审批人</el-button
              >
            </div>
            <el-form-item label="审批人为空时">
              <el-radio-group v-model="dataForm.emptyType">
                <el-radio label="autoPass">自动通过</el-radio>
                <el-radio label="employee">指定人员审批</el-radio>
              </el-radio-group>
            </el-form-item>
            <button-select-employee
              v-if="dataForm.emptyType === 'employee'"
              v-model="dataForm.employeeList"
              class="mb20"
            />
            <div v-if="isShowMoreType">
              <el-form-item label="多人审批时采用的审批方式">
                <el-radio-group v-model="dataForm.approvalMoreType">
                  <el-radio label="all">所有审批人同意</el-radio>
                  <el-radio label="one">一名审批人同意即可</el-radio>
                  <el-radio label="order">按顺序依次审批</el-radio>
                </el-radio-group>
              </el-form-item>
            </div>
          </div>
        </div>

        <!-- 办理 -->
        <div v-if="dataForm.approvalType === 'handler'">
          <p class="handler-desc">
            当流程中某个节点不需要审批，但需要对审批单进行业务办理时，可设置办理人节点，场景如财务打款、处理盖章等。
          </p>
          <div v-if="dataForm.itemList && dataForm.itemList.length > 0">
            <app-choose
              v-for="(item, index) in dataForm.itemList"
              :key="index"
              title="办理人"
              :dataForm="item"
              @handelDelete="handelDelete('handler', index)"
            />
          </div>
          <div
            v-if="!dataForm.itemList || dataForm.itemList.length < 5"
            class="mt10 mb10"
          >
            <el-button
              @click="handelAdd('handler')"
              icon="Plus"
              link
              type="primary"
              >添加办理人</el-button
            >
          </div>
          <el-form-item label="办理人为空时">
            <el-radio-group v-model="dataForm.emptyType">
              <el-radio label="autoPass">自动通过</el-radio>
              <el-radio label="employee">指定人员审批</el-radio>
            </el-radio-group>
          </el-form-item>
          <button-select-employee
            v-if="dataForm.emptyType === 'employee'"
            v-model="dataForm.employeeList"
            class="mb20"
          />
          <div v-if="isShowMoreType">
            <el-form-item label="多人办理时采用的处理方式">
              <el-radio-group v-model="dataForm.approvalMoreType">
                <el-radio label="all">需所有办理人提交</el-radio>
                <el-radio label="one">一名办理人提交即可</el-radio>
                <el-radio label="order">按顺序依次提交</el-radio>
              </el-radio-group>
            </el-form-item>
          </div>
        </div>

        <!-- 抄送 -->
        <div
          v-if="
            dataForm.approvalType === 'start' ||
            dataForm.approvalType === 'cc' ||
            (dataForm.approvalType === 'approval' &&
              dataForm.approvalMode === 'manual') ||
            dataForm.approvalType === 'end'
          "
        >
          <div v-if="dataForm.ccList && dataForm.ccList.length > 0">
            <app-choose
              v-for="(item, index) in dataForm.ccList"
              :key="index"
              title="抄送人"
              :dataForm="item"
              @handelDelete="handelDelete('cc', index)"
            />
          </div>
          <div
            v-if="!dataForm.ccList || dataForm.ccList.length < 5"
            class="mt10"
          >
            <el-button
              v-if="!dataForm.ccList || dataForm.ccList.length < 5"
              @click="handelAdd('cc')"
              icon="Plus"
              link
              type="primary"
              >添加抄送人</el-button
            >
          </div>
        </div>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="submitForm" auto-insert-space
          >确定</el-button
        >
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </template>
    </el-drawer>
  </div>
</template>

<script setup>
import appChoose from './choose.vue'
import { deepClone } from '@/utils'

const { proxy } = getCurrentInstance()
const emits = defineEmits()
const data = reactive({
  dataForm: {},
  rules: []
})
const { dataForm, rules } = toRefs(data)
const visible = ref(false)

// 是否展示多人时采用的方式
const isShowMoreType = computed(() => {
  const itemList = dataForm.value.itemList
  if (itemList && itemList.length > 0) {
    if (itemList.length > 1) {
      return true
    }
    const list = itemList.filter((item) => {
      if (item.type === 'role' || item.type === 'userGroup') {
        return true
      }
      if (
        item.type === 'employee' &&
        item.employeeList &&
        item.employeeList.length > 1
      ) {
        return true
      }
      return false
    })
    if (list && list.length > 0) {
      return true
    }
  }
  return false
})

/**
 * 添加
 */
function handelAdd(type) {
  if (type === 'approval') {
    if (!dataForm.value.itemList) {
      dataForm.value.itemList = []
    }
    dataForm.value.itemList.push({ type: 'parent' })
  } else if (type === 'cc') {
    if (!dataForm.value.ccList) {
      dataForm.value.ccList = []
    }
    dataForm.value.ccList.push({ type: 'parent' })
  } else if (type === 'handler') {
    if (!dataForm.value.itemList) {
      dataForm.value.itemList = []
    }
    dataForm.value.itemList.push({ type: 'parent' })
  }
}

/**
 * 添加审批人
 */
function handelAddApproval() {
  if (!dataForm.value.itemList) {
    dataForm.value.itemList = []
  }
  dataForm.value.itemList.push({})
}

// watch(employeeList, (val) => {
//   let str = ''
//   if (val && val.length > 0) {
//     val.forEach((item) => {
//       str += item.nickName + ','
//     })
//     str = str.substring(0, str.length - 1)
//   }
//   console.log(str)
//   ccList.value[index.value].employeeList = str
// })

function handleCancel() {
  visible.value = false
}

function submitForm() {
  console.log(deepClone(dataForm.value))
  emits('formSubmit', deepClone(dataForm.value)) // to update v-model
  visible.value = false
}

/**
 * 删除抄送
 */
function handelDelete(type, index) {
  if (type === 'approval') {
    dataForm.value.itemList.splice(index, 1)
  } else if (type === 'cc') {
    dataForm.value.ccList.splice(index, 1)
  } else if (type === 'handler') {
    dataForm.value.itemList.splice(index, 1)
  }
}

function init(item) {
  visible.value = true
  dataForm.value = deepClone(item)
  if (dataForm.value.approvalType === 'approval') {
    if (!dataForm.value.approvalMode) {
      dataForm.value.approvalMode = 'manual'
    }
    if (!dataForm.value.approvalMoreType) {
      dataForm.value.approvalMoreType = 'all'
    }
    if (!dataForm.value.emptyType) {
      dataForm.value.emptyType = 'autoPass'
    }
    if (!dataForm.value.itemList || dataForm.value.itemList.length === 0) {
      handelAdd(dataForm.value.approvalType)
    }
  } else if (dataForm.value.approvalType === 'handler') {
    if (!dataForm.value.emptyType) {
      dataForm.value.emptyType = 'autoPass'
    }
    if (!dataForm.value.approvalMoreType) {
      dataForm.value.approvalMoreType = 'all'
    }
    if (!dataForm.value.itemList || dataForm.value.itemList.length === 0) {
      handelAdd(dataForm.value.approvalType)
    }
  }
}

defineExpose({
  init
})
</script>

<style lang="scss">
.approve-form {
  .el-drawer__header {
    margin-bottom: 0;
  }
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .handler-desc {
    color: var(--el-color-info);
    font-size: 14px;
  }
}
</style>
