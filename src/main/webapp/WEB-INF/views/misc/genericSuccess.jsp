<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Success!</title>
    </head>

    <body>
        <c:if test="${message != null}">
            <p style="color: darkgreen;">
                <c:choose>
                    <c:when test="${ message == 'registration.success.message' }">
                        <spring:message code="label.gspRegister"/>
                    </c:when>
                </c:choose>
            </p>
        </c:if>
    </body>
</html>