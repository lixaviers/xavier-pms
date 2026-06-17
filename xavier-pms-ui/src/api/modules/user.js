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
 * 根据id查询员工卡片信息
 * @param {*} id
 * @returns
 */
export const getUserCardApi = (id) => {
  return request({
    url: `${url}/getCard/${id}`,
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
 * 查询用户授权角色
 * @param {*} id 用户id
 * @returns
 */
export const getAuthRole = (id) => {
  return request({
    url: `${url}/getAuthRole/${id}`,
    method: 'get'
  })
}

/**
 * 更新用户授权角色
 * @param {*} data
 * @returns
 */
export const updateAuthRole = (data) => {
  return request({
    url: `${url}/updateAuthRole`,
    method: 'put',
    data
  })
}

/**
 * 查询部门树选择
 * @returns
 */
export const deptTreeSelect = () => {
  return request({
    url: `${url}/deptTreeSelect`,
    method: 'get'
  })
}

// 以下为 user/index.vue 中引用的兼容函数
export const listUser = (data) => {
  return request({
    url: `${url}/list`,
    method: 'get',
    params: data
  })
}

export const getUser = (id) => {
  return request({
    url: `${url}/${id}`,
    method: 'get'
  })
}

export const addUser = (data) => {
  return request({
    url: `${url}`,
    method: 'post',
    data
  })
}

export const updateUser = (data) => {
  return request({
    url: `${url}`,
    method: 'put',
    data
  })
}

export const delUser = (id) => {
  return request({
    url: `${url}/${id}`,
    method: 'delete'
  })
}

export const resetUserPwd = (id, password) => {
  return request({
    url: `${url}/resetPwd`,
    method: 'put',
    data: { id, password }
  })
}

export const changeUserStatus = (id, status) => {
  return request({
    url: `${url}/changeStatus`,
    method: 'put',
    data: { id, status }
  })
}

export const uploadAvatar = (data) => {
  return request({
    url: `${url}/avatar`,
    method: 'post',
    data
  })
}

export const updateUserProfile = (data) => {
  return request({
    url: `${url}/profile`,
    method: 'put',
    data
  })
}

export const updateUserPwd = (oldPassword, newPassword) => {
  return request({
    url: `${url}/changePassword`,
    method: 'put',
    data: { oldPassword, newPassword }
  })
}

export const getUserProfile = () => {
  return request({
    url: `${url}/profile`,
    method: 'get'
  })
}

/**
 * 获取当前登录用户详细个人信息
 */
export const getProfileApi = () => {
  return request({
    url: `${url}/getProfile`,
    method: 'get'
  })
}
