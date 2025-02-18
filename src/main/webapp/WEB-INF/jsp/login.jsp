<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<t:headproperties title="Авторизация"/>
<body>
<main>
    <section class="form form--login">
        <h1>Авторизация</h1>
        <div class="error<c:if test="${not empty errorMessage}">--visible</c:if>">${errorMessage}</div>
        <form action="<c:url value="/login.jhtml"/>" method="post">
            <div class="form-group">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" placeholder="Логин" required>
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Пароль" required>
            </div>
            <input type="hidden" name="action" value="login">
            <button type="submit">Войти</button>
        </form>
    </section>
</main>
</body>
</html>
