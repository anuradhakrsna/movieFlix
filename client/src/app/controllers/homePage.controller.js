(function () {
    'use strict'

    angular.module('movieflixApp').controller('HomePageController', HomePageController);

    HomePageController.$inject = ['HomePageService', '$rootScope', '$location'];
    function HomePageController(HomePageService, $rootScope, $location) {
        var vm = this;

        vm.init = function () {

            if ($rootScope.userId === undefined) {
                $location.path('/loginPage');
            }

            vm.loginPage = false;
            vm.templateUrl = "homePageTemplate.html";
            vm.title = "Helloworld";

            HomePageService.getMovieList().then(function (data) {
                vm.movieList = data;
            });

            HomePageService.getTopRatedMovies().then(function (data) {
                vm.topRatedMovieList = data;
            });

            HomePageService.getTopRatedSeries().then(function (data) {
                vm.topRatedSeriesList = data;
            });
        }

        vm.init();
    }


})();