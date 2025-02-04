<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="header__menu menu">
    <ul class="header__menu-list">
        <li class="header__menu-item">
            <a class="header__menu-link" href="<c:url value="/welcome.jhtml"/>">Главная</a>
        </li>
        <c:forEach var="role" items="${sessionUser.roles}">
            <c:if test="${role=='ADMIN'}">
                <li class="header__menu-item">
                    <a class="header__menu-link" href="<c:url value="/userslist.jhtml"/>">Пользователи</a>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</nav>