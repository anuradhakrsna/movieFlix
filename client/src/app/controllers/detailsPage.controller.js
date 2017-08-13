(function () {
    'use strict'

    angular.module('movieflixApp').controller('DetailsPageController', DetailsPageController);

    DetailsPageController.$inject = ['detailsPageService', '$routeParams', '$rootScope', '$location'];
    function DetailsPageController(detailsPageService, $routeParams, $rootScope, $location) {
        var vm = this;
        vm.currentTime = null;
        vm.uRating = 0;

        vm.getComments = function () {
            detailsPageService.getMovieComments($routeParams.id).then(function (data) {
                vm.movieComments = data;
            });
        }

        vm.addComment = function () {

            vm.currentTime = new Date();

            var reqBody = {
                comments: vm.commentContent,
                time: vm.currentTime,
                users: {
                    id: $rootScope.userId
                },
                movies: {
                    id: $routeParams.id
                }
            }

            detailsPageService.postMovieComments(reqBody).then(function (data) {
                vm.commentContent = null;
                vm.getComments();
            });
        }

        vm.getMovieDetails = function () {
            detailsPageService.getMovieDetails($routeParams.id).then(function (data) {
                vm.movieDetails = data;
            });
        };

        vm.getAverageRating = function () {
            detailsPageService.getAverageRating($routeParams.id).then(function (data) {
                vm.averageRatings = data.averageRating + ' | ' + data.NoOfRating + ' rating(s)';
            }, function (reason) {
                vm.averageRatings = 'No Ratings'
            });
        }

        vm.getUserRatingForMovie = function () {
            detailsPageService.getUserRatingForMovie($rootScope.userId, $routeParams.id)
                .then(function (data) {
                    vm.userRating = data.uratings;
                });
        }

        vm.init = function () {

            if ($rootScope.userId === undefined) {
                $location.path('/loginPage');
            }

            vm.loginPage = false;
            vm.title = "Details Page controller connected !";
            vm.templateUrl = "detailsPageTemplate.html";

            vm.getMovieDetails();
            vm.getComments();
            vm.getAverageRating();
            vm.getUserRatingForMovie();
        }

        vm.init();

        vm.saveRating = function (val) {
            var reqBody = {
                uratings: val,
                users: {
                    id: $rootScope.userId
                },
                movies: {
                    id: $routeParams.id
                }
            }
            detailsPageService.setUserRating(reqBody).then(function (data) {
                vm.getAverageRating();
            });
        }


    }

})();