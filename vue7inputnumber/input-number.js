function isValueNumber(value){
    return (/(^-?[0-9]+\.{1}\d+$)|(^-?[1-9][0-9]*$)|(^-?0{1}$)/).test(value + '');
}

Vue.component('input-number',{
    template:'\
    <div class="input-number">\
    <input \
    type="text" \
    :value="currentValue" \
    @keyup.down="handleDown" \
    @keyup.up="handleUp" \
    @change="handleChange"> \
    <button \
    @click="handleDown" \
    :disabled="currentValue<=min">-</button> \
    <button \
    @click="handleUp" \
    :disabled="currentValue>=max">+</button>\
    </div>',
    props:{
        max: {
            type:Number,
            default:Infinity
        },
        min:{
            type:Number,
            default:-Infinity
        },
        step:{
            type:Number,
            default:1
        },
        value:{
            type:Number,
            default:0
        }
    },
    data: function () {
        return{
            currentValue: this.value
        }

    },
    watch:{
        currentValue: function (val) {
            /**
             * $emit触发组件事件
             */
            this.$emit('input',val);
            this.$emit('on-change',val);
            console.log(val);
        },
        value:function (val) {
            this.updateValue(val);
        }
    },
    methods:{
        handleDown:function(){
            if(this.currentValue<=this.min) return;
            this.currentValue-=1*this.step;
        },
        handleUp:function(){
            if(this.currentValue>=this.max) return;
            this.currentValue+=1*this.step;
        },
        handleChange:function(event){
            var val=event.target.value.trim();
            var max=this.max;
            var min=this.min;

            if(isValueNumber(val)){
                val=Number(val);
                this.currentValue=val;
                if(val>max){
                    this.currentValue = max;
                }else if(val<min){
                    this.currentValue = min;
                }
            }else{
                event.target.value=this.currentValue;
            }
        },
        updateValue: function (val) {
            if(val>this.max) val=this.max;
            if(val<this.min) val=this.min;
            this.currentValue=val;
        }
    },
    //挂载到实例后调用
    mounted: function () {
        this.updateValue(this.value);
    }
    });
