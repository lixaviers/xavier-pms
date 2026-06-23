import request from '@/utils/request'

const url = '/report'

/**
 * 获取员工信息报表
 * @returns
 */
export const getEmployeeReportApi = () => {
  return request({
    url: `${url}/employee`,
    method: 'get'
  })
}

/**
 * 获取转正统计报表
 * @returns
 */
export const getConversionReportApi = () => {
  return request({
    url: `${url}/conversion`,
    method: 'get'
  })
}

/**
 * 获取离职统计报表
 * @returns
 */
export const getResignationReportApi = () => {
  return request({
    url: `${url}/resignation`,
    method: 'get'
  })
}

/**
 * 导出员工信息Excel
 */
export const exportEmployeeExcelApi = () => {
  return request({
    url: `${url}/employee/export`,
    method: 'get',
    responseType: 'blob'
  })
}

/**
 * 导出转正统计Excel
 */
export const exportConversionExcelApi = () => {
  return request({
    url: `${url}/conversion/export`,
    method: 'get',
    responseType: 'blob'
  })
}

/**
 * 导出离职统计Excel
 */
export const exportResignationExcelApi = () => {
  return request({
    url: `${url}/resignation/export`,
    method: 'get',
    responseType: 'blob'
  })
}
