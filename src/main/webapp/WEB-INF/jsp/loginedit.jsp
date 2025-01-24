<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование профиля"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление пользователя"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <title>${title}</title>
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

    <section class="form form--login-edit">
        <h1>${title}</h1>
        <form action="<c:url value="/loginedit.jhtml?id=${user.id}"/>" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <label for="login">Логин</label>
            <input type="text" id="login" name="login" value="${user.login}" placeholder="Логин" required>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" value="${user.password}" required>
            <label for="email">Электронная почта</label>
            <input type="email" id="email" name="email" value="${user.email}" placeholder="Email" required>
            <label for="surname">Фамилия</label>
            <input type="text" id="surname" name="surname" value="${user.surname}" placeholder="Фамилия" required>
            <label for="name">Имя</label>
            <input type="text" id="name" name="name" value="${user.name}" placeholder="Имя" required>
            <label for="patronymic">Отчество</label>
            <input type="text" id="patronymic" name="patronymic" value="${user.patronymic}" placeholder="Отчество" required>
            <label for="birthdate">Дата рождения</label>
            <input type="date" id="birthdate" name="birthdate" value="${user.birthdate}" placeholder="Дата рождения" required>
            <label for="role">Роль</label><br>
            <select id="role" name="role" required>
                <option value="${user.role}" selected hidden>
                    <c:choose>
                        <c:when test="${user.role == 'ADMIN'}">
                            Администратор
                        </c:when>
                        <c:when test="${user.role == 'USER'}">
                            Пользователь
                        </c:when>
                    </c:choose></option>
                <option value="USER">Пользователь</option>
                <option value="ADMIN">Администратор</option>
            </select>
            <button type="submit">Подтвердить</button>
        </form>
    </section>
</main>
<footer>
    <div class="footer__inner">
        Natali copyright 2025
    </div>
</footer>
</body>
</html>
