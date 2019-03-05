var app = angular.module('btsApp', []);
app.controller('adminHomeCtr', function($scope, $http) {

	$scope.fetchLiveProject = function() {
		$scope.url = "AdminServlet";
		var config = 'contenttype';
		$scope.type = 'ADMIN_HOME_FETCH_LIVE_PROJECTS';

		$scope.typeWrapper = {
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.projectList = response.data;
				}, function(response) {
				});
	}

	$scope.fetchCompletedProject = function(){
		$scope.url = "AdminServlet";
		var config = 'contenttype';
		$scope.type = 'ADMIN_HOME_FETCH_COMPLETED_PROJECTS';

		$scope.typeWrapper = {
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.completedProjectList = response.data;
				}, function(response) {
				});
	}

	$scope.addNewProject = function(){
		location.href = "add-project.html";
	}
	
	$scope.waitForServiceLoad = function() {
		$scope.fetchLiveProject();
		$scope.fetchCompletedProject();
	}

	$scope.waitForServiceLoad();
});