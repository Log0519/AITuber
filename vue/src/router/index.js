import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Model from "../views/Model.vue";
import MocapNo from "../views/MocapNo.vue";
import ModelScene from "../views/ModelScene.vue";
import Mocap from "../views/Mocap.vue";
import Mocap1 from "../views/Mocap1.vue";
import Area from "../views/Area.vue";
import Area2 from "../views/Area2.vue";
import AllSet from "../views/AllSet.vue";
import DatasView from "../views/DatasView.vue";





const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:'/login',
    children:[
      {
        path: '/mocapNo',
        name: 'mocapNo',
        component: MocapNo,
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
        path: '/mocap',
        name: 'Mocap',
        component: Mocap,
      },
      {
        path: '/mocap1',
        name: 'Mocap1',
        component: Mocap1,
      },
      {
        path: '/area2',
        name: 'Area2',
        component: Area2,
      },
      {
        path: '/area',
        name: 'Area',
        component: Area,
      },
      {
        path: '/allSet',
        name: 'AllSet',
        component: AllSet,
      },
      {
        path: '/datasView',
        name: 'DatasView',
        component: DatasView,
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
