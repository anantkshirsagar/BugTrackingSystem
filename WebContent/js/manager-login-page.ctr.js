var app = angular.module('btsApp', []);
app.controller('managerLoginCtr', function($scope, $http) {
	$scope.documentEntity = {
		email : '',
		password : '',
		type : $scope.type
	};

	$scope.managerEmail = "manager@gmail.com";
	$scope.managerPassword = "123";
	$scope.login = function() {
		if ($scope.documentEntity.email == $scope.managerEmail
				&& $scope.documentEntity.password == $scope.managerPassword) {
			location.href = 'manager-home.html';
		} else {
			$scope.errorMsgFlag = true;
			$scope.errorMsg = "Username or Password Invalid";
		}
	}
	
	$scope.gotoHomePage = function(){
		location.href = "index.html";
	}
});