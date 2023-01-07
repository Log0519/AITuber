import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Model from "../views/Model.vue";
import MocapNo from "../views/MocapNo.vue";
import ModelScene from "../views/ModelScene.vue";
import Mocap from "../views/Mocap.vue";
import Area from "../views/Area.vue";
import OtherArea from "../views/OtherArea.vue";
import AllSet from "../views/AllSet.vue";
import DatasView from "../views/DatasView.vue";
import MocapWindowDialog from "../components/dialog/MocapWindowDialog.vue";




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
        path: '/otherArea',
        name: 'OtherArea',
        component: OtherArea,
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
  {
    path: '/mocapWindow',
    name: 'MocapWindow',
    component: MocapWindowDialog,
  },
]



const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
routes
})

export default router
