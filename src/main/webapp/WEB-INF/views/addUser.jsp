<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add User</h2>
<table></table>
<%--@elvariable id="user" type=""--%>
<form:form modelAttribute="user" method="post">

    Username <br/>
    <form:input path="username"/>
    <form:errors path="username"/>
    <br/>
    Password <br/>
    <form:input path="password"/>
    <form:errors path="password"/>
    <br/>
    Email <br/>
    <form:input path="email"/>
    <form:errors path="email"/>
    <br/><br/>
    Newsletter agree
    <form:checkbox path="mailingList"/> Yes
    <form:errors path="mailingList"/>
    <br/><br/>
    <button type="submit">Submit</button>
</form:form>
</body>
</html>