var app = angular.module('btsApp', []);
app.controller('viewBugsCtr', function($scope, $http, $location, $log) {

	$scope.severityList = [ 'BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR',
			'IMPROVEMENT' ];
	$scope.statusList = [ 'REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED',
			'FIXED', 'UNABLE_TO_REPRODUCE' ];
	$scope.priority = [ 'HIGH', 'MEDIUM', 'LOW' ];

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

	$scope.projectDetail = {};

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

	$scope.submit = function(bugObject) {
		$scope.currentProject = angular.copy($scope.projectDetail);
		$scope.bugs = $scope.currentProject.bugList;
		for (var i = 0; $scope.bugs.length; ++i) {
			if ($scope.bugs[i].bugDescription == bugObject.bugDescription) {
				$scope.bugs[i].bugStatus = bugObject.bugStatus;
				// $scope.currentProject.bugList[i] = bugObject;
				break;
			}
		}

		$scope.url = "AddProjectServlet";

		$scope.typeWrapper = {
			projectId : projectId,
			bugList : $scope.bugs,
			type : "UPDATE_BUG_LIST"
		}
		
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.response = response;
					$scope.bugList = response.data.bugList;
				}, function(response) {
					$log.info(response);
				});
		console.log($scope.currentProject);
	}

	$scope.gotoHomePage = function() {
		location.href = "developer-home.html?developerId=" + developerId;
	}

	$scope.gotoViewBugPage = function() {
		location.href = "developer-project-list.html?developerId="
				+ developerId;
	}
});