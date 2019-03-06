var app = angular.module('btsApp', []);
app.controller('addBugsCtr', function($scope, $http, $location) {

	$scope.severityList = ['BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR', 'IMPROVEMENT'];
	$scope.statusList = ['REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED', 'FIXED', 'UNABLE_TO_REPRODUCE'];
	$scope.priority = ['HIGH', 'MEDIUM', 'LOW'];
	
	var urlData = $location.absUrl();
	var testerId = urlData.split("=")[1];
	
	
	$scope.url = "BugServlet";
	var config = 'contenttype';
	$scope.bug = {};
	
	$scope.clearDetails = function(){
		$scope.bug = {};
	}
	
	$scope.bugList = [];
	
	$scope.saveAndSubmit = function(bugObject){
		$scope.bugList.push(bugObject);
	}
	
	$scope.addProject = function() {
		$http.post($scope.url, $scope.documentEntity, config).then(
				function(response) {
					console.log(response.data);
					
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
	$scope.fetchTesterById();
	
	$scope.gotoAddBugsPage = function(){
		location.href = "tester-add-bugs.html?testerId="+testerId;
	}
	
	$scope.gotoTesterHomePage = function(){
		location.href = "tester-home.html?testerId="+testerId;
	}

});