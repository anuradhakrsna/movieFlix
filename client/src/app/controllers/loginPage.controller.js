/**
 * Created by anuradhakrsna18 on 8/11/2017.
 */
(function () {
    'use strict'

    angular.module('movieflixApp').controller('LoginPageController', LoginPageController);

    LoginPageController.$inject = ['loginPageService', '$location', '$rootScope'];
    function LoginPageController(loginPageService, $location, $rootScope) {

        var self = this;

        self.loginCheck = loginCheck;
        self.successMsg = false;
        self.errorMsg = false;

        self.init = function() {
            $rootScope.userId = undefined;
        };

        self.init();

        self.addUser = function () {
            var reqBody = {
                firstName: self.user.firstName,
                lastName: self.user.lastName,
                email: self.user.email,
                username: self.user.email,
                password: self.user.password
            }

            loginPageService.addNewUser(reqBody)
                .then(function (data) {
                    self.user = null;
                    self.successMsg = true;
                    self.errorMsg = false;
                }, function (error) {
                    self.successMsg = false;
                    self.errorMsg = true;
                    self.cleanup = false;
                });
        }

        function loginCheck() {
            var reqBody = {
                firstName: null,
                lastName: null,
                email: self.login.username,
                username: null,
                password: self.login.password
            }

            loginPageService.verifyUser(reqBody)
                .then(function (data) {
                    if(undefined === data.username) {
                        self.ErrorMsg = 'User Credentials Invalid';
                    }
                    else {
                        self.ErrorMsg = '';
                        $rootScope.userId = data.id;
                        $location.path('/homePage');
                    }

                }, function (error) {
                    self.ErrorMsg = 'User Credentials Invalid';
                });
        }

    }


})();