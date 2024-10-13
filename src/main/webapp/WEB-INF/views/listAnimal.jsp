<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Animal list</title>
    <style>
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            margin-left: 15%;
            margin-right: 15%;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 1200px;
            overflow-y: auto;
            height: 80vh;
            text-align: left;
        }

        table {
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }
    </style>

</head>
<body>
<div class="container">
    <h2>Animal list</h2>
    <br/><br/>
    <a href="<c:url value="/animal/add"/>"><h3>Add new animal</h3></a>
    <br/><br/>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Category</th>
            <th>Description</th>
            <th>User</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${animals}" var="animal">
            <tr>
                <td>${animal.id}</td>
                <td>${animal.animalName}</td>
                <td>${animal.category}</td>
                <td>${animal.animalDescription}</td>
                <td>${animal.user.username}</td>
                <td>
                    <a href="<c:url value="/animal/edit/${animal.id}"/>">Edit</a>
                </td>
                <td>
                    <a href="<c:url value="/animal/delete/${animal.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
