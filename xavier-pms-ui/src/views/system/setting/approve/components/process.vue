<template>
  <div class="app-container xavier-flow">
    <el-form ref="formRef">
      <div
        v-for="(item, index) in processList"
        :key="index"
        class="flow-container"
      >
        <div @click="handelForm(item, index)" :class="'flow-node ' + item.type">
          <div class="title">
            <span>{{ item.title }}</span>
            <div
              v-if="
                item.type !== 'start' &&
                item.type !== 'end' &&
                processList.length > 3
              "
            >
              <el-popconfirm
                @confirm="handleDelete(index)"
                title="是否确认删除"
              >
                <template #reference>
                  <el-button
                    @click.stop=""
                    icon="Delete"
                    circle
                    size="small"
                  ></el-button>
                </template>
              </el-popconfirm>
            </div>
          </div>
          <div class="node-content">
            <div class="node-detail">
              <div v-if="item.type === 'start'">
                <span>提交人：</span>
                <span v-if="item.submitType === 'all'">全员可提交</span>
              </div>
              <div v-else-if="item.type === 'approval'">
                <div v-if="item.itemList && item.itemList.length > 0">
                  <span>审批人：</span>
                  <span
                    v-for="(childItem, childIndex) in item.itemList"
                    :key="childIndex"
                  >
                    <span>{{ childItem.typeDesc }}</span>
                    {{ (childIndex != item.itemList.length - 1 && ',') || '' }}
                  </span>
                </div>
                <div v-else class="noset">请设置审批人</div>
              </div>

              <div v-else-if="item.type === 'handler'">
                <div v-if="item.itemList && item.itemList.length > 0">
                  <span>办理人：</span>
                  <span
                    v-for="(childItem, childIndex) in item.itemList"
                    :key="childIndex"
                  >
                    <span>{{ childItem.typeDesc }}</span>
                    {{ (childIndex != item.itemList.length - 1 && ',') || '' }}
                  </span>
                </div>
                <div v-else class="noset">请设置办理人</div>
              </div>

              <div
                v-if="
                  item.type === 'start' ||
                  item.type === 'end' ||
                  item.type === 'cc' ||
                  item.type === 'approval'
                "
              >
                <div v-if="item.ccList && item.ccList.length > 0">
                  <span>抄送人：</span>
                  <span
                    v-for="(cc, ccIndex) in item.ccList"
                    :key="'cc' + cc.type"
                  >
                    <span>{{ cc.typeDesc }}</span>
                    {{ (ccIndex != item.ccList.length - 1 && ',') || '' }}
                  </span>
                </div>
                <div v-else class="noset">可设置抄送人</div>
              </div>
            </div>
            <div>
              <el-icon><ArrowRight /></el-icon>
            </div>
          </div>
        </div>
        <div v-if="item.type !== 'end'">
          <div class="flow-line"></div>
          <div class="flow-arrow"></div>
          <div class="flow-add">
            <el-popover
              trigger="click"
              :visible="item.visible"
              placement="bottom"
              :width="200"
            >
              <el-row :gutter="20">
                <el-col
                  :span="8"
                  class="node-type"
                  @click="handelAdd('approval', index)"
                >
                  <div>
                    <el-icon size="30"><User /></el-icon>
                  </div>
                  <div>审批人</div>
                </el-col>
                <el-col
                  :span="8"
                  class="node-type"
                  @click="handelAdd('cc', index)"
                >
                  <div>
                    <el-icon size="30"><CopyDocument /></el-icon>
                  </div>
                  <div>抄送人</div>
                </el-col>
                <el-col
                  :span="8"
                  class="node-type"
                  @click="handelAdd('handler', index)"
                >
                  <div>
                    <el-icon size="30"><Edit /></el-icon>
                  </div>
                  <div>办理人</div>
                </el-col>
              </el-row>
              <template #reference>
                <el-button
                  icon="Plus"
                  circle
                  size="small"
                  @click="handelShowType(index)"
                ></el-button>
              </template>
            </el-popover>
          </div>
        </div>

        <!-- 编辑抽屉 -->
        <approvalForm
          :ref="(el) => setItemRef(el, index)"
          @formSubmit="formSubmit"
        />
      </div>
    </el-form>
  </div>
</template>

<script setup>
import approvalForm from './form.vue'
import { deepClone } from '@/utils'
import { getDeptListUtil } from '@/utils/xavier'
import { getUserByDepartmentIdApi } from '@/api/company/user'

const { proxy } = getCurrentInstance()
const emits = defineEmits()

const props = defineProps({
  modelValue: {
    type: Array,
    default: []
  }
})
const formRefs = ref([])
const processList = ref([])

// const processList = computed({
//   get() {
//     // console.log('processList', processList.value)
//     // console.log('props.modelValue', props.modelValue)
//     return deepClone(props.modelValue)
//   },
//   set(val) {
//     // console.log('processList1', processList.value)
//     // console.log('props.modelValue1', props.modelValue)
//     emits('update:modelValue', deepClone(val))
//   }
// })

watch(
  props,
  (val) => {
    console.log('modelValue1111', val.modelValue)
    if (!processList.value || processList.value.length === 0) {
      processList.value = deepClone(val.modelValue)
    }
  },
  { deep: true, immediate: true }
)

watch(
  processList,
  (val) => {
    console.log('processList', val)
    emits('update:modelValue', deepClone(val))
  },
  { deep: true }
)

// watch(
//   props,
//   (val) => {
//     console.log('11', val)
//     processList.value = deepClone(val.modelValue)
//   },
//   { deep: true, immediate: true }
// )

// watch(
//   processList,
//   (val) => {
//     emits('update:modelValue', deepClone(val)) // to update v-model
//   },
//   { deep: true, immediate: true }
// )

function setItemRef(el, index) {
  if (el) {
    formRefs.value[index] = el
  }
}

async function handelAdd(type, index) {
  processList.value[index].visible = false
  processList.value.splice(index + 1, 0, {
    type: type,
    title: getTitle(type)
  })
}

function handelShowType(index) {
  processList.value.forEach((item, idx) => {
    if (idx !== index) {
      item.visible = false
    } else {
      item.visible = true
    }
  })
}

function getTitle(type) {
  switch (type) {
    case 'approval':
      return '审批'
    case 'cc':
      return '抄送'
    case 'handler':
      return '办理'
  }
}

function handleDelete(index) {
  processList.value.splice(index, 1)
}

function handelForm(obj, index) {
  obj.index = index
  formRefs.value[index].init(obj)
}
function formSubmit(obj) {
  processList.value[obj.index] = obj
}
</script>

<style lang="scss" scoped>
.xavier-flow {
  .flow-container {
    width: 200px;
    position: relative;
    margin: 0 auto;
    .flow-node {
      cursor: pointer;
      border-radius: 5px;
      padding: 5px;

      .title {
        padding: 0 0 5px 10px;
        color: #fff;
        font-size: 14px;
        display: flex;
        align-items: center;
        justify-content: space-between;
      }

      .node-content {
        margin: 0 4px 4px;
        padding: 7px;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        background: #fff;
        .node-detail {
          .noset {
            color: #8a8d93;
          }
        }
      }
    }
    .flow-line {
      width: 1px;
      height: 60px;
      background: #c2c5cc;
      margin: 0 auto;
    }
    .flow-arrow {
      width: 10px;
      height: 10px;
      -webkit-transform: matrix(1, 0, 0, -1, 0, 0);
      transform: matrix(1, 0, 0, -1, 0, 0);
      border-bottom: 10px solid #c2c5cc;
      border-right: 5px solid transparent;
      border-left: 5px solid transparent;
      background-color: #fff;
      margin: 0 auto;
    }
    .flow-add {
      position: absolute;
      bottom: 25px;
      left: 88px;
    }
  }

  .flow-node.start,
  .flow-node.end {
    background: var(--el-color-info);
  }
  .flow-node.approval {
    background: var(--el-color-warning);
  }
  .flow-node.cc {
    background: var(--el-color-primary);
  }
  .flow-node.handler {
    background: var(--el-color-danger);
  }
}
.node-type {
  cursor: pointer;
  text-align: center;
  padding-top: 5px;
  padding-bottom: 5px;
}
.node-type:hover {
  background: #d0d3da;
}
</style>
