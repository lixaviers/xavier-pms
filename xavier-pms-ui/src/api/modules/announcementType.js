import request from '@/utils/request'

const url = '/announcementType'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryAnnouncementTypeApi = (data) => {
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
export function addOrUpdateAnnouncementTypeApi(data) {
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
export const deleteAnnouncementTypeApi = (data) => {
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
export const getAnnouncementTypeApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
