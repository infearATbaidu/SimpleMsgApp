var ctrl = angular.module('ctrl', []);

ctrl.controller('register', function ($rootScope, $scope, $http) {
    $scope.message = '';
    $scope.name = '';
    $scope.password = '';

    $scope.register = function () {
        $scope.message = '';
        var user = new Object()
        user.name = $scope.name
        user.password = $scope.password
        user.passwordConfirm = $scope.passwordConfirm
        $http(
            {
                url: '/registerPost',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(user)
            }).success(function (r) {
            if (!r.result.success) {
                $scope.message = r.result.message;
                return;
            }
            window.location.href = '/';
        });
    }

});

ctrl.controller('login', function ($rootScope, $scope, $http) {
    $scope.message = '';
    $scope.name = '';
    $scope.password = '';

    $scope.login = function () {
        $scope.message = '';
        var user = new Object()
        user.name = $scope.name
        user.password = $scope.password
        $http(
            {
                url: '/loginPost',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(user)
            }).success(function (r) {
            if (!r.success) {
                $scope.message = r.message;
                return;
            }
            else {
                window.location.href = '/';
            }
        });
    }

    $scope.redirectToRegister = function () {
        window.location.href = '/register';
    }

});