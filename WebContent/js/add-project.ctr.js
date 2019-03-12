var app = angular.module('btsApp', []);
app.controller('addProjectCtr', function($scope, $http) {
	$scope.url = "AddProjectServlet";
	$scope.type = "ADD_PROJECT";
	var config = 'contenttype';

	$scope.documentEntity = {
		projectName : '',
		description : '',
		feature : null,
		subFeature : ''
	};

	$scope.typeWrapper = {
		projectEntity : $scope.documentEntity,
		type : $scope.type
	};

	$scope.goToHomePage = function() {
		location.href = "index.html";
	}

	$scope.addProject = function() {
		$http.post($scope.url, $scope.typeWrapper, config).then(
				function(response) {
					console.log(response.data);
				}, function(response) {

				});
	}
	
	$scope.gotoProjectListPage = function(){
		location.href = "project-list.html";
	}

	$scope.addNewProject = function() {
		location.href = "add-project.html";
	}
});