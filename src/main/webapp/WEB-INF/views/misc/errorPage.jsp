<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/css/errorPage.css" var="cssUrl"/>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${cssUrl}">
</head>
<%@ taglib prefix="spring2" uri="http://www.springframework.org/tags" %>
<spring:eval var="uploadDirectory"  expression="@viewPropertyConfigurer.getProperty('upload.directory')"/>
<c:url var="servletLoadImagePath" value="/loadImage?imagePath="/>
<c:url var="backgroundImage" value="${uploadDirectory}/FoodBackground.jpg"/>

<body class="background" style="background-image: url(${servletLoadImagePath}${backgroundImage})">
<header class="header"><%@ include file="../client/header.jsp" %></header>

<%-- set the message (client error / server error) --%>
<c:choose>
    <c:when test="${isServerError == false}">
        <c:set var ="genericMessage" value = "Ups! This page is not available."/>
    </c:when>
    <c:otherwise>
        <c:set var = "genericMessage" value = "Hmm... Something went wrong."/>
    </c:otherwise>
</c:choose>

<div class="row h-100 text-center">
    <div class="col-sm-12 my-auto">
        <div>
            <div class="little-text">${error}</div>
            <h1 class="errorTitle"><c:out value = "${genericMessage}"/></h1>
            <c:if test="${message != null}">
                <p class="little-text">
                    ${message}
                </p>
            </c:if>
        </div>
        <div>
            <a href="<c:url value="/restaurant/list"/>">
                <button class="btn btn-success"> <spring:message code="label.epBackButton"/> </button>
            </a>
        </div>
    </div>
</div>



</body>
</html>