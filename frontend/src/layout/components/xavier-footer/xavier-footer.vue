<template>
  <div class="xavier-footer">
    <span>©{{ currentYear }}&nbsp;{{ titile }}&nbsp;|&nbsp;V1.1.0</span>
  </div>
</template>
<script setup>
  const currentYear = new Date().getFullYear();
  const titile = import.meta.env.VITE_TITLE;
</script>

<style lang="scss" scoped>
  .xavier-footer {
    font-size: 14px;
    color: rgba(0, 0, 0, 0.45);
    text-align: center;

    a {
      color: rgba(0, 0, 0, 0.45);
    }
  }
</style>
