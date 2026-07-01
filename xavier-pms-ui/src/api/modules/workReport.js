import request from '@/utils/request'

const url = '/workReport'

// 新增/编辑工作汇报
export const addWorkReportApi = (data) => {
  return request({ url: `${url}/add`, method: 'put', data })
}

// 分页查询工作汇报
export const queryWorkReportApi = (data) => {
  return request({ url: `${url}/query`, method: 'post', data })
}

// 查询工作汇报详情
export const getWorkReportApi = (id) => {
  return request({ url: `${url}/get/${id}`, method: 'get' })
}

// 删除工作汇报
export const deleteWorkReportApi = (id) => {
  return request({ url: `${url}/delete`, method: 'delete', params: { id } })
}

// 提交工作汇报
export const submitWorkReportApi = (id) => {
  return request({ url: `${url}/submit/${id}`, method: 'put' })
}

// 添加汇报评论
export const addCommentApi = (data) => {
  return request({ url: `${url}/comment`, method: 'put', data })
}

// 汇报统计
export const reportStatApi = (params) => {
  return request({ url: `${url}/stat`, method: 'get', params })
}
