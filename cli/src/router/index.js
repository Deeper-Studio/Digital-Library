import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../components/Home';
import Upload from '../components/Paper_upload/Upload';
import Review from '../components/Paper_review/Review';
import Search from "../components/Paper_search/Search";
import Main from '../views/Main';
import Test from '../views/test';

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    component: () => import('@/views/Login'),
    hidden: true
  },
  {

    path: '/',
    name: 'Home',
    component: Home,
    children: [
      {
        path:'/main',
        name: 'Main',
        component: Main
      },
      {
        path: '/Upload',
        component: Upload
      },
      {
        path: '/Search',
        component: Search
      },
      {
        path: '/mana',
        component: Review,
        children: [
          {
            path: '/Upload',
            component: Upload
          }
        ]
      },
    ]
  },
  // {
  //   path: '/Upload',
  //   component: Upload
  // },
  // {
  //   path: '/Search',
  //   component: Search
  // },
  // {
  //   path: '/mana',
  //   component: Review,
  //   children: [
  //     {
  //       path: '/Upload',
  //       component: Upload
  //     }
  //   ]
  // },
  // {
  //   path: '/main',
  //   component: Main
  // },
  {
    path: '/:catchAll(.*)',
    name: '404',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/404.vue')
  },
]

const router = new VueRouter({
  routes
})

export default router
