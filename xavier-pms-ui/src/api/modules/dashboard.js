import request from '@/utils/request'

const url = '/dashboard'

/**
 * 获取工作台数据
 * @returns
 */
export const getDashboardDataApi = () => {
  return request({
    url: `${url}/data`,
    method: 'get'
  })
}
