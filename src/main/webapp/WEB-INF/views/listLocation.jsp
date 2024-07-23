<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Location list</title>

</head>
<body>
<h2>Location list</h2>
<a href="<c:url value="/location/add/form"/>">Add new location</a>
<br/><br/>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Biome</th>
        <th>Description</th>
        <th>User</th>
    </tr>
    <%--@elvariable id="locations" type="java.util.List"--%>
    <c:forEach items="${locations}" var="location">
        <tr>
            <td>${location.id}</td>
            <td>${location.name}</td>
            <td>${location.biome}</td>
            <td>${location.locationDescription}</td>
            <td>${location.user.username}</td>
            <td>
                <a href="<c:url value="/location/edit/form/${location.id}"/>">Edit</a>
                <a href="<c:url value="/location/delete/form/${location.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>