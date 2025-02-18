<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="title.home" var="title"/>
<t:myhtml title="${title}">
    <main>
        <h1><spring:message code="home.message"/></h1>
    </main>
</t:myhtml>

