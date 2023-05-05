import request from '@/utils/request'

const url = '/userGroup'

/**
 * 分页获取员工组列表信息
 * @returns
 */
export const queryUserGroupApi = (data) => {
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
export function addOrUpdateUserGroupApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 删除员工组
 * @param {*} id
 * @returns
 */
export const deleteUserGroupApi = (data) => {
  return request({
    url: `${url}/delete`,
    method: 'delete',
    data
  })
}

/**
 * 根据员工组ID获取员工组信息
 * @param {*} id
 * @returns
 */
export const getUserGroupApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
