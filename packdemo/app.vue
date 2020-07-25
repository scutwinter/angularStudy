<template>
    <div>
        <p>Hello {{name}}</p>
        <v-title title="Vue 组件化"></v-title>
        <v-button @click="handleClick">点击按钮</v-button>
        <p>
            <img src="./images/image.png" style="width: 200px;">
        </p>
    </div>
</template>
<script>
    //导入组件
    import vTitle from './title.vue';
    import vButton from './button.vue';

    export default {
        //对象字面量缩写，因为对象的key和value名称一致
        components:{
            vTitle,
            vButton
        },
        methods:{
            handleClick(e) {
                console.log(e);
            }
        },
        data: function () {
            return{
                name:'Vue.js'
            }
        }
        // data(){
        //     return {
        //         name:'Vue.js'
        //     }
        // }
    }
</script>
<style scoped>
    div{
        color: #ff6666;
        font-size: 24px;
    }
</style>
