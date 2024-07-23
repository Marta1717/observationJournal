<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="home.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Observation list</title>
</head>
<body>
<h2>Observation list</h2>
<a href="<c:url value="/observation/add/form"/>">Add new observation</a>
<br/><br/>
<table>
    <tr>
        <th>User</th>
        <th>Animal</th>
        <th>Location</th>
        <th>Biome</th>
        <th>Description</th>
        <th>Date</th>
        <th>Id</th>
    </tr>
    <%--@elvariable id="observations" type="java.util.List"--%>
    <c:forEach items="${observations}" var="observation">
        <tr>

            <td>${observation.user.username}</td>
            <td>${observation.animal.name}</td>
            <td>${observation.location.name}</td>
            <td>${observation.location.biome}</td>
            <td>${observation.description}</td>
            <td>${observation.date}</td>
            <td>${observation.id}</td>
            <td>
                <a href="<c:url value="/observation/edit/form/${observation.id}"/>">Edit</a>
                <a href="<c:url value="/observation/delete/form/${observation.id}"/>">Delete</a>
                <a href="<c:url value="/discussion/add/form"/>">Add comment</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
