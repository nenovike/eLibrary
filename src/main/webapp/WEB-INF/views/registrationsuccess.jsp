<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="success">
    Użytkownik ${user} został zarejestrowany!
    <br>
    <a href="<c:url value='/newUser' />">Utwórz kolejnego użytkownika</a>
    <br/>
    <a href="<c:url value='/admin' />">Panel administratora</a>
    <br/>
    <a href="<c:url value="/logout" />">Wyloguj</a>
</div>

</body>
</html>