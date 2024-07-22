<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Observation Journal</title>
</head>
<body>
<h1> Observation Journal</h1>
<table>
        <a href="${pageContext.request.contextPath}/animal/add/form">Add New Animal</a> |
        <a href="${pageContext.request.contextPath}/animal/list">Animal List</a> |
        <a href="${pageContext.request.contextPath}/location/add/form">Add New Location</a> |
        <a href="${pageContext.request.contextPath}/location/list">LocationList</a> |
        <a href="${pageContext.request.contextPath}/observation/add/form">Add New Observation</a> |
        <a href="${pageContext.request.contextPath}/observation/list">Observation List</a>
</table>

</body>
</html>