<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <c:url value="/loginedit.jhtml?id=${user.id}" var="action"/>
            <form:form action="${action}" method="post" modelAttribute="user">
                <spring:bind path="login">
                    <div class="form-group">
                        <form:label path="login">Логин</form:label>
                        <form:input path="login" value="${user.login}"/>
                        <form:errors path="login" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="password">
                    <div class="form-group">
                        <form:label path="password">Пароль</form:label>
                        <form:password path="password" value="${user.password}"/>
                        <form:errors path="password" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="name">
                    <div class="form-group">
                        <form:label path="name">Имя</form:label>
                        <form:input path="name" value="${user.name}"/>
                        <form:errors path="name" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="birthdate">
                    <div class="form-group">
                        <form:label path="birthdate">Дата рождения</form:label>
                        <form:input path="birthdate" value="${user.birthdate}"/>
                        <form:errors path="birthdate" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="age">
                    <div class="form-group">
                        <form:label path="age">Возраст</form:label>
                        <form:input path="age" value="${user.age}"/>
                        <form:errors path="age" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="salary">
                    <div class="form-group">
                        <form:label path="salary">Зарплата</form:label>
                        <form:input path="salary" value="${user.salary}"/>
                        <form:errors path="salary" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="roles">
                    <div class="form-group">
                        <c:forEach var="role" items="${rolesList}">
                            <form:label path="roles">
                                ${role}
                                <form:checkbox path="roles" value="${role}"/>
                            </form:label>
                        </c:forEach>
                        <form:errors path="roles" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <button type="submit">Подтвердить</button>
            </form:form>
        </section>
    </main>
</t:myhtml>

