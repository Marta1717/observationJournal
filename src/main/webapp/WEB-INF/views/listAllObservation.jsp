<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>All observations list</title>
        <style>
            .container {
                display: flex;
                justify-content: space-between;
                background-color: rgba(255, 255, 255, 0.9);
                padding: 30px;
                margin-left: 5%;
                margin-right: 5%;
                border-radius: 10px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                width: 95%;
                max-width: 1700px;
                overflow-y: auto;
                height: 80vh;
                /*text-align: left;*/
            }

            .form-container {
                width: 20%;
                background-color: #f4f4f4;
                padding: 20px;
                border: 2px solid #ddd;
                margin: 30px;


                display: flex;
                flex-direction: column;
            }

            .form-container label {
                text-align: center;
                display: block;
                width: 100%;
                margin-bottom: 10px;
            }

            .form-container input {
                margin-bottom: 20px;
                width: 100%;
                padding: 8px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .form-container button {
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .form-container button:hover {
                background-color: #45a049;
            }

            .table-container {
                width: 65%;
                display: flex;
                flex-direction: column;
            }

            h2 {
                text-align: center;
                color: #4CAF50;
                margin-top: 0;
            }

            table {
                width: 100%;
                border-collapse: collapse;
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
    <div class="form-container">
        <h2>Filter Observations</h2>
    <form action="${pageContext.request.contextPath}/observation/list/all" method="get">
        <label for="userId">Filter by User:</label>
        <input type="text" id="userId" name="userId">

        <label for="animalName">Filter by Animal Name:</label>
        <input type="text" id="animalName" name="animalName">

        <label for="locationName">Filter by Location Name:</label>
        <input type="text" id="locationName" name="locationName">

        <label for="biome">Filter by Biome:</label>
        <input type="text" id="biome" name="biome">

        <button type="submit">Filter</button>
    </form>
    </div>

    <br/><br/>
    <div class="table-container">
        <h2>All observation list</h2>
        <table>
            <tr>
                <th>Id</th>
                <th>User</th>
                <th>Animal Name</th>
                <th>Classis</th>
                <th>Animal Description</th>
                <th>Location Name</th>
                <th>Biome</th>
                <th>Location Details</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${pageContext.request.contextPath}/observation/list/all" var="observation">
                <tr>
                    <td>${observation.id}</td>
                    <td>${observation.user.username}</td>
                    <td>${observation.animalLocations.animalName}</td>
                    <td>${observation.animalLocations.animalClassis}</td>
                    <td>${observation.animalLocations.animaldescription}</td>
                    <td>${observation.animalLocations.locationName}</td>
                    <td>${observation.animalLocations.biome}</td>
                    <td>${observation.animalLocations.locationDescription}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>