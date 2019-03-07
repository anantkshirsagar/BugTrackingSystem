var app = angular.module('btsApp', []);
app.controller('viewBugsCtr', function($scope, $http, $location) {

	$scope.severityList = ['BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR', 'IMPROVEMENT'];
	$scope.statusList = ['REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED', 'FIXED', 'UNABLE_TO_REPRODUCE'];
	$scope.priority = ['HIGH', 'MEDIUM', 'LOW'];
	
	$scope.url = "BugServlet";
	var config = 'contenttype';
	
	var urlData = $location.absUrl();
	var developerId = urlData.split("=")[1].split("&")[0];
	var projectId = urlData.split("=")[2];
	
	$scope.bug = {};
	
	
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
	
	$scope.fetchProjectById = function() {
		$scope.url = "AddProjectServlet";
		$scope.type = 'GET_PROJ_BY_ID';

		$scope.project = {
			id : projectId
		};

		$scope.typeWrapper = {
			projectEntity : $scope.project,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.projectDetail = response.data;
					$scope.bugList = response.data.bugList;
				}, function(response) {

				});
	}
	
	$scope.viewBug = function(bug) {
		$scope.viewFlag = false;
		$scope.bug = bug;
	}

	$scope.clearDetails = function() {
		$scope.viewFlag = true;
		$scope.bug = {};
	}
	
	$scope.fetchDeveloperById();
	$scope.fetchProjectById();

	$scope.gotoHomePage = function(){
		location.href = "developer-home.html?developerId="+developerId;
	}

	$scope.gotoViewBugPage = function(){
		location.href = "developer-project-list.html?developerId="+developerId;
	}
});