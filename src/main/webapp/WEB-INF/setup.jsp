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
    <title><spring:message code="label.setup.title" /></title>
    <c:if test="${sucess == true}">
    <meta http-equiv="refresh" content="15; url=<c:url value="/" />">
    </c:if>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <script src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap-progressbar.js" />"></script>
    <link rel="shortcut icon" href="<c:url value="/resources/images/setup.ico" />" />
    <style>
    .center {
        width: 70%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 5%;
    }
    .progress .progress-bar.six-sec-ease-in-out {
        -webkit-transition: width 12s ease-in-out;
        -moz-transition: width 12s ease-in-out;
        -ms-transition: width 12s ease-in-out;
        -o-transition: width 12s ease-in-out;
        transition: width 12s ease-in-out;
    }
</style>
</head>
<body>


<div class="center">

    <p class="pull-right">
        <a href="?lang=en">en</a> | <a href="?lang=es">es</a>
    </p>

    <p align="center">
        <img src="<c:url value="/resources/images/logo.png" />">
    </p>

    <p></p>

    <c:if test="${sucess == false}">

    <div class="alert alert-info" role="alert">
        <spring:message code="label.setup.alert" />
    </div>

    <form method="post" action="/setup/install" class="form-horizontal" role="form">
        <div class="form-group form-group-sm">
            <button type="submit" class="btn btn-primary pull-right">
                <spring:message code="label.setup.btn" />
            </button>
        </div>
    </form>
    </c:if>

    <c:if test="${sucess == true}">
    <div class="progress">
        <div class="progress-bar six-sec-ease-in-out" role="progressbar" aria-valuetransitiongoal="100"></div>
    </div>
    </c:if>

</div>

<p class="text-center">
    <a href="http://www.balerocms.com/">BaleroCMS Enterprise.</a>
</p>

<script>
    $('.progress-bar').progressbar();
</script>

</body>
</html>