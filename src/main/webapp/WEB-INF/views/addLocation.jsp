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
    <h2>Add Location</h2>
    <br/><br/><br/>
    <form:form modelAttribute="location" method="post" action="${pageContext.request.contextPath}/location/add/">
        <a href="${pageContext.request.contextPath}/location/list"><h3>Location List</h3></a>
        <br/><br/><br/>

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
            <button type="submit">SUBMIT</button>
        </div>
    </form:form>
</div>
</body>
</html>
