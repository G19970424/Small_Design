import { createApp } from 'vue'
import App from './App.vue'
//import 'element-plus/lib/theme-chalk/index.css'
import router from "./router"
import 'animate.css'
let app = createApp(App)
import eleP from'./assets/js/elementPlus'
eleP(app)
app.use(router).mount('#app' )
