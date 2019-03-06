var app = angular.module('btsApp', []);
app.controller('viewBugsCtr', function($scope, $http, $location) {

	$scope.severityList = ['BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR', 'IMPROVEMENT'];
	$scope.statusList = ['REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED', 'FIXED', 'UNABLE_TO_REPRODUCE'];
	$scope.priority = ['HIGH', 'MEDIUM', 'LOW'];
	
	$scope.url = "BugServlet";
	var config = 'contenttype';
	
	var urlData = $location.absUrl();
	var developerId = urlData.split("=")[1];
	
	$scope.bug = {};
	
	$scope.clearDetails = function(){
		$scope.bug = {};
	}
	
	$scope.addProject = function() {
		$http.post($scope.url, $scope.documentEntity, config).then(
				function(response) {
					console.log(response.data);
					
				}, function(response) {

				});
	}
	
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
	
	$scope.fetchDeveloperById();
	

	$scope.gotoHomePage = function(){
		location.href = "developer-home.html?developerId="+developerId;
	}

	$scope.gotoViewBugPage = function(){
		location.href = "developer-view-bugs.html?developerId="+developerId;
	}
});