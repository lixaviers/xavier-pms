<template>
  <div>
    <el-dialog :title="title" v-model="visible" width="680px" append-to-body>
      <el-form ref="menuRef" :model="dataForm" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="上级菜单">
              <el-tree-select
                v-model="dataForm.parentId"
                :data="menuOptions"
                :props="{
                  value: 'id',
                  label: 'menuName',
                  children: 'children',
                }"
                value-key="id"
                placeholder="选择上级菜单"
                check-strictly
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="菜单类型" prop="menuType">
              <el-radio-group v-model="dataForm.menuType">
                <el-radio value="M" label="目录" />
                <el-radio value="C" label="菜单" />
                <el-radio value="I" label="接口" />
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24" v-if="dataForm.menuType != 'I'">
            <el-form-item label="菜单图标" prop="icon">
              <el-popover placement="bottom-start" :width="540" v-model:visible="showChooseIcon" trigger="click" @show="showSelectIcon">
                <template #reference>
                  <el-input v-model="dataForm.icon" placeholder="点击选择图标" @blur="showSelectIcon" v-click-outside="hideSelectIcon" readonly>
                    <template #prefix>
                      <svg-icon v-if="dataForm.icon" :icon-class="dataForm.icon" class="el-input__icon" style="height: 32px; width: 16px" />
                      <el-icon v-else style="height: 32px; width: 16px"><search /></el-icon>
                    </template>
                  </el-input>
                </template>
                <icon-select ref="iconSelectRef" @selected="selected" />
              </el-popover>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="菜单名称" prop="menuName">
              <el-input v-model="dataForm.menuName" placeholder="请输入" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="sortNumber">
              <el-input-number v-model="dataForm.sortNumber" controls-position="right" :precision="0" :min="1" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType != 'I'">
            <el-form-item>
              <template #label>
                <span>
                  <el-tooltip content="选择是外链则路由地址需要以`http(s)://`开头" placement="top">
                    <el-icon><question-filled /></el-icon> </el-tooltip
                  >是否外链
                </span>
              </template>
              <el-switch v-model="dataForm.isFrame" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType != 'I'">
            <el-form-item prop="url">
              <template #label>
                <span>
                  <el-tooltip content="访问的路由地址，如：`user`，如外网地址需内链访问则以`http(s)://`开头" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  路由地址
                </span>
              </template>
              <el-input v-model="dataForm.url" placeholder="请输入路由地址" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType == 'C'">
            <el-form-item prop="component">
              <template #label>
                <span>
                  <el-tooltip content="访问的组件路径，如：`system/user/index`，默认在`views`目录下" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  组件路径
                </span>
              </template>
              <el-input v-model="dataForm.component" placeholder="请输入" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType != 'M'">
            <el-form-item>
              <el-input v-model="dataForm.perms" placeholder="请输入权限标识" maxlength="100" />
              <template #label>
                <span>
                  <el-tooltip content="控制器中定义的权限字符，如：@PreAuthorize(`@ss.hasPermi('system:user:list')`)" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  权限字符
                </span>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType == 'C'">
            <el-form-item>
              <el-input v-model="dataForm.query" placeholder="请输入路由参数" maxlength="255" />
              <template #label>
                <span>
                  <el-tooltip content='访问路由的默认传递参数，如：`{"id": 1, "name": "ry"}`' placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  路由参数
                </span>
              </template>
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType == 'C'">
            <el-form-item>
              <template #label>
                <span>
                  <el-tooltip content="选择是则会被`keep-alive`缓存，需要匹配组件的`name`和地址保持一致" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  是否缓存
                </span>
              </template>
              <el-switch v-model="dataForm.isCache" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType != 'I'">
            <el-form-item>
              <template #label>
                <span>
                  <el-tooltip content="选择隐藏则路由将不会出现在侧边栏，但仍然可以访问" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  是否显示
                </span>
              </template>
              <el-switch v-model="dataForm.isShow" />
            </el-form-item>
          </el-col>
          <el-col :span="12" v-if="dataForm.menuType != 'I'">
            <el-form-item>
              <template #label>
                <span>
                  <el-tooltip content="选择停用则路由将不会出现在侧边栏，也不能被访问" placement="top">
                    <el-icon><question-filled /></el-icon>
                  </el-tooltip>
                  是否启用
                </span>
              </template>
              <el-switch v-model="dataForm.isEnable" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm" auto-insert-space>确定</el-button>
          <el-button @click="cancel" auto-insert-space>取消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
  import { getMenuApi, getMenuTreeApi, addOrUpdateMenuApi } from '@/api/modules/menu';
  import IconSelect from '@/components/IconSelect/index.vue';
  import { ClickOutside as vClickOutside } from 'element-plus';

  const data = reactive({
    dataForm: {},
    rules: {
      menuName: [{ required: true, message: '请输入', trigger: 'blur' }],
      sortNumber: [{ required: true, message: '请输入', trigger: 'blur' }],
    },
  });

  const { dataForm, rules } = toRefs(data);
  const { proxy } = getCurrentInstance();
  const emits = defineEmits();

  // 是否显示
  const visible = ref(false);
  const title = ref('');
  const menuOptions = ref([]);
  const showChooseIcon = ref(false);
  const iconSelectRef = ref(null);

  /**
   * 初始化页面
   */
  async function init(id, parentId) {
    visible.value = true;
    await getTreeselect();
    if (id) {
      title.value = '编辑菜单';
      const response = await getMenuApi(id);
      dataForm.value = response;
    } else {
      title.value = '新增菜单';
      dataForm.value = {
        parentId: parentId ? parentId : '0',
        menuType: 'M',
        sortNumber: 1,
        isFrame: false,
        isCache: false,
        isShow: true,
        isEnable: true,
      };
    }
  }

  /** 查询菜单下拉树结构 */
  async function getTreeselect() {
    menuOptions.value = [];
    const response = await getMenuTreeApi();
    const menu = { id: '0', menuName: '主类目', children: [] };
    menu.children = response;
    menuOptions.value.push(menu);
  }
  /** 取消按钮 */
  function cancel() {
    visible.value = false;
  }
  /** 展示下拉图标 */
  function showSelectIcon() {
    iconSelectRef.value.reset();
    showChooseIcon.value = true;
  }
  /** 选择图标 */
  function selected(name) {
    dataForm.value.icon = name;
    showChooseIcon.value = false;
  }
  /** 图标外层点击隐藏下拉列表 */
  function hideSelectIcon(event) {
    var elem = event.relatedTarget || event.srcElement || event.target || event.currentTarget;
    var className = elem.className;
    if (className !== 'el-input__inner') {
      showChooseIcon.value = false;
    }
  }
  /** 提交按钮 */
  function submitForm() {
    proxy.$refs['menuRef'].validate((valid) => {
      if (valid) {
        addOrUpdateMenuApi(dataForm.value).then((response) => {
          proxy.$modal.msgSuccess('保存成功');
          visible.value = false;
          emits('refreshDataList');
        });
      }
    });
  }

  defineExpose({
    init,
  });
</script>
