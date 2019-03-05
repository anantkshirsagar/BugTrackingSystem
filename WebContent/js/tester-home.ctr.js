var app = angular.module('btsApp', []);
app.controller('testerHomeCtr', function($scope, $http, $location) {

	
	var config = 'contenttype';
	$scope.type = 'TESTER_HOME';
	
	$scope.assignedProjectList = [];
	$scope.fetchAssignedProject = function() {
		$scope.url = "EmployeeServlet";
		
		var urlData = $location.absUrl();
		var testerId = urlData.split("=")[1];
		
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
	
	$scope.waitForServiceLoad = function(){
		$scope.fetchAssignedProject();
	}
	
	$scope.waitForServiceLoad();

});