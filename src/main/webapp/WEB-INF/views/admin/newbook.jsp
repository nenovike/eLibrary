<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dodaj książkę</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>

<body>

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
                    <a href="/user/books" class="nav-link">Panel Użytkownika</a>
                </li>
            </c:if>
        </c:forEach>
        <li class="nav-item active dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="adminPanelDropdown" role="button"
               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Panel Administatora<span
                    class="sr-only">(current)</span></a>
            <div class="dropdown-menu" aria-labelledby="adminPanelDropdown">
                <a class="dropdown-item active btn-dark" href="/admin/newUser">Dodaj użytkownika</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/admin/books">Przeglądaj książki</a>
            </div>
        </li>
    </ul>
</nav>

<div class="container pt-5">
    <h1>Dodaj nową książkę</h1>
    <div class="form-container">

        <form:form method="POST" modelAttribute="book" class="form-horizontal">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="name">Tytuł</label>
                    <div class="col-md-7">
                        <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="name" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="isbn">ISBN</label>
                    <div class="col-md-7">
                        <form:input type="text" path="isbn" id="isbn" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="isbn" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="authorFirstName">Autor Imię</label>
                    <label class="col-md-3 control-lable" for="authorLastName">Autor Nazwisko</label>
                    <div>
                        <div class="col-md-3 d-inline-block">
                            <form:input type="text" path="authorFirstName" id="authorFirstName"
                                        class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="authorFirstName" class="help-inline"/>
                            </div>
                        </div>
                        <div class="col-md-4 d-inline-block">
                            <form:input type="text" path="authorLastName" id="authorLastName"
                                        class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="authorLastName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="genreDaos">Gatunek</label>
                    <div class="col-md-7">
                        <form:select path="genreDaos" items="${genres}" multiple="true" itemValue="id" itemLabel="name"
                                     class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="genreDaos" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="publisher">Wydawnictwo</label>
                    <div class="col-md-7">
                        <form:input type="text" path="publisher" id="publisher" class="form-control input-sm"/>
                        <div class="has-error">
                            <form:errors path="publisher" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <input type="submit" value="Dodaj" class="btn btn-dark btn-sm">
                    <a href="<c:url value='/admin/books' />" class="btn btn-secondary btn-sm">Anuluj</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>