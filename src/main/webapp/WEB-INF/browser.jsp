<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lastprophet (Anibal Gomez)

  Media Repo
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Media Repository</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/css/thumbnail-gallery.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            background: #45484d; /* Old browsers */
            background: -moz-linear-gradient(top,  #45484d 0%, #000000 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#45484d), color-stop(100%,#000000)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  #45484d 0%,#000000 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  #45484d 0%,#000000 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  #45484d 0%,#000000 100%); /* IE10+ */
            background: linear-gradient(to bottom,  #45484d 0%,#000000 100%); /* W3C */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#45484d', endColorstr='#000000',GradientType=0 ); /* IE6-9 */
            color: #c0c0c0;
        }
    </style>
    <script>
        function CheckAll(chk)
        {
            for (i = 0; i < chk.length; i++)
                chk[i].checked = true ;
        }

        function UnCheckAll(chk)
        {
            for (i = 0; i < chk.length; i++)
                chk[i].checked = false ;
        }
    </script>
</head>
<body>

<body>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">Repository</h1>
        </div>

        <form method="post" action="/upload/remove/media" name="myform">
            ${files}
            <p></p>
                <div class="pull-right">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                    <button type="button" class="btn btn-primary" name="Check_All" value="Check All" onClick="CheckAll(document.myform.list)">
                        <span class="glyphicon glyphicon-ok"></span>
                    </button>
                    <button type="button" class="btn btn-primary" name="Un_CheckAll" value="Uncheck All" onClick="UnCheckAll(document.myform.list)">
                        <span class="glyphicon glyphicon-remove"></span>
                    </button>
               </div>
        </form>

        <!--
        Template
        <div class="col-lg-3 col-md-4 col-xs-6 thumb">
            <a class="thumbnail" href="#">
                <img class="img-responsive" src="http://placehold.it/400x300" alt="">
            </a>
        </div>
        -->

    </div>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Balero CMS Enterprise - Media Repository</p>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<script>
    function getUrl(cback, imgfilepath)
    {
        window.opener.CKEDITOR.tools.callFunction(cback, imgfilepath);
        window.close(); //close file browser when file chosen
    }
</script>
<!-- jQuery Version 1.11.0 -->
<script src="/resources/js/jquery-1.9.1.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>