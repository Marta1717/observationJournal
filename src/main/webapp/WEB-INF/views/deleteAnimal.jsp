<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete Animal</title>

    <style>
        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: left;
        }

        a:hover {
            text-decoration: underline;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Delete animal</h2>
    <br/><br/>
    <h3>Are you sure you want to delete the animal named: <strong>${animal.animalName}</strong>?</h3>
    <form:form method="post" action="/animal/delete">
        <input type="hidden" name="id" value="${animal.id}"/>
        <p>Name:<br/><br/>${animal.animalName}</p>
        <p>Classis:<br/><br/>${animal.animalClassis}</p>
        <p>Description:<br/><br/>${animal.animalDescription}</p>
        <strong><a href="${pageContext.request.contextPath}/animal/list"><h4>Cancel</h4></a></strong>
        <br/><br/>
        <strong><br/>
            <button type="submit" name="action">Delete</button>
        </strong><br/><br/>
    </form:form>
</div>
</body>
</html>