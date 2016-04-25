
angular.module('myApp.tipoUsuario.controller', ['myApp.tipousuario.service'])
.controller('TipoUsuarioCTRL', ['$scope', 'tipoUserService',function($scope, tipoUserService){

    $scope.tipos = [];
    $scope.tipoAgregar = {};

    $scope.init = function(){
        $scope.showEdit = false;
    }

    $scope.init();


    $scope.cancelEdit = () => {
        $scope.isEditing = false;
        $scope.tipoAgregar = {};
    }
    $scope.acceptEdit = () => {
        $scope.insertarTipo();
        $scope.cancelEdit();
    }

    tipoUserService.getTiposUsuarios().then(r => { $scope.tipos = r; });

    $scope.insertarTipo = () => {
        tipoUserService.saveTipo($scope.tipoAgregar).then( r => { $scope.tipos = r; });
    }

    $scope.deleteTipo = function(tipo){
        tipoUserService.deleteTipo(tipo).then(r => {
            $scope.tipos.splice($scope.tipos.indexOf(tipo),1)
        });
    }

    $scope.show = function(tipo){
        $scope.showEdit = true;
        $scope.editTipo(tipo);
    }

    $scope.editTipo = function(tipo){
        $scope.tipoAgregar = tipo;
        $scope.insertarTipo();
    }


}]);
