var app = angular.module('btsApp', []);
app.controller('addProjectCtr', function($scope, $http) {

	$scope.url = "AddProjectServlet";
	var config = 'contenttype';
	$scope.documentEntity = {
		projectName : '',
		description : '',
		feature : null,
		subFeature : ''
	};

	$scope.addProject = function() {
		$http.post($scope.url, $scope.documentEntity, config).then(
				function(response) {
					console.log(response.data);
				}, function(response) {

				});
	}

});