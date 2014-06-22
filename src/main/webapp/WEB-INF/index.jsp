<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <script src="<c:url value="/resources/js/jquery-1.9.1.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <!-- bxSlider Javascript file -->
    <script src="<c:url value="/resources/js/jquery.bxslider.min.js" />"></script>
    <!-- bxSlider CSS file -->
    <link href="<c:url value="/resources/css/jquery.bxslider.css" />" rel="stylesheet" />
    <!-- Eternity template CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/eternity.css" />">
</head>
<body>

<ul class="bxslider">
    <li><img src="<c:url value="/resources/images/pic1.jpg" />"></li>
    <li><img src="<c:url value="/resources/images/pic2.jpg" />"></li>
    <li><img src="<c:url value="/resources/images/pic3.jpg" />"></li>
    <li><img src="<c:url value="/resources/images/pic4.jpg" />"></li>
</ul>

<div class="menu">

    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Menu</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li><a href="#">Link</a></li>
                    <li><a href="#" id="add" data-toggle="modal" data-target="#NewPageModal"><span class="glyphicon glyphicon-plus"></span></a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

</div>

<!-- Site name and slogan -->
<div id="site-name">
    <h1>Balero CMS</h1>
    <h5>Simple is powerful</h5>
</div>

    <div class="logo-wrapper">
        <img class="img-responsive" src="http://placehold.it/200x200&text=Logo" />
    </div>

    <!-- Admin edit button -->
    <button class="btn btn-default btn-lg pull-right toolbox" data-toggle="modal" data-target="#myModal">
        <span class="glyphicon glyphicon-pencil"></span> Edit
    </button>

<!-- Logo Image Modal -->
<div class="modal fade" id="NewPageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Add New Page</h4>
            </div>
            <div class="modal-body">
                <input class="form-control input-lg" type="text" placeholder="Title">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Add</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Load Slider and settiings
    $('.bxslider').bxSlider({
        infiniteLoop: false,
        hideControlOnEnd: true,
        controls: true,
        adaptiveHeight: true
    });
    // Hide and show Site Name because slider
    // divs are disabled When menu's show,
    // so it will fix the issue
    var flag = 0;
    $('.navbar-toggle').click(function() {
        flag = flag+1;
        //alert(flag);
        if (flag == 1) $("#site-name").hide();
        if (flag == 2) {
            $("#site-name").show();
            flag = 0;
        }
    });
</script>

</body>
</html>