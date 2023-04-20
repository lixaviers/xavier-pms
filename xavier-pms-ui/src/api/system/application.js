import request from '@/utils/request'

const url = '/application'

/**
 * 创建/编辑
 * @returns
 */
export function addOrUpdateApplicationApi(data) {
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
export const deleteApplicationApi = (id) => {
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
export const getApplicationApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
