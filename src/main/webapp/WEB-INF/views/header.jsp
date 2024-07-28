<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            margin-left: 35%;
            margin-right: 35%;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: left;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        h3 {
            text-align: left;
            color: #4cafaf;
        }

        h4 {
            text-align: center;
            color: darkred;:
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

        /*.user-info {*/
        /*    position: absolute;*/
        /*    top: 20px;*/
        /*    right: 20px;*/
        /*    color: #006400;*/
        /*    font-weight: bold;*/
        /*}*/
    </style>
</head>
<body>
<div class="header">
    <br/>
    <h1>Observation Journal</h1>
    <br/><br/>
<%--    <div class="user-info">--%>
<%--        Logged in as: <c:out value="${username}"/>--%>
<%--    </div>--%>
<%--    <div class="header-links">--%>
<%--        <a href="${pageContext.request.contextPath}/animal/add/form">Add New Animal</a>--%>
<%--        <a href="${pageContext.request.contextPath}/animal/list">Animal List</a>--%>
<%--        <a href="${pageContext.request.contextPath}/location/add/form">Add New Location</a>--%>
<%--        <a href="${pageContext.request.contextPath}/location/list">Location List</a>--%>
<%--&lt;%&ndash;        <a href="${pageContext.request.contextPath}/observation/add/form">Add New Observation</a>&ndash;%&gt;--%>
<%--        <a href="${pageContext.request.contextPath}/observation/list">Observation List</a>--%>
    </div>
</body>