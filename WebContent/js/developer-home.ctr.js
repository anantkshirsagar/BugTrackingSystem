var app = angular.module('btsApp', []);
app.controller('developerHomeCtr', function($scope, $http, $location) {

	
	var config = 'contenttype';
	$scope.type = 'DEVELOPER_HOME';
	
	$scope.assignedProjectList = [];
	$scope.fetchAssignedProject = function() {
		$scope.url = "DeveloperServlet";
		
		var urlData = $location.absUrl();
		var developerId = urlData.split("=")[1];
		
		$scope.developerEntity = {
			 id: developerId
		};
		
		$scope.typeWrapper = {
			developerEntity : $scope.developerEntity,
			type : $scope.type
		};
		
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					$scope.assignedProjectList = response;
				}, function(response) {

				});
	}
	
	$scope.waitForServiceLoad = function(){
		$scope.fetchAssignedProject();
	}
	
	$scope.waitForServiceLoad();

});