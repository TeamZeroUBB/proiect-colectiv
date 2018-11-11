<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Success!</title>
    </head>

    <body>
        <c:url var="loginUrl" value="/loginForm"/>
        <c:if test="${message != null}">
            <p style="color: darkgreen;">
                <c:choose>
                    <c:when test="${ message == 'register.accountActive.message' }">
                       <spring:message code="label.rspAccountActive"/>
                    </c:when>
                </c:choose>
            </p>
        </c:if>
        <div>
            <a href="${loginUrl}">
                <button class="btn btn-primary">Login</button>
            </a>
        </div>
    </body>
</html>