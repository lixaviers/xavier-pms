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
        <el-button
          v-if="dataList && dataList.length > 0"
          @click="handleCreate()"
          icon="Plus"
          type="primary"
          >新建审批</el-button
        >
      </div>
    </div>

    <div class="approval-list">
      <div v-for="(item, index) in dataList" :key="index" class="approval-item">
        <div class="title">
          <div>{{ item.groupName }}</div>
          <div>
            <el-button @click="handleEditGroup(item.id)" circle icon="Edit" />

            <el-popconfirm
              v-if="!item.applicationList || item.applicationList.length === 0"
              @confirm="handleDelete(item.id)"
              title="是否确认删除"
            >
              <template #reference>
                <el-button icon="Delete" circle></el-button>
              </template>
            </el-popconfirm>
          </div>
        </div>
        <div class="app-list">
          <div
            v-for="application in item.applicationList"
            :key="application.id"
            class="app-item"
          >
            <div class="item-left">
              <div class="xavier-approve-icon">
                <svg-icon :icon-class="application.icon" />
              </div>
              <div class="item-content">
                <div class="name">{{ application.appName }}</div>
                <div class="desc">{{ application.remarks }}</div>
              </div>
            </div>
            <div class="item-center">
              <span v-if="application.submitType === 'all'">全员可见</span>
            </div>
            <div class="item-right">
              <el-button
                @click="handleCreate(application.id)"
                circle
                icon="Edit"
              />

              <el-popconfirm
                @confirm="handleDeleteApp(application.id)"
                title="是否确认删除"
              >
                <template #reference>
                  <el-button icon="Delete" circle></el-button>
                </template>
              </el-popconfirm>
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
import { deleteApplicationApi } from '@/api/system/application'
import editGroup from './editGroup.vue'
const router = useRouter()
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

function handleCreate(id) {
  router.push({ path: '/system/editApproval', query: { id: id } })
}

async function getDataList() {
  dataList.value = await queryApplicationGroupApi(queryParams.value)
}

/**
 * 删除按钮操作
 */
function handleDelete(id) {
  deleteApplicationGroupApi(id).then(() => {
    proxy.$modal.msgSuccess('删除成功')
    getDataList()
  })
}

/**
 * 删除应用按钮操作
 */
function handleDeleteApp(id) {
  deleteApplicationApi(id).then(() => {
    proxy.$modal.msgSuccess('删除成功')
    getDataList()
  })
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
            min-width: 300px;

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
