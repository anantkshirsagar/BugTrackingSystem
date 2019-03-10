var app = angular.module('btsApp', []);
app.controller('developerHomeCtr', function($scope, $http, $location) {

	var config = 'contenttype';
	$scope.type = 'DEVELOPER_HOME';

	$scope.assignedProjectList = [];
	var urlData = $location.absUrl();
	var developerId = urlData.split("=")[1];

	$scope.fetchAssignedProject = function() {
		$scope.url = "EmployeeServlet";

		$scope.developerEntity = {
			id : developerId
		};

		$scope.typeWrapper = {
			developerEntity : $scope.developerEntity,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.assignedProjectList = response.data;
				}, function(response) {

				});
	}

	$scope.developer = {};
	$scope.fetchDeveloperById = function() {
		$scope.url = "EmployeeServlet";
		$scope.type = 'DEV_HOME_FETCH_DEVELOPER';
		
		$scope.developer = {
			id: developerId
		};
		
		$scope.typeWrapper = {
			developerEntity : $scope.developer,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.developer = response.data;
				}, function(response) {

				});
	}

	$scope.waitForServiceLoad = function() {
		$scope.fetchAssignedProject();
		$scope.fetchDeveloperById();
	}

	$scope.waitForServiceLoad();
	
	$scope.gotoHomePage = function(){
		location.href = "developer-home.html?developerId="+developerId;
	}

	$scope.gotoViewBugPage = function(){
		location.href = "developer-project-list.html?developerId="+developerId;
	}
	
	$scope.gotoApplicationPage = function(){
		location.href = "developer-add-application.html?developerId="+developerId;
	}
});