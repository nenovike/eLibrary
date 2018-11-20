<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/static/css/app.css' />"/>
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
    </ul>
</nav>
<div class="container login-container mt-5">
    <c:url var="loginUrl" value="/login"/>
    <form action="${loginUrl}" method="post" class="form-horizontal">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Niepoprawny użytnownik lub hasło.</p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>Wylogowano pomyślnie.</p>
            </div>
        </c:if>
        <div class="input-group input-sm">
            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
            <input type="text" class="form-control" id="username" name="ssoId"
                   placeholder="Nazwa użytkownika" required>
        </div>
        <div class="input-group input-sm">
            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Hasło"
                   required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <div class="form-actions">
            <input type="submit"
                   class="btn btn-block btn-dark" value="Log in">
        </div>
    </form>
</div>
</body>
</html>