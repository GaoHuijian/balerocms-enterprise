<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h1>Spring MVC internationalization example</h1>

var message = ${message}

<br>

Language : <a href="?language=en">English</a>|<a href="?language=es">Español</a>

<h3>
    welcome.springmvc : <spring:message code="label.springmvc" text="default text" />
</h3>

Current Locale : ${pageContext.response.locale}

</body>
</html>