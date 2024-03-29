<%--
  Created for BaleroCMS.
  User: Anibal Gomez
  Date: 18/07/14
  Time: 01:20 PM
  Eternity Template (balerocms.com).
--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="author" content="Anibal Gomez">
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
    <!-- favicon.ico -->
    <link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" />
</head>
<body>

<!-- Slider Headers -->
<ul class="bxslider">

    <li><img src="<c:url value="/" />${defaultCover}"></li>

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
                <a class="navbar-brand" href="/">
                    <span class="glyphicon glyphicon-home"></span>
                </a>
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

                    <c:forEach var="p" items="${pages}">
                        <li><a href="/page/${p.slug}">${p.name}</a></li>
                    </c:forEach>

                    <c:if test="${admin == true}">

                        <li><a href="/post/add" id="add"><span class="glyphicon glyphicon-pencil"></span></a></li>
                        <li><a href="#" id="new" data-toggle="modal" data-target="#NewPageModal"><span class="glyphicon glyphicon-plus"></span></a></li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b class="glyphicon glyphicon-cog"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" id="pwdsetup" data-toggle="modal" data-target="#PwdSetupModal"><spring:message code="label.pwd" /></a></li>
                                <li class="divider"></li>
                                <li><a href="#" id="settings" data-toggle="modal" data-target="#SettingsModal"><spring:message code="label.settings" /></a></li>
                                <li class="divider"></li>
                                <li><a href="/logout" id="logout"><spring:message code="label.logout" /></a></li>
                            </ul>
                        </li>

                    </c:if>

                    <c:if test="${admin == false}">
                        <li><a href="#" id="login" data-toggle="modal" data-target="#LoginModal"><spring:message code="label.login" /></a></li>
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
                <button type="button" class="btn btn-primary btn-lg inverse" data-toggle="modal" data-target="#UploadModal">
                    <span class="glyphicon glyphicon-upload"></span>
                </button>
                <!-- Remove -->
                <button type="submit" class="btn btn-danger btn-lg inverse" onclick="getSlider()">
                    <span class="glyphicon glyphicon-remove"></span>
                </button>
                <!-- Data Container -->
                <input type="hidden" id="sliderContainer" name="sliderContainer">
            </form>

            <!-- Save Cover -->
            <form method="post" action="/upload/save" class="pull-right">
                <!-- Edit header background (Modal) -->
                <button type="submit" class="btn btn-default btn-lg inverse" onclick="javascript:getDefaultSlider()">
                    <span class="glyphicon glyphicon-floppy-saved"></span>
                </button>
                <!--Container -->
                <input type="hidden" id="defaultSliderContainer" name="defaultSliderContainer">
            </form>

        </div>
    </c:if>

    <h1><a href="${url}">${sitename}</a></h1>
    <h5>${slogan}</h5>

</div>

<div class="container">

    <!-- Loop -->
    <c:forEach var="p" items="${content}">
    <form method="post" action="/post/edit/${p.id}">

        <div id="editor1" contenteditable="${admin}" class="hidden">
                ${p.content}
        </div>

        <div id="editor2" contenteditable="${admin}">
                ${p.full}
            <c:if test="${more == 1}">
                (Full Post...)
            </c:if>
        </div>

        <c:if test="${admin == true}">

            <!-- Language codes -->
            <select name="code">
                <option value="${pageContext.response.locale}">Default (${pageContext.response.locale})</option>
                <option value="af">af</option><option value="sq">sq</option><option value="ap">ap</option>
                <option value="hy">hy</option><option value="eu">eu</option><option value="bn">bn</option>
                <option value="bg">bg</option><option value="ca">ca</option><option value="km">km</option>
                <option value="zh">zh</option><option value="hr">hr</option><option value="cs">cs</option>
                <option value="da">da</option><option value="nl">nl</option><option value="en">en</option>
                <option value="et">et</option><option value="fj">fj</option><option value="fi">fi</option>
                <option value="fr">fr</option><option value="ka">ka</option><option value="de">de</option>
                <option value="el">el</option><option value="gu">gu</option><option value="he">he</option>
                <option value="hi">hi</option><option value="hu">hu</option><option value="is">is</option>
                <option value="id">id</option><option value="ga">ga</option><option value="it">it</option>
                <option value="ja">ja</option><option value="jw">jw</option><option value="ko">ko</option>
                <option value="la">la</option><option value="lv">lv</option><option value="lt">lt</option>
                <option value="mk">mk</option><option value="ms">ms</option><option value="ml">ml</option>
                <option value="mt">mt</option><option value="mi">mi</option><option value="mp">mp</option>
                <option value="mn">mn</option><option value="ne">ne</option><option value="no">no</option>
                <option value="fa">fa</option><option value="pl">pl</option><option value="pt">pt</option>
                <option value="pa">pa</option><option value="qu">qu</option><option value="ro">ro</option>
                <option value="ru">ru</option><option value="sm">sm</option><option value="sp">sp</option>
                <option value="sk">sk</option><option value="sl">sl</option><option value="es">es</option>
                <option value="sw">sw</option><option value="sv">sv</option><option value="ta">ta</option>
                <option value="tt">tt</option><option value="te">te</option><option value="th">th</option>
                <option value="bo">bo</option><option value="to">to</option><option value="tr">tr</option>
                <option value="uk">uk</option><option value="ur">ur</option><option value="uz">uz</option>
                <option value="vi">vi</option><option value="cy">cy</option><option value="xy">xy</option>
            </select>

            <p></p>

            <!-- Toolbox -->
            <button type="submit" class="btn btn-default btn-lg" id="submit">
                <span class="glyphicon glyphicon-floppy-disk"></span>
            </button>

            <a href="/post/delete?id=${p.id}" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-remove"></span>
            </a>

        </c:if>

        <hr contenteditable="false">

        <c:forEach var="q" items="${comments}">
            <!-- Comments -->
                <div class="bubble">
                    <c:if test="${admin == true}">
                        <div class="pull-right">
                            <a href="/comments/delete/${q.id}?postId=${p.id}">
                                <span class="glyphicon glyphicon-floppy-remove"></span>
                            </a>
                        </div>
                    </c:if>
                    <b>${q.name}</b>
                    <br>
                        ${q.comment}
                    <br />
                        ${q.date}
                </div>
        </c:forEach>

        <!-- Data Container -->
        <input type="hidden" name="content" class="content" value="init">
        <input type="hidden" name="full" class="full" value="">
        <script>
            $( "#submit" ).click(function() {
                <c:if test="${mobile == false}">
                var datacontent = CKEDITOR.instances.editor1.getData();
                var datafull = CKEDITOR.instances.editor2.getData();
                </c:if>
                <c:if test="${mobile == true}">
                var datacontent =  $("#editor1").html();
                var datafull =  $("#editor2").html();
                </c:if>
                $(".content").val(datacontent);
                $(".full").val(datafull);
            });
        </script>

    </form>

<!-- Comment Form -->
    <form action="/comments/${p.id}" method="post" role="form">
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-user"></span>
                </div>
                <input type="text" class="form-control" placeholder="<spring:message code="label.comments.name" />" name="name">
            </div>
        </div>
        <div class="form-group">
            <textarea class="form-control" rows="10" placeholder="<spring:message code="label.comments.comment" />" name="comment"></textarea>
        </div>
            <button type="submit" class="btn btn-default pull-right">
            <span class="glyphicon glyphicon-ok"></span>
        </button>
    </form>

</div> <!-- Container -->

</c:forEach>

<!-- /Loop -->

<!-- Footer -->
<div id="footer">

    <c:forEach var="p" items="${footer}">

        <c:if test="${admin == true}">
            <form method="post" action="/footer">
                <div class="pull-right">
                    <button type="submit" class="btn btn-default btn-sm" id="savefooter">
                        <span class="glyphicon glyphicon-floppy-disk"></span>
                    </button>
                    <!-- Footer Hidden Container -->
                    <input type="hidden" name="fContainer" id="fContainer">
                    <script>
                        $( "#savefooter" ).click(function() {
                            <c:if test="${mobile == false}">
                            var footerdata = CKEDITOR.instances.editorfooter.getData();
                            </c:if>
                            <c:if test="${mobile == true}">
                            var footerdata =  $("#editorfooter").html();
                            </c:if>
                            $("#fContainer").val(footerdata);
                        });
                    </script>
                </div>
            </form>
        </c:if>

        <div id="editorfooter" contenteditable="${admin}">
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
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.login.title" /></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="inputUsername"><spring:message code="label.login.user" /></label>
                        <input type="text" name="inputUsername" class="form-control" id="inputUsername">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword"><spring:message code="label.login.pass" /></label>
                        <input type="password" name="inputPassword" class="form-control" id="inputPassword">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal"><spring:message code="label.btn.close" /></button>
                    <button type="submit" class="btn btn-primary"><spring:message code="label.login" /></button>
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
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.upload.title" /></h4>
                </div>
                <div class="modal-body">
                    <input type="file" name="file">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal"><spring:message code="label.btn.close" /></button>
                    <button type="input" class="btn btn-primary"><spring:message code="label.upload.btn" /></button>
                </div>
            </div>
        </form>
    </div>
</div>
</div>

<!-- Add Page Modal -->
<div class="modal fade" id="NewPageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="/page/new">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.page.new" /></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" id="name" placeholder="<spring:message code="label.page.title" />">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal"><spring:message code="label.btn.close" /></button>
                    <button type="submit" class="btn btn-primary"><spring:message code="label.btn.ok" /></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Password Setup Modal -->
<div class="modal fade" id="PwdSetupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="/users/god">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.pwd.title" /></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="pwd1"><spring:message code="label.pwd.pwd1" /></label>
                        <input type="text" name="pwd1" class="form-control" id="pwd1">
                    </div>
                    <div class="form-group">
                        <label for="pwd2"><spring:message code="label.pwd.pwd2" /></label>
                        <input type="text" name="pwd2" class="form-control" id="pwd2">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal"><spring:message code="label.btn.close" /></button>
                    <button type="submit" class="btn btn-primary"><spring:message code="label.bn.save" /></button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Settings Modal -->
<div class="modal fade" id="SettingsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="/users/administrator">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel"><spring:message code="label.settings" /></h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="sitename"><spring:message code="label.settings.sitename" /></label>
                        <input type="text" name="sitename" class="form-control" id="sitename" value="${sitename}">
                    </div>
                    <div class="form-group">
                        <label for="slogan"><spring:message code="label.settings.slogan" /></label>
                        <input type="text" name="slogan" class="form-control" id="slogan" value="${slogan}">
                    </div>
                    <div class="form-group">
                        <label for="url">URL</label>
                        <input type="text" name="url" class="form-control" id="url" value="${url}">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="id" id="id" value="${settingsId}">
                    <button type="button" class="btn btn-default" id="show-site-name" data-dismiss="modal"><spring:message code="label.btn.close" /></button>
                    <button type="submit" class="btn btn-primary"><spring:message code="label.btn.save" /></button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/balero-system.js" />"></script>
<script>
    // Bxslider
    // Load Slider and settiings
    slider = $('.bxslider').bxSlider({
        pager: ${admin},
        infiniteLoop: false,
        hideControlOnEnd: true,
        controls: true,
        adaptiveHeight: true
    });
    // Message center
    $(window).load(function(){
        $('#myModal').modal('show');
    });
</script>

</body>
</html>