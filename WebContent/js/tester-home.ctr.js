var app = angular.module('btsApp', []);
app.controller('testerHomeCtr', function($scope, $http, $location) {
	var config = 'contenttype';
	$scope.type = 'TESTER_HOME';
	
	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1];
	
	$scope.assignedProjectList = [];
	$scope.fetchAssignedProject = function() {
		$scope.url = "EmployeeServlet";
		
		$scope.testerEntity = {
			 id: testerId
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
			id: testerId
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
	
	$scope.waitForServiceLoad = function(){
		$scope.fetchAssignedProject();
		$scope.fetchTesterById();
	}
	
	$scope.waitForServiceLoad();
	
	$scope.gotoAddBugsPage = function(){
		location.href = "tester-add-bugs.html?testerId="+testerId;
	}
	
	$scope.gotoTesterHomePage = function(){
		location.href = "tester-home.html?testerId="+testerId;
	}

});