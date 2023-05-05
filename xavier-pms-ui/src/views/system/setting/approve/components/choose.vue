<template>
  <el-card class="mt10" shadow="never">
    <template #header>
      <div class="card-header">
        <span>{{ props.title }}</span>
        <el-button
          @click="handelDelete"
          icon="Delete"
          circle
          size="small"
          type="danger"
        ></el-button>
      </div>
    </template>
    <el-form-item>
      <el-radio-group
        v-model="props.dataForm.type"
        @change="handelChangeCcType"
      >
        <el-radio
          v-for="item in typeList"
          :key="item.value"
          :label="item.value"
          >{{ item.label }}</el-radio
        >
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-select
        v-if="props.dataForm.type === 'role'"
        v-model="props.dataForm.idList"
        style="width: 100%"
        multiple
      >
        <el-option
          v-for="item in roleList"
          :key="item.id"
          :value="item.id"
          :label="item.roleName"
        />
      </el-select>
      <el-select
        v-else-if="props.dataForm.type === 'userGroup'"
        v-model="props.dataForm.idList"
        style="width: 100%"
        multiple
      >
        <el-option
          v-for="userGroup in userGroupList"
          :key="userGroup.id"
          :value="userGroup.id"
          :label="userGroup.groupName"
        />
      </el-select>
      <div v-else-if="props.dataForm.type === 'employee'">
        <button-select-employee v-model="props.dataForm.employeeList" />
      </div>
    </el-form-item>
  </el-card>
</template>

<script setup>
import { queryUserGroupApi } from '@/api/modules/userGroup'
import { queryRoleApi } from '@/api/modules/role'
const props = defineProps({
  title: {
    type: String,
    default: ''
  },
  dataForm: {
    type: Object,
    required: true
  }
})
const emits = defineEmits()
// 用户组列表
const userGroupList = ref([])
// 角色列表
const roleList = ref([])
const typeList = ref([
  {
    value: 'parent',
    label: '直属上级'
  },
  {
    value: 'dept',
    label: '部门负责人'
  },
  {
    value: 'role',
    label: '角色'
  },
  {
    value: 'userGroup',
    label: '员工组'
  },
  {
    value: 'employee',
    label: '指定员工'
  },
  {
    value: 'submitter',
    label: '提交人'
  }
])

/**
 * 修改抄送人类型
 */
function handelChangeCcType(val) {
  props.dataForm.typeDesc = typeList.value.filter(
    (item) => item.value === val
  )[0].label
  if (val === 'role') {
    getRoleList()
  } else if (val === 'userGroup') {
    getUserGroupList()
  }
}

/**
 * 查询角色列表
 */
function getRoleList() {
  queryRoleApi({ pageSize: 1000 }).then((response) => {
    roleList.value = response.records
  })
}

/**
 * 查询用户组列表
 */
function getUserGroupList() {
  queryUserGroupApi({ pageSize: 1000 }).then((response) => {
    userGroupList.value = response.records
  })
}

function handelDelete() {
  emits('handelDelete')
}

onMounted(() => {
  handelChangeCcType(props.dataForm.type)
})
</script>
