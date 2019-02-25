var app = angular.module('btsApp', []);
app.controller('adminLoginCtr', function($scope, $http) {
	$scope.documentEntity = {
		email : '',
		password : '',
		type : $scope.type
	};
	$scope.login = function() {
		if ($scope.documentEntity.email == 'admin@123'
				&& $scope.documentEntity.password == '123') {
			location.href = 'admin-home.html';
		} else {
			$scope.errorMsgFlag = true;
			$scope.errorMsg = "Username or Password Invalid";
		}
	}
});