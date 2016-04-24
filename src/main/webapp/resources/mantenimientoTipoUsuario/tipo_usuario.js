/**
 * Created by melvin on 1/31/2016.
 */

angular.module('myApp.tipoUsuario', ['ngRoute', 'myApp.tipoUsuario.controller'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view3', {
            templateUrl: 'resources/mantenimientoTipoUsuario/mantenimiento_tipo_usuario.html',
            controller: 'TipoUsuarioCTRL'
        });
    }])
