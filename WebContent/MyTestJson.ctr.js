var app = angular.module('myJson', []);
app.controller('myJsonCtr', function($scope, $http) {
	$scope.studentObj = {
		id : 12,
		name : "Pranav Joshi",
		percentage : 80.99
	};

	$scope.url = "EndpointServlet";
	$scope.parameter = JSON.stringify($scope.studentObj);
//	console.log($scope.parameter);

	/*
	 * $http.get({ method : "GET", url : $scope.servletName
	 * }).then(function(response) { $scope.content = response.data;
	 * $scope.statuscode = response.status; $scope.statustext =
	 * response.statusText; });
	 */
	/*var config = 'contenttype';
	$http.post($scope.url, $scope.parameter, config).then(function(response) {

		// This function handles success

	}, function(response) {

		// this function handles error

	});*/
	
	
	$scope.jsonObj={"devnagariText":"MYTEXT"};
	$scope.restURL = "rest/testJersey/add";
	$scope.data = JSON.stringify($scope.studentObj);
	var config = 'contenttype';
	
	$http.get($scope.restURL, $scope.data, config).then(function(response) {
	}, function(response) {
	});
	
	
});