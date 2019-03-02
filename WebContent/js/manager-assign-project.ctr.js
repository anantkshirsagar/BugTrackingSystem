var app = angular.module('btsApp', []);
app.controller('managerAssignProjectCtr', function($scope, $http, $location,
		$log) {
	$scope.url = "ManagerAssignProjectServlet";
	var config = 'contenttype';
	$scope.url = "ManagerAssignProjectServlet";

	$scope.manager = {
		name : 'Anant'
	}

	var urlData = $location.absUrl();
	var projectId = urlData.split("=")[1];

	$scope.projectEntity = {};
	$scope.findProjectById = function() {
		angular.forEach($scope.projectList, function(project) {
			if (project.id == projectId) {
				$scope.projectEntity = project;
			}
		});
	}

	$scope.selectedDeveloperList = [];
	$scope.selectedDevelopers = function(developer, isSelected, index) {
		if (isSelected) {
			$scope.selectedDeveloperList.push(developer);
		} else {
			$scope.selectedDeveloperList.splice(index, 1);
		}
	}

	$scope.selectedTesterList = [];
	$scope.selectedTester = function(tester, isSelected, index) {
		if (isSelected) {
			$scope.selectedTesterList.push(tester);
		} else {
			$scope.selectedTesterList.splice(index, 1);
		}
	}
	
	$scope.config = 'contenttype';

	$scope.saveDocument = function() {
		$scope.employeeWrapper = {
			developerList : $scope.selectedDeveloperList,
			testerList : $scope.selectedTesterList
		};

		$scope.type = 'SAVE_EMPLOYEE_WRAPPER';
		$scope.url = "ManagerAssignProjectServlet";
		
		$scope.typeWrapper = {
			employeeWrapper : $scope.employeeWrapper,
			type : $scope.type
		};
		
		
		$http.post($scope.url, $scope.typeWrapper, $scope.config).then(
				function(response) {
					$log.info(" Manager project assigned, saved successfully.");
				}, function(error) {
					$log.info(" Manager assign project: " + error);
				});
	}

	$scope.developerList = [];
	$scope.projectList = [];
	$scope.testerList = [];

	$scope.fetchEmployeeDetails = function() {
		$scope.url = "ManagerAssignProjectServlet";
		$scope.type = 'WRAPPER_LIST';
		$scope.typeWrapper = {
			employeeWrapper : null,
			type : $scope.type
		};

		$http.post($scope.url, $scope.typeWrapper, $scope.config).then(
				function(response) {
					var data = response.data;
					$scope.developerList = data.developerList;
					$scope.projectList = data.projectList;
					$scope.testerList = data.testerList;
					$scope.findProjectById();
				}, function(response) {

				});
	}

	$scope.waitForServiceLoad = function() {
		$scope.fetchEmployeeDetails();
	}

	$scope.waitForServiceLoad();
});