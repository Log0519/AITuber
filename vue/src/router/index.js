import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Model from "../views/Model.vue";
import Mocap from "../views/Mocap.vue";
import ModelScene from "../views/ModelScene.vue";
import MocapTest from "../views/MocapTest.vue";



const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/login',
    children:[
      {
        path: '/mocap',
        name: 'mocap',
        component: Mocap,
      },
      {
        path: '/modelscene',
        name: 'modelscene',
        component: ModelScene,
      },
      {
        path: '/model',
        name: 'Model',
        component: Model,
      },
      {
        path: '/mocapTest',
        name: 'MocapTest',
        component: MocapTest,
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
