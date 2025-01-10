import { createApp } from 'vue';
import App from './App.vue';
import '@/assets/styles/index.scss';
import Router from './router/index';
import ElementPlus from 'element-plus';
import zhCn from 'element-plus/es/locale/lang/zh-cn';
import 'element-plus/dist/index.css';
import store from './store';

import directive from './directive';

import 'virtual:svg-icons-register';
import SvgIcon from '@/components/SvgIcon/index.vue';
import elementIcons from '@/components/SvgIcon/svgicon';
import plugins from './plugins'; // plugins

import './permission';

import { parseTime, resetForm, addDateRange, handleTree } from '@/utils/xavier';

// 分页组件
import Pagination from '@/components/Pagination/index.vue';
// 自定义表格工具组件
import RightToolbar from '@/components/RightToolbar/index.vue';
// 图片预览组件
import ImagePreview from '@/components/ImagePreview/index.vue';
// 自定义树选择组件
import TreeSelect from '@/components/TreeSelect/index.vue';
// 自定义选择员工组件
import selectEmployee from '@/components/selectEmployee/index.vue';
// 自定义选择员工组件
import buttonSelectEmployee from '@/components/selectEmployee/button.vue';
// 员工卡片
import employeeCard from '@/components/employeeCard/index.vue';

const app = createApp(App);
app.config.productionTip = false;

// 全局方法挂载
app.config.globalProperties.parseTime = parseTime;
app.config.globalProperties.resetForm = resetForm;
app.config.globalProperties.handleTree = handleTree;
app.config.globalProperties.addDateRange = addDateRange;

// 全局组件挂载
app.component('Pagination', Pagination);
app.component('TreeSelect', TreeSelect);
app.component('ImagePreview', ImagePreview);
app.component('RightToolbar', RightToolbar);
app.component('selectEmployee', selectEmployee);
app.component('buttonSelectEmployee', buttonSelectEmployee);
app.component('employeeCard', employeeCard);

app.use(Router);
app.use(store);
app.use(plugins);
app.use(elementIcons);
app.component('svg-icon', SvgIcon);

directive(app);

app.use(ElementPlus, {
  locale: zhCn,
});
app.mount('#app');
