var ctrl = angular.module('ctrl', []);

ctrl.config(function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
});

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

ctrl.controller('index', function ($rootScope, $scope, $http, $window, ws) {
    $scope.userId = '';
    $scope.searchResults = []
    $scope.contacts = []
    $scope.msgCnt = new Map();
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

    $scope.updateContact = function (userId, flag) {
        var addContactReq = new Object();
        addContactReq.userId = userId;
        addContactReq.isAdd = flag;
        $http(
            {
                url: '/updateContact',
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
                $scope.msgCnt.clear();
                for (i in $scope.contacts) {
                    $scope.msgCnt.set($scope.contacts[i].id, $scope.contacts[i]);
                }
            }
        });
    }

    $scope.initStompClient = function () {
        ws.init('/ws');

        ws.connect(function (frame) {
            ws.subscribe("/app/contactList?id=" + $scope.userId, function (message) {
                $scope.contactList();
            });

            ws.subscribe("/app/unreadMsgCnt?id=" + $scope.userId, function (message) {
                var r = JSON.parse(message.body);
                if (r.resetFlag) {
                    $scope.msgCnt.get(r.srcId).unreadMsgCnt = 0;
                }
                else {
                    $scope.msgCnt.get(r.srcId).unreadMsgCnt++;
                }
            });

        });

    }

    $scope.init = function () {
        $http(
            {
                url: '/getUserInfo',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {}
            }).success(function (r) {
            if (r) {
                $scope.userId = r;
                $scope.contactList();
                $scope.initStompClient();
            }
        });

    }

    $scope.startChart = function (user) {
        $window.open('/chart?id=' + user.id);
    }
});

ctrl.controller('chat', function ($rootScope, $scope, $http, $location, ws) {
    $scope.userId = '';
    $scope.userName = '';
    $scope.userId2Chat = $location.search().id;
    $scope.username2Chat = ''

    $scope.chatUserInfo = new Map();

    $scope.msgs = [];
    $scope.newMsg = '';

    $scope.init = function () {
        $http(
            {
                url: '/getChartInfo',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"userId2Chat": $scope.userId2Chat}
            }).success(function (r) {
            if (r) {
                $scope.userId = r.srcId;
                $scope.userName = r.srcName;
                $scope.userId2Chat = r.destId;
                $scope.username2Chat = r.destName;
                $scope.chatUserInfo.set($scope.userId, $scope.userName);
                $scope.chatUserInfo.set($scope.userId2Chat, $scope.username2Chat);
                $scope.queryMsgHistory();
                $scope.initStompClient();
            }
        });


    }

    $scope.queryMsgHistory = function () {
        $http(
            {
                url: '/queryMsgHistory',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: {"srcId": $scope.userId, "destId": $scope.userId2Chat}
            }).success(function (r) {
            if (r) {
                $scope.msgs = r;
            }
        });
    }

    $scope.sendMsg = function () {
        var msg = new Object();
        msg.srcId = $scope.userId;
        msg.srcName = $scope.userName;
        msg.destId = $scope.userId2Chat;
        msg.destName = $scope.username2Chat;
        msg.content = $scope.newMsg;
        $http(
            {
                url: '/sendMsg',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(msg)
            }).success(function (r) {
            if (r) {
                $scope.msgs.push(r);
            }
        });

        $scope.newMsg = '';
    }

    $scope.initStompClient = function () {
        ws.init('/ws');

        ws.connect(function (frame) {
            ws.subscribe("/app/msgList?id=" + $scope.userId, function (message) {
                $scope.msgs.push(JSON.parse(message.body));
            });

        });
    }

    $scope.delMsg = function (index) {
        var req = new Object();
        req.msgId = $scope.msgs[index].id;
        req.srcId = $scope.msgs[index].srcId;
        $http(
            {
                url: '/deleteMsg',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(req)
            }).success(function (r) {
            if (r) {
                $scope.msgs.splice(index, 1);
            }
        });
    }

});