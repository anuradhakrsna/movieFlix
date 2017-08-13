(function () {
    'use strict'

    angular.module('movieflixApp').service('detailsPageService', detailsPageService);

    detailsPageService.$inject = ['$http', '$q', 'CONFIG'];
    function detailsPageService($http, $q, CONFIG) {
        var self = this;

        self.getMovieDetails = function (id) {
            var movieDetailsLink = CONFIG.API_HOST + '/movies/' + id;
            return $http.get(movieDetailsLink).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }

        self.getMovieComments = function (id) {
            var movieCommentsLink = CONFIG.API_HOST + '/comments/movie/' + id;
            return $http.get(movieCommentsLink).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        };

        self.postMovieComments = function (reqBody) {
            return $http.post(CONFIG.API_HOST + '/comments', reqBody).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }

        self.getAverageRating = function (id) {
            var movieRatings = CONFIG.API_HOST + '/uratings/findStats?movieId=' + id;
            return $http.get(movieRatings).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }

        self.setUserRating = function (reqBody) {
            return $http.post(CONFIG.API_HOST + '/uratings', reqBody).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        }

        self.getUserRatingForMovie = function (userId, movieId) {
            var movieCommentsLink = CONFIG.API_HOST + '/uratings/findRating?userId=' + userId + '&movieId=' + movieId;
            return $http.get(movieCommentsLink).then(function (response) {
                return response.data;
            }, function (error) {
                return $q.reject(error.status);
            })
        };
    }

})();