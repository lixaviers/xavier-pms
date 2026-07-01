import request from '@/utils/request'

const url = '/reportTemplate'

// 新增/编辑汇报模板
export const addReportTemplateApi = (data) => {
  return request({ url: `${url}/add`, method: 'put', data })
}

// 分页查询汇报模板
export const queryReportTemplateApi = (data) => {
  return request({ url: `${url}/query`, method: 'post', data })
}

// 查询汇报模板详情
export const getReportTemplateApi = (id) => {
  return request({ url: `${url}/get/${id}`, method: 'get' })
}

// 删除汇报模板
export const deleteReportTemplateApi = (id) => {
  return request({ url: `${url}/delete`, method: 'delete', params: { id } })
}
