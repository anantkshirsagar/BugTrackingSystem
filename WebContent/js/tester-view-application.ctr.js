var app = angular.module('btsApp', []);
app.controller('testerViewApplicationCtr', function($scope, $http, $location) {

	var config = 'contenttype';
	$scope.type = 'TESTER_HOME';

	$scope.assignedProjectList = [];
	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1];

	$scope.fetchAssignedProject = function() {
		$scope.url = "EmployeeServlet";

		$scope.testerEntity = {
			id : testerId
		};

		$scope.typeWrapper = {
			testerEntity : $scope.testerEntity,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.assignedProjectList = response.data;
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

	$scope.waitForServiceLoad = function() {
		$scope.fetchAssignedProject();
		$scope.fetchTesterById();
	}

	$scope.waitForServiceLoad();

	$scope.selectedProjectRecord = {};
	$scope.selectProject = function(projectRecord) {
		$scope.selectedProjectRecord = projectRecord;
		$scope.links = $scope.selectedProjectRecord.applicationList;
	}

	$scope.clearDetails = function() {
		$scope.links = [];
	}

	$scope.gotoTesterHomePage = function() {
		location.href = "tester-home.html?testerId=" + testerId;
	}

	$scope.gotoViewBugPage = function() {
		location.href = "tester-project-list.html?testerId=" + testerId;
	}

	$scope.gotoTesterViewApplicationPage = function() {
		location.href = "tester-view-application.html?testerId=" + testerId;
	}
});