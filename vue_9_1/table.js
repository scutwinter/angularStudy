Vue.component('vTable',{
    props:{
        columns:{
            type:Array,
            default:function () {
                return [];
            }
        },
        data: {
            type: Array,
            default: function () {
                return [];
            }
        }
    },
    data: function () {
        return {
            currentColumns:[],
            currentData:[]
        }

    },
    methods:{
        makeColumns:function () {
            this.currentColumns = this.columns.map(function (col,index) {
                col._sortType='normal';
                col._index = index;
                return col;

            })

        },
        makeData:function () {
            this.currentData = this.data.map(function (row,index) {
                row._index=index;
                return row;
            });

        },
        handleSortByAsc:function (index) {
            var key = this.currentColumns[index].key;
            this.currentColumns.forEach(function (col) {
                col._sortType='normal';
            });
            this.currentColumns[index]._sortType = 'asc';
            this.currentData.sort(function (a,b) {
                return a[key] > b[key] ? 1 : -1;
            })
        },
        handleSortByDesc:function (index) {
            var key = this.currentColumns[index].key;
            this.currentColumns.forEach(function (col) {
                col._sortType='normal';
            });
            this.currentColumns[index]._sortType = 'desc';
            this.currentData.sort(function (a,b) {
                return a[key] < b[key] ? 1 : -1;
            })
        }
    },
    mounted(){
        this.makeColumns();
        this.makeData();
    },
    render:function (h) {
        var _this=this;
        var ths = [];
        var trs = [];
        var cols = [];

        this.currentColumns.forEach(function (col,index) {
            cols.push(h('col',{
                attrs:{
                    width:col.width
                }
            }));
            if(col.sortable){
                ths.push(h('th',[
                    h('span',col.title),
                    //升序
                    h('a',{
                        class:{
                            on: col._sortType==='asc'
                        },
                        on:{
                            click: function () {
                                _this.handleSortByAsc(index)
                            }
                        }
                    },'↑'),
                    //降序
                    h('a',{
                        class:{
                            on: col._sortType==='desc'
                        },
                        on:{
                            click: function () {
                                _this.handleSortByDesc(index)
                            }
                        }
                    },'↓')
                ]));
            }else{
                ths.push(h('th',col.title));
            }
        });

        this.currentData.forEach(function (row) {
            var tds=[];
            _this.currentColumns.forEach(function (cell) {
                tds.push(h('td',row[cell.key]));
            });
            trs.push(h('tr',tds));
        });

        return h('table',[
            h('colgroup',{
                attrs:{
                    span:this.currentColumns.length
                }
            },cols),
            h('thead',[
                h('tr',ths)
            ]),
            h('tbody',trs)
        ])

    },
    watch:{
        data:function() {
            this.makeData();
            var sortedColumn = this.currentColumns.filter(function (col) {
                return col._sortType!=='normal';
            });
            if(sortedColumn.length>0){
                if(sortedColumn[0]._sortType ==='asc'){
                    this.handleSortByAsc(sortedColumn[0]._index);
                }else{
                    this.handleSortByDesc(sortedColumn[0]._index);
                }
            }

        }

    }
});