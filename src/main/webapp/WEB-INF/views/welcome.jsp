<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome page</title>
    <script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script defer src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <ul class="navbar-nav">
        <li class="nav-item mr-2">
            <c:choose>
                <c:when test="${user eq 'anonymousUser'}">
                    <a href="/login" class="nav-link">Zaloguj</a>
                </c:when>
                <c:otherwise>
                    <a href="/logout" class="nav-link">Wyloguj</a>
                </c:otherwise>
            </c:choose>
        </li>
        <li class="nav-item active mr-2">
            <a class="nav-link" href="/">Strona Główna<span class="sr-only">(current)</span></a>
        </li>
        <c:set var="contains" value="true"/>
        <c:forEach var="role" items="${roles}">
            <c:if test="${role eq 'USER'}">
                <li class="nav-item mr-2">
                    <a href="/user/books" class="nav-link">Panel Użytkownika</a>
                </li>
            </c:if>
        </c:forEach>
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
<c:if test="${param.addSuccess != null}">
    <div class="alert alert-success">
        <p>Pomyślnie utworzono użytkownika!</p>
    </div>
</c:if>
<div class="container pt-5 pl-5">
    <h1 class="mt-5">Internetowy system obsługi biblioteki</h1>
    <h2 class="mt-5">Autor: Konrad Snopkowski</h2>
</div>
</body>
</html>