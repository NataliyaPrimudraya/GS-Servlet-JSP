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
                <label for="name">Имя</label>
                <input type="text" id="name" name="name" value="${user.name}" placeholder="Имя">
                <label for="birthdate">Дата рождения</label>
                <input type="date" id="birthdate" name="birthdate" value="${user.birthdate}"
                       placeholder="Дата рождения">
                <label for="age">Возраст</label>
                <input type="number" id="age" name="age" value="${user.age}">
                <label for="salary">Зарплата</label>
                <input type="number" id="salary" name="salary" value="${user.salary}">

                <c:forEach var="role" items="${rolesList}">
                    <c:set var="checked" value="false"/>
                    <c:forEach var="userRole" items="${user.roles}">
                        <c:if test="${userRole == role}">
                            <c:set var="checked" value="true"/>
                        </c:if>
                    </c:forEach>
                    <label>
                        ${role}
                        <input type="checkbox" id="${role}Role" name="roles" value="${role}" <c:if test="${checked == 'true'}">checked</c:if>>
                    </label>

                </c:forEach>

                <button type="submit">Подтвердить</button>
            </form>
        </section>
    </main>
</t:myhtml>

