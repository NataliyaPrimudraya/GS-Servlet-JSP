<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true" %>
<%@attribute name="headproperties" required="false" %>
<%@attribute name="params" required="false" %>
<html>
<t:headproperties title="${title}">${headproperties}</t:headproperties>
<body>
<t:header>
    <t:headerwelcome params="${params}"/>
    <hr>
    <t:headermenu/>
</t:header>
<jsp:doBody/>
<t:footer>Natali copyright 2025</t:footer>
</body>
</html>