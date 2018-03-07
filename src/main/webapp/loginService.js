(function () {

    angular.module("PlagsafeApp")
        .factory("LoginService", loginService);

    function loginService($http) {
        var api = {
            "findUser" : findUser
        };
        return api;

        function findUser(username, password) {
            return $http.get("/logincheck?name="+ username+ "&password="+ password)
                .then(function (value) {
                    return value;
                },function (reason) {
                    console.log(reason)
                });
        }

    }
})();