
angular.module('myApp.modal', ['ngRoute','ui.bootstrap'])
.controller('ModalInstanceCtrl', ['$scope', '$http','$location','$upload','$uibModalInstance','item','$route',  function($scope, $http,$location,$upload,$uibModalInstance,item, $route)
{
	$scope.itemToEdit = item;
	$scope.rent={};
	$scope.files = {};
	$scope.onError = false;
	$scope.tipoAlquilerList = [];
	$scope.requestObject = {};
  	$scope.allRents = [];
	
    $scope.init = function() {
    	
    $http.get('rest/protected/tipoAlquiler/getAll')
		.success(function(response) {
			$scope.tipoAlquilerList = response.tipoAlquilerList;
			$scope.requestObject.idTipoAlquiler = $scope.tipoAlquilerList[0].idTipoAlquiler;
			
		});

    $http.get('rest/protected/rent/getAll').success(function(response) {
      $scope.allRents = response.alquilerList;  
    });
    	
    };
    
    $scope.init();

    $scope.onFileSelect = function($files) {
		$scope.files = $files;
    };


    $scope.saveRent = function(event){
	
	if(this.editRentForm.$valid){
		$scope.onError = false;
		
		//$files: an array of files selected, each file has name, size, and type.
		for ( var i = 0; i < $scope.files.length; i++) {
			var file = $scope.files[i];
			$scope.upload = $upload.upload({
				url : 'rest/protected/rent/save',
				data : {
					idAlquiler: $scope.itemToEdit.idAlquiler,
					idTipoAlquiler:$scope.itemToEdit.idTipoAlquiler,
					name:$scope.itemToEdit.name,
					description:$scope.itemToEdit.description,
				},
				file : file,
			}).progress(
				function(evt) {
					console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
				}).success(function(data, status, headers, config) {
					// Rent is uploaded successfully
					console.log(data);
				});
    			//.error(...)
    			//.then(success, error, progress); 
		}
	}else{
		$scope.onError = true;
	}
};

}]);
  
