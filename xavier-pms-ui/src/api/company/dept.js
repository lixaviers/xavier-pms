import request from '@/utils/request'

const url = '/department'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryDepartmentApi = (data) => {
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
export function addOrUpdateDepartmentApi(data) {
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
export const deleteDepartmentApi = (id) => {
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
export const getDepartmentApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
