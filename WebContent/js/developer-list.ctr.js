var app = angular.module('btsApp', []);
app.controller('developerListCtr', function($scope, $http) {

	$scope.url = "AdminServlet";
	var config = 'contenttype';
	$scope.type = 'DEVELOPER_LIST';
	$scope.list = [];
	$scope.typeWrapper = {
		type : $scope.type
	};

	$scope.employeeEntity = {
		fullName : '',
		email : '',
		phoneNo : null,
		isApproved : false
	};

	$scope.editEmployee = function(record) {
		$scope.employeeEntity = record;
	};

	$scope.saveChanges = function() {
		$scope.url = "RegistrationServlet";
		var config = 'contenttype';
		$scope.type = 'DEVELOPER_REGISTRATION';
		$scope.typeWrapper = {
			developerEntity : $scope.employeeEntity,
			type : $scope.type
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					if (response.data)
						location.href = 'developer-login.html';
				}, function(response) {

				});
	}

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		$scope.list = response.data;
	}, function(response) {

	});
});