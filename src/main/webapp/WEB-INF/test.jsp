<%--
  Created for BaleroCMS.
  User: Anibal Gomez
  Date: 18/07/14
  Time: 01:20 PM
  Eternity Template (balerocms.com).
--%>
<!--
Unit Test View Page
-->
<%@ taglib prefix="slg" uri="http://github.com/slugify" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hibernate Unit Test</title>
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
	<h1>Hibernate users</h1>
	<ul>
		<c:forEach var="p" items="${users}">
			<li>${p.id} - ${p.name} - ${p.email}</li>
            <form method="GET" action="/delete/${p.id}">
                <input type="submit" value="Delete">
            </form>
		</c:forEach>
	</ul>
    <form method="GET" action="/add">
        <input type="submit" value="Add">
    </form>
    <!-- Slug - Result: hello-world -->
    ${slg:slugify('Hello, world Im a slug!')}
</body>
</html>