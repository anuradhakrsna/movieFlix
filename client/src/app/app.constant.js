(function(){
    'use strict';

    angular
        .module('movieflixApp')
        .constant('CONFIG', {
            'API_HOST': 'http://localhost:8080/MovieFlix/api'
        });

})();