<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--    <%@include file="header.jsp"%>--%>
    <title>Add Animal</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/image.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: left;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
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
    </style>

</head>
<body>
<div class="container">
    <h2>Add Animal</h2>
    <br/><br/>
    <a href="${pageContext.request.contextPath}/animal/list">Animal List</a>
    <br/><br/><br/>
    <%--@elvariable id="animal" type=""--%>
    <form:form modelAttribute="animal" method="post">

        <%--    <a href="${pageContext.request.contextPath}/animal/edit/form/{id}">Edit Animal</a>--%>
        <%--    <a href="${pageContext.request.contextPath}/animal/delete/form{id}">Delete Animal</a>--%>

    <div class="mb-3">
        Select user<br/>
        <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>
        <form:errors path="user.id"/>
    </div>

    <div class="mb-3">
        Select classis:<br/>
        <form:select path="animalClassis" items="${classis}"/>
        <form:errors path="animalClassis"/>
    </div>

    <div class="mb-3">
        Name <br/>
        <form:input path="name"/>
        <form:errors path="name"/>

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