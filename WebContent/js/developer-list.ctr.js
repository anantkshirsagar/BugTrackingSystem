var app = angular.module('btsApp', []);
app.controller('developerListCtr', function($scope, $http) {

	$scope.url = "EndpointServlet";
	var config = 'contenttype';
	$scope.obj = {
		fullName : 'Anant',
		email : 'anant@gmail.com',
		phoneNo : '8888',
		isApproved : false
	};
	$scope.type = 'DEVELOPER_LIST';
	$scope.typeWrapper = {
		developerEntity : $scope.obj,
		type : $scope.type
	};

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		response = response.data;
	}, function(response) {

	});
});