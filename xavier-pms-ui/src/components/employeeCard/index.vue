<template>
  <div>
    <el-popover :width="320" @before-enter="handleShow">
      <template #reference>
        <el-link type="primary"> {{ props.label }}</el-link>
      </template>
      <template #default>
        <div class="employee-card" v-loading="loading">
          <div class="card-header">
            <div class="avatar-placeholder">
              <el-icon :size="24"><User /></el-icon>
            </div>
            <div class="header-info">
              <div class="header-name">
                <span class="name-text">{{ employeeInfo.nickName || '—' }}</span>
                <el-tag v-if="employeeInfo.gender === '女'" type="danger" size="small" round effect="plain">女</el-tag>
                <el-tag v-else-if="employeeInfo.gender === '男' || employeeInfo.gender === '1'" size="small" round effect="plain">男</el-tag>
              </div>
              <div class="header-tags">
                <el-tag v-if="employeeInfo.deptName && cardConfig.showDept" size="small" effect="plain">{{ employeeInfo.deptName }}</el-tag>
                <el-tag v-if="employeeInfo.postName && cardConfig.showPosition" size="small" type="info" effect="plain">{{ employeeInfo.postName }}</el-tag>
              </div>
            </div>
          </div>
          <el-divider style="margin: 12px 0" />
          <div class="card-body">
            <div class="info-row">
              <span class="info-label">工号</span>
              <span class="info-value">{{ employeeInfo.employeeNumber || '—' }}</span>
            </div>
            <div v-if="cardConfig.showMobile" class="info-row">
              <span class="info-label">手机号</span>
              <span class="info-value">{{ employeeInfo.mobile || '—' }}</span>
            </div>
            <div v-if="cardConfig.showEmail" class="info-row">
              <span class="info-label">邮箱</span>
              <span class="info-value">{{ employeeInfo.email || '—' }}</span>
            </div>
            <div v-if="cardConfig.showPosition" class="info-row">
              <span class="info-label">职称</span>
              <span class="info-value">{{ employeeInfo.titleName || '—' }}</span>
            </div>
            <div class="info-row">
              <span class="info-label">直属上级</span>
              <span class="info-value">{{ directLeaderText }}</span>
            </div>
          </div>
        </div>
      </template>
    </el-popover>
  </div>
</template>

<script setup>
import { getUserCardApi } from '@/api/modules/user'
import { getEmployeeCardConfig } from '@/api/modules/systemConfig'
import { User } from '@element-plus/icons-vue'

const props = defineProps({
  modelValue: {
    type: [String, Number]
  },
  label: {
    type: String,
    default: ''
  }
})

const loading = ref(false)
const employeeInfo = ref({})

// 模块级缓存，多个实例只请求一次配置接口
let _configPromise = null
const cardConfig = ref({
  showDept: true,
  showEmail: true,
  showMobile: true,
  showPosition: true
})

function loadConfig() {
  if (!_configPromise) {
    _configPromise = getEmployeeCardConfig()
      .then((data) => {
        if (data) {
          return {
            showDept: data.showDept ?? true,
            showEmail: data.showEmail ?? true,
            showMobile: data.showMobile ?? true,
            showPosition: data.showPosition ?? true
          }
        }
        return cardConfig.value
      })
      .catch(() => cardConfig.value)
  }
  _configPromise.then((config) => {
    cardConfig.value = config
  })
}

const directLeaderText = computed(() => {
  if (employeeInfo.value.directLeaderId === 0) return '无（最上级）'
  return employeeInfo.value.directLeader || '—'
})

function handleShow() {
  if (!props.modelValue) return
  loading.value = true
  getUserCardApi(props.modelValue)
    .then((response) => {
      employeeInfo.value = response || {}
    })
    .finally(() => {
      loading.value = false
    })
}

onMounted(() => {
  loadConfig()
})
</script>

<style lang="scss" scoped>
.employee-card {
  .card-header {
    display: flex;
    align-items: center;
    gap: 12px;

    .avatar-placeholder {
      width: 48px;
      height: 48px;
      border-radius: 50%;
      background: var(--el-color-primary-light-5);
      color: #fff;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-shrink: 0;
    }

    .header-info {
      min-width: 0;

      .header-name {
        display: flex;
        align-items: center;
        gap: 8px;
        margin-bottom: 6px;

        .name-text {
          font-size: 16px;
          font-weight: 600;
          color: var(--el-text-color-primary);
        }
      }

      .header-tags {
        display: flex;
        gap: 6px;
        flex-wrap: wrap;
      }
    }
  }

  .card-body {
    .info-row {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 6px 0;
      border-bottom: 1px solid var(--el-border-color-lighter);

      &:last-child {
        border-bottom: none;
      }

      .info-label {
        font-size: 13px;
        color: var(--el-text-color-secondary);
        flex-shrink: 0;
      }

      .info-value {
        font-size: 13px;
        color: var(--el-text-color-primary);
        font-weight: 500;
        text-align: right;
        word-break: break-all;
        margin-left: 16px;
      }
    }
  }
}
</style>
