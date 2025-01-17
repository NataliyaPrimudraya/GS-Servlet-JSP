<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Главная</title>
</head>
<body>
<h1>Главная страница приложения</h1>
<form action="/webdispatch/logout.jhtml" method="post">
    <input type="hidden" name="action" value="logout">
    <button type="submit">Выйти</button>
</form>
<form action="/webdispatch/loginedit.jhtml">
    <button>Изменить пароль</button>
</form>
</body>
</html>
