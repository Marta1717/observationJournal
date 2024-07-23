<<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete observation</title>
</head>
<body>
<h2>Delete observation</h2>
<h3>Are you sure you want to delete this observation<strong>${observation.id}</strong>?</h3>
<form:form method="post" action="/observation/delete">
    <input type="hidden" name="id" value="${observation.id}"/>
    <p>Date:<br/> ${observation.date}</p>
<%--    <p>Biome:<br/> ${observation.location.biome}</p>--%>
    <p>Description:<br/> ${observation.description}</p>
    <strong><a href="${pageContext.request.contextPath}/observation/list">Cancel</a></strong>
    <strong><button type="submit" name="action">Delete</button></strong>
</form:form>
</body>
</html>
