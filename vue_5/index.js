

var app = new Vue({
    el: '#app',
    data: {
        list: [
            {
                category:'电子产品',
                product:[
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
                        id:3,
                        name:'MacBook Pro',
                        price:21488,
                        count:1
                    }
                ]
            },
            {
                category:'生活用品',
                product:[
                    {
                        id:4,
                        name:'小米电视机',
                        price:4588,
                        count:1
                    },
                    {
                        id:5,
                        name:'格力空调',
                        price:6988,
                        count:1
                    },
                    {
                        id:6,
                        name:'西门子冰箱',
                        price:9880,
                        count:1
                    }
                ]
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

        handleReduce: function (tableindex,index) {
            if(this.list[tableindex].product[index].count===1) return;
            this.list[tableindex].product[index].count--;
        },
        handleAdd: function (tableindex,index) {
            this.list[tableindex].product[index].count++;
        },
        handleRemove: function (tableindex,index) {
            this.list[tableindex].product.splice(index,1)
        },
        handleSelect:function (tableindex,index,e) {

            function getRemoveIndex(tableindex,index) {
                for(var i=0;i<app.listSelect.length;i++)
                {
                    if(app.listSelect[i].id==app.list[tableindex].product[index].id)
                        return i;
                }
                return 0;

            }
            if(e.target.checked==true)
                this.listSelect.push(this.list[tableindex].product[index]);
            else
            {
                this.listSelect.splice(getRemoveIndex(tableindex,index));
            }

        }


    }

});

