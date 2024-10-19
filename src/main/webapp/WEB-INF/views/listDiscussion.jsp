<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" %>--%>
<%--<%@include file="header-links.jsp"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <title>Discussion List</title>--%>

<%--    <style>--%>
<%--        .container {--%>
<%--            background-color: rgba(255, 255, 255, 0.9);--%>
<%--            padding: 30px;--%>
<%--            margin-left: 15%;--%>
<%--            margin-right: 15%;--%>
<%--            border-radius: 10px;--%>
<%--            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);--%>
<%--            width: 90%;--%>
<%--            max-width: 1200px;--%>
<%--            overflow-y: auto;--%>
<%--            height: 80vh;--%>
<%--            text-align: left;--%>
<%--        }--%>

<%--        h2 {--%>
<%--            text-align: center;--%>
<%--            color: #4CAF50;--%>
<%--            margin-top: 0;--%>
<%--        }--%>

<%--        h3 {--%>
<%--            text-align: left;--%>
<%--            color: #4cafaf;--%>
<%--        }--%>

<%--        table {--%>
<%--            width: 100%;--%>
<%--        }--%>

<%--        th, td {--%>
<%--            border: 1px solid #ddd;--%>
<%--            padding: 8px; /* Zmniejsza przestrzeń wewnętrzną komórek */--%>
<%--            text-align: left;--%>
<%--        }--%>

<%--        th {--%>
<%--            background-color: #4CAF50;--%>
<%--            color: white;--%>
<%--        }--%>

<%--        tr:nth-child(even) {--%>
<%--            background-color: #f2f2f2;--%>
<%--        }--%>

<%--        tr:hover {--%>
<%--            background-color: #ddd;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--    <h2>Discussion List</h2>--%>
<%--    <br/><br/>--%>
<%--    <a href="<c:url value="/discussion/add/${observation.id}"/>"><h3>Add New Comment</h3></a>--%>
<%--    <br/><br/>--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <th>Observation User</th>--%>
<%--            <th>Observation Location</th>--%>
<%--            <th>Observation Animal</th>--%>
<%--            <th>Comment User</th>--%>
<%--            <th>Comment</th>--%>
<%--            <th>CreatedAt</th>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${discussion}" var="discussion">--%>
<%--            <tr>--%>
<%--                <td>${discussion.observation.user.username}</td>--%>
<%--                <td>${discussion.observation.location.locationName}</td>--%>
<%--                <td>${discussion.observation.animal.animalName}</td>--%>
<%--                <td>${discussion.user.username}</td>--%>
<%--                <td>${discussion.comment}</td>--%>
<%--                <td>${discussion.createdAt}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
