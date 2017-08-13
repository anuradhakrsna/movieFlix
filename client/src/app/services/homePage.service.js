(function () {
    'use strict'

    angular.module('movieflixApp').service('HomePageService', HomePageService);

    HomePageService.$inject = ['$http', '$q', 'CONFIG'];
    function HomePageService($http, $q, CONFIG) {
        var self = this;

        self.getMovieList = function () {
            return $http.get(CONFIG.API_HOST + '/movies').then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        };

        self.getTopRatedMovies = function () {
            return $http.get(CONFIG.API_HOST + '/movies/toprated').then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        };

        self.getTopRatedSeries = function () {
            return $http.get(CONFIG.API_HOST + '/movies/toprated').then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }
    }

})();