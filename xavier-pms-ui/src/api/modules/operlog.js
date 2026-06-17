import request from '@/utils/request'
const url = '/operateLog'

/**
 * 分页获取操作日志列表
 * @param {*} data 查询参数
 * @returns
 */
export const queryOperateLogApi = (data) => {
  return request({
    url: `${url}/query`,
    method: 'post',
    data
  })
}
