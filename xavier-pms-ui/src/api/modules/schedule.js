import request from '@/utils/request'

const url = '/schedule'

// 新增/编辑日程
export const addScheduleApi = (data) => {
  return request({ url: `${url}/add`, method: 'put', data })
}

// 分页查询日程
export const queryScheduleApi = (data) => {
  return request({ url: `${url}/query`, method: 'post', data })
}

// 查询日程详情
export const getScheduleApi = (id) => {
  return request({ url: `${url}/get/${id}`, method: 'get' })
}

// 删除日程
export const deleteScheduleApi = (id) => {
  return request({ url: `${url}/delete`, method: 'delete', params: { id } })
}

// 日历视图查询
export const calendarScheduleApi = (params) => {
  return request({ url: `${url}/calendar`, method: 'get', params })
}

// 接受/拒绝会议
export const acceptScheduleApi = (params) => {
  return request({ url: `${url}/accept`, method: 'put', params })
}

// 今日待提醒日程
export const todayRemindScheduleApi = () => {
  return request({ url: `${url}/todayRemind`, method: 'get' })
}

// 即将到来的日程
export const upcomingScheduleApi = () => {
  return request({ url: `${url}/upcoming`, method: 'get' })
}
