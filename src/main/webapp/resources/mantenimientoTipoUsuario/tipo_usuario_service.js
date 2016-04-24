'use strict';

angular.module('myApp.tipousuario.service', [])
    .service('tipoUserService', function($http) {
       let userRequest =
       {
           "pageNumber": 0,
           "pageSize": 0,
           "direction": "",
           "sortBy": [""],
           "searchColumn": "string",
           "searchTerm": "",
           "user": {}
       };
        let typeUserRequest = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": "",
            "tipo": {}
        };

        this.getTiposUsuarios =
            () => $http.post('rest/protected/users/getTiposUsuario', userRequest)
                       .then( response => response.data.tipoUsuariosList, response => {});

        this.saveTipo = tipo => {
            typeUserRequest.tipo = tipo;
            return $http.post('rest/protected/users/createTipo',typeUserRequest)
                        .then(r => r.data.tipoUsuariosList ,
                              r => alert(r.data.errorMessage));
        }

        this.deleteTipo = tipo => {
            typeUserRequest.tipo = tipo;
            return $http.post('rest/protected/users/deleteTipo',typeUserRequest)
                .then(r => r.codeMessage,
                    r => alert(r.data.errorMessage));
        }



    });
