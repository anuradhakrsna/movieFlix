(function () {
    'use strict'

    angular.module('movieflixApp').controller('AccountPageController', AccountPageController);

    AccountPageController.$inject = ['accountPageService', '$rootScope', '$location'];
    function AccountPageController(accountPageService, $rootScope, $location) {
        var vm = this;

        vm.updatingUser = function () {
            var reqBody = {
                id: vm.userId,
                firstName: vm.userDetail.firstName,
                lastName: vm.userDetail.lastName,
                email: vm.userDetail.email,
                username: vm.userDetail.email,
                password: vm.userDetail.password
            }

            accountPageService.updatingExistingUser(reqBody, vm.userId).then(function (data) {
                vm.successMsg = true;
                vm.errorMsg = '';
            }, function (error) {
                vm.errorMsg = true;
                vm.cleanup = false;
            });
        }

        vm.deleteUser = function () {
            accountPageService.deletingUser(vm.userId).then(function (data) {
            }, function (error) {
                console.log(error);
            });
        };

        vm.init = function () {
            if ($rootScope.userId === undefined) {
                $location.path('/loginPage');
            }

            vm.loginPage = false;
            vm.title = "Account Page controller connected !";
            vm.templateUrl = "accountPageTemplate.html";
            vm.userId = $rootScope.userId;

            accountPageService.getUserDetails(vm.userId).then(function (data) {
                vm.userDetail = data;
                vm.userDetail.password = '';
                vm.userDetail.cpassword = vm.userDetail.password;
            });
        }

        vm.init();
    }

})();