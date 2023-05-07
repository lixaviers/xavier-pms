<template>
  <div class="app-container xavier-approve-initiate">
    <el-row :gutter="20">
      <el-col v-if="props.type === 'self'" :span="6">
        <el-card>
          <template #header>
            <div class="title">全部申请</div>
          </template>
          <div
            class="approve-container"
            v-for="item in approvalList"
            :key="item.id"
          >
            <div class="approve-group">{{ item.groupName }}</div>
            <el-row :gutter="20">
              <el-col
                v-for="approval in item.approvalList"
                :key="approval.id"
                :span="8"
                class="approve-item"
                @click="handleSelect(null, approval.id)"
              >
                <div class="xavier-approve-icon">
                  <svg-icon :icon-class="approval.icon" />
                </div>
                <div class="approve-name">{{ approval.approvalName }}</div>
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
      <el-col :span="props.type === 'self' ? 18 : 24">
        <el-tabs @tab-change="handelChange">
          <el-tab-pane
            v-for="item in props.statusList"
            :key="item.value"
            :label="item.label"
            :name="item.value"
          ></el-tab-pane>
        </el-tabs>
        <el-table v-loading="loading" :data="dataList" border>
          <el-table-column label="编号" align="center" prop="id" width="120" />
          <el-table-column label="申请人" align="center" prop="nickName">
            <template #default="scope">
              <employee-card
                v-model="scope.row.userId"
                :label="scope.row.nickName"
            /></template>
          </el-table-column>
          <el-table-column label="申请时间" align="center" prop="createTime" />
          <el-table-column
            label="审批状态"
            align="center"
            prop="auditStatusDesc"
          />
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template #default="scope">
              <el-button
                link
                type="primary"
                icon="Edit"
                @click="handleSelect(scope.row.id)"
                >详情</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total > 0"
          :total="total"
          v-model:page="queryParams.pageNo"
          v-model:limit="queryParams.pageSize"
          @pagination="getDataList"
        />
      </el-col>
    </el-row>
    <approve-edit ref="approveEditRef" @refreshDataList="getDataList" />
  </div>
</template>
<script setup>
import approveEdit from './edit.vue'
import { queryAuditFormApi } from '@/api/modules/auditForm'
import { listApprovalGroupApi } from '@/api/modules/approvalGroup'

const { proxy } = getCurrentInstance()

const props = defineProps({
  type: {
    type: String,
    required: true
  },
  statusList: {
    type: Array,
    default: []
  }
})

const approvalList = ref([])
const approveEditRef = ref(null)
const loading = ref(false)
const dataList = ref([])
const total = ref(0)
const queryParams = ref({
  pageNo: 1,
  pageSize: 10
})

/**
 * 查询审批列表
 */
async function getApprovalList() {
  approvalList.value = await listApprovalGroupApi()
}

function handleSelect(id, approvalId) {
  approveEditRef.value.init(id, approvalId)
}

function handelChange(tabPaneName) {
  let auditStatus = tabPaneName
  if (tabPaneName == 0) {
    auditStatus = undefined
  }
  queryParams.value.auditStatus = auditStatus
  getDataList()
}

/**
 * 查询列表
 */
function getDataList() {
  loading.value = true
  queryParams.value.type = props.type
  queryAuditFormApi(queryParams.value)
    .then((response) => {
      dataList.value = response.records
      total.value = response.total
    })
    .finally(() => {
      loading.value = false
    })
}

onMounted(async () => {
  await getApprovalList()
  getDataList()
})
</script>

<style lang="scss" scoped>
.xavier-approve-initiate {
  .title {
    font-weight: 700;
    font-size: 18px;
  }
  .approve-container {
    .approve-group {
      font-weight: 600;
      padding: 5px 0 10px 0;
    }
    &:first-child {
      padding-top: 0;
    }
    .approve-item {
      padding: 12px 0 5px 0;
      text-align: center;
      cursor: pointer;

      &:hover {
        background: var(--el-card-border-color);
        color: var(--el-color-primary);
      }

      .xavier-approve-icon {
        margin: 0 auto;
      }

      .approve-name {
      }
    }
  }
}
</style>
