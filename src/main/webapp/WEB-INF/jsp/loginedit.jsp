<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:choose>
    <c:when test="${not empty user.id}">
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
            <c:url value="/loginedit.jhtml?id=${user.id}" var="action"/>
            <form:form action="${action}" method="post" modelAttribute="user">
                <form:label path="login">Логин</form:label>
                <form:input path="login" value="${user.login}"/>
                <form:label path="password">Пароль</form:label>
                <form:password path="password" value="${user.password}"/>
                <form:label path="name">Имя</form:label>
                <form:input path="name" value="${user.name}"/>
                <form:label path="birthdate">Дата рождения</form:label>
                <form:input path="birthdate" value="${user.birthdate}"/>
                <form:label path="age">Возраст</form:label>
                <form:input path="age" value="${user.age}"/>
                <form:label path="salary">Зарплата</form:label>
                <form:input path="salary" value="${user.salary}"/>
                <c:forEach var="role" items="${rolesList}">
                    <form:label path="roles">
                            ${role}
                        <form:checkbox path="roles" value="${role}"/>
                    </form:label>
                </c:forEach>
                <button type="submit">Подтвердить</button>
            </form:form>
        </section>
    </main>
</t:myhtml>

