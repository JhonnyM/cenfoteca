
angular.module('myApp.rentarModal', ['ngRoute','ui.bootstrap'])
.controller('ModalRentarCtrl', ['$scope', '$http','$uibModalInstance','user','$route',  function($scope, $http,$uibModalInstance,user, $route)
{
	$scope.usertoRent = user;
	$scope.itemTorent = {};
	$scope.files = {};
	$scope.onError = false;
	$scope.tipoAlquilerList = [];
	$scope.requestObject = {};
  	$scope.allRents = [];
	
    $scope.init = function() {
    	
        $http.get('rest/protected/tipoAlquiler/getAll').success(function(response) {
			$scope.tipoAlquilerList = response.tipoAlquilerList;
			$scope.requestObject.idTipoAlquiler = $scope.tipoAlquilerList[0].idTipoAlquiler;
    			
    	});

        $http.get('rest/protected/rent/getAll').success(function(response) {
            $scope.allRents = response.alquilerList;  
        });

        $http.post('rest/protected/users/getAll',$scope.requestObject).success(function(response) {
            console.log("response",response)
            $scope.usuarios = response.usuarios;
            console.log("$scope.usuarios",$scope.usuarios)
        });

        $http.get('rest/protected/rent/getAll').success(function(response) {
            $scope.allRents = response.alquilerList;  
        });
    	
    };
    
    $scope.init();

    $scope.save = function(){
        var idAlquiler = parseInt($scope.itemTorent);
        $scope.requestObject = 
                                {"pageNumber": 0,
                                 "pageSize": 0,
                                 "direction": "string",
                                 "sortBy": ["string"],
                                 "searchColumn": "string",
                                 "searchTerm": "string",
                                 "usuarioHasAlquiler": {"idItem":    idAlquiler,
                                              "idUsuario": user.idUsuario}};

        $http.post('rest/protected/userRent/rentItem',$scope.requestObject).success(function(response) {
            console.log("response",response)
            $uibModalInstance.close();
            $scope.init();
        });
    	
    }

}]);
  
