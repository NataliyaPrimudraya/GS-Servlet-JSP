<%@tag pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        <%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/styles.css"%>
    </style>
    <jsp:doBody/>
</head>