var app = angular.module('btsApp', []);
app.controller('testerRegistrationCtr', function($scope, $http) {

	$scope.url = "RegistrationServlet";
	var config = 'contenttype';
	$scope.type = 'TESTER_REGISTRATION';
	$scope.documentEntity = {
		fullName : '',
		email : '',
		phoneNo : null,
		password : ''
	};

	$scope.addDeveloper = function() {
		$scope.typeWrapper = {
			testerEntity : $scope.documentEntity,
			type : $scope.type
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					if (response.data)
						location.href = 'tester-login.html';
				}, function(response) {

				});
	}
});