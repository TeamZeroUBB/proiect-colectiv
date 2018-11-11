<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/6/2018
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Hompage</title>
</head>
<body>
    <h1> Welcome ${pageContext.request.userPrincipal.name}, this page is accessible only if you are logged in</h1>
</body>
</html>
