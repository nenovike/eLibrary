function findGetParameter(parameterName) {
    var result = null,
        tmp = [];
    location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
            tmp = item.split("=");
            if (tmp[0] === parameterName) result = '?search=' + decodeURIComponent(tmp[1]);
        });
    return result;
}

var app = angular.module('consumeRestApp', []);
app.controller("BooksCtrl", function ($scope, $http) {
    search = findGetParameter('search');
    if (search != null)
        $http.get('/admin/api/books' + search).success(function (data) {
            $scope.books = data;
        });
    else
        $http.get('/admin/api/books').success(function (data) {
            $scope.books = data;
        });
});

app.controller("UserBooksCtrl", function ($scope, $http) {
    search = findGetParameter('search');
    if (search != null)
        $http.get('/user/api/books' + search).success(function (data) {
            $scope.books = data;
        });
    else
        $http.get('/user/api/books').success(function (data) {
            $scope.books = data;
        });
});