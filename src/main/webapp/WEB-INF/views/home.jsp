<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observation Journal</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>">
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f8f0;
            margin: 0;
            padding: 0;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }
        .main-content {
            padding: 50px;
            color: olive;
        }

        .main-content img {
            max-width: 100%;
            height: auto;
            font-family: Arial, sans-serif;
            text-align: center;
        }
    </style>
</head>
<body>
<%--<div class="user-info">--%>
<%--    Logged in as: <c:out value="${sessionScope.username}"/>--%>
<%--</div>--%>
<div class="main-content">
    <h2>Welcome in Observation Journal!</h2>
</div>
</body>
</html>