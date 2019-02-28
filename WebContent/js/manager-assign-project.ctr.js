var app = angular.module('btsApp', []);
app.controller('managerAssignProjectCtr', function($scope, $http) {
	$scope.url = "ManagerAssignProjectServlet";
	var config = 'contenttype';
	$scope.url = "ManagerAssignProjectServlet";

	$scope.manager = {
		name : 'Anant'
	}

	$scope.documentEntity = {
		callType : 'RETRIEVE'
	}

	$scope.fetchEmployeeDetails = function() {
		$http.post($scope.url, $scope.documentEntity, config).then(
				function(response) {

				}, function(response) {
				});
	}
	
	$scope.waitForServiceLoad = function() {
		$scope.fetchEmployeeDetails();
	}

	$scope.waitForServiceLoad();

	$scope.addProject = function() {
		$http.post($scope.url, $scope.documentEntity, config).then(
				function(response) {
					console.log(response.data);

				}, function(response) {

				});
	}

});