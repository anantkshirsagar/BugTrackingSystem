var app = angular.module('btsApp', []);

app.controller('developerProjectListCtr', function($scope, $http, $location) {

	var urlData = $location.absUrl();
	var developerId = urlData.split("=")[1];
	var config = 'contenttype';

	$scope.fetchDeveloperById = function() {
		$scope.url = "EmployeeServlet";
		$scope.type = 'DEV_HOME_FETCH_DEVELOPER';

		$scope.developer = {
			id : developerId
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

	$scope.fetchProjectList = function() {
		$scope.url = "EmployeeServlet";
		$scope.type = 'DEVELOPER_HOME';
		$scope.typeWrapper = {
			developerEntity : $scope.developer,
			type : $scope.type
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.projectList = response.data;
				}, function(response) {
				});
	}

	$scope.selectProject = function(project) {
		$scope.projectId = project.id;
		location.href = "developer-view-bugs.html?developerId=" + developerId
				+ "&projectId=" + $scope.projectId;
	}

	$scope.fetchDeveloperById();
	$scope.fetchProjectList();

	$scope.gotoAddBugsPage = function() {
		location.href = "developer-project-list.html?developerId=" + developerId;
	}

	$scope.gotoTesterHomePage = function() {
		location.href = "developer-home.html?developerId=" + developerId;
	}
	
	$scope.gotoApplicationPage = function(){
		location.href = "developer-add-application.html?developerId="+developerId;
	}
});
;