<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ciprianmunteanu
  Date: 26-Jul-18
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sidebar</title>

    <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
    <link rel="stylesheet" href="${bootstrapCss}">
    <spring:url value="/resources/css/myAccountSidebar.css" var="myAccountSidebarCss"/>
    <link rel="stylesheet" href="${myAccountSidebarCss}">
</head>
<body>

<ul class="nav nav-pills">
    <li class="nav-item account-sidebar-pill">
        <c:url var="myAccountUrl" value="/account"/>
        <spring:message code="label.masAccountDetails" var="accountDetailsLabel"/>
        <a class="nav-link" id="myAccountSidePill" data-toggle="pill" href="${myAccountUrl}">${accountDetailsLabel}</a>
    </li>

    <li class="nav-item account-sidebar-pill">
        <c:url var="orderHistoryUrl" value="/account/order-history"/>
        <spring:message code="label.masOrderHistory" var="orderHistoryLabel"/>
        <a class="nav-link" id="orderHistorySidePill" data-toggle="pill" href="${orderHistoryUrl}">${orderHistoryLabel}</a>
    </li>
</ul>

</body>

<spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/lib/jquery-3.3.1.min.js" var="jQuery"/>
<script src="${bootstrapJs}"></script>
<script src="${jQuery}"></script>

<spring:url value="/resources/js/myAccountSidebar.js" var="myAccountSidebarJs"/>
<script src="${myAccountSidebarJs}"></script>

</html>
