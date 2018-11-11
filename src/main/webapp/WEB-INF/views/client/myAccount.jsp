<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ciprianmunteanu
  Date: 26-Jul-18
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="label.maPageTitle"/></title>

    <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
    <link rel="stylesheet" href="${bootstrapCss}">
</head>
<body>
<header><%@ include file="header.jsp" %></header>

<!--hack-->
<div hidden id="currentPage" data-page="myAccount"></div>

<div class="container-fluid">
    <div class="row">
        <div class="col-2">
            <%@ include file="myAccountSidebar.jsp" %>
        </div>

        <div class="col-10">
            <h2><spring:message code="label.maPlaceholder"/></h2>
        </div>
    </div>
</div>

</body>
<spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/lib/jquery-3.3.1.min.js" var="jQuery"/>
<script src="${bootstrapJs}"></script>
<script src="${jQuery}"></script>

</html>
