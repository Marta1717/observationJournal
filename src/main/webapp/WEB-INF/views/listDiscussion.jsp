<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>discussion List</title>
</head>
<body>
<h2>discussion List</h2>
<a href="<c:url value="/discussion/add/form"/>">Add new discussion</a>
<br/><br/>
<table>
    <tr>
        <th>Id</th>
<%--        <th>Animal</th>--%>
        <th>Comment</th>
        <th>CreatedAt</th>

    </tr>
    <c:forEach items="${discussion}" var="discussion">
        <tr>
            <td>${discussion.id}</td>
<%--            <td>${discussion.animal.name}</td>--%>
            <td>${discussion.comment}</td>
            <td>${discussion.createdAt}</td>
            <td>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
