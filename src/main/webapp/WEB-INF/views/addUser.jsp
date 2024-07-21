<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add User</h2><br/>
<table></table>
<%--@elvariable id="user" type=""--%>
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
    <form:radiobuttons path="newsletter" items="${newsletteragree}"/>
    <form:errors path="newsletter"/>
    <br/><br/>
</div>
    <button type="submit">Submit</button>
</form:form>
</body>
</html>