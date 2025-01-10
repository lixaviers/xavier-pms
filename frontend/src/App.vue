<template>
  <router-view />
</template>

<script setup>
  onMounted(() => {
    document.getElementById('loadingPage').remove();
  });
</script>
<style lang="less"></style>
