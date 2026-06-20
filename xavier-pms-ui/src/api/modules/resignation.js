import request from '@/utils/request'

const url = '/resignation'

/**
 * 分页查询离职记录
 * @returns
 */
export const queryResignationApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}

/**
 * 员工离职
 * @returns
 */
export const addResignationApi = (data) => {
  return request({
    url: `${url}/add`,
    method: 'put',
    data
  })
}

/**
 * 查询离职详情
 * @param {*} id
 * @returns
 */
export const getResignationApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
