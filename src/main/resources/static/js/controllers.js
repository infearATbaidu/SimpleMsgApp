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
            if (!r.success) {
                $scope.message = r.message;
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
            window.location.href = '/';
        });
    }

    $scope.redirectToRegister = function () {
        window.location.href = '/register';
    }

});

ctrl.controller('index', function ($rootScope, $scope, $http) {
    $scope.searchResults = []
    $scope.contacts = []
    //搜索用户
    $scope.search = function () {
        var seReq = new Object()
        seReq.name = $scope.usernameToSearch
        $http(
            {
                url: '/search',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(seReq)
            }).success(function (r) {
            if (!r) {
                $scope.message = 'No match result';
                return;
            }
            $scope.searchResults = r;
        });
    }

    $scope.addContact = function (userId) {
        var addContactReq = new Object();
        addContactReq.userId = userId;
        $http(
            {
                url: '/addContact',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(addContactReq)
            }).success(function (r) {
            if (!r) {
                $scope.message = 'Add Contact Failed';
                return;
            }
        });
    }

    $scope.contactList = function () {
        $scope.contacts = [];
        $http(
            {
                url: '/contactList',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {}
            }).success(function (r) {
            if (r.length != 0) {
                $scope.contacts = r;
            }
        });
    }

    $scope.contactList()
});