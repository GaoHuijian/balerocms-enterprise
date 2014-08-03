<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lastprophet (Anibal Gomez)
  Date: 2/08/14
  Time: 08:47 PM
  To change this template use File | Settings | File Templates.
  http://jokoscode.blogspot.mx/2012/04/part-2-create-simple-image-browser-for.html
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
${files}
<script>
    function getUrl(cback, imgfilepath)
    {
        window.opener.CKEDITOR.tools.callFunction(cback, imgfilepath);
        window.close(); //close file browser when file chosen
    }
</script>
</body>
</html>
