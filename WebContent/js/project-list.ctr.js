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
		location.href = "manager-assign-project.html?projectId="
				+ projectRecord.id;
	}

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		$scope.list = response.data;
		angular.forEach($scope.list, function(record) {
			record.startDate = new Date(record.startDate);
			record.endDate = new Date(record.endDate);
		});
	}, function(response) {

	});

	$scope.addNewProject = function() {
		location.href = "add-project.html";
	}
});
;