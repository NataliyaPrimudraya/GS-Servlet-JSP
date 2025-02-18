<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="title.authorization" var="title"/>
<spring:message code="label.login" var="login"/>
<spring:message code="label.password" var="password"/>
<spring:message code="button.enter" var="enter"/>
<html>
<t:headproperties title="${title}"/>
<body>
<main>
    <t:languages/>
    <section class="form form--login">
        <h1>${title}</h1>
        <div class="error<c:if test="${not empty errorMessage}">--visible</c:if>">${errorMessage}</div>
        <form action="<c:url value="/login.jhtml"/>" method="post">
            <div class="form__group">
                <label for="login">${login}</label>
                <input type="text" id="login" name="login" placeholder="${login}" required>
            </div>
            <div class="form__group">
                <label for="password">${password}</label>
                <input type="password" id="password" name="password" placeholder="${password}" required>
            </div>
            <input type="hidden" name="action" value="login">
            <button type="submit">${enter}</button>
        </form>
    </section>
</main>
</body>
</html>
