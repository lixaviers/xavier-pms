<template>
  <div>
    <el-dropdown>
      <el-badge :is-dot="props.count > 0">
        <el-icon size="22"><Bell /></el-icon>
      </el-badge>
      <template #dropdown>
        <div class="notification-container">
          <el-dropdown-menu>
            <div class="notification-list">
              <el-tabs v-model="activeName">
                <el-tab-pane label="通知" name="first">
                  <div
                    v-for="(item, index) in noList"
                    :key="index"
                    class="notification-item"
                  >
                    <div class="notification-item-title">
                      {{ item.content }}
                    </div>
                    <div class="item">{{ item.createTime }}</div>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </div>
          </el-dropdown-menu>
        </div>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup name="notification">
import { formatTime } from '@/utils/xavier'
const props = defineProps({
  count: {
    type: Number,
    default: 0
  },
  list: {
    type: Array,
    default: []
  }
})

const noList = computed(() => {
  if (!props.list || props.list.length === 0) {
    return []
  }
  props.list.forEach((item) => {
    item.createTime = formatTime(item.createTime)
  })
  return props.list
})

const activeName = ref('first')
</script>

<style lang="scss">
.notification-container {
  padding-bottom: 20px;
  .el-tabs__header {
    margin: 0;
  }
  .notification-list {
    width: 300px;
    padding: 0 15px;

    .notification-item {
      padding: 10px 0;
      border-bottom: 1px solid #e8eaec;
      cursor: pointer;
      -webkit-transition: background-color 0.2s ease-in-out;
      transition: background-color 0.2s ease-in-out;
      .notification-item-title {
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        color: #091e42;
        display: -webkit-box;
        line-height: 18px;
        max-height: 3em;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal;
        word-break: break-all;
      }
      .item {
        font-size: 12px;
        color: #808695;
      }
    }
  }
}
</style>
