<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Animal list</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('images/image.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
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
        .header-links {
            text-align: center;
            margin-bottom: 20px;
        }

        .header-links a {
            color: #4CAF50;
            text-decoration: none;
            margin-right: 10px;
            font-weight: bold;
        }

        .header-links a:hover {
            text-decoration: underline;
        }

        table {
            width: 100%; /* Szerokość tabeli na 100% szerokości kontenera */
            border-collapse: collapse; /* Usuwa odstępy między komórkami tabeli */
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

        /*a {*/
        /*    color: #4CAF50;*/
        /*    text-decoration: none;*/
        /*    margin-right: 10px;*/
        /*}*/

        /*a:hover {*/
        /*    text-decoration: underline;*/
        /*}*/

        /*label {*/
        /*    display: block;*/
        /*    margin: 10px 0 5px;*/
        /*}*/

        /*input, select, textarea, button {*/
        /*    width: 100%;*/
        /*    padding: 10px;*/
        /*    margin: 5px 0 15px;*/
        /*    border: 1px solid #ccc;*/
        /*    border-radius: 5px;*/
        /*}*/

        /*button {*/
        /*    background-color: #4CAF50;*/
        /*    color: white;*/
        /*    border: none;*/
        /*    cursor: pointer;*/
        /*}*/

        /*button:hover {*/
        /*    background-color: #45a049;*/
        /*}*/
    </style>

</head>
<body>
<div class="container">
<h2>Animal list</h2>
<br/><br/>
<a href="<c:url value="/animal/add/form"/>">Add new animal</a>
<br/><br/>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Classis</th>
        <th>Description</th>
        <th>User</th>
    </tr>
    <c:forEach items="${animals}" var="animal">
        <tr>
            <td>${animal.id}</td>
            <td>${animal.name}</td>
            <td>${animal.animalClassis}</td>
            <td>${animal.animalDescription}</td>
            <td>${animal.user.username}</td>
            <td>
                <a href="<c:url value="/animal/edit/form/${animal.id}"/>">Edit</a>
            </td>
            <td>
                <a href="<c:url value="/animal/delete/form/${animal.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
