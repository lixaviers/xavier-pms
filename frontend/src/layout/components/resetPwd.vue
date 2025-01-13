<template>
  <el-dialog title="修改密码" v-model="visible" width="500px" append-to-body :close-on-click-modal="false">
    <el-form ref="pwdRef" :model="user" :rules="rules" label-width="80px">
      <el-form-item label="旧密码" prop="oldPassword">
        <el-input v-model="user.oldPassword" placeholder="请输入旧密码" type="password" show-password />
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="user.newPassword" placeholder="请输入新密码" type="password" show-password />
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="user.confirmPassword" placeholder="请确认新密码" type="password" show-password />
      </el-form-item>
      <el-form-item>
        <el-button :loading="loading" type="primary" @click="submit" auto-insert-space>保存</el-button>
        <el-button type="danger" @click="close" auto-insert-space>关闭</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script setup>
  import { updateUserPwdApi } from '@/api/modules/user';
  const { proxy } = getCurrentInstance();

  const user = reactive({
    oldPassword: undefined,
    newPassword: undefined,
    confirmPassword: undefined,
  });
  const visible = ref();
  const loading = ref(false);

  const equalToPassword = (rule, value, callback) => {
    if (user.newPassword !== value) {
      callback(new Error('两次输入的密码不一致'));
    } else {
      callback();
    }
  };
  const rules = ref({
    oldPassword: [{ required: true, message: '旧密码不能为空', trigger: 'blur' }],
    newPassword: [
      { required: true, message: '新密码不能为空', trigger: 'blur' },
      { min: 6, max: 20, message: '长度在6到20个字符', trigger: 'blur' },
    ],
    confirmPassword: [
      { required: true, message: '确认密码不能为空', trigger: 'blur' },
      { required: true, validator: equalToPassword, trigger: 'blur' },
    ],
  });
  async function init(id) {
    visible.value = true;
  }

  /** 提交按钮 */
  function submit() {
    proxy.$refs.pwdRef.validate((valid) => {
      if (valid) {
        loading.value = true;
        updateUserPwdApi({ oldPassword: user.oldPassword, newPassword: user.newPassword })
          .then(() => {
            proxy.$modal.msgSuccess('修改成功');
            close();
          })
          .finally(() => {
            loading.value = false;
          });
      }
    });
  }

  function close() {
    visible.value = false;
    proxy.resetForm('pwdRef');
  }

  defineExpose({
    init,
  });
</script>
