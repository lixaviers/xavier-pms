<template>
  <div class="approve-basic-form">
    <el-form
      ref="formRef"
      :model="props.dataForm"
      :rules="rules"
      label-width="80px"
      label-position="top"
    >
      <el-form-item label="图标" prop="icon">
        <div v-if="props.dataForm.icon" class="xavier-approve-icon">
          <svg-icon :icon-class="props.dataForm.icon" />
        </div>
        <el-popover
          placement="bottom"
          title="选择图标"
          :width="180"
          trigger="click"
          :visible="visible"
          @show="showSelectIcon"
        >
          <template #reference>
            <el-button @click="showSelectIcon">修改</el-button>
          </template>
          <div class="icon-list">
            <div
              v-for="item in iconList"
              :key="item"
              class="xavier-approve-icon"
              @click="handleClick(item)"
            >
              <svg-icon :icon-class="item" />
            </div>
            <div class="xavier-approve-icon">
              <svg-icon icon-class="user" />
            </div>
          </div>
        </el-popover>
      </el-form-item>
      <el-form-item label="审批名称" prop="approvalName">
        <el-input
          v-model="props.dataForm.approvalName"
          max="100"
          placeholder="请输入"
        />
      </el-form-item>
      <el-form-item label="分组" prop="approvalGroupId">
        <el-select
          v-model="props.dataForm.approvalGroupId"
          style="width: 100%"
        >
          <el-option
            v-for="item in props.groupList"
            :key="item.id"
            :label="item.groupName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="说明" prop="remarks">
        <el-input
          v-model="props.dataForm.remarks"
          type="textarea"
          placeholder="请输入"
          maxlength="200"
        />
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
const { proxy } = getCurrentInstance()
const emits = defineEmits()

const props = defineProps({
  dataForm: {
    type: Object,
    required: true
  },
  groupList: {
    type: Array,
    default: []
  }
})
const data = reactive({
  rules: {
    icon: [{ required: true, message: '请选择', trigger: 'blur' }],
    approvalName: [{ required: true, message: '请输入', trigger: 'blur' }],
    approvalGroupId: [
      { required: true, message: '请选择', trigger: 'change' }
    ],
    submitType: [{ required: true, message: '请选择', trigger: 'change' }]
  }
})
const { rules } = toRefs(data)
const visible = ref(false)
const iconList = ['user', 'approve', 'form', 'car', 'system']

function handleClick(icon) {
  props.dataForm.icon = icon
  visible.value = false
}

/**
 * 展示下拉图标
 */
function showSelectIcon() {
  visible.value = true
}
</script>

<style lang="scss" scoped>
.approve-basic-form {
  width: 400px;
  margin: 0 auto;
}
.icon-list {
  display: grid;
  grid-template-columns: repeat(3, 40px);
  grid-template-rows: repeat(auto, 40px);
  grid-gap: 16px;
  justify-content: flex-start;
  align-items: flex-start;
  flex-wrap: wrap;

  .xavier-approve-icon {
    cursor: pointer;
  }
}
</style>
