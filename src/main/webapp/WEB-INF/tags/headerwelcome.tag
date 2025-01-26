<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <a class="col header__logo" href="<c:url value="/welcome.jhtml"/>">
        <div class="logo">LOGO</div>
    </a>
    <div class="col header__welcome">
        Привет, <a href="<c:url value="/loginedit.jhtml?id=${sessionUser.id}"/>">${sessionUser.login}</a>.
        <a href="<c:url value="/logout.jhtml"/>">Выйти</a>
    </div>
</div>