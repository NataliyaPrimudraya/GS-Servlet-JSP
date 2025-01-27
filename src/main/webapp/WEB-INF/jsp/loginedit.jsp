<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:choose>
    <c:when test="${not empty user}">
        <c:set var="title" value="Редактирование профиля"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление пользователя"/>
    </c:otherwise>
</c:choose>
<t:myhtml title="${title}">
    <main>
        <section class="form form--login-edit">
            <h1>${title}</h1>
            <div class="error<c:if test="${not empty errorMessages}">--visible</c:if>">
                    <c:forEach var="message" items="${errorMessages}">
                        ${message}<br>
                    </c:forEach>
            </div>
            <form action="<c:url value="/loginedit.jhtml?id=${user.id}"/>" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <label for="login">Логин</label>
                <input type="text" id="login" name="login" value="${user.login}" placeholder="Логин">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" value="${user.password}">
                <label for="email">Электронная почта</label>
                <input type="text" id="email" name="email" value="${user.email}" placeholder="Email">
                <label for="surname">Фамилия</label>
                <input type="text" id="surname" name="surname" value="${user.surname}" placeholder="Фамилия">
                <label for="name">Имя</label>
                <input type="text" id="name" name="name" value="${user.name}" placeholder="Имя">
                <label for="patronymic">Отчество</label>
                <input type="text" id="patronymic" name="patronymic" value="${user.patronymic}" placeholder="Отчество">
                <label for="birthdate">Дата рождения</label>
                <input type="date" id="birthdate" name="birthdate" value="${user.birthdate}"
                       placeholder="Дата рождения">
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
</t:myhtml>

