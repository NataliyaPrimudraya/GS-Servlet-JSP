<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:message code="header.link.home" var="home"/>
<spring:message code="header.link.users" var="users"/>
<nav class="header__menu menu">
    <ul class="header__menu-list">
        <li class="header__menu-item">
            <a class="header__menu-link" href="<c:url value="/welcome.jhtml"/>">${home}</a>
        </li>
        <sec:authentication property="principal.authorities" var="roles"/>
        <c:forEach var="role" items="${roles}">
            <c:if test="${role=='ADMIN'}">
                <li class="header__menu-item">
                    <a class="header__menu-link" href="<c:url value="/userslist.jhtml"/>">${users}</a>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</nav>