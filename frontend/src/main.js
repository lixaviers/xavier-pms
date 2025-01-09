import { createApp } from 'vue';
import App from './App.vue';
import './assets/global.less';
import components from './components/global';
import Router from './router/index';
import ElementPlus from 'element-plus';
import zhCn from 'element-plus/es/locale/lang/zh-cn';
import 'element-plus/dist/index.css';

const app = createApp(App);
app.config.productionTip = false;

// components
for (const i in components) {
  app.component(i, components[i]);
}

app.use(Router);
app.use(ElementPlus, {
  locale: zhCn,
});
app.mount('#app');
