<<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete location</title>
</head>
<body>
<h2>Delete location</h2>
<h3>Are you sure you want to delete the location named: <strong>${location.name}</strong>?</h3>
<form:form method="post" action="/location/delete">
    <input type="hidden" name="id" value="${location.id}"/>
    <p>Name:<br/> ${location.name}</p>
    <p>Name:<br/> ${location.biome}</p>
    <p>Description:<br/> ${location.locationDescription}</p>
    <strong><a href="${pageContext.request.contextPath}/location/list">Cancel</a></strong>
    <strong><button type="submit" name="action">Delete</button></strong>
</form:form>
</body>
</html>