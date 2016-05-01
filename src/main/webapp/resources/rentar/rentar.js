'use strict';

angular.module('myApp.rentar', ['ngRoute', 'ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/rentar', {
    templateUrl: 'resources/rentar/rentar.html',
    controller: 'RentarCtrl'
  });
}])

.controller('RentarCtrl', ['$scope', '$http','$uibModal',function($scope, $http, $uibModal) {
	$scope.usuarios = [];
	$scope.allRents = [];
	$scope.userRents = [];
	$scope.selectedUser = {};
	$scope.relationObjects = {};
	$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
    $scope.verTabla = false;

    $scope.init = function() {
	    	
	    $http.get('rest/protected/tipoAlquiler/getAll')
			.success(function(response) {
				$scope.tipoAlquilerList = response.tipoAlquilerList;
				$scope.requestObject.idTipoAlquiler = $scope.tipoAlquilerList[0].idTipoAlquiler;
				
			});

	    $http.get('rest/protected/rent/getAll').success(function(response) {
	      $scope.allRents = response.alquilerList;  
	    });

	    $http.post('rest/protected/users/getAll',$scope.requestObject)
		.success(function(response) {
			console.log("response",response)
			$scope.usuarios = response.usuarios;
			console.log("$scope.usuarios",$scope.usuarios)

		});

		$http.get('rest/protected/userRent/getAll').success(function(response) {
	      $scope.relationObjects = response.usuarioHasAlquiler;  
	    });
    	
    };
    
    $scope.init();


	$scope.rentar = function(user){
		console.log(user);
		var modalInstance = $uibModal.open({
          backdrop:'static',
          keyboard:false,
          templateUrl: 'resources/rentar/rentar-modal.html',
          controller: 'ModalRentarCtrl',
          size: "lg",
          windowClass:"modal",
          resolve: {
            user:function(){return user}
          }
        });

	}

	$scope.devolver = function(item){
		$scope.selectedUser = item[0].idUsuario;
		var relationSelectedUser = $scope.relationObjects.find($scope.findUser);
		var data = {};
		data = {
				  "code": 0,
				  "codeMessage": "string",
				  "errorMessage": "string",
				  "totalPages": 0,
				  "totalElements": 0,
				  "usuarioHasAlquiler": relationSelectedUser
				};

		$http.post('rest/protected/userRent/returnItem',data).success(function(response) {
			console.log("response",response);
			alert("Items devueltos correctamente");
		});
		$scope.init();
	};

	$scope.ver = function(user){
		var data = {};
		$scope.verTabla = true;
		data = {
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": "string",
				  "user":  user
				}
		$http.post('rest/protected/users/getAlquileres',data).success(function(response) {
			console.log("response",response)
			$scope.userRents = response.usuarios;
			console.log("$scope.usuarios",$scope.usuarios)

		});

	}

	$scope.findRelation = function (user) { 
    	return user.idObjetivo === $scope.requestObject.idObjetivo;
  	};

  	$scope.findUser = function (relation) { 
    	return relation.usuario.idUsuario === $scope.selectedUser;
  	};

	
}]);
