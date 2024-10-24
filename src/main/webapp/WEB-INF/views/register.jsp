<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add User</title>
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

        .radio-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 5px;
        }

        .radio-container label {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add User</h2><br/>
    <%--@elvariable id="user" type=""--%>
    <a href="${pageContext.request.contextPath}/user/list"><h3>User List</h3></a>
    <br/><br/>
    <form:form modelAttribute="user" method="post">
        <div class="mb-3">
            Username <br/>
            <form:input path="username" id="username" required="required" class="form-control"/>
            <form:errors path="username"/>
            <br/>
        </div>
        <div class="mb-3">
            Password <br/>
            <form:password path="password" id="password" required="required" class="form-control"/>
            <form:errors path="password"/>
            <br/>
        </div>
        <div class="mb-3">
            Email <br/>
            <form:input path="email" id="email" required="required" class="form-control"/>
            <form:errors path="email"/>
            <br/>
        </div>
        <div class="mb-3">
            Newsletter agree <br><br>
            <div class="radio-container">
                <label class="form-check-label" for="newsletteryes">Yes</label>
                <form:radiobutton path="newsletter" value="YES" id="newsletteryes" class="form-check-input"/>
            </div>
            <br>
            <div class="radio-container">
                <label class="form-check-label" for="newsletterno">No</label>
                <form:radiobutton path="newsletter" value="NO" id="newsletterno" class="form-check-input"/>
            </div>
            <form:errors path="newsletter" cssClass="error"/>
            <br/><br/>
        </div>
        <div class="mb-3">
            <button type="submit" class="btn btn-primary">REGISTER</button>
        </div>
    </form:form>
</div>
</body>
</html>