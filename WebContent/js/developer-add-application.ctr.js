var app = angular.module('btsApp', []);
app.controller('developerAddApplicationCtr',
		function($scope, $http, $location) {

			var config = 'contenttype';
			$scope.type = 'DEVELOPER_HOME';

			$scope.assignedProjectList = [];
			var urlData = $location.absUrl();
			var developerId = urlData.split("=")[1];

			$scope.fetchAssignedProject = function() {
				$scope.url = "EmployeeServlet";

				$scope.developerEntity = {
					id : developerId
				};

				$scope.typeWrapper = {
					developerEntity : $scope.developerEntity,
					type : $scope.type
				};

				$http.post($scope.url, $scope.typeWrapper, config).then(
						function(response) {
							$scope.assignedProjectList = response.data;
						}, function(response) {

						});
			}

			$scope.developer = {};
			$scope.fetchDeveloperById = function() {
				$scope.url = "EmployeeServlet";
				$scope.type = 'DEV_HOME_FETCH_DEVELOPER';

				$scope.developer = {
					id : developerId
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

			$scope.waitForServiceLoad = function() {
				$scope.fetchAssignedProject();
				$scope.fetchDeveloperById();
			}

			$scope.waitForServiceLoad();

			$scope.selectedProjectRecord = {};
			$scope.selectProject = function(projectRecord) {
				$scope.selectedProjectRecord = projectRecord;
				$scope.links = $scope.selectedProjectRecord.applicationList;
			}

			$scope.links = [];
			$scope.addNewLink = function(link) {
				$scope.application = {
					id : developerId,
					link : link
				};

				$scope.url = "EmployeeServlet";
				$scope.type = 'ADD_LINK';

				$scope.typeWrapper = {
					projectId : $scope.selectedProjectRecord.id,
					application : $scope.application,
					type : $scope.type
				};

				$http.post($scope.url, $scope.typeWrapper, config).then(
						function(response) {
							$scope.links = response.data.applicationList;
						}, function(response) {

						});
				$scope.links.push($scope.application);
			}
			
			$scope.clearDetails = function() {
				$scope.links = [];
			}

			$scope.gotoHomePage = function() {
				location.href = "developer-home.html?developerId="
						+ developerId;
			}

			$scope.gotoViewBugPage = function() {
				location.href = "developer-project-list.html?developerId="
						+ developerId;
			}

			$scope.gotoApplicationPage = function() {
				location.href = "developer-application-page.html?developerId="
						+ developerId;
			}
		});