<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    
<head>
    <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/lib/jquery-3.3.1.min.js" var="jQuery"/>
    <spring:url value="/resources/css/main.css" var="MainCss"/>
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${MainCss}">
</head>
    
<body>
<header><%@ include file="../client/header.jsp" %></header>

<c:set var="resultMessage" value="Your account has been created. Please wait until it's approved."/>
<c:set var="messageColor" value="darkgreen"/>

<div class="row align-items-center justify-content-center">
    <div class="controls col-3">
        <h2> Backoffice - Create an Account </h2>

        <%-- error/success message --%>
        <c:if test="${success != null}">
            <c:if test="${success == false}">
                <c:set var="messageColor" value="darkred"/>
            </c:if>
            <p style="color: <c:out value="${messageColor}"/>;">
                <c:choose>
                    <c:when test="${ message == 'account.nameOrPassword.error' }">
                        Invalid username or password!
                    </c:when>
                    <c:when test="${ message == 'server.fail.error' }">
                        There is an inconvenience on the server side, please try again
                    </c:when>
                    <c:otherwise>
                        ${resultMessage}
                    </c:otherwise>
                </c:choose>
            </p>
        </c:if>

        <%-- register form --%>
        <c:url var="actionUrl" value="/backoffice/account/register"/>
        <form:form method="post" action="${actionUrl}" modelAttribute="backofficeUserDto">
            <div class="form-group">
                <form:label path="username" placeholder="Enter username">Username:</form:label>
                <form:input path="username" class="form-control"/>
                <small class="form-text text-muted">This will be your unique username.</small>
            </div>
            <div class="form-group">
                <form:label path="password" placeholder="Enter password">Password:</form:label>
                <form:password path="password" class="form-control"/>
                <small class="form-text text-muted">The password must have minimum 8 characters, 1 uppercase and 1 number.
                </small>
            </div>
            <div class="form-group">
                <form:label path="repeatPassword" placeholder="Repeat password">Repeat password:</form:label>
                <form:password path="repeatPassword" class="form-control"/>
            </div>

            <button type="submit" class="btn btn-success">Submit</button>
        </form:form>


    </div>
</div>
<spring:url value="/resources/js/main.js" var="mainJs"/>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jQuery}"></script>
<script type="text/javascript" src="${mainJs}"></script>
</body>


</html>