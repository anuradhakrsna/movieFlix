(function () {
    'use strict'

    angular.module('movieflixApp').service('searchSortService', searchSortService);

    searchSortService.$inject = ['$http', '$q', 'CONFIG'];
    function searchSortService($http, $q, CONFIG) {
        var self = this;
        self.getMovieList = getMovieList;


        function getMovieList() {
            return $http.get(CONFIG.API_HOST + '/movies').then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }


    }

})();