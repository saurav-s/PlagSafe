var app=angular.module('PlagsafeApp', ['ngRoute', 'ngFileUpload']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
        templateUrl: 'views/login.html'
        })
        .when('/upload', {
            resolve:{
                "check": function($location, $rootScope) {
                    if(!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'views/upload.html'
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
                    $location.path('/upload');
                }
            });
    };
});

app.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
        	  	  scope.$apply(function(){
                  if (element[0].files.length > 1) {
                    modelSetter(scope, element[0].files);
                  }
                  else {
                    modelSetter(scope, element[0].files[0]);
                  }
                });
          });
       }
    };
}]); 


app.controller('getFilesController', ['$scope', '$http', function($scope, $http){
    $scope.doGetFiles = function(){
       var url = "/api/getallfiles";
       $http.get(url).then(function (response) {
			$scope.lstFiles = response.data;
		}, function (response) {
			
		});
    };
}]);


app.controller('UploadFileController', ['$scope', '$http', 'Upload', '$timeout', function($scope, $http, Upload, $timeout){

      $scope.strategy="ALL"
      $scope.uploadFile = function($fileList1, $fileList2, $strategy){
          Upload.upload({
                url: '/api/uploadfile',
                data: {
                        uploadfile1: $fileList1,
                        uploadfile2: $fileList2,

                        strategy: $scope.strategy
                },
                arrayKey: ''
              }).success(function (data) {

                  $scope.reports = data;

              }).then(function (response) {
                  $timeout(function () {
                      $scope.result = response.data;
                  });
              }, function (response) {
                  if (response.status > 0) {
                      $scope.errorMsg = response.status + ': ' + response.data;
                  }
              });
          };

      $scope.uploadClassSubmission = function ($fileList, $strategy) {
          console.log("Getting relative paths of all the files present");
          var pathsList = [];

          for (var i = 0; i < $fileList.length; i++) {
              console.log("file path");
              var file = $fileList[i];
              console.log(file.webkitRelativePath);
              pathsList.push(file.webkitRelativePath);
          }

          for(var i=0;i <$fileList.length;i++) {
              console.log("file path: " + $fileList[i].webkitRelativePath);
              console.log("file name: " + $fileList[i].webkito)
          }
      };
}]);

