import request from '@/utils/request'

const url = '/applicationGroup'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryApplicationGroupApi = (data) => {
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
export function addOrUpdateApplicationGroupApi(data) {
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
export const deleteApplicationGroupApi = (id) => {
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
export const getApplicationGroupApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
