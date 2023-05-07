<template>
  <div>
    <approve-index
      type="deal"
      :statusList="[
        { value: '0', label: '待办' },
        { value: '1', label: '已办' }
      ]"
    />
  </div>
</template>
<script setup>
import approveIndex from './index.vue'
</script>
