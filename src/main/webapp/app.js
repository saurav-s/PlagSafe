var app = angular.module('PlagsafeApp', [ 'ngRoute', 'ngFileUpload' ]);

app.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/login.html'
	}).when('/upload', {
		resolve : {
			"check" : function($location, $rootScope) {
				if (!$rootScope.loggedIn) {
					$location.path('/');
				}
			}
		},
		templateUrl : 'views/upload.html'
	}).when('/system', {
		templateUrl : 'views/system_stats.html'
	});
});

app
		.controller(
				'LoginController',
				function($scope, $location, $rootScope, LoginService, $http) {
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
													&& user.userName !== undefined) {
												$rootScope.loggedIn = true;
												$rootScope.userName = username
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
			$scope.doGetFiles = function() {
				var url = "/api/getallfiles";
				$http.get(url).then(function(response) {
					$scope.lstFiles = response.data;
				}, function(response) {

				});
			};
		} ]);

app.controller('UploadFileController', [
		'$scope',
		'$http',
		'Upload',
		'$timeout',
		'$location',
		function($scope, $http, Upload, $timeout, $location) {

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
				Upload.upload({
					url : '/api/uploadfile',
					data : {
						uploadfile1 : $fileList1,
						uploadfile2 : $fileList2,

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

app.controller('systemStatisticsController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.get_system_stats = function() {
				var url = "/api/system/usage";
				$http.get(url).then(function(response) {
					$rootScope.stats = response.data;
				}, function(response) {

				});
			};
		} ]);
