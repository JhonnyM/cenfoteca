/**
 * Created by melvin on 1/31/2016.
 */
angular.module('myApp.tipoUsuario.controller', ['myApp.tipousuario.service'])
    .controller('TipoUsuarioCTRL', ['$scope', 'tipoUserService',
        function($scope, tipoUserService){

            $scope.tipos = [];
            $scope.tipoAgregar = {};
            $scope.isEditing = false;


            $scope.$on('EDIT_CHANNEL', (event, tipo) => {
                $scope.isEditing = true;
                $scope.tipoAgregar.name = tipo.name;
                $scope.tipoAgregar.idTipoUsuario = tipo.idTipoUsuario;
            });

            $scope.$on('DELETE_CHANNEL', (event, tipo) => {
                tipoUserService.deleteTipo(tipo)
                    .then(r => {
                        $scope.tipos.splice($scope.tipos.indexOf(tipo),1)
                    });
            });


            $scope.cancelEdit = () => {
                $scope.isEditing = false;
                $scope.tipoAgregar = {};
            }
            $scope.acceptEdit = () => {
                $scope.insertarTipo();
                $scope.cancelEdit();
            }

            tipoUserService.getTiposUsuarios()
                           .then(r => { $scope.tipos = r; });

            $scope.insertarTipo = () => {
                tipoUserService.saveTipo($scope.tipoAgregar)
                    .then( r => { $scope.tipos = r; });
            }




        }]);