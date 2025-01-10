import request from '@/utils/request'

const url = '/approval'

/**
 * 创建/编辑
 * @returns
 */
export function addOrUpdateApprovalApi(data) {
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
export const deleteApprovalApi = (id) => {
  return request({
    url: `${url}/delete/${id}`,
    method: 'delete'
  })
}

/**
 * 修改状态
 * @param {*} id
 * @returns
 */
export const updateStatusApprovalApi = (data) => {
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
export const getApprovalApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
