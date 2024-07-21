<<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Animal</title>
</head>
<body>
<h2>Delete User</h2>
<h3>Are you sure you want to delete the animal named: <strong>${animal.name}</strong>?</h3>
<form:form method="post" action="/animal/delete">
    <input type="hidden" name="id" value="${animal.id}"/>
    <p>Name:<br/> ${animal.name}</p>
    <p>Description:<br/> ${animal.animalDescription}</p>
    <strong><a href="${pageContext.request.contextPath}/animal/list">Cancel</a></strong>
    <strong><button type="submit" name="action">Delete</button></strong>
</form:form>
</body>
</html>