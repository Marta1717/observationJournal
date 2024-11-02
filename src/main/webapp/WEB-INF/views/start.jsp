<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observation Journal</title>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value='/css/styles.css'/>">--%>
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
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 10px;
            margin: 20px auto;
            max-width: 800px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

    </style>
</head>
<body>
<div class="main-content">

    <br/><br/>
    <h2>Welcome in Observation Journal!</h2>
<br/><br/><br/>

    <div class="px-4 py-3 bg-light rounded-2">
        <h3> Track, catalog, and analyze your wildlife observations with ease.<br/><br/>
            Sort by location, animal or habitat for a comprehensive view of nature.
        </h3>
        <br/><br/><br/>
    </div>

    <strong><a href="${pageContext.request.contextPath}/login"><h4>LOG IN</h4></a></strong>
    <br/><br/>
</div>
</body>
</html>