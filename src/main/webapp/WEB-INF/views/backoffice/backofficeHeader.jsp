<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andreivaida
  Date: 23.07.2018
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>

<body>
<spring:eval var="uploadDirectory"  expression="@viewPropertyConfigurer.getProperty('upload.directory')"/>
<c:url var="homeIconImageUrl" value="${uploadDirectory}/HomeIcon.png"/>
<c:url var="backofficeHomeIcon" value="${uploadDirectory}/BackofficeHomeIcon.png"/>
<c:url var="logoutIconImageUrl" value="${uploadDirectory}/LogoutIcon.png"/>
<c:url var="servletLoadImagePath" value="/loadImage?imagePath="/>
<c:url value="/logout" var="logoutUrl" />

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <ul class="navbar-nav mr-auto">
        <%-- home --%>
        <a class="navbar-brand" href="<c:url value="/home"/>">
            <img src="${servletLoadImagePath}${homeIconImageUrl}" height="30px" alt="Home">
            Home
        </a>

        <%-- backoffice home --%>
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value="/backoffice"/>">
                <img src="${servletLoadImagePath}${backofficeHomeIcon}" height="30px" alt="BackofficeHome">
                Backoffice home
            </a>
        </li>

        <%-- restaurant management --%>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Restaurant management
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="<c:url value="/backoffice/restaurant/list"/>">View all restaurant</a>
                <a class="dropdown-item" href="<c:url value="/backoffice/restaurant/new"/>">Add a restaurant</a>
            </div>
        </li>

        <%-- category management --%>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Category management
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="<c:url value="/backoffice/category/list"/>">View all categories</a>
                <a class="dropdown-item" href="<c:url value="/backoffice/category/new"/>">Add a category</a>
            </div>
        </li>

        <%-- product management --%>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Product management
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="<c:url value="/backoffice/product/list"/>">View all products</a>
                <a class="dropdown-item" href="<c:url value="/backoffice/product/new"/>">Add a product</a>
            </div>
        </li>
    </ul>

    <%-- logout --%>
    <div class="nav-item">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a class="nav-link" href="javascript:document.getElementById('logout').submit()">
                <img class="float-left" src="${servletLoadImagePath}${logoutIconImageUrl}" height="30px" alt="Logout">
                Logout
            </a>
            <form id="logout" action="${logoutUrl}" method="post" >
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </c:if>
    </div>
</nav>


</body>

<%-- scipts --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</html>