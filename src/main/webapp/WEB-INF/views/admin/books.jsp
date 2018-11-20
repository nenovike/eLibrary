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
            <a href="/" class="nav-link">Strona Główna</a>
        </li>
        <c:set var="contains" value="true"/>
        <c:forEach var="role" items="${roles}">
            <c:if test="${role eq 'USER'}">
                <li class="nav-item mr-2">
                    <a href="/user" class="nav-link">Panel Użytkownika</a>
                </li>
            </c:if>
        </c:forEach>
        </li>
        <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="adminPanelDropdown" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">Panel Administatora<span class="sr-only">(current)</span></a>
            <div class="dropdown-menu" aria-labelledby="adminPanelDropdown">
                <a class="dropdown-item" href="/admin/newUser">Dodaj użytkownika</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item active btn-dark" href="/admin/books">Przeglądaj książki</a>
            </div>
        </li>
    </ul>
</nav>

<div class="table-container container" ng-controller='BooksCtrl'>
    <c:if test="${param.addSuccess != null}">
        <div class="alert alert-success">
            <p>Książka dodana pomyślnie.</p>
        </div>
    </c:if>
    <c:if test="${param.deleteSuccess != null}">
        <div class="alert alert-success">
            <p>Książka usunięta pomyślnie.</p>
        </div>
    </c:if>
    <c:if test="${param.deleteFault != null}">
        <div class="alert alert-danger">
            <p>Nie udało się usunąć książki.</p>
        </div>
    </c:if>
    <c:if test="${param.returnSuccess != null}">
        <div class="alert alert-success">
            <p>Książka zwrócona pomyślnie.</p>
        </div>
    </c:if>
    <c:if test="${param.borrowSuccess != null}">
        <div class="alert alert-success">
            <p>Książka wypożyczona pomyślnie.</p>
        </div>
    </c:if>
    <a class="btn btn-success" href="/admin/newBook">Dodaj książkę</a>

    <!-- Table -->
    <table class="table">
        <thead>
        <th scope="col" class="col-auto">#</th>
        <th scope="col" class="col-md-auto">Id</th>
        <th scope="col" class="col-md-auto">Tytuł</th>
        <th scope="col" class="col-md-auto">ISBN</th>
        <th scope="col" class="col-md-auto">Autor</th>
        <th scope="col" class="col-md-auto">Gatunek</th>
        <th scope="col" class="col-md-auto">Wydawnictwo</th>
        <th scope="col" class="col-md-auto">Akcje</th>
        </thead>
        <tr ng-repeat="book in books">
            <th scope="row"> {{$index+1}}</th>
            <td>{{book.id}}</td>
            <td>{{book.name}}</td>
            <td>{{book.isbn}}</td>
            <td>
                <div ng-repeat="author in book.authors">{{author.firstName}} {{author.lastName}}</div>
            </td>
            <td>
                <div ng-repeat="genre in book.genres">{{genre.name}}</div>
            </td>
            <td>{{book.publisher.name}}</td>
            <td>
                <A ng-if="book.currentBorrowId != null" class="btn btn-warning"
                   HREF="/admin/returnBook?id={{book.currentBorrowId}}">Zwróć</A>
                <button ng-if="book.currentBorrowId == null" type="button" class="btn btn-primary" data-toggle="modal"
                        data-target=".bd-example-modal-lg{{book.id}}">Wypożycz
                </button>
                <A ng-if="book.currentBorrowId == null" class="btn btn-danger" HREF="/admin/deleteBook?id={{book.id}}">Usuń</A>
                <div class="modal fade bd-example-modal-lg{{book.id}}" tabindex="-1" role="dialog"
                     aria-labelledby="Wypożycz książkę" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="form-container">
                                <form:form action="/admin/borrow" method="POST" modelAttribute="borrow"
                                           class="form-horizontal">
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label class="col-md-3 control-lable" for="user">Użytkownik</label>
                                            <div class="col-md-7">
                                                <form:select path="user" items="${users}" itemValue="id"
                                                             itemLabel="ssoId"
                                                             class="form-control input-sm"/>
                                                <form:input type="hidden" path="book" value="{{book.id}}"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <input type="submit" value="Wypożycz" class="btn btn-dark btn-sm">
                                            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                                                Zamknij
                                            </button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>