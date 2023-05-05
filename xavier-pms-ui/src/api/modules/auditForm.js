import request from '@/utils/request'

const url = '/auditForm'

/**
 * 创建/编辑
 * @returns
 */
export function addAuditFormApi(data) {
  return request({
    url: `${url}/add`,
    method: 'put',
    data
  })
}

/**
 * 分页获取列表信息
 * @param {*} id
 * @returns
 */
export const queryAuditFormApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}

/**
 * 修改状态
 * @param {*} id
 * @returns
 */
export const updateStatusAuditFormApi = (data) => {
  return request({
    url: `${url}/updateStatus`,
    method: 'post',
    data
  })
}

/**
 * 根据ID获取信息
 * @param {*} id
 * @returns
 */
export const getAuditFormApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}

/**
 * 根据审批单id获取审批单流程信息
 * @param {*} id
 * @returns
 */
export const getAuditFormProcessApi = (id) => {
  return request({
    url: `${url}/getProcess/${id}`,
    method: 'get'
  })
}
