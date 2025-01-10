<template>
  <div>
    <el-button icon="Plus" @click="handleSelect()"></el-button>
    <span v-if="props.modelValue && props.modelValue.length > 0">
      <el-tag v-for="(employee, index) in props.modelValue" :key="employee.id" closable class="ml5" size="large" @close="handelDelete(index)">{{
        employee.nickName
      }}</el-tag>
    </span>
    <select-employee :ref="(el) => setItemRef(el)" v-model="employeeList" multiple @submitForm="submitForm" />
  </div>
</template>

<script setup>
  import { queryUserApi } from '@/api/modules/user';
  import { getDeptListUtil } from '@/utils/xavier';
  import { deepClone } from '@/utils';

  const { proxy } = getCurrentInstance();
  const emits = defineEmits();

  const props = defineProps({
    modelValue: {
      type: Array,
      default: [],
    },
  });
  const employeeList = ref([]);
  const selectEmployeeRef = ref();

  function setItemRef(el) {
    if (el) {
      selectEmployeeRef.value = el;
    }
  }

  /**
   * 选择员工
   */
  function handleSelect() {
    console.log(props.modelValue);
    selectEmployeeRef.value.init(props.modelValue);
  }

  function handelDelete(index) {
    props.modelValue.splice(index, 1);
  }

  function submitForm() {
    emits('update:modelValue', deepClone(employeeList.value)); // to update v-model
  }
</script>
