<template>
  <div class="app-container xavier-edit-app">
    <el-tabs model-value="first">
      <el-tab-pane label="基础信息" name="first">
        <app-basic-form :dataForm="dataForm" :groupList="groupList" />
      </el-tab-pane>
      <el-tab-pane label="表单设计" name="second">
        <v-form-designer
          ref="vfDesignerRef"
          :designer-config="designerConfig"
        />
      </el-tab-pane>
      <el-tab-pane label="流程设计" name="third">
        <app-process v-model="dataForm.processList" />
      </el-tab-pane>
    </el-tabs>
    <div class="submit">
      <el-button @click="router.push('/system/approvalList')" auto-insert-space
        >返回</el-button
      >
      <el-button
        v-if="!dataForm.appStatus || dataForm.appStatus === 0"
        @click="handelSave"
        type="primary"
        plain
        auto-insert-space
        >保存</el-button
      >
      <el-button @click="handelRelease" type="primary" auto-insert-space
        >发布</el-button
      >
    </div>
  </div>
</template>

<script setup>
import appBasicForm from './components/basic.vue'
import appProcess from './components/process.vue'
import { addOrUpdateApprovalApi, getApprovalApi } from '@/api/modules/approval'
import { queryApprovalGroupApi } from '@/api/modules/approvalGroup'

const router = useRouter()
const route = useRoute()
const { proxy } = getCurrentInstance()
const emits = defineEmits()

const data = reactive({
  dataForm: {}
})
const { dataForm } = toRefs(data)
// 审批分组列表
const groupList = ref([])
const vfDesignerRef = ref(null)
const designerConfig = reactive({
  languageMenu: false, //是否显示语言切换菜单
  externalLink: false, //是否显示GitHub、文档等外部链接
  formTemplates: false, //是否显示表单模板
  eventCollapse: true, //是否显示组件事件属性折叠面板
  widgetNameReadonly: false, //禁止修改组件名称
  importJsonButton: false, //是否显示导入JSON按钮
  exportJsonButton: false, //是否显示导出JSON器按钮
  exportCodeButton: false, //是否显示导出代码按钮
  generateSFCButton: false, //是否显示生成SFC按钮
  clearDesignerButton: false

  //presetCssCode: '.abc { font-size: 16px; }',
})

function handelSave() {
  dataForm.value.form = JSON.stringify(vfDesignerRef.value.getFormJson())
  console.log(dataForm.value)
  addOrUpdateApprovalApi(dataForm.value).then(() => {
    proxy.$modal.msgSuccess(`保存成功`)
    proxy.$tab.closePage().then(() => {
      router.push('/modules/approvalList')
    })
  })
}

function handelRelease() {
  dataForm.value.appStatus = 1
  dataForm.value.form = JSON.stringify(vfDesignerRef.value.getFormJson())
  console.log(dataForm.value)
  addOrUpdateApprovalApi(dataForm.value).then(() => {
    proxy.$modal.msgSuccess(`发布成功`)
    proxy.$tab.closePage().then(() => {
      router.push('/system/approvalList')
    })
  })
}

async function getDataList() {
  groupList.value = await queryApprovalGroupApi({})
}

onMounted(async () => {
  await getDataList()
  const id = route.query.id
  if (id) {
    dataForm.value = await getApprovalApi(id)
    vfDesignerRef.value.setFormJson(dataForm.value.form)
  } else {
    dataForm.value.appStatus = 0
    dataForm.value.icon = 'user'
    dataForm.value.approvalName = '未命名审批'
    dataForm.value.approvalGroupId = groupList.value[0].id
    dataForm.value.processList = [
      {
        approvalType: 'start',
        title: '提交',
        submitType: 'all'
      },
      {
        approvalType: 'approval',
        title: '审批'
      },
      {
        approvalType: 'end',
        title: '结束'
      }
    ]
    vfDesignerRef.value.setFormJson({ widgetList: [] })
  }
})
</script>

<style lang="scss">
#app {
  .xavier-edit-app {
    .main-container {
      margin-left: 0px;

      .el-header.main-header {
        display: none;
      }
    }
    .submit {
      position: absolute;
      top: 15px;
      right: 20px;
    }
  }
}
</style>
