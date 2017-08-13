(function () {
    'use strict'

    angular.module('movieflixApp').service('accountPageService', accountPageService);

    accountPageService.$inject = ['$http', '$q', 'CONFIG'];
    function accountPageService($http, $q, CONFIG) {
        var self = this;
        self.getUserDetails = getUserDetails;
        self.updatingExistingUser = updatingExistingUser;
        self.deletingUser = deletingUser;

        function getUserDetails(id) {
            var url = CONFIG.API_HOST + '/users/' + id;
            return $http.get(url).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }

        function deletingUser(id) {
            var url = CONFIG.API_HOST + '/users/' + id;
            return $http({
                url: url,
                dataType: 'json',
                method: 'DELETE'
            }).then(function (response) {
                return response.data;
            }).catch(function (error) {
                return $q.reject(error.status);
            });
        }


        function updatingExistingUser(reqBody, id) {
            console.log(reqBody);
            var url = CONFIG.API_HOST + '/users/' + id;
            return $http.put(url, reqBody).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }
    }

})();