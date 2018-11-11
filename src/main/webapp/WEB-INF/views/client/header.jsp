<%--
  Created by IntelliJ IDEA.
  User: andreivaida
  Date: 18.07.2018
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/lib/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/resources/lib/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/resources/lib/jquery-3.1.1.slim.js" var="jQuery"/>
</head>

<%--css--%>
<link rel="stylesheet" type="text/css" href="${bootstrapCss}">
<%-- scipts --%>
<body>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <%-- LEFT SIDE --%>
        <ul class="navbar-nav mr-auto">
            <%-- home --%>
            <%--<a class="navbar-brand" href="<c:url value="/home"/>">--%>
                <%--<img src="${servletLoadImagePath}${homeIconImageUrl}" height="30px" alt="Home">--%>
                <%--<spring:message code = "label.hHome"/>--%>
            <%--</a>--%>



            <%-- backoffice home (only if the user is logged in and it is a backoffice user) --%>
            <!-- also the 'My account' tab if the user is a logged in client-->
            <li class="nav-item">
                <security:authorize access="hasRole('ROLE_ADMIN')" var="isAdmin" />

                <c:if test="${pageContext.request.userPrincipal.name != null}"> <!-- if the user is logged in-->
                    <c:choose>
                        <c:when test="${isAdmin}">
                            <%--<a class="nav-link" href="<c:url value="/backoffice"/>">--%>
                                <%--<img src="${servletLoadImagePath}${backofficeHomeIcon}" height="30px" alt="BackofficeHome">--%>
                                <%--<spring:message code = "label.hBackofficeHome"/>--%>
                            <%--</a>--%>
                        </c:when>
                        <c:otherwise> <!-- if the user is a regular client-->

                        </c:otherwise>
                    </c:choose>
                </c:if>
            </li>

        </ul>


        <%-- CENTER SIDE --%>
        <div class="col-lg-4 col-lg-offset-5 col-xs-2 col-xs-offset-4">
            <ul class="navbar-nav">
                <%-- welcome mesage (only if the user is logged in) --%>
                <li class="nav-item">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <a class="nav-link">
                            <spring:message code = "label.hWelcome"/> ${pageContext.request.userPrincipal.name} !
                        </a>
                    </c:if>
                </li>
            </ul>
        </div>


        <%-- RIGHT SIDE --%>
        <ul class="navbar-nav">
            <%-- language --%>
            <%--<li class="nav-item dropdown">--%>
                <%--<a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                    <%--<spring:message code = "label.hLanguage"/>--%>
                <%--</a>--%>
                <%--<div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
                    <%--<a class="dropdown-item" href="?language=en"><img height="20px" src="${servletLoadImagePath}${ukFlagImageUrl}"/>English</a>--%>
                    <%--<a class="dropdown-item" href="?language=ro"><img height="20px" src="${servletLoadImagePath}${roFlagImageUrl}"/>Română</a>--%>
                <%--</div>--%>
            <%--</li>--%>

            <%-- login/logout button --%>
            <li class="nav-item">
                <c:choose>
                    <%--login--%>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <a class="nav-link" href="<c:url value='/loginForm'/>">
                            <img class="float-left" src="${servletLoadImagePath}${logoutIconImageUrl}" height="30px" alt="Login">
                            <spring:message code = "label.hLoginOrRegister"/>
                        </a>
                    </c:when>
                    <%--logout--%>
                    <c:otherwise>
                        <a class="nav-link" href="javascript:document.getElementById('logout').submit()">
                            <img class="float-left" src="${servletLoadImagePath}${logoutIconImageUrl}" height="30px" alt="Logout">
                            <spring:message code = "label.hLogout"/>
                        </a>
                        <form id="logout" action="<c:url value='/logout'/>" method="post" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </li>


        </ul>
    </div>
</nav>


<script src="${jQuery}"></script>
<script src="${bootstrapJs}"></script>
</body>

</html>
