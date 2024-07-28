<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit User</title>

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

        h2 {
            text-align: center;
            color: #4CAF50;
        }


        h4 {
            text-align: center;
            color: #ff6f00;
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
    <h2>Edit User</h2>
    <br/><br/>
    <%--@elvariable id="user" type=""--%>
    <form:form modelAttribute="user" method="post" action="/user/edit">
        <form:hidden path="id"/>

    <div class="mb-3">
    Username <br/>
        <form:input path="username"/>
        <form:errors path="username"/>
    </div>

    <div class="mb-3">
    Password <br/>
        <form:input path="password"/>
        <form:errors path="password"/>
    </div>

    <div class="mb-3">
    Email <br/>
        <form:input path="email"/>
        <form:errors path="email"/>
    </div>

    <div class="mb-3">
    Newsletter agree
        <form:radiobuttons path="newsletter" items="${newsletteragree}"/>
        <form:errors path="newsletter"/>
    </div>

    <div class="mb-3">
    <button type="submit">Submit</button>
    </div>
    </form:form>
</div>
</body>
</html>
