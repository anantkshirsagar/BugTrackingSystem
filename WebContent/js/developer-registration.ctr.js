var app = angular.module('btsApp', []);
app.controller('developerRegistrationCtr', function($scope, $http) {

	$scope.url = "RegistrationServlet";
	var config = 'contenttype';
	$scope.type = 'DEVELOPER_REGISTRATION';
	$scope.documentEntity = {
		fullName : '',
		email : '',
		phoneNo : null,
		password : ''
	};

	$scope.addDeveloper = function() {
		$scope.typeWrapper = {
			developerEntity : $scope.documentEntity,
			type : $scope.type
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					if (response.data)
						location.href = 'developer-login.html';
				}, function(response) {

				});
	}

});