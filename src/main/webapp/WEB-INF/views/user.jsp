<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Książki</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script defer src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
    <script defer src="/js/consume-rest.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body ng-app='consumeRestApp'>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav">
        <li class="nav-item mr-2">
            <a href="/logout" class="nav-link">Wyloguj</a>
        </li>
        <li class="nav-item mr-2">
            <a href="/" class="nav-link">Strona Główna<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item mr-2">
            <a href="/user" class="nav-link active">Panel Użytkownika<span class="sr-only">(current)</span></a>
        </li>
        <c:set var="contains" value="true"/>
        <c:forEach var="role" items="${roles}">
            <c:if test="${role eq 'ADMIN'}">
                <li class="nav-item dropdown">

                    <a class="nav-link dropdown-toggle" href="#" id="adminPanelDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Panel Administatora</a>
                    <div class="dropdown-menu" aria-labelledby="adminPanelDropdown">
                        <a class="dropdown-item" href="/admin/newUser">Dodaj użytkownika</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/admin/books">Przeglądaj książki</a>
                    </div>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</nav>

<div class="table-container container" ng-controller='UserBooksCtrl'>
    <!-- Table -->
    <table class="table">
        <thead>
        <th scope="col" class="col-auto">#</th>
        <th scope="col" class="col-md-auto">Tytuł</th>
        <th scope="col" class="col-md-auto">ISBN</th>
        <th scope="col" class="col-md-auto">Autor</th>
        <th scope="col" class="col-md-auto">Gatunek</th>
        <th scope="col" class="col-md-auto">Wydawnictwo</th>
        </thead>
        <tr ng-repeat="book in books">
            <th scope="row"> {{$index+1}}</th>
            <td>{{book.name}}</td>
            <td>{{book.isbn}}</td>
            <td>
                <div ng-repeat="author in book.authors">{{author.firstName}} {{author.lastName}}</div>
            </td>
            <td>
                <div ng-repeat="genre in book.genres">{{genre.name}}</div>
            </td>
            <td>{{book.publisher.name}}</td>
        </tr>
    </table>
</div>
</body>
</html>