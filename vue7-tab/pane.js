Vue.component('pane',{
    name: 'pane',
    template:'\
    <div class="pane" v-show="show">\
        <slot></slot>\
    </div>',
    props:{
        name:{
            type: String,
        },
        label:{
            type: String,
            default: ''
        },
        closable:{
            type: Boolean,
            default: false
        }
    },
    data: function(){
        return{
            show:true
        }
    },
    methods:{
        updateNav:function(){
            this.$parent.updateNav();
        }
    },
    watch:{
        label:function(){
            this.updateNav();
        }
    },
    mounted: function(){
        this.updateNav();
    }
})
