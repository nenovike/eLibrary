var app = angular.module('consumeRestApp', []);
app.controller("BooksCtrl", function ($scope, $http) {
    $http.get('/admin/api/books').success(function (data) {
        $scope.books = data;
    });
});

app.controller("UserBooksCtrl", function ($scope, $http) {
    $http.get('/user/api/books').success(function (data) {
        $scope.books = data;
    });
});