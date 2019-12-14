/**
 * 定义模块actionApp,并依赖路由模块ngRout
 */
var actionApp = angular.module('actionApp',['ngRoute']);

/**
 * 配置路由，并注入$routeProvider
 */
actionApp.config(['$locationProvider','$routeProvider',function ($locationProvider,$routeProvider) {
    /**
     *  AngularJS 1.6 has changed the default for hash-bang urls in the $location service
     *  So $locationProvider.hashPrefix('')
     */
    $locationProvider.hashPrefix('');
    $routeProvider.when('/oper',{ // /oper为路由名称
        controller: 'View1Controller',// controller 定义的室路由控制器名称
        templateUrl:'views/view1.html', // 定义视图的真正地址
    }).when('/directive',{
        controller: 'view2Controller',
        templateUrl:'views/view2.html',
    });
}]);