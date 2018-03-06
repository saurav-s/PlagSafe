var app=angular.module('PlagsafeApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
        templateUrl: 'views/login.html'
        })
        .when('/dashboard', {
            resolve:{
                "check": function($location, $rootScope) {
                    if(!$rootscope.loggedIn) {
                        $location.path('/');
                    } else {
                        templateUrl: 'dashboard.html'
                    }
                }
            }
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller('LoginController', function($scope) {
    $scope.submit = function () {
        var username = $scope.username;
        var password = $scope.password;

        if(username == 'admin' && password == 'admin') {
            $rootScope.loggedIn = true;
            $location.path('/dashboard');
        }
    };
});