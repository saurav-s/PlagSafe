var app=angular.module('PlagsafeApp', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
        templateUrl: 'views/login.html'
        })
        .when('/dashboard', {
            resolve:{
                "check": function($location, $rootScope) {
                    if(!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'views/dashboard.html'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller('LoginController', function($scope, $location, $rootScope, LoginService) {
    $scope.submit = function () {
        var username = $scope.username;
        var password = $scope.password;
        $rootScope.loggedIn = false;

        LoginService.findUser(username, password)
            .then(function(value) {
                var user = value.data;
                if(user != undefined && user.userName !== undefined) {
                    $rootScope.loggedIn = true;
                    $location.path('/dashboard');
                }
            });
    };
});

