
import Vue from 'vue';
import App from './app.vue';
import VueRouter from 'vue-router';
Vue.use(VueRouter);
const Routers=[
    {
        path:'/index',
        meta:{
            title:'首页'
        },
        component:(resolve) => require(['./views/index.vue'],resolve)
    } ,
    {
        path:'/about',
        meta:{
            title:'关于'
        },
        component:(resolve) => require(['./views/about.vue'],resolve)
    },
    {
        path:'/user/:id',
        meta: {
            title: '个人主页'
        },
        component:(resolve) => require(['./views/user.vue'],resolve)
    },
    {
        path:'*',
        redirect:'/index'
    }
];
const RouterConfig={
  //使用html5的history路由模式
  mode:'history',
  routes:Routers
};
const router = new VueRouter(RouterConfig);
router.beforeEach((to,from,next)=>{
    window.document.title = to.meta.title;
    next();
})
// document.getElementById('app').innerHTML = 'Hello day.';
new Vue({
    el:'#app',
    router:router,
    render:h=>h(App)
});
