var app = angular.module('btsApp', []);
app.controller('developerListCtr', function($scope, $http) {

	$scope.url = "EndpointServlet";
	var config = 'contenttype';
	$scope.obj = {
		fullName : 'Anant',
		email : 'anant@gmail.com',
		phoneNo : '8888',
		isApproved : false,
		type : 'DEVELOPER_LIST'
	};
	$http.post($scope.url, $scope.obj, config).then(function(response) {
		response = response.data;
	}, function(response) {

	});
});