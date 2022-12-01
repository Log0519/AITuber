import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'element-plus/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import locale from 'element-plus/lib/locale/lang/zh-cn'
import './assets/css/main.css'
import ElementPlus from 'element-plus';
import vueBaberrage from "vue-baberrage";
const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus,{ locale,size:'small'})
app.use(vueBaberrage);
app.mount('#app')
