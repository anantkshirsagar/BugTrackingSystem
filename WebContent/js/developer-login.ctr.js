var app = angular.module('btsApp', []);
app
		.controller(
				'developerLoginCtr',
				function($scope, $http) {

					$scope.url = "RegistrationServlet";
					var config = 'contenttype';
					$scope.type = 'DEVELOPER_LOGIN';
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
												if(!$scope.response.isApproved){
													alert("You are not approved developer! When admin is approved then you can login.");
													return;
												}
												if ($scope.response.password == $scope.documentEntity.password) {
													location.href = 'developer-home.html?developerId='+$scope.response.id;
												} else {
													$scope.errorMsgFlag = true;
													$scope.errorMsg = "Username or Password Invalid";
												}
											}
										}, function(response) {

										});
					}
					
					$scope.newRegistration = function(){
						location.href = "developer-registration.html";
					}
				});