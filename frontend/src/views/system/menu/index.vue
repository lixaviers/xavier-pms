<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="success" plain icon="Plus" @click="handleAdd()" v-hasPermi="['system:menu:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="isExpandAll" type="info" plain icon="Sort" @click="toggleExpandAll">折叠</el-button>
        <el-button v-else type="info" plain icon="Sort" @click="toggleExpandAll">展开</el-button>
      </el-col>
      <right-toolbar :searchFlag="false" @queryTable="getDataList"></right-toolbar>
    </el-row>

    <el-table border v-if="refreshTable" v-loading="loading" :data="dataList" row-key="id" :default-expand-all="isExpandAll">
      <el-table-column prop="menuName" label="菜单名称" :show-overflow-tooltip="true" width="160"></el-table-column>
      <el-table-column prop="icon" label="图标" align="center" width="100">
        <template #default="scope">
          <svg-icon v-if="scope.row.icon" :icon-class="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column prop="sortNumber" label="排序" width="60"></el-table-column>
      <el-table-column prop="menuCode" label="权限标识" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="url" label="路由地址" :show-overflow-tooltip="true" />
      <el-table-column prop="component" label="组件路径" :show-overflow-tooltip="true" />
      <el-table-column prop="status" label="状态" width="80">
        <template #default="scope">
          <el-tag v-if="scope.row.isEnable">启用</el-tag>
          <el-tag type="danger" v-else>禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" width="160" prop="createTime" />
      <el-table-column fixed="right" label="操作" align="center" width="210">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleAdd(scope.row.id)" v-hasPermi="['system:menu:edit']">修改</el-button>
          <el-button link type="success" icon="Plus" @click="handleAdd(null, scope.row.id)" v-hasPermi="['system:menu:add']">新增</el-button>
          <el-button link type="danger" icon="Delete" @click="handleDelete(scope.row.id)" v-hasPermi="['system:menu:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <addOrUpdate ref="addOrUpdateRef" @refreshDataList="getDataList()" />
  </div>
</template>

<script setup name="Menu">
  import { deleteMenuApi, getMenuTreeApi } from '@/api/modules/menu';
  import addOrUpdate from './addOrUpdate.vue';

  const { proxy } = getCurrentInstance();

  // 数据列表
  const dataList = ref([]);
  // 表格加载
  const loading = ref(true);
  const isExpandAll = ref(false);
  const refreshTable = ref(true);
  const addOrUpdateRef = ref(null);

  /**
   * 查询菜单列表
   */
  function getDataList() {
    loading.value = true;
    getMenuTreeApi().then((response) => {
      dataList.value = response;
      loading.value = false;
    });
  }

  /**
   * 新增按钮操作
   */
  function handleAdd(id, parentId) {
    addOrUpdateRef.value.init(id, parentId);
  }
  /** 展开/折叠操作 */
  function toggleExpandAll() {
    refreshTable.value = false;
    isExpandAll.value = !isExpandAll.value;
    nextTick(() => {
      refreshTable.value = true;
    });
  }
  /** 删除按钮操作 */
  function handleDelete(id) {
    proxy.$modal
      .confirm('是否确认删除?')
      .then(function () {
        return deleteMenuApi(id);
      })
      .then(() => {
        getDataList();
        proxy.$modal.msgSuccess('删除成功');
      })
      .catch(() => {});
  }

  onMounted(() => {
    getDataList();
  });
</script>
