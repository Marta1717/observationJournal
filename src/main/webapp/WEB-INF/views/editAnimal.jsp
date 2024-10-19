<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Animal</title>
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
    <h2>Edit Animal</h2>
    <br/><br/>
    <a href="${pageContext.request.contextPath}/animal/list"><h3>Animal List</h3></a>
    <br/><br/><br/>
    <form:form modelAttribute="animal" method="post" action="${pageContext.request.contextPath}/animal/edit/">
        <form:hidden path="id" />
<%--        <div class="mb-3">--%>
<%--            Select user<br/>--%>
<%--            <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>--%>
<%--            <form:errors path="user.id"/>--%>
<%--        </div>--%>
        <div class="mb-3">
            Category:<br/>
            <form:select path="category" items="${category}"/>
            <form:errors path="category"/>
        </div>

        <div class="mb-3">
            Name <br/>
            <form:input path="animalName"/>
        </div>

        <div class="mb-3">
            Additional description <br/>
            <form:textarea path="animalDescription" rows="4"/>
        </div>

        <div class="mb-3">
            <button type="submit">SUBMIT</button>
        </div>

    </form:form>
</div>
</body>
</html>