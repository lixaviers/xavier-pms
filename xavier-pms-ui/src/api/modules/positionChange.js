import request from '@/utils/request'

const url = '/positionChange'

/**
 * 分页查询变动记录
 * @returns
 */
export const queryPositionChangeApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}

/**
 * 员工职位/职称变动
 * @returns
 */
export const addPositionChangeApi = (data) => {
  return request({
    url: `${url}/add`,
    method: 'put',
    data
  })
}

/**
 * 查询变动详情
 * @param {*} id
 * @returns
 */
export const getPositionChangeApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}
