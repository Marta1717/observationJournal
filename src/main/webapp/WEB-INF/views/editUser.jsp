<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp" %>
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
    <h2>Edit User</h2>
    <br/><br/>
    <%--@elvariable id="user" type=""--%>
    <form:form modelAttribute="user" method="post" action="${pageContext.request.contextPath}/user/edit/">
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
    <form:radiobutton path="newsletter" value="Yes"/>
    <form:radiobutton path="newsletter" value="No"/>
    <form:errors path="newsletter"/>
</div>
<div class="mb-3">
    <button type="submit">SUBMIT</button>
</div>
</form:form>
</body>
</html>
