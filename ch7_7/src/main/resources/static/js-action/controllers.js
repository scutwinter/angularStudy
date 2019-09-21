actionApp.controller('View1Controller',['$rootScope','$scope','$http',
    function ($rootScope,$scope,$http) {
    var vm=$scope;
    vm.$on('$viewContentLoaded',function () {
        console.log('页面加载完成');
    });
    //新版本的AngularJs中取消了success和error，用promise规则 所以此处用then
    $scope.search=function () {
        personName=$scope.personName;
        $http.get('search',{
            params:{personName:personName}
        }).then(function (data) {
            vm.person=data.data;
            console.log(vm.person.address);
        },function (error) {
            alert(error.toString());
        });
    }
}]);

actionApp.controller('View2Controller',['$rootScope','$scope','$http',
    function ($rootScope,$scope,$http) {
    $scope.$on('$viewContentLoaded',function () {
        console.log('页面加载完成');
    });
}]);