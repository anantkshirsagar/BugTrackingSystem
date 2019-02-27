var app = angular.module('btsApp', []);
app.controller('addBugsCtr', function($scope, $http) {

	$scope.severityList = ['BLOCKER', 'CRITICAL', 'MAJOR', 'MINOR', 'IMPROVEMENT'];
	$scope.statusList = ['REJECT', 'DUPLICATE', 'POSTPONED', 'NOT_FIXED', 'FIXED', 'UNABLE_TO_REPRODUCE'];
	$scope.priority = ['HIGH', 'MEDIUM', 'LOW'];
	
	$scope.url = "BugServlet";
	var config = 'contenttype';
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

});