var app = angular.module('btsApp', []);
app.controller('testerListCtr', function($scope, $http) {

	$scope.url = "AdminServlet";
	var config = 'contenttype';
	$scope.type = 'TESTER_LIST';
	$scope.list = [];
	$scope.typeWrapper = {
		type : $scope.type
	};

	$http.post($scope.url, $scope.typeWrapper, config).then(function(response) {
		$scope.list = response.data;
	}, function(response) {

	});

	
	$scope.setProjectList = function(testerObj) {
		$scope.testerWiseProjectList = [];
		$scope.testerWiseProjectIds = testerObj.projectIdList;
		$scope.type = 'FETCH_PROJECT_BY_ID';
		angular.forEach($scope.testerWiseProjectIds, function(projectId) {
			$scope.typeWrapper = {
				projectId : projectId,
				type : $scope.type
			};
			$http.post($scope.url, $scope.typeWrapper, config).then(
					function(response) {
						$scope.testerWiseProjectList.push(response.data);
					}, function(response) {
					});
		});
	}
});