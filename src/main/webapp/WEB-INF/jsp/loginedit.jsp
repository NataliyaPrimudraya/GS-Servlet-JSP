<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Смена пароля</title>
</head>
<body>
<h1>Введите новый пароль</h1>
<form action="/webdispatch/loginedit.jhtml" method="post">
  <input type="password" name="password" placeholder="Новый пароль" required>
  <button type="submit">Подтвердить</button>
</form>
</body>
</html>
