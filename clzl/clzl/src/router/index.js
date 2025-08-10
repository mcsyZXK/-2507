import { createRouter, createWebHistory } from 'vue-router'

//导入组件
import LoginVue from '@/views/login.vue'
import LayoutVue from '@/views/layout.vue'
import CarVue from '@/views/car/car.vue'
import OrderVue from '@/views/order/order.vue'
import AnnouncementVue from '@/views/announcement/announcement.vue'
import UserVue from '@/views/user/user.vue'
const routes = [
  {
    path: '/',
    component: LayoutVue,
    redirect:'/car',
    children: [
      {
        path: '/car', 
        component: CarVue
      },
      {
        path:'/announcement',
        component:AnnouncementVue
      },
      {
        path:'/order',
        component:OrderVue
      },
      {
        path: '/user',
        component: UserVue
      }
    ]
  },
  {
    path: '/login',
    component: LoginVue
  },
]


//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

//导出路由
export default router
