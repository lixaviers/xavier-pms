<template>
  <div class="app-container xavier-approval">
    <div class="approval-header">
      <div class="header-left">
        <el-input clearable @keyup.enter="getDataList()" style="width: 240px">
          <template #append>
            <el-button @click="getDataList()" icon="Search" />
          </template>
        </el-input>
      </div>
      <div class="header-right">
        <el-button @click="handleEditGroup()">新建分组</el-button>
        <el-button icon="Plus" type="primary">新建审批</el-button>
      </div>
    </div>

    <div class="approval-list">
      <div v-for="(item, index) in dataList" :key="index" class="approval-item">
        <div class="title">
          <div>{{ item.groupName }}</div>
          <div>
            <el-button @click="handleEditGroup(item.id)" circle icon="Edit" />
            <el-button @click="handleDelete(item.id)" circle icon="Delete" />
          </div>
        </div>
        <div class="app-list">
          <div v-for="i in 5" :key="i" class="app-item">
            <div class="item-left">
              <div class="item-icon">
                <svg-icon icon-class="user" />
              </div>
              <div class="item-content">
                <div class="name">招聘</div>
                <div class="desc">111111</div>
              </div>
            </div>
            <div class="item-center">全员可见</div>
            <div class="item-right">
              <el-button circle icon="Delete" />
              <el-button circle icon="Delete" />
              <el-button circle icon="Delete" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 添加或修改分组对话框 -->
    <edit-group ref="editGroupRef" @refreshDataList="getDataList" />
  </div>
</template>

<script setup>
import {
  queryApplicationGroupApi,
  deleteApplicationGroupApi
} from '@/api/system/applicationGroup'
import editGroup from './editGroup.vue'
const { proxy } = getCurrentInstance()
const emits = defineEmits()

const data = reactive({
  queryParams: {}
})
const { queryParams } = toRefs(data)
const editGroupRef = ref()
const dataList = ref([])

/**
 * 编辑分组
 */
function handleEditGroup(id) {
  editGroupRef.value.init(id)
}

async function getDataList() {
  dataList.value = await queryApplicationGroupApi(queryParams.value)
}

/**
 * 删除按钮操作
 */
function handleDelete(id) {
  proxy.$modal
    .confirm('是否确认删除分组？')
    .then(function () {
      return deleteApplicationGroupApi(id)
    })
    .then(() => {
      proxy.$modal.msgSuccess('删除成功')
      getDataList()
    })
    .catch(() => {})
}

onMounted(async () => {
  await getDataList()
})
</script>

<style lang="scss" scoped>
.xavier-approval {
  .approval-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;
  }

  .approval-list {
    .approval-item {
      box-shadow: var(--el-box-shadow-light);
      margin-bottom: 16px;
      .title {
        display: flex;
        justify-content: space-between;
        height: 50px;
        line-height: 50px;
        background-color: rgba(244, 245, 246);
        padding: 0 20px;
      }

      .app-list {
        padding-bottom: 20px;
        .app-item {
          display: flex;
          justify-content: space-between;
          padding: 20px 20px 10px 20px;
          .item-left {
            display: flex;

            .item-icon {
              width: 45px;
              height: 45px;
              font-size: 25px;
              margin-right: 10px;
              border: 1px solid #000;
              border-radius: 50%;
              padding: 6px 5px 3px 5px;
              text-align: center;
            }

            .item-content {
              .name {
              }
              .desc {
                margin-top: 5px;
                color: var(--el-color-info);
              }
            }
          }
          .item-center {
          }
          .item-right {
          }
        }
      }
    }
  }
}
</style>
