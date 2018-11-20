<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration Form</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>

<body>

<div class="form-container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item mr-2">
                <a href="/logout" class="nav-link">Wyloguj</a>
            </li>
            <li class="nav-item mr-2">
                <a href="/" class="nav-link">Strona Główna</a>
            </li>
            <c:set var="contains" value="true"/>
            <c:forEach var="role" items="${userRoles}">
                <c:if test="${role eq 'USER'}">
                    <li class="nav-item mr-2">
                        <a href="/user" class="nav-link">Panel Użytkownika</a>
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

    <h1>Dodaj nowego użytkownika</h1>

    <form:form method="POST" modelAttribute="user" class="form-horizontal">

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="firstName">Imię</label>
                <div class="col-md-7">
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="lastName">Nazwisko</label>
                <div class="col-md-7">
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="ssoId">Login</label>
                <div class="col-md-7">
                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="ssoId" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Hasło</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="email">Email</label>
                <div class="col-md-7">
                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="userProfiles">Role</label>
                <div class="col-md-7">
                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type"
                                 class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="userProfiles" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Register" class="btn btn-primary btn-sm"> or <a
                    href="<c:url value='/admin/newUser' />">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>