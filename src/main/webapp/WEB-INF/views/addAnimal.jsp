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

        .error{
            color: red;
            font-weight: bold;
        }
    </style>

</head>
<body>
<div class="container">
    <h2>Add Animal</h2>
    <br/><br/>
    <form:form modelAttribute="animal" method="post" action="${pageContext.request.contextPath}/animal/add/">
    <a href="${pageContext.request.contextPath}/animal/animal-list"><h3>Animal List</h3></a>
    <br/><br/><br/>
<%--        <form:hidden path="user" value="${loggedInUser.id}"/>--%>

        <div class="mb-3">Select location<br/>
            <form:select path="location.id" id="location" items="${locations}" itemLabel="locationName" itemValue="id"/>
            <form:errors path="location.id" cssClass="error"/>
        </div>

        <div class="mb-3">
            Select category:<br/>
            <form:select path="category" id="category" items="${category}" itemValue="name"/>
            <form:errors path="category"/>
        </div>

        <div class="mb-3">Name <br/>
            <form:input path="animalName"/>
            <form:errors path="animalName" cssClass="error"/>

        </div>

        <div class="mb-3">Additional description <br/>
            <form:textarea path="animalDescription" rows="4"/>
            <form:errors path="animalDescription"/>
        </div>

        <div class="mb-3">
            <button type="submit">SUBMIT</button>
        </div>

    </form:form>
</div>
</body>
</html>