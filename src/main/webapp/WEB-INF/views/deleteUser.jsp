<%--
  Created by IntelliJ IDEA.
  User: marta-ol
  Date: 19.07.2024
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete User</title>
</head>
<body>
<h2>Delete User</h2>
<h3>Are you sure you want to delete the user with username: <strong>${user.username}</strong>?</h3>
<%--@elvariable id="user" type=""--%>
<form:form method="post" action="/user/delete">
    <input type="hidden" name="id" value="${user.id}"/>
    <p>Username:<br/> ${user.username}</p>
    <p>Email:<br/> ${user.email}</p>
    <strong><a href="/">Cancel</a></strong>
    <strong><button type="submit" name="action">Delete</button></strong>
</form:form>
</body>
</html>
