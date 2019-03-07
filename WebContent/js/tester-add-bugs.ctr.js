var app = angular.module('btsApp', []);
app.controller('addBugsCtr', function($scope, $http, $location, $log) {

	$scope.severityList = [ 'BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR',
			'IMPROVEMENT' ];
	$scope.statusList = [ 'REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED',
			'FIXED', 'UNABLE_TO_REPRODUCE' ];
	$scope.priority = [ 'HIGH', 'MEDIUM', 'LOW' ];

	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1].split("&")[0];
	var projectId = urlData.split("=")[2];

	var config = 'contenttype';
	$scope.bug = {};
	$scope.bugList = [];

	$scope.saveAndSubmit = function(bugObject) {
		$scope.url = "AddProjectServlet";
		$scope.testerEntity = {
			id : testerId,
			fullName : $scope.tester.fullName
		};
		$scope.typeWrapper = {
			projectId : projectId,
			bug : $scope.bug,
			testerEntity : $scope.testerEntity,
			type : "UPDATE_PROJECT"
		}
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.response = response;
					$scope.bugList = response.data.bugList;
				}, function(response) {

				});
	}

	$scope.tester = {};
	$scope.fetchTesterById = function() {
		$scope.url = "EmployeeServlet";
		$scope.type = 'TEST_HOME_FETCH_TESTER';

		$scope.tester = {
			id : testerId
		};

		$scope.typeWrapper = {
			testerEntity : $scope.tester,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.tester = response.data;
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
	// $scope.fetchProjectList = function() {
	// $scope.url = "EmployeeServlet";
	// var config = 'contenttype';
	// $scope.type = 'TESTER_HOME';
	// $scope.typeWrapper = {
	// type : $scope.type,
	// testerEntity : $scope.tester
	// };
	// $http.post($scope.url, $scope.typeWrapper, config).then(
	// function(response) {
	// $scope.projectList = response.data;
	// }, function(response) {
	// });
	// }

	$scope.fetchTesterById();
	$scope.fetchProjectById();
	// $scope.fetchProjectList();

	$scope.gotoAddBugsPage = function() {
		location.href = "tester-add-bugs.html?testerId=" + testerId;
	}

	$scope.gotoTesterHomePage = function() {
		location.href = "tester-home.html?testerId=" + testerId;
	}

});