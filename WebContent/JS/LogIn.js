/**
 * 
 */

var app = angular.module('appLogIn', []);
	app.controller('ctlLogIn', function($scope, $http) {
		$scope.restURL = "";
		
		$scope.LogIN = function(){
			url = $scope.restURL;
						
			$http.post(url, {"userName" : $scope.userName, "password" : $scope.password, "clientType" : $scope.clientType })
			.then(function(response) {
				//$scope.showInfoMessage("Successfully LogedIn");				
			}, $scope.handleError);
		}		

	
	
	$scope.handleError = function(response){		
		
		if (response.error != null){
			$scope.errorDetails = "Error: " + response.error;
		}if (response.data != null && response.data.error != null){
			$scope.errorDetails = "Error: " + response.data.error;
		}else{
			$scope.errorDetails = "Error: " + response;
		}
	};
	
}); //end of 
	

