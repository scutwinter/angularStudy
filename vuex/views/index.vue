<template>
    <div>
        <h1>首页</h1>
        {{count}}
        <button @click="handleIncrement">+1</button>
        <button @click="handleDecrease">-1</button>
        <button @click="handleIncrementmore">+5</button>
        <button @click="handleActionIncrement">action+1</button>
        <div>{{list}}</div>
        <div>{{listCount}}</div>
        <router-link to="/about">跳转到</router-link>
    </div>
</template>

<script>
    export default {
        computed:{
            count(){
                return this.$store.state.count;
            },
            list(){
                return this.$store.getters.filteredList;
            },
            listCount(){
                return this.$store.getters.listCount;
            }
        },
        methods:{
            handleIncrement:function () {
                this.$store.commit('increment');
            },
            handleDecrease:function () {
                this.$store.commit('decrease');

            },
            handleIncrementmore:function () {
                this.$store.commit('increment',5);
            },
            handleActionIncrement:function () {
                this.$store.dispatch('increment');
            }
        }

    }
</script>

<style scoped>

</style>
