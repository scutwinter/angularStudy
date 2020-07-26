
import Vue from 'vue';
import App from './app.vue';
import VueRouter from 'vue-router';
import Vuex from 'vuex'

Vue.use(VueRouter);
Vue.use(Vuex);
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
});
const store =new Vuex.Store({
    state:{
        count:0,
        list:[1,5,8,10,30,50]
    },
    getters:{
        filteredList:state => {
            return state.list.filter(item=>item<10);
        },
        listCount:(state,getters)=>{
            return getters.filteredList.length;
        }
    },
    mutations:{
        increment:function (state,n=1) {
            state.count +=n;
        },
        decrease:function (state) {
            state.count --;
        }
    },
    actions:{
        increment(context){
            context.commit('increment');
        }
    }

});
// document.getElementById('app').innerHTML = 'Hello day.';
new Vue({
    el:'#app',
    router:router,
    store:store,
    render:h=>h(App)
});
