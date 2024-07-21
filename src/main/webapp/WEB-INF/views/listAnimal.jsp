<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Animal List</title>
</head>
<body>
<h2>Animal List</h2>
<a href="<c:url value="/animal/add/form"/>">Add new animal</a>
<table>
    <tr>
        <th>Id</th width="">
        <th>Name</th>
        <th>Classis</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${animals}" var="animal">
        <tr>
            <td>${animal.id}</td>
            <td>${animal.name}</td>
            <td>${animal.animalClassis}</td>
            <td>${animal.animalDescription}</td>
            <td>
                <a href="<c:url value="/animal/edit/form/${animal.id}"/>">Edit</a>
                <a href="<c:url value="/animal/delete/form/${animal.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
