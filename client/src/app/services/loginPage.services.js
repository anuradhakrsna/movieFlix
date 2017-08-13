/**
 * Created by anuradhakrsna18 on 8/11/2017.
 */
(function () {
    'use strict'

    angular.module('movieflixApp').service('loginPageService', loginPageService);

    loginPageService.$inject = ['$http', '$q', 'CONFIG'];
    function loginPageService($http, $q, CONFIG) {
        var self = this;

        self.addNewUser = function (reqBody) {
            return $http.post(CONFIG.API_HOST + '/users/', reqBody)
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error.status);
                })
        }

        self.verifyUser = function (reqBody) {
            return $http.post(CONFIG.API_HOST + '/users/login', reqBody)
                .then(function (response) {
                    return response.data;
                }, function (error) {
                    return $q.reject(error.status);
                })
        }
    }
})();