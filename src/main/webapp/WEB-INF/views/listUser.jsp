<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User list</title>
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

        h2 {
            text-align: center;
            color: #4CAF50;
            margin-top: 0;
        }

        h3 {
            text-align: left;
            color: #4cafaf;
        }

        table {
            width: 100%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px; /* Zmniejsza przestrzeń wewnętrzną komórek */
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
    <h2>User List</h2>
    <br/><br/>
    <a href="<c:url value="/register"/>"><h3>Add New User</h3></a>
    <br/><br/>
    <table>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Email</th>
            <th>Newsletter</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr></tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.newsletter}</td>
                <td>
                    <a href="<c:url value="/user/edit/${user.id}"/>">Edit</a>
                </td>
                <td>
                    <a href="<c:url value="/user/delete/${user.id}"/>">Delete</a>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/user/subscribe/${user.id}" method="post">
                        <button type="submit" class="btn btn-primary">YES</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/user/unsubscribe/${user.id}" method="post">
                        <button type="submit" class="btn btn-warning">NO</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
