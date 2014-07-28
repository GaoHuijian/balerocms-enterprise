<%--
  Created for BaleroCMS.
  User: Anibal Gomez
  Date: 18/07/14
  Time: 01:20 PM
  Eternity Template (balerocms.com).
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome - Setup Wizard</title>
    <c:if test="${sucess == true}">
    <meta http-equiv="refresh" content="15; url=<c:url value="/" />">
    </c:if>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <script src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <style>
        .alert {
            width: 90%;
            margin-left: auto;;
            margin-right: auto;
        }
    </style>
</head>
<body>

<div class="center">

    <p align="center">
        <img src="<c:url value="/resources/images/logo.png" />">
    </p>

    <p></p>

    <div class="alert alert-success" role="alert">
        <b>Well done!</b> System is already installed.
    </div>

</div>

<p class="text-center">
    <a href="http://www.balerocms.com/">BaleroCMS Enterprise.</a>
</p>

</body>
</html>