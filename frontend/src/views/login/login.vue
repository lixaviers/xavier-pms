<template>
  <div class="login-container">
    <div class="box-item">
      <h1 class="tagline">
        <span class="accent">星辰人事管理系统</span>
      </h1>
      <el-form :model="loginForm" ref="loginFormRef" :rules="rules" :inline="false">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" maxlength="20" :prefix-icon="User" size="large" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item prop="password" style="margin-top: 25px">
          <el-input v-model="loginForm.password" maxlength="20" type="password" :prefix-icon="Key" size="large" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            v-model="loginForm.verCode"
            size="large"
            :prefix-icon="Lock"
            auto-complete="off"
            placeholder="验证码"
            style="width: 216px"
            maxlength="5"
            @keyup.enter="onSubmit"
          />
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img" />
          </div>
        </el-form-item>
        <el-form-item style="margin-top: 25px">
          <el-button type="primary" @click="onSubmit" :loading="loading" size="large" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
  import { loginApi } from '@/api/modules/login-api.js';

  import { User, Lock, Key } from '@element-plus/icons-vue';
  import useUserStore from '@/store/modules/user';

  const userStore = useUserStore();
  const router = useRouter();

  const loginFormRef = ref();

  const loginForm = reactive({
    username: '10000',
    password: 'qweasd123',
    verCode: '',
    uuid: '',
  });
  const rules = {
    username: [{ required: true, message: '用户名不能为空' }],
    password: [{ required: true, message: '密码不能为空' }],
    verCode: [{ required: true, message: '验证码不能为空' }],
  };
  const codeUrl = ref('');
  const loading = ref(false);

  onMounted(() => {
    getCode();
  });

  function getCode() {
    loginApi.getCaptchaImage().then((res) => {
      codeUrl.value = res.codeUrl;
      loginForm.uuid = res.uuid;
    });
  }

  function onSubmit() {
    loginFormRef.value.validate((valid) => {
      if (valid) {
        userStore
          .login(loginForm)
          .then(() => {
            router.push({ path: '/' });
          })
          .catch(() => {
            getCode();
          })
          .finally(() => {
            loading.value = false;
          });
      }
    });
  }
</script>

<style lang="less" scoped>
  @import './login.scss';
</style>
