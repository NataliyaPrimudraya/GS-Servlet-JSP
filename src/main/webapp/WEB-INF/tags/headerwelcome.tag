<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="params" required="false" %>
<spring:message code="header.welcome" var="welcome"/>
<spring:message code="header.link.logout" var="logout"/>
<div class="row">
    <a class="col header__logo" href="<c:url value="/welcome.jhtml"/>">
        <div class="logo">LOGO</div>
    </a>
    <div class="col header__welcome">
        ${welcome}, <sec:authentication property="name"/>.
            <form action="<c:url value="/logout.jhtml"/>" method="post" class="link-form">
                <button type="submit" class="link-button">${logout}</button>
            </form> <br>
        <t:languages params="${params}"/>
    </div>
</div>