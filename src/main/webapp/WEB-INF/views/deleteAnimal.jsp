<<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Animal</title>

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
            width: 400px;
            text-align: left;
        }

        h2 {
            text-align: center;
            color: #4CAF50;
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
<h2>Delete animal</h2>
<br/><br/>
<h3>Are you sure you want to delete the animal named: <strong>${animal.name}</strong>?</h3>
<form:form method="post" action="/animal/delete">
    <input type="hidden" name="id" value="${animal.id}"/>
    <p>Name:<br/><br/>${animal.name}</p>
    <p>Classis:<br/><br/>{animal.animalClassis}</p>
    <p>Description:<br/><br/>{animal.animalDescription}</p>
    <strong><a href="${pageContext.request.contextPath}/animal/list">Cancel</a></strong>
    <strong><button type="submit" name="action">Delete</button></strong>
</form:form>
</div>
</body>
</html>