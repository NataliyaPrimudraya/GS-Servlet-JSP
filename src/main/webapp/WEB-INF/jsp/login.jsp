<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/styles.css"%>
    </style>
</head>
<body>
<main>
    <section class="form form--login">
        <h1>Авторизация</h1>
        <div class="error<c:if test="${not empty errorMessage}">--visible</c:if>">${errorMessage}</div>
        <form action="<c:url value="/login.jhtml"/>" method="post">
            <label for="login">Логин</label>
            <input type="text" id="login" name="login" placeholder="Логин" required>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" placeholder="Пароль" required>
            <input type="hidden" name="action" value="login">
            <button type="submit">Войти</button>
        </form>
    </section>
</main>
</body>
</html>
