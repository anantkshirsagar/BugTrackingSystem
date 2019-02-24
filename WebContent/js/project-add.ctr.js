var app = angular.module('btsApp', []);
app.controller('developerListCtr', function($scope, $http) {

	$scope.url = "EndpointServlet";
	var config = 'contenttype';
	$scope.type = 'PROJECT_ADD';
	$scope.obj = {
		
	};
	
	$scope.typeWrapper = {
		projectEntity : $scope.obj,
		type : $scope.type
	};

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		response = response.data;
	}, function(response) {

	});
});