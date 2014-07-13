<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <!-- CKE Editor -->
    <script src="/resources/vendors/ckeditor/ckeditor.js"></script>
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
                <a class="navbar-brand" href="./">Home</a>
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

                        <li><a href="/add" id="add"><span class="glyphicon glyphicon-pencil"></span></a></li>
                        <li><a href="/new" id="new"><span class="glyphicon glyphicon-plus"></span></a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="glyphicon glyphicon-cog"></b></a>
                            <ul class="dropdown-menu">
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
        <div class="pull-right">
        <form method="post" action="/upload/remove" class="pull-right">
            <!-- Edit header background (Modal) -->
            <button type="button" class="btn btn-default btn-lg inverse" data-toggle="modal" data-target="#UploadModal">
                <span class="glyphicon glyphicon-upload"></span>
            </button>
            <!-- Submit -->
            <button type="submit" class="btn btn-default btn-lg inverse" onclick="javascript:getSlider()">
                <span class="glyphicon glyphicon-remove"></span>
            </button>
            <!-- Data Container -->
            <input type="hidden" id="sliderContainer" name="sliderContainer">
        </form>
        </div>
    </c:if>

    <h1>Balero CMS</h1>
    <h5>Simple is powerful</h5>

</div>

<!-- Loop -->
<form method="post" action="/save">
<c:forEach var="p" items="${rows}">
    <div id="editable-${p.id}" class="type1" contenteditable="${admin}">
        ${p.content}
    </div>

    <c:if test="${admin == true}">

        <div class="pull-left">
            <button type="submit" class="btn btn-default btn-lg" onclick="javascript:content('editable-${p.id}', '${p.id}')">
                <span class="glyphicon glyphicon-floppy-disk"></span>
            </button>
        </div>

        <!-- btn -dlete -->
        <div class="add-button">
            <a href="/delete?id=${p.id}" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-remove"></span>
            </a>
        </div>

    </c:if>

</c:forEach>
<!-- Data Container -->
<input type="hidden" name="dataContainer" id="dataContainer">
<input type="hidden" name="id" id="id">
</form>
<!-- /Loop -->

<!-- Footer -->
<div id="footer">

        <c:forEach var="p" items="${footer}">

            <c:if test="${admin == true}">
                <form method="post" action="/footer">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-default btn-sm" onclick="javascript:footer('${p.id}')">
                            <span class="glyphicon glyphicon-floppy-disk"></span>
                        </button>
                        <!-- Footer Hidden Container -->
                        <input type="hidden" name="fContainer" id="fContainer">
                        <input type="hidden" name="fid" id="fid">
                    </div>
                </form>
            </c:if>

            <div id="editable-footer" contenteditable="${admin}">
                ${p.content}
            </div>

        </c:forEach>

</div>
<!-- /Footer -->

<!-- Modals -->

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
    // Save CKE Editor Content
    // InTo Server Data
    function content(divName, id) {
        // Save Div Content into Hidden Field
        // 'postContainer'
        var editor = CKEDITOR.instances[divName];
        // CKE Editor Method
        var data = editor.getData();
        // Variable Datas Has Content
        document.getElementById("dataContainer").value = data;
        document.getElementById("id").value = id;
    }
    // Footer container
    function footer(fid) {
        // Save Div Content into Hidden Field
        // 'postContainer'
        var editor = CKEDITOR.instances["editable-footer"];
        // CKE Editor Method
        var data = editor.getData();
        // Variable Datas Has Content
        document.getElementById("fContainer").value = data;
        document.getElementById("fid").value = fid;
    }
    // Bxslider
    // Load Slider and settiings
    slider = $('.bxslider').bxSlider({
        pager: ${admin},
        infiniteLoop: false,
        hideControlOnEnd: true,
        controls: true,
        adaptiveHeight: true
    });
    function getSlider() {
        var current = slider.getCurrentSlide();
        document.getElementById("sliderContainer").value = current;
    }
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
    // Remove one plugin.
    // http://docs.ckeditor.com/#!/
    // guide/dev_howtos_basic_configuration
    CKEDITOR.config.removePlugins = 'about, pastefromword, pastetext, undo, clipboard, scayt, wsc, removeformat';
</script>

</body>
</html>