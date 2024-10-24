<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            background-image: url('images/image.jpg');
            background-color: #def6de;
            margin: 0;
            padding: 0;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        h3 {
            text-align: left;
            color: #4cafaf;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
            margin-right: 10px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input, select, textarea, button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        a:hover {
            text-decoration: underline;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header-links a {
            color: white;
            text-decoration: none;
            margin: 0 60px;
            font-weight: bold;
        }

        .header-links a:hover {
            text-decoration: underline;
        }

        .user-info {
            position: absolute;
            top: 20px;
            right: 20px;
            color: #036400;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="header">
    <br/>
    <h1>
        <a href="${pageContext.request.contextPath}/home" style="text-decoration: underline; color: white;">Observation Journal</a>
    </h1>
    <br/><br/>
    <div class="user-info">
        Logged in as: <c:out value="${sessionScope.loggedInUser.username}"/>
    </div>
    <div class="header-links">
        <a href="${pageContext.request.contextPath}/location/add">Add New Location</a>
        <a href="${pageContext.request.contextPath}/location/list">Location List</a>
        <a href="${pageContext.request.contextPath}/animal/add">Add New Animal</a>
        <a href="${pageContext.request.contextPath}/animal/list">Animal List</a>
        <a href="${pageContext.request.contextPath}/observation/add">Add Observation</a>
        <a href="${pageContext.request.contextPath}/observation/list/all">Observation List</a>
    </div>
</div>
</body>
