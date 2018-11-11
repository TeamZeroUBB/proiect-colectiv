<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    
    <head>
        <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
        <spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
        <spring:url value="/resources/lib/jquery-3.1.1.slim.js" var="jQuery"/>
        <spring:url value="/resources/css/main.css" var="MainCss"/>
        <link rel="stylesheet" href="${bootstrapCss}"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${MainCss}">
    </head>
        
    <body>
        <header><%@ include file="header.jsp" %></header>


        <c:url var="actionUrl" value="/client/account/register"/>
        <div class="row align-items-center justify-content-center">
            <div class="controls col-3">

                <h2> <spring:message code="label.crCreateAccount"/> </h2>

                <p> <spring:message code="label.crInfo"/> </p>

                <c:if test = "${message != null}">
                    <p style="color: red;">
                        <c:choose>
                            <c:when test="${ message == 'register.usernameOrPassword.error' }">
                                <spring:message code="label.crErrorUsernameOrPassword"/>
                            </c:when>
                            <c:when test="${ message == 'register.emailTaken.error' }">
                                <spring:message code="label.crErrorEmailTaken"/>
                            </c:when>
                            <c:when test="${ message == 'register.tokenExpired.error' }">
                                <spring:message code="label.crErrorToken"/>
                            </c:when>
                        </c:choose>
                    </p>
                </c:if>

                <form:form method="post" action="${actionUrl}" modelAttribute="ClientUserDto">
                    <div class="form-group">
                        <form:label path="email">  <spring:message code="label.crEmail"/>  :</form:label>
                        <form:input type="email" path="email" required="required" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="password"> <spring:message code="label.crPassword"/> :</form:label>
                        <form:password path="password" required="required" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="repeatPassword"> <spring:message code="label.crConfirmPassword"/> :</form:label>
                        <form:password path="repeatPassword" required="required" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="firstName"> <spring:message code="label.crFirstName"/> :</form:label>
                        <form:input path="firstName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="lastName"> <spring:message code="label.crLastName"/> :</form:label>
                        <form:input path="lastName" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="town"> <spring:message code="label.crTown"/> :</form:label>
                        <form:input path="town" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="address"> <spring:message code="label.crAddress"/> :</form:label>
                        <form:textarea path="address" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <form:label path="zipCode"> <spring:message code="label.crZipCode"/> :</form:label>
                        <form:input path="zipCode" class="form-control"/>
                    </div>

                    <button type="submit" class="btn btn-success"> <spring:message code="label.crSubmitButton"/> </button>
                </form:form>
            </div>


        <spring:url value="/resources/js/main.js" var="mainJs"/>
        <script type="text/javascript" src="${bootstrapJs}"></script>
        <script type="text/javascript" src="${jQuery}"></script>
        <script type="text/javascript" src="${mainJs}"></script>
    </body>


</html>