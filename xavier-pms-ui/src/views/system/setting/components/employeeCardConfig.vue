<template>
  <div class="card-config" v-loading="loading">
    <el-row :gutter="24">
      <el-col :xs="24" :sm="24" :md="14" :lg="14" :xl="14">
        <div class="section-title">显示配置</div>
        <div class="config-grid">
          <div
            v-for="item in configItems"
            :key="item.key"
            class="config-item"
            :class="{ 'is-active': formData[item.key] }"
          >
            <div class="config-item__icon">
              <el-icon :size="24"><component :is="item.icon" /></el-icon>
            </div>
            <div class="config-item__content">
              <div class="config-item__title">{{ item.label }}</div>
              <div class="config-item__desc">{{ item.desc }}</div>
            </div>
            <el-switch v-model="formData[item.key]" />
          </div>
        </div>
        <el-button type="primary" @click="handleSave" :loading="saving" style="margin-top: 20px">
          保存配置
        </el-button>
      </el-col>
      <el-col :xs="24" :sm="24" :md="10" :lg="10" :xl="10" class="preview-col">
        <div class="section-title">效果预览</div>
        <div class="preview-card">
          <div class="preview-card__header">
            <div class="preview-card__avatar">
              <el-icon :size="18"><User /></el-icon>
            </div>
            <div class="preview-card__info">
              <div class="preview-card__name">
                <span>张三</span>
                <el-tag size="small" round effect="plain">男</el-tag>
              </div>
              <div class="preview-card__tags">
                <el-tag v-if="formData.showDept" size="small" effect="plain">技术部</el-tag>
                <el-tag v-if="formData.showPosition" size="small" type="info" effect="plain">前端工程师</el-tag>
              </div>
            </div>
          </div>
          <el-divider style="margin: 10px 0" />
          <div class="preview-card__body">
            <div class="preview-card__row">
              <span class="preview-card__label">工号</span>
              <span class="preview-card__value">EMP001</span>
            </div>
            <div v-if="formData.showMobile" class="preview-card__row">
              <span class="preview-card__label">手机号</span>
              <span class="preview-card__value">138****8888</span>
            </div>
            <div v-if="formData.showEmail" class="preview-card__row">
              <span class="preview-card__label">邮箱</span>
              <span class="preview-card__value">zhangsan@example.com</span>
            </div>
            <div v-if="formData.showPosition" class="preview-card__row">
              <span class="preview-card__label">职称</span>
              <span class="preview-card__value">中级工程师</span>
            </div>
            <div class="preview-card__row">
              <span class="preview-card__label">直属上级</span>
              <span class="preview-card__value">李四</span>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { getEmployeeCardConfig, saveEmployeeCardConfig } from '@/api/modules/systemConfig'
import { User, OfficeBuilding, Message, Iphone, Postcard } from '@element-plus/icons-vue'

const { proxy } = getCurrentInstance()

const loading = ref(false)
const saving = ref(false)
const formData = reactive({
  showDept: true,
  showEmail: true,
  showMobile: true,
  showPosition: true
})

const configItems = [
  { key: 'showDept', label: '显示部门', desc: '在卡片头部展示员工所属部门', icon: OfficeBuilding },
  { key: 'showEmail', label: '显示邮箱', desc: '在卡片详情中展示员工邮箱地址', icon: Message },
  { key: 'showMobile', label: '显示手机号', desc: '在卡片详情中展示员工手机号码', icon: Iphone },
  { key: 'showPosition', label: '显示职位', desc: '在卡片头部和详情中展示职位与职称', icon: Postcard }
]

function getConfig() {
  loading.value = true
  getEmployeeCardConfig()
    .then((data) => {
      if (data) {
        formData.showDept = data.showDept ?? true
        formData.showEmail = data.showEmail ?? true
        formData.showMobile = data.showMobile ?? true
        formData.showPosition = data.showPosition ?? true
      }
    })
    .finally(() => {
      loading.value = false
    })
}

function handleSave() {
  saving.value = true
  saveEmployeeCardConfig({ ...formData })
    .then(() => {
      proxy.$modal.msgSuccess('保存成功')
    })
    .finally(() => {
      saving.value = false
    })
}

onMounted(() => {
  getConfig()
})
</script>

<style lang="scss" scoped>
.card-config {
  padding: 8px 0;
}

.section-title {
  font-size: 15px;
  font-weight: 600;
  color: var(--el-text-color-primary);
  margin-bottom: 16px;
}

.config-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
}

.config-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px;
  border-radius: 8px;
  border: 1px solid var(--el-border-color-lighter);
  background: var(--el-bg-color);
  transition: all 0.25s;

  &:hover {
    border-color: var(--el-color-primary-light-5);
  }

  &.is-active {
    border-color: var(--el-color-primary-light-5);
    background: var(--el-color-primary-light-9);
  }

  &__icon {
    flex-shrink: 0;
    width: 40px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 8px;
    background: var(--el-color-primary-light-8);
    color: var(--el-color-primary);
  }

  &__content {
    flex: 1;
    min-width: 0;
  }

  &__title {
    font-size: 14px;
    font-weight: 500;
    color: var(--el-text-color-primary);
  }

  &__desc {
    margin-top: 2px;
    font-size: 12px;
    color: var(--el-text-color-secondary);
    line-height: 1.4;
  }
}

.preview-card {
  width: 300px;
  padding: 16px;
  border-radius: 8px;
  border: 1px solid var(--el-border-color-lighter);
  background: var(--el-bg-color);
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.04);

  &__header {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  &__avatar {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    background: var(--el-color-primary-light-8);
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
  }

  &__name {
    display: flex;
    align-items: center;
    gap: 6px;
    font-size: 14px;
    font-weight: 500;
  }

  &__tags {
    display: flex;
    gap: 4px;
    margin-top: 4px;
  }

  &__row {
    display: flex;
    justify-content: space-between;
    padding: 4px 0;
    font-size: 13px;
  }

  &__label {
    color: var(--el-text-color-secondary);
  }

  &__value {
    color: var(--el-text-color-primary);
  }
}

@media (max-width: 768px) {
  .config-grid {
    grid-template-columns: 1fr;
  }

  .preview-col {
    border-left: none !important;
    padding-left: 0 !important;
    margin-top: 16px;
  }

  .preview-card {
    width: 100%;
  }
}

@media (min-width: 769px) {
  .preview-col {
    border-left: 1px solid var(--el-border-color-lighter);
    padding-left: 24px;
  }
}
</style>
