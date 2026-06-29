<template>
  <el-dialog
    v-model="visible"
    :title="isEdit ? '编辑日程' : (scheduleType === 2 ? '预约会议' : '新增日程')"
    width="600px"
    destroy-on-close
    :close-on-click-modal="false"
    class="schedule-dialog"
    @close="handleClose"
  >
    <div class="form-type-indicator" :style="{ background: scheduleType === 2 ? 'linear-gradient(135deg, #67C23A, #95d475)' : 'linear-gradient(135deg, #409EFF, #79bbff)' }">
      <el-icon :size="20"><component :is="scheduleType === 2 ? VideoCamera : Calendar" /></el-icon>
      <span>{{ scheduleType === 2 ? '会议' : '个人日程' }}</span>
    </div>

    <el-form ref="formRef" :model="form" :rules="rules" label-width="80px" class="schedule-form">
      <el-form-item label="标题" prop="title">
        <el-input v-model="form.title" placeholder="请输入日程标题" maxlength="200" show-word-limit />
      </el-form-item>

      <el-form-item label="时间" prop="startTime">
        <div class="time-range">
          <el-date-picker
            v-model="form.startTime"
            type="datetime"
            placeholder="开始时间"
            format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="flex: 1"
          />
          <span class="time-sep">至</span>
          <el-date-picker
            v-model="form.endTime"
            type="datetime"
            placeholder="结束时间"
            format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="flex: 1"
          />
        </div>
      </el-form-item>

      <el-row :gutter="16">
        <el-col :span="8">
          <el-form-item label="全天">
            <el-switch v-model="form.isAllDay" />
          </el-form-item>
        </el-col>
        <el-col :span="16">
          <el-form-item label="提醒">
            <el-select v-model="form.remindType" placeholder="提醒方式" style="width: 100%">
              <el-option v-for="r in remindOptions" :key="r.value" :label="r.label" :value="r.value" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="地点">
        <el-input v-model="form.location" placeholder="请输入地点" maxlength="200" />
      </el-form-item>

      <el-form-item label="会议室" v-if="scheduleType === 2">
        <el-select v-model="form.meetingRoomId" placeholder="请选择会议室" clearable style="width: 100%">
          <el-option v-for="item in meetingRoomList" :key="item.id" :label="item.meetingName" :value="item.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="颜色">
        <div class="color-picker-row">
          <div
            v-for="c in predefineColors"
            :key="c"
            :class="['color-dot', { active: form.color === c }]"
            :style="{ backgroundColor: c }"
            @click="form.color = c"
          ></div>
          <el-color-picker v-model="form.color" size="small" :predefine="predefineColors" />
        </div>
      </el-form-item>

      <el-form-item label="参与人" v-if="scheduleType === 2">
        <el-select v-model="form.participantIds" multiple filterable placeholder="请选择参与人" style="width: 100%">
          <el-option v-for="item in employeeList" :key="item.id" :label="`${item.nickName}（${item.employeeNumber}）`" :value="item.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="公开" v-if="scheduleType === 2">
        <el-switch v-model="form.isPublic" active-text="团队可见" inactive-text="仅自己可见" />
      </el-form-item>

      <el-form-item label="描述">
        <el-input v-model="form.description" type="textarea" :rows="3" placeholder="请输入描述" maxlength="2000" show-word-limit />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" @click="handleSubmit" class="submit-btn">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { VideoCamera, Calendar } from '@element-plus/icons-vue'
import { addScheduleApi, getScheduleApi } from '@/api/modules/schedule'
import { queryMeetingApi } from '@/api/modules/meeting'
import { queryUserApi } from '@/api/modules/user'

const emit = defineEmits(['refresh'])

const visible = ref(false)
const isEdit = ref(false)
const scheduleType = ref(1)
const formRef = ref(null)
const meetingRoomList = ref([])
const employeeList = ref([])

const predefineColors = [
  '#409EFF', '#67C23A', '#E6A23C', '#F56C6C', '#909399',
  '#00b96b', '#722ed1', '#13c2c2', '#eb2f96', '#594f4f'
]

const remindOptions = [
  { value: 0, label: '不提醒' },
  { value: 1, label: '提前5分钟' },
  { value: 2, label: '提前15分钟' },
  { value: 3, label: '提前30分钟' },
  { value: 4, label: '提前1小时' },
  { value: 5, label: '提前1天' }
]

const form = ref(getDefaultForm())

const rules = {
  title: [{ required: true, message: '请输入日程标题', trigger: 'blur' }],
  startTime: [{ required: true, message: '请选择开始时间', trigger: 'change' }],
  endTime: [{ required: true, message: '请选择结束时间', trigger: 'change' }]
}

function getDefaultForm() {
  return {
    id: null, title: '', scheduleType: 1, startTime: '', endTime: '',
    isAllDay: false, location: '', meetingRoomId: null, remindType: 0,
    color: '#409EFF', description: '', isPublic: false, participantIds: []
  }
}

async function init(id, type, dateStr) {
  visible.value = true
  isEdit.value = !!id
  scheduleType.value = type

  try {
    const res = await queryMeetingApi({ pageNo: 1, pageSize: 100, isEnable: true })
    meetingRoomList.value = res.records || []
  } catch (e) { meetingRoomList.value = [] }

  try {
    const res = await queryUserApi({ pageNo: 1, pageSize: 1000, userStatus: 1 })
    employeeList.value = res.records || []
  } catch (e) { employeeList.value = [] }

  if (id) {
    const res = await getScheduleApi(id)
    form.value = {
      id: res.id, title: res.title, scheduleType: res.scheduleType,
      startTime: res.startTime ? res.startTime.replace('T', ' ').substring(0, 19) : '',
      endTime: res.endTime ? res.endTime.replace('T', ' ').substring(0, 19) : '',
      isAllDay: res.isAllDay || false, location: res.location || '',
      meetingRoomId: res.meetingRoomId || null, remindType: res.remindType ?? 0,
      color: res.color || '#409EFF', description: res.description || '',
      isPublic: res.isPublic || false, participantIds: (res.participants || []).map(p => p.userId)
    }
  } else {
    const defaultColor = type === 2 ? '#67C23A' : '#409EFF'
    let startTime = '', endTime = ''
    if (dateStr) {
      const d = dateStr.substring(0, 10)
      startTime = `${d} 09:00:00`
      endTime = `${d} 10:00:00`
    }
    form.value = {
      id: null, title: '', scheduleType: type, startTime, endTime,
      isAllDay: false, location: '', meetingRoomId: null,
      remindType: type === 2 ? 3 : 0, color: defaultColor,
      description: '', isPublic: type === 2, participantIds: []
    }
  }
}

function handleClose() {
  visible.value = false
  form.value = getDefaultForm()
}

async function handleSubmit() {
  try { await formRef.value.validate() } catch (e) { return }

  const data = { ...form.value }
  if (data.isAllDay && data.startTime) {
    data.startTime = data.startTime.substring(0, 10) + ' 00:00:00'
    data.endTime = (data.endTime ? data.endTime.substring(0, 10) : data.startTime.substring(0, 10)) + ' 23:59:59'
  }

  try {
    await addScheduleApi(data)
    ElMessage.success(isEdit.value ? '编辑成功' : '新增成功')
    handleClose()
    emit('refresh')
  } catch (e) { /* handled by interceptor */ }
}

defineExpose({ init })
</script>

<style scoped lang="scss">
.form-type-indicator {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 10px 16px;
  border-radius: 10px;
  color: #fff;
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 20px;
}

.time-range {
  display: flex;
  align-items: center;
  gap: 8px;
  width: 100%;
}

.time-sep {
  color: #c0c4cc;
  font-size: 14px;
  flex-shrink: 0;
}

.color-picker-row {
  display: flex;
  align-items: center;
  gap: 8px;
  flex-wrap: wrap;
}

.color-dot {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  cursor: pointer;
  transition: all 0.15s;
  border: 2px solid transparent;

  &:hover { transform: scale(1.15); }
  &.active { border-color: #303133; box-shadow: 0 0 0 2px rgba(48, 49, 51, 0.15); }
}

.dialog-footer {
  display: flex;
  justify-content: flex-end;
  gap: 8px;
}

.submit-btn {
  min-width: 80px;
}

.schedule-form {
  :deep(.el-form-item__label) {
    font-weight: 500;
    color: #4e5969;
  }
}
</style>
