

var app = new Vue({
    el: '#app',
    data: {
        list: [
            {
                id:1,
                name:'iphone 7',
                price:6188,
                count:1
            },
            {
                id:2,
                name:'iPad Pro',
                price:5888,
                count:1
            },
            {
                id:1,
                name:'MacBook Pro',
                price:21488,
                count:1
            }
        ],
        listSelect:[]

    },
    computed:{
        totalPrice:function () {
            var total = 0;
            for (var i=0;i<this.list.length;i++){
                var item = this.list[i];
                total+=item.price * item.count;
            }
            return total.toString().replace(/\B(?=(\d{3})+$)/g,',');

        },
        totalPriceSel:function () {
            var total = 0;
            for (var i=0;i<this.listSelect.length;i++){
                var item = this.listSelect[i];
                total+=item.price * item.count;
            }
            return total.toString().replace(/\B(?=(\d{3})+$)/g,',');

        }

    },
    methods:{

        handleReduce: function (index) {
            if(this.list[index].count===1) return;
            this.list[index].count--;
        },
        handleAdd: function (index) {
            this.list[index].count++;
        },
        handleRemove: function (index) {
            this.list.splice(index,1)
        },
        handleSelect:function (index,e) {
            function getRemoveIndex(index) {
                for(var i=0;i<app.listSelect.length;i++)
                {
                    if(app.listSelect[i].id==app.list[index].id)
                        return i;
                }
                return 0;

            }
            if(e.target.checked==true)
                this.listSelect.push(this.list[index]);
            else
            {
                this.listSelect.splice(getRemoveIndex(index));
            }

        }


    }

});

