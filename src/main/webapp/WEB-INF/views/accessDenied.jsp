<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AccessDenied page</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
Użytkownik <strong>${user}</strong> nie ma dostępu do tej zawartości.
<br/>
<a href="<c:url value="/home" />">Powrót do strony głównej</a> lub <a href="<c:url value="/logout" />">Wyloguj</a>
</body>
</html>