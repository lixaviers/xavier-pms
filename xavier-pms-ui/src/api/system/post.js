import request from '@/utils/request'

const url = '/post'

/**
 * 分页获取职位列表信息
 * @returns
 */
export const queryPostApi = (data) => {
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
export function addOrUpdatePostApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 删除职位
 * @param {*} id
 * @returns
 */
export const deletePostApi = (data) => {
  return request({
    url: `${url}/delete`,
    method: 'delete',
    data
  })
}

/**
 * 根据职位ID获取职位信息
 * @param {*} id
 * @returns
 */
export const getPostApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
