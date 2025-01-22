<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Смена пароля</title>
</head>
<body>
<h1>Введите новый пароль</h1>
<form action="<c:url value="/loginedit.jhtml"/>" method="post">
  <input type="password" name="password" placeholder="Новый пароль" required>
  <button type="submit">Подтвердить</button>
</form>
</body>
</html>
