<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp"%>
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

        /*h2 {*/
        /*    text-align: center;*/
        /*    color: #4CAF50;*/
        /*}*/

        /*h3 {*/
        /*    text-align: left;*/
        /*    color: #4cafaf;*/
        /*}*/

        /*a {*/
        /*    color: #4CAF50;*/
        /*    text-decoration: none;*/
        /*    margin-right: 10px;*/
        /*}*/

        /*a:hover {*/
        /*    text-decoration: underline;*/
        /*}*/

        /*label {*/
        /*    display: block;*/
        /*    margin: 10px 0 5px;*/
        /*}*/

        /*input, select, textarea, button {*/
        /*    width: 100%;*/
        /*    padding: 10px;*/
        /*    margin: 5px 0 15px;*/
        /*    border: 1px solid #ccc;*/
        /*    border-radius: 5px;*/
        /*}*/

        /*button {*/
        /*    background-color: #4CAF50;*/
        /*    color: white;*/
        /*    border: none;*/
        /*    cursor: pointer;*/
        /*}*/

        /*button:hover {*/
        /*    background-color: #45a049;*/
        /*}*/
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
    <form:input path="username"/>
    <form:errors path="username"/>
    <br/>
</div>
<div class="mb-3">
    Password <br/>
    <form:input path="password"/>
    <form:errors path="password"/>
    <br/>
</div>
<div class="mb-3">
    Email <br/>
    <form:input path="email"/>
    <form:errors path="email"/>
    <br/>
</div>
<div class="mb-3">
    Newsletter agree
        <form:radiobutton path="newsletter" value="YES"/> Yes
        <form:radiobutton path="newsletter" value="NO"/> No
        <form:errors path="newsletter" cssClass="error"/>
    <br/><br/>
</div>
    <div class="mb-3">
    <button type="submit">Submit</button>
</div>
</form:form>
</div>
<div class="container">
    <div class="mb-4"><a href="${pageContext.request.contextPath}/home"><h4>or register</h4></a></div>
</div>
</body>
</html>