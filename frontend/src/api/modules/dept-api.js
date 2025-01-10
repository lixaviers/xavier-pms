import request from '@/utils/request.js';

const url = '/department';

export const deptApi = {
  // 分页获取列表信息
  query: (data) => {
    return request({
      url: `${url}/query`,
      method: 'post',
      data,
    });
  },
  // 创建/编辑
  addOrUpdate: (data) => {
    return request({
      url: `${url}${!data.id ? '/add' : '/update'}`,
      method: 'put',
      data,
    });
  },
  // 删除
  del: (id) => {
    return request({
      url: `${url}/delete/${id}`,
      method: 'delete',
    });
  },
  // 根据ID获取信息
  get: (id) => {
    return request({
      url: `${url}/get/${id}`,
      method: 'get',
    });
  },
};
