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

<!-- Slider Headers -->
<ul class="bxslider">
    <li><img src="<c:url value="/resources/images/eternity.png" />"></li>
    <c:if test="${admin == true}">
        <!-- /media/uploads/ -->
        ${files}
    </c:if>
</ul>
<!-- /Slider Headers -->

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

            <c:if test="${message != null}">
                <!-- Message Center -->
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <div class="modal-body">
                                <p class="message-center">
                                ${message}
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal">OK</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${admin == true}">

                        <li><a href="#" id="add" data-toggle="modal" data-target="#NewPageModal"><span class="glyphicon glyphicon-plus"></span></a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="glyphicon glyphicon-cog"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Media Repository</a></li>
                                <li><a href="#">Password Setup</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="/logout" id="logout">Logout</a></li>
                            </ul>
                        </li>

                    </c:if>
                    <c:if test="${admin == false}">
                        <li><a href="#" id="login" data-toggle="modal" data-target="#LoginModal">Login</a></li>
                    </c:if>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

</div>

<!-- Site name and slogan -->
<div id="site-name">

    <c:if test="${admin == true}">
        <!-- Upload And Delete Headers -->
        <p align="right" class="right">
                <!-- Edit header background -->
                <button type="button" class="btn btn-default btn-lg inverse" data-toggle="modal" data-target="#UploadModal">
                    <span class="glyphicon glyphicon-upload"></span>
                </button>
        <button type="button" class="btn btn-default btn-lg inverse">
            <span class="glyphicon glyphicon-remove"></span>
        </button>
        </p>
    </c:if>

    <h1>Balero CMS</h1>
    <h5>Simple is powerful</h5>

</div>


<!-- Login Modal -->
<div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="/login">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Admin Login</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="inputUsername">Username</label>
                    <input type="text" name="inputUsername" class="form-control" id="inputUsername" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <label for="inputPassword">Password</label>
                    <input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Enter password">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
            </form>
        </div>
    </div>
</div>

<!-- New Page Modal -->
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

<!-- Image Upload Modal -->
<div class="modal fade" id="UploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form method="post" action="/upload" enctype="multipart/form-data">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Upload Header</h4>
            </div>
            <div class="modal-body">
                Name: <input type="text" name="name">
                <br />
                <input type="file" name="file">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal">Close</button>
                <button type="input" class="btn btn-primary">Add</button>
            </div>
            </div>
         </form>
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
    // Message center
    $(window).load(function(){
        $('#myModal').modal('show');
    });
</script>

</body>
</html>