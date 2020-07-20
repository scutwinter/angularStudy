Vue.component('child-component',{
    template:'\
    <div class="container">\
    <slot></slot>\
    <br>\
    <button @click="handleNext" v-show="step<2" :disabled="bDisabled">下一步</button>\
    <button @click="handlePre" v-show="step>0">上一步</button>\
    <button>重置</button>\
    </div>',
    data:function(){
        return{
            myStep:this.step,
            myPicked:this.picked,
            myChecked:this.checked,
            bDisabled:true
        }
    },
    props:['step','picked','checked'],
    methods:{
        handleNext:function () {
            this.myStep++;
            console.log(this.myStep);
            this.$emit('clicknext',this.myStep);
            this.bDisabled=true;

        },
        handlePre:function () {
            this.myStep--;
            this.$emit('clickpre',this.myStep);
            this.bDisabled=true;

        }

    },
    watch:{
        picked:function () {
            console.log(this.picked);
            if(this.step==0 && this.picked=='')
                this.bDisabled=true;
            else
                this.bDisabled=false;
            console.log(this.bDisabled);
        },
        checked:function () {
            if(this.step==1 && this.checked.length<2)
                this.bDisabled=true;
            else
                this.bDisabled=false;

        }
    }


});
var app=new Vue({
    el:'#app',
    data:{
        picked :'',
        checked : [],
        text : '',
        step :0
    },
    methods: {
        getPage:function (step) {
            console.log(this.myStep);
            this.step=step;

        }

    }


})
