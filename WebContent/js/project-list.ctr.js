var app = angular.module('btsApp', []);

app.controller('projectListCtr', function($scope, $http) {

	$scope.url = "AdminServlet";
	var config = 'contenttype';
	$scope.type = 'PROJECT_LIST';
	$scope.list = [];
	$scope.typeWrapper = {
		type : $scope.type
	};

	$scope.gotoManagerPage = function(projectRecord) {
		location.href = "manager-assign-project.html?projectId="+projectRecord.id;
	}

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		$scope.list = response.data;
	}, function(response) {

	});
	
	$scope.addNewProject = function(){
		location.href = "add-project.html";
	}
});
;