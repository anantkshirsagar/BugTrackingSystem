var app = angular.module('btsApp', []);
app.controller('addBugsCtr', function($scope, $http, $location) {

	$scope.severityList = [ 'BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR',
			'IMPROVEMENT' ];
	$scope.statusList = [ 'REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED',
			'FIXED', 'UNABLE_TO_REPRODUCE' ];
	$scope.priority = [ 'HIGH', 'MEDIUM', 'LOW' ];

	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1];

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
			projectId : $scope.bug.projectId,
			bug : $scope.bug,
			testerEntity : $scope.testerEntity,
			type : "UPDATE_PROJECT"
		}
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.response = response
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

	$scope.fetchProjectList = function() {
		$scope.url = "AdminServlet";
		var config = 'contenttype';
		$scope.type = 'PROJECT_LIST';
		$scope.typeWrapper = {
			type : $scope.type
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.projectList = response.data;
				}, function(response) {
				});
	}

	// $scope.fetchTesterById();
	$scope.fetchProjectList();

	$scope.gotoAddBugsPage = function() {
		location.href = "tester-add-bugs.html?testerId=" + testerId;
	}

	$scope.gotoTesterHomePage = function() {
		location.href = "tester-home.html?testerId=" + testerId;
	}

});