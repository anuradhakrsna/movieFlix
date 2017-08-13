/**
 * Created by anuradhakrsna18 on 8/12/2017.
 */
(function(){
    'use strict'

    angular.module('movieflixApp').controller('HeaderController',HeaderController);

    HeaderController.$inject=['$location'];
    function HeaderController($location) {
        var vm = this;
        vm.title="headerCtrl connected";
        // vm.currentLink="notUser";

        init();

        function init(){
            console.log("HEADER CONTROLLER----------------------------------");
            console.log($location.host());
            console.log($location.path());
            console.log("********************----------------------------------");

        }

    }
})();