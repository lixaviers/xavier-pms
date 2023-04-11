<template>
  <div>
    <el-popover :width="300" @before-enter="handleShow">
      <template #reference>
        <el-link type="primary"> {{ props.label }}</el-link>
      </template>
      <template #default>
        <div class="employee-card" v-loading="loading">
          <h3 class="nick-name">
            {{ employeeInfo.nickName }}
            <span
              v-if="employeeInfo.gender === '女'"
              style="color: var(--el-color-danger)"
              ><svg-icon icon-class="women"
            /></span>
            <span v-else style="color: var(--el-color-primary)"
              ><svg-icon icon-class="man"
            /></span>
          </h3>
          <div class="card-item">
            <span class="label">工号</span>
            <span class="value">{{ employeeInfo.employeeNumber }}</span>
          </div>
          <div class="card-item">
            <span class="label">上级</span>
            <span class="value">
              <span v-if="employeeInfo.directLeader">{{
                employeeInfo.directLeader
              }}</span>
              <span v-else>-</span>
            </span>
          </div>
          <div class="card-item">
            <span class="label">手机号</span>
            <span class="value">{{ employeeInfo.mobile }}</span>
          </div>
          <div class="card-item">
            <span class="label">邮箱</span>
            <span class="value">{{ employeeInfo.email }}</span>
          </div>
          <div class="card-item">
            <span class="label">部门</span>
            <span class="value">{{ employeeInfo.deptName }}</span>
          </div>
          <div class="card-item">
            <span class="label">职位</span>
            <span class="value">{{ employeeInfo.postName }}</span>
          </div>
          <div class="card-item">
            <span class="label">职称</span>
            <span class="value">
              <span v-if="employeeInfo.titleName">{{
                employeeInfo.titleName
              }}</span>
              <span v-else>-</span>
            </span>
          </div>
          <div class="card-item">
            <span class="label">创建时间</span>
            <span class="value">{{ employeeInfo.createTime }}</span>
          </div>
        </div>
      </template>
    </el-popover>
  </div>
</template>

<script setup>
import { getUserCardApi } from '@/api/company/user'
const { proxy } = getCurrentInstance()
const emits = defineEmits()

const props = defineProps({
  modelValue: {
    type: String
  },
  label: {
    type: String,
    default: ''
  }
})
const loading = ref(false)
const employeeInfo = ref({})

// 获取员工信息
function handleShow() {
  loading.value = true
  setTimeout(function () {
    getUserCardApi(props.modelValue)
      .then((response) => {
        employeeInfo.value = response
      })
      .finally(() => {
        loading.value = false
      })
  }, 500)
}
</script>

<style lang="scss" scoped>
.employee-card {
  padding: 0 10px;
  .nick-name {
    margin: 0 0 10px 0;
  }
  .card-item {
    padding: 5px 0;
    .label {
      width: 70px;
      display: inline-block;
      color: #a1a5ad;
    }
    .value {
    }
  }
}
</style>
