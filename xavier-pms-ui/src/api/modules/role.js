import request from '@/utils/request'

const url = '/role'

/**
 * 分页获取角色列表信息
 * @returns
 */
export const queryRoleApi = (data) => {
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
export function addOrUpdateRoleApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 删除角色
 * @param {*} id
 * @returns
 */
export const deleteRoleApi = (data) => {
  return request({
    url: `${url}/delete`,
    method: 'delete',
    data
  })
}

/**
 * 根据角色ID获取角色信息
 * @param {*} id
 * @returns
 */
export const getRoleApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
