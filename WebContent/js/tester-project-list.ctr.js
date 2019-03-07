var app = angular.module('btsApp', []);

app.controller('testerProjectListCtr', function($scope, $http, $location) {

	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1];
	var config = 'contenttype';

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
		$scope.url = "EmployeeServlet";
		$scope.type = 'TESTER_HOME';
		$scope.typeWrapper = {
			type : $scope.type,
			testerEntity : $scope.tester
		};
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.projectList = response.data;
				}, function(response) {
				});
	}

	$scope.selectProject = function(project) {
		$scope.projectId = project.id;
		location.href = "tester-add-bugs.html?testerId=" + testerId
		+ "&projectId=" + $scope.projectId;
	}

	$scope.fetchTesterById();
	$scope.fetchProjectList();

	$scope.gotoAddBugsPage = function() {
		location.href = "tester-project-list.html?testerId=" + testerId;
	}

	$scope.gotoTesterHomePage = function() {
		location.href = "tester-home.html?testerId=" + testerId;
	}
});
;