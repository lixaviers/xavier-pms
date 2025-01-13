<template>
  <!-- 添加或修改对话框 -->
  <el-dialog :title="title" v-model="visible" width="500px" append-to-body :close-on-click-modal="false">
    <el-form ref="formRef" :model="dataForm" :rules="rules" label-width="80px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="dataForm.roleName" max="50" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="角色名称" prop="roleName">
        <el-checkbox v-model="menuExpand" @change="handleCheckedTreeExpand($event, 'menu')">展开/折叠</el-checkbox>
        <el-checkbox v-model="menuNodeAll" @change="handleCheckedTreeNodeAll($event, 'menu')">全选/全不选</el-checkbox>
        <el-tree
          class="tree-border"
          :data="menuList"
          show-checkbox
          ref="menuRef"
          node-key="id"
          empty-text="加载中，请稍候"
          :props="{
            value: 'id',
            label: 'menuName',
            children: 'children',
          }"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input v-model="dataForm.remarks" type="textarea" placeholder="请输入" maxlength="200" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="submitForm" auto-insert-space>确定</el-button>
        <el-button @click="handleCancel" auto-insert-space>取消</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
  import { addOrUpdateRoleApi, getRoleApi } from '@/api/modules/role';
  import { getMenuTreeApi } from '@/api/modules/menu';

  const { proxy } = getCurrentInstance();
  const emits = defineEmits();

  const data = reactive({
    dataForm: {},
    rules: {
      roleName: [{ required: true, message: '请输入', trigger: 'blur' }],
    },
  });

  const { dataForm, rules } = toRefs(data);
  const menuRef = ref(null);
  const visible = ref(false);
  const title = ref('');
  const menuList = ref([]);
  const menuExpand = ref(false);
  const menuNodeAll = ref(false);

  /**
   * 取消按钮
   */
  function handleCancel() {
    visible.value = false;
    reset();
  }

  /**
   * 表单重置
   */
  function reset() {
    dataForm.value = {};
    proxy.resetForm('formRef');
  }

  /**
   * 提交按钮
   */
  function submitForm() {
    proxy.$refs['formRef'].validate((valid) => {
      if (valid) {
        dataForm.value.menuIdList = getMenuAllCheckedKeys();
        addOrUpdateRoleApi(dataForm.value).then((response) => {
          proxy.$modal.msgSuccess(`${!dataForm.value.id ? '新增' : '编辑'}成功`);
          visible.value = false;
          emits('refreshDataList');
        });
      }
    });
  }

  /**
   * 查询菜单列表
   */
  async function getMenuList() {
    menuList.value = await getMenuTreeApi();
  }

  /**
   * 树权限（展开/折叠）
   */
  function handleCheckedTreeExpand(value, type) {
    let treeList = menuList.value;
    for (let i = 0; i < treeList.length; i++) {
      menuRef.value.store.nodesMap[treeList[i].id].expanded = value;
    }
  }
  /**
   * 树权限（全选/全不选）
   */
  function handleCheckedTreeNodeAll(value, type) {
    menuRef.value.setCheckedNodes(value ? menuList.value : []);
  }

  /**
   * 所有菜单节点数据
   */
  function getMenuAllCheckedKeys() {
    // 目前被选中的菜单节点
    let checkedKeys = menuRef.value.getCheckedKeys();
    // 半选中的菜单节点
    let halfCheckedKeys = menuRef.value.getHalfCheckedKeys();
    checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
    return checkedKeys;
  }

  /**
   * 初始化
   */
  async function init(id) {
    visible.value = true;
    menuExpand.value = false;
    menuNodeAll.value = false;
    await getMenuList();
    if (id) {
      title.value = '编辑角色';
      const response = await getRoleApi(id);
      dataForm.value = response;
      nextTick(() => {
        if (response.menuIdList) {
          response.menuIdList.forEach((v) => {
            nextTick(() => {
              menuRef.value.setChecked(v, true, false);
            });
          });
        }
      });
    } else {
      reset();
      title.value = '新增角色';
    }
  }

  defineExpose({
    init,
  });
</script>
