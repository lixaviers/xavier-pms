<template>
  <div class="app-container">
    <el-tabs v-model="activeTab" type="border-card">
      <el-tab-pane label="个人卡片配置" name="employeeCard">
        <employee-card-config />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import EmployeeCardConfig from './components/employeeCardConfig.vue'

const activeTab = ref('employeeCard')
</script>

<style lang="scss" scoped>
.app-container {
  padding: 20px;
}
</style>
