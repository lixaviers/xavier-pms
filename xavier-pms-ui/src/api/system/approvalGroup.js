import request from '@/utils/request'

const url = '/approvalGroup'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryApprovalGroupApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}

/**
 * 创建/编辑
 * @returns
 */
export function addOrUpdateApprovalGroupApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 删除
 * @param {*} id
 * @returns
 */
export const deleteApprovalGroupApi = (id) => {
  return request({
    url: `${url}/delete/${id}`,
    method: 'delete'
  })
}

/**
 * 根据ID获取信息
 * @param {*} id
 * @returns
 */
export const getApprovalGroupApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
