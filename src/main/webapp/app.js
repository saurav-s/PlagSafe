var app = angular.module('PlagsafeApp', [ 'ngRoute', 'ngFileUpload' ]);

app.config(function($routeProvider,$windowProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/login.html'
	}).when('/registration', {
		templateUrl : 'views/registration.html',
	}).when('/upload', {
		resolve : {
			"check" : function($location, $rootScope) {
				if (!$rootScope.loggedIn) {
					var $window = $windowProvider.$get();
					var currentUser = $window.localStorage.getItem("currentUser");
					if(!currentUser){
						$location.path('/');
					}
				}
			}
		},
		templateUrl : 'views/upload.html'
	}).when('/services', {
		templateUrl : 'views/system_stats.html'
	}).when('/similarity', {
	    templateUrl : 'views/similarity.html'
    });
});

app.controller(
				'LoginController',
				function($scope, $location, $rootScope, LoginService, $http, $window) {
					
					$scope.registerUser = function() {
						$location.path('/registration');
					}
					
					$scope.submit = function() {
						var username = $scope.username;
						var password = $scope.password;
						$rootScope.loggedIn = false;

						LoginService
								.findUser(username, password)
								.then(
										function(value) {
											var user = value.data;
											if (user != undefined
													&& user.email !== undefined) {
												$rootScope.loggedIn = true;
												$rootScope.userName = username
												$window.localStorage.setItem("currentUser", username);
												$location.path('/upload');
											} else {
												$('#alert_placeholder')
														.html(
																'<div class="alert alert-danger alert-dismissible fade show">'
																		+ '<a class="close" data-dismiss="alert">×</a><span>'
																		+ '<strong>Invalid credentials</strong>'
																		+ '</span></div>')
											}
										});
					};

				});

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					if (element[0].files.length > 1) {
						modelSetter(scope, element[0].files);
					} else {
						modelSetter(scope, element[0].files[0]);
					}
				});
			});
		}
	};
} ]);

app.controller('getFilesController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.doGetFiles = function($user) {
				var url = "/api/getallfiles";
				$http.get(url).then(function(response) {
					$scope.lstFiles = response.data;
				}, function(response) {

				});
			};
		} ]);


app.controller('RegistrationController', [ '$scope', '$http','$rootScope','$timeout','$location',
	function($scope, $http, $rootScope, $timeout, $location) {
		$scope.showSucccessMessage = false;
			$scope.doRegister = function($user) {
				$http({
					method : 'POST',
					url : '/user/registration',
					params : {
						email : $user.email,
						password : $user.password,
						confirmPassword : $user.confirmPassword,
						firstName : $user.firstName,
						lastName : $user.lastName,
						confirmEmail : $user.confirmEmail
					},
					headers : 'Accept:application/json'
				}).then(function(response) {
					$scope.showSucccessMessage = true;
					$timeout();
					$location.path('/');
				});
		};
		
		$timeout(function() {
		}, 5000);
	} ]);

app.controller('UploadFileController', [
		'$scope',
		'$http',
		'Upload',
		'$timeout',
		'$location',
		'$window',
		'$rootScope',
		function($scope, $http, Upload, $timeout, $location, $window,$rootScope) {

			$(document).ready(function(){
			    $('[data-toggle="tooltip"]').tooltip();   
			});
			
			$('#f1').on('change', function(evt) {
				var file1 = evt.target.files[0];
				var file2 = $("#f2")[0].files.length;
				if (file1 && file2) {
					$scope.disableTwinUpload = false;
				} else {
					$scope.disableTwinUpload = true;
				}
			});

			$('#f2').on('change', function(evt) {
				var file2 = evt.target.files[0];
				var file1 = $("#f1")[0].files.length;
				if (file1 && file2) {
					$scope.disableTwinUpload = false;
				} else {
					$scope.disableTwinUpload = true;
				}
			});

			$('#dir1').on('change', function(evt) {
				var file = evt.target.files[0];
				if (file) {
					$scope.disableUpload = false
				} else {
					$scope.disableUpload = true
				}
			});

			//Init default states
			$scope.strategy = "ALL";
			$scope.disableTwinUpload = true;
			$scope.disableUpload = true;

			//upload function for 2 submissions
			$scope.uploadFile = function($fileList1, $fileList2, $strategy) {
				$scope.disableTwinUpload = true;
				$scope.twinUploadProgress = 0;
				$scope.showTwinUploadProgress = true;

                var $pathsList1 = [];
                for (var i = 0; i < $fileList1.length; i++) {
                    var file = $fileList1[i];
                    $pathsList1.push(file.webkitRelativePath);
                }

                var $pathsList2 = [];
                for (i = 0; i < $fileList2.length; i++) {
                    file = $fileList2[i];
                    $pathsList2.push(file.webkitRelativePath);
                }

				Upload.upload({
					url : '/api/uploadfile',
					data : {
						uploadFile1 : $fileList1,
                        submission1Paths: $pathsList1,
						uploadFile2 : $fileList2,
                        submission2Paths: $pathsList2,
						strategy : $scope.strategy
					},
					arrayKey : ''
				}).success(function(data) {
					$scope.reports = data;
					$scope.disableTwinUpload = false;
					$scope.showTwinUploadProgress = false;
					$('#uploadCollapse1').collapse('hide');
					$('#resultCollapse').collapse('show');
				}).then(
						function(response) {
							$scope.disableTwinUpload = false;
							$timeout(function() {
								$scope.result = response.data;
							});
						},
						function(response) {
							if (response.status > 0) {
								$scope.errorMsg = response.status + ': '
										+ response.data;
							}
						},
						function(evt) {
							$scope.twinUploadProgress = Math.min(100,
									parseInt(100.0 * evt.loaded / evt.total));
						});
			};

			$scope.fetchSimilarities = function($fileOneName, $fileTwoName) {
				console.log($fileOneName)
                console.log($fileTwoName)
                var url = '/match/snippet?firstFile=' + $fileOneName + '&secondFile=' + $fileTwoName

                $http.get(url).then(function(response) {
                    $rootScope.simData = response.data;
                    $window.location = '#!/similarity';
                }, function(response){

                });
			}

			//upload function for class submission
			$scope.uploadClassSubmission = function($fileList, $strategy) {
				$scope.disableUpload = true;
				$scope.classUploadProgress = 0;
				$scope.showClassUploadProgress = true;
				var $pathsList = [];
				for (var i = 0; i < $fileList.length; i++) {
					var file = $fileList[i];
					$pathsList.push(file.webkitRelativePath);
				}

				Upload.upload({
					url : '/api/class/submissions',
					data : {
						submissionFiles : $fileList,
						relativePaths : $pathsList,
						strategy : $scope.strategy
					},
					arrayKey : ''
				}).success(function(data) {
					$scope.reports = data;
					$scope.disableUpload = false;
					$scope.showClassUploadProgress = false;
					$('#uploadCollapse2').collapse('hide');
					$('#resultCollapse').collapse('show');

				}).then(
						function(response) {
							$scope.disableUpload = false;
							$timeout(function() {
								$scope.result = response.data;
							});
						},
						function(response) {
							if (response.status > 0) {
								$scope.errorMsg = response.status + ' : '
										+ response.data;
							}
						},
						function(evt) {
							$scope.classUploadProgress = Math.min(100,
									parseInt(100.0 * evt.loaded / evt.total));
						});
			};

		} ]);

app.controller('SystemStatsController', [ '$scope', '$http','$rootScope',
		function($scope, $http, $rootScope) {
			$scope.get_system_stats = function() {
				var url = "/api/system/usage";
				$http.get(url).then(function(response) {
					$rootScope.stats = response.data;
				}, function(response) {

				});
			};
		} ]);


app.controller('LogoutController', [ '$location', '$scope', '$window',
		function($location, $scope, $window) {

			$scope.logout = function() {
				$window.localStorage.clear();
				$location.path('/');
			};
		} ]);


	