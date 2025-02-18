<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="label.user.login" var="login"/>
<spring:message code="label.user.password" var="password"/>
<spring:message code="label.user.name" var="name"/>
<spring:message code="label.user.birthdate" var="birthdate"/>
<spring:message code="label.user.age" var="age"/>
<spring:message code="label.user.salary" var="salary"/>
<spring:message code="label.user.roles" var="roles"/>
<spring:message code="button.confirm" var="confirm"/>
<c:choose>
    <c:when test="${not empty user.id}">
        <spring:message code="title.user.edit" var="title"/>
        <c:set var="params" value="id=${user.id}"/>
    </c:when>
    <c:otherwise>
        <spring:message code="title.user.add" var="title"/>
        <c:set var="params" value="action=add"/>
    </c:otherwise>
</c:choose>
<t:myhtml title="${title}" params="${params}">
    <main>
        <section class="form form--login-edit">
            <h1>${title}</h1>
            <c:url value="/loginedit.jhtml?id=${user.id}" var="action"/>
            <form:form action="${action}" method="post" modelAttribute="user">
                <spring:bind path="login">
                    <div class="form__group">
                        <form:label path="login">${login}</form:label>
                        <form:input path="login" value="${user.login}"/>
                        <form:errors path="login" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="password">
                    <div class="form__group">
                        <form:label path="password">${password}</form:label>
                        <form:password path="password" value="${user.password}"/>
                        <form:errors path="password" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="name">
                    <div class="form__group">
                        <form:label path="name">${name}</form:label>
                        <form:input path="name" value="${user.name}"/>
                        <form:errors path="name" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="birthdate">
                    <div class="form__group">
                        <form:label path="birthdate">${birthdate}</form:label>
                        <form:input path="birthdate" value="${user.birthdate}"/>
                        <form:errors path="birthdate" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="age">
                    <div class="form__group">
                        <form:label path="age">${age}</form:label>
                        <form:input path="age" value="${user.age}"/>
                        <form:errors path="age" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="salary">
                    <div class="form__group">
                        <form:label path="salary">${salary}</form:label>
                        <form:input path="salary" value="${user.salary}"/>
                        <form:errors path="salary" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <spring:bind path="roles">
                    <div class="form__group">
                        ${roles}
                        <c:forEach var="role" items="${rolesList}">
                            <div>
                                <form:label path="roles"><spring:message code="${role}"/></form:label>
                                <form:checkbox cssClass="checkbox" path="roles" value="${role}"/>
                            </div>
                        </c:forEach>
                        <form:errors path="roles" cssClass="${status.error ? 'error--visible' : 'error'}"/>
                    </div>
                </spring:bind>
                <button type="submit">${confirm}</button>
            </form:form>
        </section>
    </main>
</t:myhtml>

