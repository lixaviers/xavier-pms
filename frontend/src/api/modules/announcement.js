import request from '@/utils/request'

const url = '/announcement'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryAnnouncementApi = (data) => {
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
export function addOrUpdateAnnouncementApi(data) {
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
export const deleteAnnouncementApi = (data) => {
  return request({
    url: `${url}/delete`,
    method: 'delete',
    data
  })
}

/**
 * 根据ID获取信息
 * @param {*} id
 * @returns
 */
export const getAnnouncementApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
