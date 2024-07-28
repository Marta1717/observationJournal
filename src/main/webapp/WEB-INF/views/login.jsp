<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

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
            color: #055305;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input, button {
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

        .error {
            color: red;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2><br/>
    <c:if test="${not empty loginError}">
        <div class="error">
                ${loginError}
        </div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/login" method="post" modelAttribute="login">

    <div class="mb-3">
        <label for="username">Username</label><br/>
        <form:input path="username" id="username"/>
        <form:errors path="username" cssClass="error"/>
        <br/>
    </div>
    <div class="mb-3">
        <label for="password">Password</label>
        <form:password path="password" id="password"/>
        <form:errors path="password" cssClass="error"/>
<br/>
    </div>
    <div class="mb-3">
        <button type="submit">Login</button>
    </div>
    </form:form>
</div>
<div class="container">
<div class="mb-4"><a href="${pageContext.request.contextPath}/user/add/form"><h4>or register</h4></a></div>
</div>
</body>
</html>