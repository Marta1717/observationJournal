<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Location</title>

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

        h4 {
            text-align: center;
            color: #ff6f00;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Edit Location</h2>
    <br/><br/>
    <a href="${pageContext.request.contextPath}/animal/list"><h3>Location List</h3></a>
    <br/><br/><br/>
    <form:form modelAttribute="location" method="post" action="${pageContext.request.contextPath}/location/edit">

        <div class="mb-3">
            Select animal<br/>
            <form:select path="animal.id" id="user" items="${animals}" itemLabel="name" itemValue="id"/>
            <form:errors path="animal.id"/>
        </div>

        <div class="mb-3">
            Name <br/>
            <form:input path="locationName"/>
        </div>

        <div class="mb-3">
            Biome <br/>
            <form:input path="biome"/>
        </div>

        <div class="mb-3">
            Additional description <br/>
            <form:textarea path="locationDescription" rows="4"/>
        </div>

        <div class="mb-3">
            <button type="submit">Submit</button>
        </div>

    </form:form>
</div>
</body>
</html>