import request from '@/utils/request'

// 获取个人卡片显示配置
export function getEmployeeCardConfig() {
  return request({
    url: '/systemConfig/getEmployeeCardConfig',
    method: 'get'
  })
}

// 保存个人卡片显示配置
export function saveEmployeeCardConfig(data) {
  return request({
    url: '/systemConfig/saveEmployeeCardConfig',
    method: 'post',
    data: data
  })
}
