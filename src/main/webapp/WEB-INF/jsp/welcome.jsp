<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<h1>Главная страница приложения</h1>
<form action="<c:url value="/logout.jhtml"/>" method="get">
    <input type="hidden" name="action" value="logout">
    <button type="submit">Выйти</button>
</form>
<form action="<c:url value="/loginedit.jhtml"/>">
    <button>Изменить пароль</button>
</form>
</body>
</html>
