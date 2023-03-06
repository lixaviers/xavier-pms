import request from '@/utils/request'

const url = '/menu'

/**
 * 获取当前登录用户的菜单
 * @returns
 */
export const getUserMenuApi = () => {
  return request({
    url: `${url}/getUserMenu`,
    method: 'get'
  })
}

/**
 * 获取所有菜单树
 * @returns
 */
export const getMenuTreeApi = () => {
  return request({
    url: `${url}/getTree`,
    method: 'get'
  })
}

/**
 * 创建/编辑
 * @returns
 */
export function addOrUpdateMenuApi(data) {
  return request({
    url: `${url}${!data.id ? '/add' : '/update'}`,
    method: 'put',
    data
  })
}

/**
 * 获取菜单信息
 * @param {*} id
 * @returns
 */
export const getMenuApi = (id) => {
  return request({
    url: `${url}/get/${id}`,
    method: 'get'
  })
}

/**
 * 删除菜单
 * @param {*} id
 * @returns
 */
export const deleteMenuApi = (id) => {
  return request({
    url: `${url}/delete/${id}`,
    method: 'delete'
  })
}

// 查询菜单列表
export function listMenu(query) {
  return request({
    url: '/system/menu/list',
    method: 'get',
    params: query
  })
}

// 查询菜单详细
export function getMenu(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'get'
  })
}

// 查询菜单下拉树结构
export function treeselect() {
  return request({
    url: '/system/menu/treeselect',
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function roleMenuTreeselect(roleId) {
  return request({
    url: '/system/menu/roleMenuTreeselect/' + roleId,
    method: 'get'
  })
}

// 新增菜单
export function addMenu(data) {
  return request({
    url: '/system/menu',
    method: 'post',
    data: data
  })
}

// 修改菜单
export function updateMenu(data) {
  return request({
    url: '/system/menu',
    method: 'put',
    data: data
  })
}

// 删除菜单
export function delMenu(menuId) {
  return request({
    url: '/system/menu/' + menuId,
    method: 'delete'
  })
}
