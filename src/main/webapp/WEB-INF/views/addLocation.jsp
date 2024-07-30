<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Location</title>

    <style>

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
    </style>
</head>
<body>
<div class="container">
    <h2>Add new location</h2>
    <br/><br/><br/>
    <form:form modelAttribute="location" method="post" action="${pageContext.request.contextPath}/location/add/form">
        <a href="${pageContext.request.contextPath}/location/list">Location List</a>
        <br/><br/><br/>

        <form:hidden path="user" value="${loggedInUser.id}"/>

        <div class="mb-3">
            Select animal<br/>
            <form:select path="animal.id" id="animal" items="${animals}" itemLabel="animalName" itemValue="id"/>
            <form:errors path="animal.id"/>
        </div>

        <div class="mb-3">
            Place name <br/>
            <form:input path="locationName"/>
            <form:errors path="locationName"/>
        </div>

        <div class="mb-3">
            Biome <br/>
            <form:input path="biome"/>
            <form:errors path="biome"/>
        </div>

        <div class="mb-3">
            Additional description <br/>
            <form:textarea path="locationDescription" rows="4"/>
            <form:errors path="locationDescription"/>
        </div>

        <div class="mb-3">
            <button type="submit">Add location</button>
        </div>
    </form:form>
</div>
</body>
</html>
