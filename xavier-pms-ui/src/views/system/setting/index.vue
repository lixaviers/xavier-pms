<template>
  <div class="app-container">
    <el-tabs v-model="activeTab" type="border-card">
      <el-tab-pane label="发起审批" name="approve" class="tab-approve">
        <approve-initiate
          type="self"
          :status-list="[
            { value: '0', label: '全部' },
            { value: '3', label: '审批中' },
            { value: '4', label: '审批通过' },
            { value: '5', label: '审批拒绝' }
          ]"
        />
      </el-tab-pane>
      <el-tab-pane label="个人卡片配置" name="employeeCard">
        <employee-card-config />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import ApproveInitiate from '@/views/approve/index.vue'
import EmployeeCardConfig from './components/employeeCardConfig.vue'

const activeTab = ref('approve')
</script>

<style lang="scss" scoped>
.app-container {
  padding: 20px;
}

.tab-approve {
  :deep(.app-container) {
    padding: 0;
  }
}
</style>
