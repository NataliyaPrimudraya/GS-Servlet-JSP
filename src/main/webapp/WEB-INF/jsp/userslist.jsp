<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/styles.css"%>
    </style>
</head>
<body>
<header>
    <div class="header__inner">
        <div class="row">
            <a class="col header__logo" href="<c:url value="/welcome.jhtml"/>">
                <div class="logo">LOGO</div>
            </a>
            <div class="col header__welcome" >
                Привет, <a href="<c:url value="/loginedit.jhtml?id=${sessionUser.id}"/>">${sessionUser.login}</a>.
                <a href="<c:url value="/logout.jhtml"/>">Выйти</a>
            </div>
        </div>
        <hr>
        <nav class="header__menu menu">
            <ul class="header__menu-list">
                <li class="header__menu-item">
                    <a class="header__menu-link" href="<c:url value="/welcome.jhtml"/>">Главная</a>
                </li>
                <c:if test="${sessionUser.role=='ADMIN'}">
                    <li class="header__menu-item">
                        <a class="header__menu-link" href="<c:url value="/userslist.jhtml"/>">Пользователи</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>
<main>
    <table class="users-list">
        <thead>
        <tr>
            <th>ID</th>
            <th>Логин</th>
            <th>Email</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Дата рождения</th>
            <th>Роль</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <th>${user.id}</th>
                <th>${user.login}</th>
                <th>${user.email}</th>
                <th>${user.surname}</th>
                <th>${user.name}</th>
                <th>${user.patronymic}</th>
                <th>${user.birthdate}</th>
                <th>${user.role}</th>
                <th><a class="icon-link" title="редактировать" href="<c:url value="/loginedit.jhtml?id=${user.id}"/>">&#9881;</a></th>
                <th><a class="icon-link" title="удалить" href="<c:url value="/loginedit.jhtml?id=${user.id}&action=delete"/>">&#128465;</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="add-button">
        <button>
            <a href="<c:url value="/loginedit.jhtml?action=add"/>">Добавить пользователя</a>
        </button>
    </div>
</main>
<footer>
    <div class="footer__inner">
        Natali copyright 2025
    </div>
</footer>
</body>
</html>
