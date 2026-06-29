<template>
  <div class="app-container dashboard">
    <!-- 数据统计看板 -->
    <el-row :gutter="16" class="stat-row">
      <el-col :xs="12" :sm="6">
        <el-card shadow="hover" class="stat-card stat-pending" @click="goTo('/approve/deal')">
          <div class="stat-icon">
            <el-icon :size="36"><Clock /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ data.pendingAuditCount || 0 }}</div>
            <div class="stat-label">待我审批</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card shadow="hover" class="stat-card stat-dealt" @click="goTo('/approve/deal')">
          <div class="stat-icon">
            <el-icon :size="36"><CircleCheck /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ data.dealtCount || 0 }}</div>
            <div class="stat-label">已处理</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card shadow="hover" class="stat-card stat-my" @click="goTo('/approve/self')">
          <div class="stat-icon">
            <el-icon :size="36"><Promotion /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ data.myProcessingCount || 0 }}</div>
            <div class="stat-label">我的审批中</div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="12" :sm="6">
        <el-card shadow="hover" class="stat-card stat-cc" @click="goTo('/approve/cc')">
          <div class="stat-icon">
            <el-icon :size="36"><Bell /></el-icon>
          </div>
          <div class="stat-info">
            <div class="stat-value">{{ data.ccUnreadCount || 0 }}</div>
            <div class="stat-label">抄送我的</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="16" class="content-row">
      <!-- 左侧：待办事项 + 快捷入口 -->
      <el-col :xs="24" :sm="24" :md="16">
        <!-- 待办事项 -->
        <el-card shadow="hover" class="section-card">
          <template #header>
            <div class="section-header">
              <span class="section-title">待办事项</span>
              <el-button link type="primary" @click="goTo('/approve/deal')">查看更多</el-button>
            </div>
          </template>
          <div v-if="data.todoList && data.todoList.length > 0">
            <div
              v-for="item in data.todoList"
              :key="item.id"
              class="todo-item"
              @click="goToAuditForm(item.id)"
            >
              <div class="todo-left">
                <el-tag size="small" type="warning">{{ item.auditStatusDesc }}</el-tag>
                <span class="todo-name">{{ item.approvalName }}</span>
              </div>
              <span class="todo-time">{{ item.createTime }}</span>
            </div>
          </div>
          <el-empty v-else description="暂无待办事项" :image-size="60" />
        </el-card>

        <!-- 快捷入口 -->
        <el-card shadow="hover" class="section-card">
          <template #header>
            <div class="section-header">
              <span class="section-title">快捷入口</span>
            </div>
          </template>
          <el-row :gutter="16">
            <el-col :xs="8" :sm="6" v-for="item in shortcuts" :key="item.label">
              <div class="shortcut-item" @click="goTo(item.path)">
                <div class="shortcut-icon" :style="{ backgroundColor: item.color }">
                  <el-icon :size="24" color="#fff"><component :is="item.icon" /></el-icon>
                </div>
                <span class="shortcut-label">{{ item.label }}</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>

      <!-- 右侧：公告 + 统计 + 日程 -->
      <el-col :xs="24" :sm="24" :md="8">
        <!-- 组织概览 -->
        <el-card shadow="hover" class="section-card">
          <template #header>
            <div class="section-header">
              <span class="section-title">组织概览</span>
            </div>
          </template>
          <div class="overview-item">
            <span class="overview-label">在职员工</span>
            <span class="overview-value">{{ data.employeeCount || 0 }} 人</span>
          </div>
          <div class="overview-item">
            <span class="overview-label">部门数量</span>
            <span class="overview-value">{{ data.departmentCount || 0 }} 个</span>
          </div>
        </el-card>

        <!-- 最近公告 -->
        <el-card shadow="hover" class="section-card">
          <template #header>
            <div class="section-header">
              <span class="section-title">最近公告</span>
              <el-button link type="primary" @click="goTo('/note')">查看更多</el-button>
            </div>
          </template>
          <div v-if="data.announcementList && data.announcementList.length > 0">
            <div
              v-for="item in data.announcementList"
              :key="item.id"
              class="announcement-item"
            >
              <div class="announcement-left">
                <el-tag size="small" type="info" v-if="item.typeName">{{ item.typeName }}</el-tag>
                <span class="announcement-title">{{ item.title }}</span>
              </div>
              <span class="announcement-time">{{ item.createTime }}</span>
            </div>
          </div>
          <el-empty v-else description="暂无公告" :image-size="60" />
        </el-card>

        <!-- 日程提醒 -->
        <el-card shadow="hover" class="section-card">
          <template #header>
            <div class="section-header">
              <span class="section-title">日程提醒</span>
              <el-button link type="primary" @click="goTo('/company/schedule')">查看更多</el-button>
            </div>
          </template>
          <div v-if="data.scheduleList && data.scheduleList.length > 0">
            <div
              v-for="item in data.scheduleList"
              :key="item.id"
              class="schedule-item"
            >
              <div class="schedule-dot" :style="{ backgroundColor: item.color || (item.scheduleTypeDesc === '会议' ? '#67C23A' : '#409EFF') }"></div>
              <div class="schedule-info">
                <div class="schedule-top">
                  <span class="schedule-title">{{ item.title }}</span>
                  <el-tag size="small" :type="item.scheduleTypeDesc === '会议' ? 'success' : 'primary'">{{ item.scheduleTypeDesc }}</el-tag>
                </div>
                <div class="schedule-desc">
                  {{ formatScheduleTime(item.startTime, item.endTime) }}
                  <span v-if="item.location" style="margin-left: 8px;">📍 {{ item.location }}</span>
                </div>
              </div>
            </div>
          </div>
          <div v-else class="schedule-item">
            <div class="schedule-dot" style="background-color: #E6A23C;"></div>
            <div class="schedule-info">
              <div class="schedule-title">今日暂无日程安排</div>
              <div class="schedule-desc">{{ today }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
import { getDashboardDataApi } from '@/api/modules/dashboard'
import { useRouter } from 'vue-router'

const router = useRouter()
const data = ref({})

const today = computed(() => {
  const d = new Date()
  const week = ['日', '一', '二', '三', '四', '五', '六']
  return `${d.getFullYear()}年${d.getMonth() + 1}月${d.getDate()}日 星期${week[d.getDay()]}`
})

// 快捷入口
const shortcuts = [
  { label: '发起审批', path: '/approve/index', icon: 'Edit', color: '#409EFF' },
  { label: '员工管理', path: '/company/employee', icon: 'User', color: '#67C23A' },
  { label: '日程管理', path: '/company/schedule', icon: 'Calendar', color: '#36cfc9' },
  { label: '员工入职', path: '/company/entry', icon: 'Plus', color: '#E6A23C' },
  { label: '员工离职', path: '/company/resignation', icon: 'SwitchButton', color: '#F56C6C' },
  { label: '变动记录', path: '/company/positionChange', icon: 'Sort', color: '#909399' },
  { label: '公告管理', path: '/note', icon: 'Bell', color: '#b37feb' },
  { label: '系统设置', path: '/system/setting', icon: 'Setting', color: '#ffc53d' }
]

function goTo(path) {
  router.push(path)
}

function goToAuditForm(id) {
  router.push({ path: '/system/editApproval', query: { id } })
}

function formatScheduleTime(startTime, endTime) {
  if (!startTime) return ''
  const start = startTime.substring(5, 16).replace('-', '/').replace(' ', ' ')
  const end = endTime ? endTime.substring(11, 16) : ''
  return end ? `${start}-${end}` : start
}

function getData() {
  getDashboardDataApi().then((res) => {
    data.value = res
  })
}

onMounted(() => {
  getData()
})
</script>

<style scoped lang="scss">
.dashboard {
  padding: 16px;
}

/* 统计看板 */
.stat-row {
  margin-bottom: 16px;
}
.stat-card {
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 0;
  transition: transform 0.2s;
  &:hover {
    transform: translateY(-2px);
  }
  :deep(.el-card__body) {
    display: flex;
    align-items: center;
    padding: 20px;
    width: 100%;
  }
}
.stat-icon {
  width: 64px;
  height: 64px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 16px;
  flex-shrink: 0;
}
.stat-pending .stat-icon {
  background: #e6f4ff;
  color: #1677ff;
}
.stat-dealt .stat-icon {
  background: #f6ffed;
  color: #52c41a;
}
.stat-my .stat-icon {
  background: #fff7e6;
  color: #fa8c16;
}
.stat-cc .stat-icon {
  background: #f9f0ff;
  color: #722ed1;
}
.stat-info {
  flex: 1;
}
.stat-value {
  font-size: 28px;
  font-weight: 600;
  line-height: 1.2;
  color: #303133;
}
.stat-label {
  font-size: 14px;
  color: #909399;
  margin-top: 4px;
}

/* 通用卡片 */
.content-row {
  .section-card {
    margin-bottom: 16px;
  }
}
.section-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.section-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

/* 待办事项 */
.todo-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
  cursor: pointer;
  &:last-child {
    border-bottom: none;
  }
  &:hover {
    background-color: #fafafa;
  }
}
.todo-left {
  display: flex;
  align-items: center;
  gap: 8px;
}
.todo-name {
  font-size: 14px;
  color: #303133;
}
.todo-time {
  font-size: 12px;
  color: #c0c4cc;
}

/* 快捷入口 */
.shortcut-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px 8px;
  cursor: pointer;
  border-radius: 8px;
  transition: background-color 0.2s;
  &:hover {
    background-color: #f5f7fa;
  }
}
.shortcut-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 8px;
}
.shortcut-label {
  font-size: 13px;
  color: #606266;
  text-align: center;
}

/* 组织概览 */
.overview-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 12px 0;
  border-bottom: 1px solid #f0f0f0;
  &:last-child {
    border-bottom: none;
  }
}
.overview-label {
  font-size: 14px;
  color: #606266;
}
.overview-value {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

/* 公告 */
.announcement-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
  &:last-child {
    border-bottom: none;
  }
}
.announcement-left {
  display: flex;
  align-items: center;
  gap: 8px;
  flex: 1;
  overflow: hidden;
}
.announcement-title {
  font-size: 14px;
  color: #303133;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.announcement-time {
  font-size: 12px;
  color: #c0c4cc;
  flex-shrink: 0;
  margin-left: 8px;
}

/* 日程提醒 */
.schedule-item {
  display: flex;
  align-items: flex-start;
  gap: 12px;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
  &:last-child {
    border-bottom: none;
  }
}
.schedule-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
  margin-top: 6px;
  flex-shrink: 0;
}
.schedule-info {
  flex: 1;
  min-width: 0;
}
.schedule-top {
  display: flex;
  align-items: center;
  gap: 6px;
}
.schedule-title {
  font-size: 14px;
  color: #606266;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.schedule-desc {
  font-size: 12px;
  color: #c0c4cc;
  margin-top: 4px;
}
</style>
