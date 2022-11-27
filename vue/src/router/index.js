import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout.vue";
import HomeView from "../views/HomeView.vue"
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Model from "../views/Model.vue";



const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/login',
    children:[
      {
        path: '/home',
        name: 'HomeView',
        component: HomeView,
      },
      {
        path: '/model',
        name: 'Model',
        component: Model,
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
]



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
routes
})

export default router
