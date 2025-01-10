import { loginApi } from '@/api/modules/login-api.js';
import { getToken, setToken, removeToken } from '@/utils/auth';
import defAva from '@/assets/images/profile.jpg';

const useUserStore = defineStore('user', {
  state: () => ({
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: [],
  }),
  actions: {
    // 登录
    login(userInfo) {
      const userName = userInfo.username.trim();
      const userPwd = userInfo.password;
      const verCode = userInfo.verCode;
      const uuid = userInfo.uuid;
      return new Promise((resolve, reject) => {
        loginApi
          .login({
            userName: userName,
            userPwd: userPwd,
            verCode: verCode,
            uuid: uuid,
          })
          .then((res) => {
            setToken(res);
            this.token = res;
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 获取用户信息
    getInfo() {
      return new Promise((resolve, reject) => {
        loginApi
          .getInfo()
          .then((res) => {
            const user = res.user;
            this.roles = ['ROLE_DEFAULT'];
            this.permissions = res.permissions;
            this.name = user.nickName;
            this.avatar = defAva;
            resolve(res);
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    // 退出系统
    logOut() {
      return new Promise((resolve, reject) => {
        loginApi
          .logout(this.token)
          .then(() => {
            this.token = '';
            this.roles = [];
            this.permissions = [];
            removeToken();
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
});

export default useUserStore;
