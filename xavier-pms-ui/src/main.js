import { createApp } from 'vue'

import ElementPlus from 'element-plus'
import locale from 'element-plus/lib/locale/lang/zh-cn' // 中文语言

import '@/assets/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'
import directive from './directive' // directive

// 注册指令
import plugins from './plugins' // plugins
import { download } from '@/utils/request'

// svg图标
import 'virtual:svg-icons-register'
import SvgIcon from '@/components/SvgIcon'
import elementIcons from '@/components/SvgIcon/svgicon'

import './permission' // permission control

import { parseTime, resetForm, addDateRange, handleTree } from '@/utils/xavier'

// 分页组件
import Pagination from '@/components/Pagination'
// 自定义表格工具组件
import RightToolbar from '@/components/RightToolbar'
// 图片预览组件
import ImagePreview from '@/components/ImagePreview'
// 自定义树选择组件
import TreeSelect from '@/components/TreeSelect'
// 自定义选择员工组件
import selectEmployee from '@/components/selectEmployee'
// 员工卡片
import employeeCard from '@/components/employeeCard'

const app = createApp(App)

// 全局方法挂载
app.config.globalProperties.download = download
app.config.globalProperties.parseTime = parseTime
app.config.globalProperties.resetForm = resetForm
app.config.globalProperties.handleTree = handleTree
app.config.globalProperties.addDateRange = addDateRange

// 全局组件挂载
app.component('Pagination', Pagination)
app.component('TreeSelect', TreeSelect)
app.component('ImagePreview', ImagePreview)
app.component('RightToolbar', RightToolbar)
app.component('selectEmployee', selectEmployee)
app.component('employeeCard', employeeCard)

app.use(router)
app.use(store)
app.use(plugins)
app.use(elementIcons)
app.component('svg-icon', SvgIcon)

directive(app)

// 使用element-plus 并且设置全局的大小
app.use(ElementPlus, {
  locale: locale,
  // 支持 large、default、small
  size: 'default'
})

app.mount('#app')
