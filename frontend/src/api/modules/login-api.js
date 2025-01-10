import request from '@/utils/request';

export const loginApi = {
  // 登录
  login: (data) => {
    return request({
      url: '/login',
      method: 'post',
      data: data,
    });
  },
  // 获取用户详细信息
  getInfo: () => {
    return request({
      url: '/getInfo',
      method: 'get',
    });
  },
  // 退出
  logout: () => {
    return request({
      url: '/logout',
      method: 'post',
    });
  },
  // 获取验证码
  getCaptchaImage: () => {
    return request({
      url: '/getCaptchaImage',
      method: 'post',
    });
  },
};
