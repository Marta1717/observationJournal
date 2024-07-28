<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User's Observations</title>

    <style>

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            margin-left: 35%;
            margin-right: 35%;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: left;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
        }

        h3 {
            text-align: left;
            color: #4cafaf;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input, select, textarea, button {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>

</head>
<body>
<div class="container">
    <h2>All your observations</h2>
    <table>
        <tr>
            <th>Id</th>
            <th>Animal Name</th>
            <th>Classis</th>
            <th>Animal Description</th>
            <th>Location Name</th>
            <th>Biome</th>
            <th>Location Details</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${pageContext.request.contextPath}/observation/list/user" var="observation">
            <tr>
                <td>${observation.id}</td>
                <td>${observation.animalLocations.animal.animalName}</td>
                <td>${observation.animalLocations.animal.animalClassis}</td>
                <td>${observation.animalLocations.animal.animaldescription}</td>
                <td>${observation.animalLocations.location.locationName}</td>
                <td>${observation.animalLocations.location.biome}</td>
                <td>${observation.animalLocations.location.locationDescription}</td>
                <td>
                    <a href="<c:url value='/observations/edit/${animalLocation.id}'/>">Edit</a>
                </td>
                <td>
                    <a href="<c:url value='/observations/delete/${animalLocation.id}'/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>