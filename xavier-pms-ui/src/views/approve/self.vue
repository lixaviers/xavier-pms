<template>
  <div>
    <approve-index
      type="self"
      :statusList="[
        { value: '0', label: '全部' },
        { value: '3', label: '审批中' },
        { value: '4', label: '审批通过' },
        { value: '5', label: '审批拒绝' }
      ]"
    />
  </div>
</template>
<script setup>
import approveIndex from './index.vue'
</script>
