<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Animal</title>

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
    <h2>Add Animal</h2>
    <br/><br/>
    <form:form modelAttribute="animal" method="post">
    <a href="${pageContext.request.contextPath}/animal/list"><h3>Animal List</h3></a>
    <br/><br/><br/>
        <form:hidden path="userId" value="${loggedInUser.id}"/>

<%--        <div class="mb-3">--%>
<%--            Select user<br/>--%>
<%--            <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>--%>
<%--            <form:errors path="user.id"/>--%>
<%--        </div>--%>

        <div class="mb-3">
            Select classis:<br/>
            <form:select path="animalClassis" items="${classis}"/>
            <form:errors path="animalClassis"/>
        </div>

        <div class="mb-3">
            Name <br/>
            <form:input path="animalName"/>
            <form:errors path="animalName"/>

        </div>

        <div class="mb-3">
            Additional description <br/>
            <form:textarea path="animalDescription" rows="4"/>
            <form:errors path="animalDescription"/>
        </div>

        <div class="mb-3">
            <button type="submit">Submit</button>
        </div>

    </form:form>
</div>
</body>
</html>