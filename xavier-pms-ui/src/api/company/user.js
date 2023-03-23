import request from '@/utils/request'

const url = '/user'

/**
 * 分页获取列表信息
 * @returns
 */
export const queryUserApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}

/**
 * 根据部门id查询员工列表信息
 * @param {*} id 部门id
 * @returns
 */
export const getUserByDepartmentIdApi = (id) => {
  return request({
    url: `${url}/getByDepartmentId/${id}`,
    method: 'get'
  })
}

/**
 * 创建/编辑
 * @returns
 */
export function addOrUpdateUserApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 获取预计转正日期
 * @returns
 */
export function getEstimatedConversionDateApi(entryDate, probationPeriod) {
  return request({
    url: `${url}/getEstimatedConversionDate?entryDate=${entryDate}&probationPeriod=${probationPeriod}`,
    method: 'get'
  })
}

/**
 * 删除
 * @param {*} id
 * @returns
 */
export const deleteUserApi = (id) => {
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
export const getUserApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
