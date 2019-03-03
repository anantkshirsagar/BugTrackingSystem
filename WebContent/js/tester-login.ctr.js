var app = angular.module('btsApp', []);
app
		.controller(
				'testerLoginCtr',
				function($scope, $http) {

					$scope.url = "RegistrationServlet";
					var config = 'contenttype';
					$scope.type = 'TESTER_LOGIN';
					$scope.documentEntity = {
						email : '',
						type : $scope.type
					};
					$scope.login = function() {
						$http
								.post($scope.url, $scope.documentEntity, config)
								.then(
										function(response) {
											if (response.data) {
												$scope.response = response.data;
												if ($scope.response.password == $scope.documentEntity.password) {
													location.href = 'tester-home.html';
												} else {
													$scope.errorMsgFlag = true;
													$scope.errorMsg = "Username or Password Invalid";
												}
											}
										}, function(response) {

										});
					}
				});