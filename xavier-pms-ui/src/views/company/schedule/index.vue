<template>
  <div class="schedule-page">
    <!-- 顶部导航栏 -->
    <div class="schedule-header">
      <div class="header-left">
        <h2 class="page-title">日程管理</h2>
        <div class="nav-controls">
          <button class="nav-btn" @click="goPrev">
            <el-icon><ArrowLeft /></el-icon>
          </button>
          <button class="nav-btn today-btn" @click="goToday">今天</button>
          <button class="nav-btn" @click="goNext">
            <el-icon><ArrowRight /></el-icon>
          </button>
          <span class="calendar-title">{{ calendarTitle }}</span>
        </div>
      </div>
      <div class="header-right">
        <div class="view-switcher">
          <button
            v-for="v in views"
            :key="v.key"
            :class="['view-btn', { active: currentView === v.key }]"
            @click="changeView(v.key)"
          >{{ v.label }}</button>
        </div>
        <div class="filter-group">
          <button
            v-for="f in filters"
            :key="f.value"
            :class="['filter-chip', { active: filterType === f.value }]"
            @click="filterType = f.value; refreshCalendar()"
          >
            <span class="filter-dot" :style="{ backgroundColor: f.color }"></span>
            {{ f.label }}
          </button>
        </div>
        <el-checkbox v-model="onlyMine" @change="refreshCalendar" class="mine-check">仅我的</el-checkbox>
        <div class="action-buttons">
          <button class="action-btn primary" @click="handleAdd()">
            <el-icon><Plus /></el-icon>新增日程
          </button>
          <button class="action-btn success" @click="handleAddMeeting()">
            <el-icon><VideoCamera /></el-icon>预约会议
          </button>
        </div>
      </div>
    </div>

    <!-- 日历主体 -->
    <div class="calendar-wrapper">
      <FullCalendar ref="fullCalendar" :options="calendarOptions" />
    </div>

    <!-- 新增/编辑弹窗 -->
    <add-or-update ref="addOrUpdateRef" @refresh="refreshCalendar" />

    <!-- 详情侧滑面板 -->
    <el-drawer v-model="detailVisible" :show-close="false" size="420px" direction="rtl">
      <template #header>
        <div class="drawer-header">
          <div class="drawer-type-badge" :style="{ backgroundColor: detail.color || (detail.scheduleType === 2 ? '#67C23A' : '#409EFF') }">
            {{ detail.scheduleTypeDesc }}
          </div>
          <div class="drawer-actions">
            <el-button text @click="handleEdit(detail)"><el-icon><Edit /></el-icon></el-button>
            <el-button text type="danger" @click="handleDelete(detail.id)"><el-icon><Delete /></el-icon></el-button>
          </div>
        </div>
      </template>
      <div class="drawer-body">
        <h3 class="detail-title">{{ detail.title }}</h3>

        <div class="detail-section">
          <div class="detail-row">
            <div class="detail-icon"><el-icon><Clock /></el-icon></div>
            <div class="detail-content">
              <div class="detail-label">时间</div>
              <div class="detail-value">{{ formatTime(detail.startTime) }} - {{ formatTime(detail.endTime) }}</div>
            </div>
          </div>
          <div class="detail-row" v-if="detail.location">
            <div class="detail-icon"><el-icon><Location /></el-icon></div>
            <div class="detail-content">
              <div class="detail-label">地点</div>
              <div class="detail-value">{{ detail.location }}</div>
            </div>
          </div>
          <div class="detail-row" v-if="detail.meetingRoomName">
            <div class="detail-icon"><el-icon><OfficeBuilding /></el-icon></div>
            <div class="detail-content">
              <div class="detail-label">会议室</div>
              <div class="detail-value">{{ detail.meetingRoomName }}</div>
            </div>
          </div>
          <div class="detail-row" v-if="detail.remindType > 0">
            <div class="detail-icon"><el-icon><Bell /></el-icon></div>
            <div class="detail-content">
              <div class="detail-label">提醒</div>
              <div class="detail-value">{{ detail.remindTypeDesc }}</div>
            </div>
          </div>
          <div class="detail-row" v-if="detail.description">
            <div class="detail-icon"><el-icon><Document /></el-icon></div>
            <div class="detail-content">
              <div class="detail-label">描述</div>
              <div class="detail-value desc">{{ detail.description }}</div>
            </div>
          </div>
        </div>

        <!-- 参与人 -->
        <div class="participant-section" v-if="detail.scheduleType === 2 && detail.participants && detail.participants.length > 0">
          <div class="section-label">参与人 ({{ detail.participants.length }})</div>
          <div class="participant-list">
            <div class="participant-item" v-for="p in detail.participants" :key="p.id">
              <el-avatar :size="32" class="participant-avatar">{{ p.nickName?.substring(0, 1) }}</el-avatar>
              <div class="participant-info">
                <span class="participant-name">{{ p.nickName }}</span>
                <span :class="['participant-status', `status-${p.acceptStatus}`]">{{ p.acceptStatusDesc }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script setup name="Schedule">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ArrowLeft, ArrowRight, Plus, VideoCamera, Clock, Location, OfficeBuilding, Bell, Document, Edit, Delete } from '@element-plus/icons-vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin from '@fullcalendar/interaction'
import { calendarScheduleApi, getScheduleApi, deleteScheduleApi } from '@/api/modules/schedule'
import AddOrUpdate from './addOrUpdate.vue'

const addOrUpdateRef = ref(null)
const fullCalendar = ref(null)
const filterType = ref(0)
const onlyMine = ref(false)
const detailVisible = ref(false)
const detail = ref({})
const calendarTitle = ref('')
const currentView = ref('month')

let calendarApi = null

const views = [
  { key: 'day', label: '日' },
  { key: 'week', label: '周' },
  { key: 'month', label: '月' }
]

const filters = [
  { value: 0, label: '全部', color: '#409EFF' },
  { value: 1, label: '个人', color: '#409EFF' },
  { value: 2, label: '会议', color: '#67C23A' }
]

const calendarOptions = reactive({
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  initialView: 'dayGridMonth',
  locale: 'zh-cn',
  headerToolbar: false,
  height: 'auto',
  firstDay: 1,
  editable: false,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: 4,
  weekends: true,
  events: [],
  eventClick: handleEventClick,
  dateClick: handleDateClick,
  datesSet: handleDatesSet,
  eventTimeFormat: { hour: '2-digit', minute: '2-digit', hour12: false },
  dayHeaderContent: (args) => {
    const days = ['日', '一', '二', '三', '四', '五', '六']
    return '周' + days[args.date.getDay()]
  }
})

function changeView(view) {
  const api = getCalendarApi()
  if (!api) return
  const viewMap = { day: 'timeGridDay', week: 'timeGridWeek', month: 'dayGridMonth' }
  api.changeView(viewMap[view])
  currentView.value = view
}

function goToday() { getCalendarApi()?.today() }
function goPrev() { getCalendarApi()?.prev() }
function goNext() { getCalendarApi()?.next() }

function handleDatesSet(info) {
  calendarTitle.value = info.view.title
  // 同步当前视图类型
  const viewType = info.view.type
  if (viewType === 'dayGridMonth') currentView.value = 'month'
  else if (viewType === 'timeGridWeek') currentView.value = 'week'
  else if (viewType === 'timeGridDay') currentView.value = 'day'
  loadCalendarEvents(info.startStr, info.endStr)
}

function handleEventClick(info) {
  getScheduleApi(info.event.id).then(res => {
    detail.value = res
    detailVisible.value = true
  })
}

function handleDateClick(info) { handleAdd(info.dateStr) }

function getCalendarApi() {
  if (!calendarApi) calendarApi = fullCalendar.value?.getApi()
  return calendarApi
}

function loadCalendarEvents(startStr, endStr) {
  const params = {
    startTime: startStr.replace('T', ' ').replace('Z', ''),
    endTime: endStr.replace('T', ' ').replace('Z', '')
  }
  calendarScheduleApi(params).then(res => {
    const api = getCalendarApi()
    if (!api) return
    const events = (res || []).map(item => ({
      id: item.id,
      title: (item.scheduleType === 2 ? '👥 ' : '📌 ') + item.title,
      start: item.startTime,
      end: item.endTime,
      allDay: item.isAllDay,
      backgroundColor: item.color || (item.scheduleType === 2 ? '#67C23A' : '#409EFF'),
      borderColor: 'transparent',
      extendedProps: { scheduleType: item.scheduleType }
    }))
    const filtered = events.filter(e => {
      if (filterType.value !== 0 && e.extendedProps.scheduleType !== filterType.value) return false
      return true
    })
    api.removeAllEvents()
    api.addEventSource(filtered)
  })
}

function refreshCalendar() {
  const api = getCalendarApi()
  if (api) {
    const currentDate = api.getDate()
    const start = new Date(currentDate)
    start.setDate(1); start.setHours(0, 0, 0, 0)
    const end = new Date(currentDate)
    end.setMonth(end.getMonth() + 1); end.setDate(0); end.setHours(23, 59, 59, 0)
    loadCalendarEvents(formatDateTime(start), formatDateTime(end))
  }
}

function formatDateTime(date) {
  const pad = n => String(n).padStart(2, '0')
  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`
}

function formatTime(t) {
  if (!t) return ''
  return t.replace('T', ' ').substring(0, 16)
}

function handleAdd(dateStr) { addOrUpdateRef.value?.init(null, 1, dateStr) }
function handleAddMeeting() { addOrUpdateRef.value?.init(null, 2) }
function handleEdit(row) {
  detailVisible.value = false
  addOrUpdateRef.value?.init(row.id, row.scheduleType)
}
function handleDelete(id) {
  ElMessageBox.confirm('确认删除该日程？', '提示', {
    confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning'
  }).then(() => {
    deleteScheduleApi(id).then(() => {
      ElMessage.success('删除成功')
      detailVisible.value = false
      refreshCalendar()
    })
  }).catch(() => {})
}

onMounted(() => {})
</script>

<style scoped lang="scss">
.schedule-page {
  padding: 0;
  background: #f5f7fa;
  min-height: calc(100vh - 84px);
  display: flex;
  flex-direction: column;
}

/* ── 顶部导航 ── */
.schedule-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px 28px 16px;
  background: #fff;
  border-bottom: 1px solid #ebeef5;
  flex-wrap: wrap;
  gap: 16px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 28px;
}

.page-title {
  font-size: 20px;
  font-weight: 700;
  color: #1d2129;
  margin: 0;
  letter-spacing: -0.3px;
}

.nav-controls {
  display: flex;
  align-items: center;
  gap: 6px;
}

.nav-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 36px;
  height: 36px;
  border: 1px solid #e5e6eb;
  border-radius: 8px;
  background: #fff;
  color: #4e5969;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.2s;

  &:hover { border-color: #409EFF; color: #409EFF; background: #f0f7ff; }
}

.today-btn {
  width: auto;
  padding: 0 14px;
  font-weight: 500;
}

.calendar-title {
  font-size: 17px;
  font-weight: 600;
  color: #1d2129;
  margin-left: 10px;
  min-width: 140px;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 16px;
  flex-wrap: wrap;
}

/* ── 视图切换 ── */
.view-switcher {
  display: flex;
  background: #f2f3f5;
  border-radius: 8px;
  padding: 3px;
}

.view-btn {
  padding: 6px 16px;
  border: none;
  border-radius: 6px;
  background: transparent;
  color: #86909c;
  cursor: pointer;
  font-size: 13px;
  font-weight: 500;
  transition: all 0.2s;

  &.active {
    background: #fff;
    color: #1d2129;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  }

  &:hover:not(.active) { color: #4e5969; }
}

/* ── 筛选标签 ── */
.filter-group {
  display: flex;
  gap: 8px;
}

.filter-chip {
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 5px 14px;
  border-radius: 20px;
  border: 1px solid #e5e6eb;
  background: #fff;
  color: #86909c;
  cursor: pointer;
  font-size: 13px;
  transition: all 0.2s;

  &.active {
    border-color: #409EFF;
    color: #409EFF;
    background: #f0f7ff;
  }

  &:hover:not(.active) { border-color: #c9cdd4; color: #4e5969; }
}

.filter-dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
}

.mine-check {
  :deep(.el-checkbox__label) { font-size: 13px; color: #86909c; }
}

/* ── 操作按钮 ── */
.action-buttons {
  display: flex;
  gap: 8px;
}

.action-btn {
  display: inline-flex;
  align-items: center;
  gap: 5px;
  padding: 8px 18px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 13px;
  font-weight: 500;
  transition: all 0.2s;

  &.primary {
    background: linear-gradient(135deg, #409EFF, #337ecc);
    color: #fff;
    box-shadow: 0 2px 6px rgba(64, 158, 255, 0.3);
    &:hover { box-shadow: 0 4px 12px rgba(64, 158, 255, 0.4); transform: translateY(-1px); }
  }

  &.success {
    background: linear-gradient(135deg, #67C23A, #529b2e);
    color: #fff;
    box-shadow: 0 2px 6px rgba(103, 194, 58, 0.3);
    &:hover { box-shadow: 0 4px 12px rgba(103, 194, 58, 0.4); transform: translateY(-1px); }
  }
}

/* ── 日历主体 ── */
.calendar-wrapper {
  flex: 1;
  padding: 20px 28px;
  background: #fff;
  margin: 16px;
  border-radius: 16px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.04);
}

/* ── FullCalendar 深度样式覆盖 ── */
:deep(.fc) {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
  border: none !important;
}

:deep(.fc-toolbar) { display: none; }

:deep(.fc-scrollgrid) {
  border: none !important;
  border-radius: 0;
}

:deep(.fc-scrollgrid td),
:deep(.fc-scrollgrid th) {
  border-color: #f0f0f0 !important;
}

:deep(.fc-col-header-cell) {
  background: #fafafa;
  padding: 10px 0 !important;
  border-bottom: 1px solid #f0f0f0 !important;
}

:deep(.fc-col-header-cell-cushion) {
  color: #86909c;
  font-size: 13px;
  font-weight: 500;
  text-decoration: none !important;
}

:deep(.fc-daygrid-day) {
  transition: background 0.15s;
  &:hover { background: #f7f9fc; }
}

:deep(.fc-daygrid-day-number) {
  font-size: 14px;
  font-weight: 500;
  color: #4e5969;
  padding: 8px !important;
  text-decoration: none !important;
}

:deep(.fc-day-today) {
  background: #f0f7ff !important;

  .fc-daygrid-day-number {
    color: #409EFF;
    font-weight: 700;
  }
}

:deep(.fc-day-other) {
  .fc-daygrid-day-number { color: #c9cdd4; }
}

:deep(.fc-event) {
  cursor: pointer;
  border-radius: 6px !important;
  border: none !important;
  padding: 2px 8px !important;
  margin: 1px 4px !important;
  font-size: 12px !important;
  font-weight: 500;
  transition: all 0.15s;

  &:hover {
    transform: translateY(-1px);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    z-index: 10;
  }
}

:deep(.fc-daygrid-event-dot) { display: none; }

:deep(.fc-more-link) {
  color: #409EFF;
  font-weight: 500;
  font-size: 12px;
}

/* 时间网格视图 */
:deep(.fc-timegrid-slot) {
  height: 48px !important;
  border-color: #f5f5f5 !important;
}

:deep(.fc-timegrid-slot-label) {
  font-size: 12px;
  color: #86909c;
}

:deep(.fc-timegrid-event) {
  border-radius: 6px !important;
  border: none !important;
}

/* ── 详情侧滑面板 ── */
.drawer-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
}

.drawer-type-badge {
  display: inline-block;
  padding: 4px 14px;
  border-radius: 20px;
  color: #fff;
  font-size: 12px;
  font-weight: 600;
}

.drawer-actions {
  display: flex;
  gap: 4px;
}

.drawer-body {
  padding-top: 8px;
}

.detail-title {
  font-size: 20px;
  font-weight: 700;
  color: #1d2129;
  margin: 0 0 24px;
  line-height: 1.4;
}

.detail-section {
  display: flex;
  flex-direction: column;
  gap: 18px;
}

.detail-row {
  display: flex;
  gap: 14px;
  align-items: flex-start;
}

.detail-icon {
  width: 36px;
  height: 36px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;
  background: #f2f3f5;
  color: #86909c;
  font-size: 16px;
  flex-shrink: 0;
}

.detail-content {
  flex: 1;
}

.detail-label {
  font-size: 12px;
  color: #c9cdd4;
  margin-bottom: 2px;
}

.detail-value {
  font-size: 14px;
  color: #4e5969;
  line-height: 1.5;

  &.desc {
    background: #f7f8fa;
    padding: 10px 14px;
    border-radius: 8px;
    white-space: pre-wrap;
  }
}

/* ── 参与人 ── */
.participant-section {
  margin-top: 28px;
  padding-top: 20px;
  border-top: 1px solid #f0f0f0;
}

.section-label {
  font-size: 13px;
  font-weight: 600;
  color: #86909c;
  margin-bottom: 12px;
}

.participant-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.participant-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 12px;
  background: #f7f8fa;
  border-radius: 10px;
}

.participant-avatar {
  background: linear-gradient(135deg, #409EFF, #337ecc);
  color: #fff;
  font-size: 13px;
  font-weight: 600;
}

.participant-info {
  display: flex;
  align-items: center;
  gap: 10px;
  flex: 1;
}

.participant-name {
  font-size: 14px;
  color: #4e5969;
  font-weight: 500;
}

.participant-status {
  font-size: 12px;
  padding: 2px 8px;
  border-radius: 10px;
  font-weight: 500;

  &.status-0 { background: #fff7e6; color: #d46b08; }
  &.status-1 { background: #f6ffed; color: #389e0d; }
  &.status-2 { background: #fff1f0; color: #cf1322; }
}
</style>
