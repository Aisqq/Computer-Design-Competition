import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePageView from '../views/HomePage/HomePage.vue'
import EntranceView from '../views/Entrance/Entrance.vue'
import LoginView from '../views/Login/Login.vue'
import StaffView from '../views/staffManagement/Staff.vue'
import CarView from '@/views/CarManagement/Car.vue'
import MaterialView from '../views/MaterialManagement/Material.vue'
import DeviceView from '../views/DeviceManagement/Device.vue'
import BackView from '../views/Login/back.vue'
import MapView from '../views/Map/map.vue'
import SecurityView from '../views/Security/security.vue'
import TaskPageView from '../views/Task/TaskPage.vue'
import CalenderView from '../views/staffManagement/StaffView.vue'
import TasklightView from "@/views/Task/tasklight.vue";
import TestView from "@/views/test.vue"
import mapList from "@/views/Map/mapList.vue";
Vue.use(VueRouter)
//  entrance与login同级路由，基础路由配置
const routes = [
  {
    path:'/test',
    name:'test',
    component:TestView
  },
  {
    path:'/tasklight',
    name:'tasklight',
    component:TasklightView
  },

  {
    path: '/',
    name: 'entrance',
    component: EntranceView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path:'/back',
    name: 'back',
    component:BackView,
    children:[
      {
        path: 'homepage',
        name: 'homepage',
        component: HomePageView
      },
      {
        path:'staff',
        name:'staff',
        component: StaffView
      },
      {
        path:'mapList',
        name:'mapList',
        component: mapList
      },
      {
        path:'staCalender',
        name:'staCalender',
        component: CalenderView
      },
      {
        path:'car',
        name:'car',
        component: CarView
      },
      {
        path:'material',
        name:'material',
        component: MaterialView
      },
      {
        path:'device',
        name:'device',
        component: DeviceView
      },
      {
        path:'map',
        name:'map',
        component: MapView
      },
      {
        path:'security',
        name:'security',
        component: SecurityView
      },
      {
        path:'task',
        name:'task',
        component: TaskPageView
      }
    ]
  }



];

const router = new VueRouter({
  mode: 'history',
  //base: process.env.BASE_URL,
  routes
})

export default router
