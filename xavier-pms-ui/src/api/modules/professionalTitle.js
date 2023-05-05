import request from '@/utils/request'

const url = '/professionalTitle'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryProfessionalTitleApi = (data) => {
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
export function addOrUpdateProfessionalTitleApi(data) {
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
export const deleteProfessionalTitleApi = (data) => {
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
export const getProfessionalTitleApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
