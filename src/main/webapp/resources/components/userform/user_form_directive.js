/**
 * Created by melvin on 1/30/2016.
 */
'use strict';

angular.module('myApp.userform.directive', [])

    .directive('myUserForm', [function() {
        return {
            restrict: 'E',
            scope: {
                info: '='
            },
            templateUrl: 'resources/components/userform/user_form_template.html'
        };
    }]);