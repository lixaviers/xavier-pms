<template>
  <div class="login-container">
    <div class="box-item">
      <h1 class="tagline">
        <span class="accent">星辰人事管理系统</span>
      </h1>
      <el-form :model="loginForm" ref="loginFormRef" :rules="rules" :inline="false">
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" size="large" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item prop="password" style="margin-top: 25px">
          <el-input v-model="loginForm.password" type="password" size="large" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item style="margin-top: 25px">
          <el-button type="primary" @click="onSubmit" size="large" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
  import { onMounted, onUnmounted, reactive, ref } from 'vue';

  const loginFormRef = ref();

  const loginForm = reactive({
    username: 'admin',
    password: '',
    captchaCode: '',
  });
  const rules = {
    username: [{ required: true, message: '用户名不能为空' }],
    password: [{ required: true, message: '密码不能为空' }],
    captchaCode: [{ required: true, message: '验证码不能为空' }],
  };

  function onSubmit() {
    loginFormRef.value.validate((valid) => {
      console.log(valid);
    });
  }
</script>

<style scoped>
  @import './login.scss';
</style>
