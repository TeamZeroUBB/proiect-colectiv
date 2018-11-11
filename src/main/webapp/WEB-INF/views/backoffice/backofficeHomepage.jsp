<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/6/2018
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Backoffice Homepage</title>
</head>
<body>
    <h1>Welcome ${ pageContext.request.userPrincipal.name }, this page is accessible only to the backoffice users!</h1>
</body>
</html>
