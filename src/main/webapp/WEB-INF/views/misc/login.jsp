<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ali
  Date: 06.07.2018
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/lib/jquery-3.3.1.min.js" var="jQuery"/>
    <spring:url value="/resources/css/main.css" var="MainCss"/>
    <link rel="stylesheet" type="text/css" href="https://netdna.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${MainCss}">
</head>
<body>
<header><%@ include file="../client/header.jsp" %></header>

<br>
<div class="row align-items-center justify-content-center">

<div class="controls col-3 align-items-center">
    <h2> <spring:message code="label.lpHeader"/> </h2>

    <c:url value="/login" var="loginUrl"/>

    <form action="${loginUrl}" method="post">
        <c:if test="${param.error != null}">
            <p>
                <spring:message code="label.lpErrorUsernameOrPassword"/>
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                <spring:message code="label.lpErrorLogout"/>
            </p>
        </c:if>
        <div class="form-group">
            <label for="username"> <spring:message code="label.lpUsername"/> :</label> <br>
            <input type="text" id="username" name="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password"> <spring:message code="label.lpPassword"/> :</label><br>
            <input type="password" id="password" name="password" class="form-control"/>
        </div>
        <button type="submit" class="btn btn-success"> <spring:message code="label.lpLogIn"/> </button>
    </form>
    <c:url var="registerBackofficeUrl" value="/backoffice/account/register"/>
    <c:url var="registerClientUrl" value="/client/account/register"/>
    <div class="container">
        <div class="row">
            <div class="col-xs-4" style="margin-right: 7px">
                <a href="${registerBackofficeUrl}">
                    <button  class="btn btn-primary"> <spring:message code="label.lpRegisterBackoffice"/> </button>
                </a>
            </div>
            <div class="col-xs-4">
                <a href=${registerClientUrl}>
                    <button  class="btn btn-primary"> <spring:message code="label.lpRegisterClient"/> </button>
                </a>
            </div>
        </div>
    </div>



</div>

</div>


<spring:url value="/resources/js/main.js" var="mainJs"/>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="${mainJs}"></script>

</body>
</html>
