<template>
  <div class="app-container xavier-approval">
    <div class="approval-header">
      <div class="header-left">
        <el-input
          v-model="queryParams.keyword"
          clearable
          @keyup.enter="getDataList()"
          @clear="getDataList()"
          style="width: 240px"
        >
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

    <div v-if="dataList && dataList.length > 0" class="approval-list">
      <div v-for="(item, index) in dataList" :key="index" class="approval-item">
        <div class="title">
          <div>{{ item.groupName }}</div>
          <div>
            <el-tooltip effect="dark" content="编辑">
              <el-button @click="handleEditGroup(item.id)" circle icon="Edit" />
            </el-tooltip>

            <el-tooltip
              effect="dark"
              content="删除"
              v-if="!item.approvalList || item.approvalList.length === 0"
            >
              <el-button
                @click="handleDelete(item.id)"
                icon="Delete"
                circle
                type="danger"
              ></el-button>
            </el-tooltip>
          </div>
        </div>
        <div class="app-list">
          <div
            v-for="approval in item.approvalList"
            :key="approval.id"
            class="app-item"
          >
            <div class="item-left">
              <div class="xavier-approve-icon">
                <svg-icon :icon-class="approval.icon" />
              </div>
              <div class="item-content">
                <div class="name">
                  <span>{{ approval.approvalName }}</span>
                  <el-tag
                    v-if="approval.appStatus === 0"
                    type="info"
                    class="ml5"
                    >未发布</el-tag
                  >
                  <el-tag
                    v-else-if="approval.appStatus === 2"
                    type="danger"
                    class="ml5"
                    >已停用</el-tag
                  >
                </div>
                <div class="desc">{{ approval.remarks }}</div>
              </div>
            </div>
            <div class="item-center">
              <span v-if="approval.submitType === 'all'">全员可见</span>
            </div>
            <div class="item-right">
              <el-tooltip effect="dark" content="编辑">
                <el-button
                  @click="handleCreate(approval.id)"
                  circle
                  icon="Edit"
                />
              </el-tooltip>

              <el-tooltip
                v-if="approval.appStatus === 2"
                effect="dark"
                content="启用"
              >
                <el-button
                  @click="handleUpdateStatus(approval.id, 1)"
                  circle
                  icon="Select"
                  type="success"
                />
              </el-tooltip>

              <el-tooltip
                v-if="approval.appStatus === 1"
                effect="dark"
                content="停用"
              >
                <el-button
                  @click="handleUpdateStatus(approval.id, 2)"
                  circle
                  icon="CloseBold"
                  type="warning"
                />
              </el-tooltip>

              <el-tooltip effect="dark" content="删除">
                <el-button
                  @click="handleDeleteApp(approval.id)"
                  icon="Delete"
                  circle
                  type="danger"
                ></el-button>
              </el-tooltip>
            </div>
          </div>
        </div>
      </div>
    </div>

    <el-empty v-else />
    <!-- 添加或修改分组对话框 -->
    <edit-group ref="editGroupRef" @refreshDataList="getDataList" />
  </div>
</template>

<script setup>
import {
  queryApprovalGroupApi,
  deleteApprovalGroupApi
} from '@/api/system/approvalGroup'
import {
  deleteApprovalApi,
  updateStatusApprovalApi
} from '@/api/system/approval'
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
  let url = '/system/editApproval'
  if (id) {
    url += '?id=' + id
  }
  window.open(url, '_blank')
}

async function getDataList() {
  dataList.value = await queryApprovalGroupApi(queryParams.value)
}

/**
 * 删除按钮操作
 */
function handleDelete(id) {
  proxy.$modal
    .confirm('是否确认删除?')
    .then(function () {
      return deleteApprovalGroupApi(id)
    })
    .then(() => {
      getDataList()
      proxy.$modal.msgSuccess('删除成功')
    })
    .catch(() => {})
}

/**
 * 删除审批按钮操作
 */
function handleDeleteApp(id) {
  proxy.$modal
    .confirm('删除审批后，进行中的申请将无法继续，确定要删除吗?')
    .then(function () {
      return deleteApprovalApi(id)
    })
    .then(() => {
      getDataList()
      proxy.$modal.msgSuccess('删除成功')
    })
    .catch(() => {})
}

/**
 * 修改状态
 */
function handleUpdateStatus(id, appStatus) {
  let text = '启用'
  if (appStatus === 2) {
    text = '停用'
  }
  proxy.$modal
    .confirm(`确定${text}审批吗?`)
    .then(function () {
      return updateStatusApprovalApi({ id: id, appStatus: appStatus })
    })
    .then(() => {
      getDataList()
      proxy.$modal.msgSuccess(`${text}成功`)
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
            min-width: 300px;

            .item-content {
              .name {
              }
              .desc {
                margin-top: 5px;
                color: var(--el-color-info);
                font-size: 14px;
              }
            }
          }
          .item-center {
          }
          .item-right {
            min-width: 150px;
            text-align: right;
          }
        }
      }
    }
  }
}
</style>
