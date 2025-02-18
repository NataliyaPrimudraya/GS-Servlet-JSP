<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="title.users" var="title"/>
<spring:message code="label.user.login" var="login"/>
<spring:message code="label.user.password" var="password"/>
<spring:message code="label.user.name" var="name"/>
<spring:message code="label.user.birthdate" var="birthdate"/>
<spring:message code="label.user.age" var="age"/>
<spring:message code="label.user.salary" var="salary"/>
<spring:message code="label.user.roles" var="roles"/>
<spring:message code="button.confirm" var="confirm"/>
<spring:message code="button.add.user" var="add"/>
<spring:message code="tooltip.delete" var="delete"/>
<spring:message code="tooltip.edit" var="edit"/>
<t:myhtml title="${title}">
    <main>
        <table class="users-list">
            <thead>
            <tr>
                <th>ID</th>
                <th>${login}</th>
                <th>${name}</th>
                <th>${age}</th>
                <th>${birthdate}</th>
                <th>${salary}</th>
                <th>${roles}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <th>${user.id}</th>
                    <th>${user.login}</th>
                    <th>${user.name}</th>
                    <th>${user.age}</th>
                    <th>${user.birthdate}</th>
                    <th>${user.salary}</th>
                    <th>
                        <c:forEach var="role" items="${user.roles}">
                            <spring:message code="${role}"/> <br>
                        </c:forEach>
                    </th>
                    <th><a class="icon-link" title="${edit}"
                           href="<c:url value="/loginedit.jhtml?id=${user.id}"/>">&#9881;</a></th>
                    <th><a class="icon-link" title="${delete}"
                           href="<c:url value="/loginedit.jhtml?id=${user.id}&action=delete"/>">&#128465;</a></th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="add-button">
            <button>
                <a href="<c:url value="/loginedit.jhtml?action=add"/>">${add}</a>
            </button>
        </div>
    </main>
</t:myhtml>
