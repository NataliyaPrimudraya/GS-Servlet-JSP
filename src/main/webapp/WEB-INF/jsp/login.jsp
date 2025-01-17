<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<h1>Авторизация</h1>
<form action="/webdispatch/login.jhtml" method="post">
    <input type="text" name="login" placeholder="Логин" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <input type="hidden" name="action" value="login">
    <button type="submit">Войти</button>
</form>
<c:if test="${not empty errorMessage}">
    <div>${errorMessage}</div>
</c:if>
</body>
</html>
