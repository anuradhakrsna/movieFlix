(function(){
    'use strict'

    angular.module('movieflixApp',['ngRoute','ngMessages'])
    .config(moduleConfig)
    .run(moduleRun);

    moduleConfig.$inject=['$routeProvider'];

    function moduleConfig($routeProvider){
        $routeProvider.when('/homePage',{
            templateUrl:'app/views/homePageTemplate.html',
            controller:'HomePageController',
            controllerAs:'hc'
        }).when('/detailsPage/:id',{
            templateUrl:'app/views/detailsPageTemplate.html',
            controller:'DetailsPageController',
            controllerAs:'dc'
        }).when('/searchPage/:type',{
            templateUrl:'app/views/searchSortPageTemplate.html',
            controller:'SearchSortController',
            controllerAs:'ssc'
        }).when('/userAccountPage',{
            templateUrl:'app/views/accountPageTemplate.html',
            controller:'AccountPageController',
            controllerAs:'ac'
        }).when('/signupPage',{
            templateUrl:'app/views/signupPageTemplate.html',
            controller:'LoginPageController',
            controllerAs:'lc'
        }).when('/loginPage',{
            templateUrl:'app/views/loginPageTemplate.html',
            controller:'LoginPageController',
            controllerAs:'lc'
        })
        .otherwise({
            redirectTo:'/loginPage'
        });
    }
    function moduleRun(){
        console.log("Message from App.module.js- .....APP STARTED.....");
    }
})();