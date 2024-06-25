import { createRouter, createWebHashHistory } from 'vue-router'

const router = createRouter({
        history: createWebHashHistory(),
        routes:[
            //主页面
            {
                path: "/home",
                component:()=>import('../views/home.vue')
            },
            {
                path:"/login",
                component:()=>import('../views/login.vue')

            }
        ],
})

export default router