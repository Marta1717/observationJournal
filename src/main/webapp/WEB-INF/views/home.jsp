<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observation Journal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f0f8f0;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
        }
        .main-content {
            padding: 50px;
        }
        .main-content img {
            max-width: 100%;
            height: auto;
        }
        .description {
            font-size: 1.2em;
            color: #333;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Observation Journal</h1>
</div>

        <a href="${pageContext.request.contextPath}/animal/add/form">Add New Animal</a> |
        <a href="${pageContext.request.contextPath}/animal/list">Animal List</a> |
        <a href="${pageContext.request.contextPath}/location/add/form">Add New Location</a> |
        <a href="${pageContext.request.contextPath}/location/list">LocationList</a> |
        <a href="${pageContext.request.contextPath}/observation/add/form">Add New Observation</a> |
        <a href="${pageContext.request.contextPath}/observation/list">Observation List</a>
</body>
</html>