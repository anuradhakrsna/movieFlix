(function () {
    'use strict'

    angular.module('movieflixApp').controller('SearchSortController', SearchSortController);

    SearchSortController.$inject = ['searchSortService', '$routeParams', 'HomePageService', '$rootScope'];
    function SearchSortController(searchSortService, $routeParams, HomePageService, $rootScope) {
        var vm = this;

        init();

        function init() {

            if ($rootScope.userId === undefined) {
                $location.path('/loginPage');
            }

            console.log("inside init service call before hitting service");
            vm.loginPage = false;
            vm.title = "SearchSort Page controller connected !";
            vm.templateUrl = "searchSortPageTemplate.html";
            vm.searchType = $routeParams.type;
            vm.searchTitle = 'All Movies';

            if (vm.searchType == 'topRatedMovies') {
                vm.searchTitle = 'Top Rated Movies';
                HomePageService.getTopRatedMovies().then(function (data) {
                    vm.movieList = data;
                });
            } else if (vm.searchType == 'topRatedSeries') {
                vm.searchTitle = 'Top Rated Series';
                HomePageService.getTopRatedSeries().then(function (data) {
                    vm.movieList = data;
                });

            } else if (vm.searchType == 'latest') {
                vm.searchTitle = 'Latest Movies';
                searchSortService.getMovieList().then(function (data) {
                    vm.movieList = data;
                });

            } else {
                vm.searchTitle = 'All Movies';
                searchSortService.getMovieList().then(function (data) {
                    vm.movieList = data;
                });
            }
        }

    }

})();