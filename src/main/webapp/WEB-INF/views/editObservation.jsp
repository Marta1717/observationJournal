<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Observation</title>

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


        h4 {
            text-align: center;
            color: #ff6f00;
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
    <%--@declare id="animal"--%>
    <%--@declare id="location"--%>
    <%--@elvariable id="observation" type=""--%>
    <h2>Edit Observation</h2>
    <br/><br/>

    <a href="${pageContext.request.contextPath}/observation/list">Observation List</a>
    <br/><br/><br/>
    <form:form modelAttribute="observation" method="post"
               action="${pageContext.request.contextPath}/observation/edit/">
        <input type="hidden" name="id" value="${observation.id}"/>
        <br><br>

        <div class="mb-3">
            <label for="animal">Select animal:</label>
            <form:select path="animal.id" items="${animals}" itemLabel="animalName" itemValue="id"/>
            <form:errors path="animal.id"/>
        </div>

        <div class="mb-3">
            <label for="location">Location:</label>
            <form:select path="location.id" items="${locations}" itemLabel="LocationName" itemValue="id"/>
            <form:errors path="location.id"/>
        </div>

        <div class="mb-3">
            <label for="description" rows="6" cols="20">Notes</label>
            <form:textarea path="description" id="description"/>
            <form:errors path="description"/>
        </div>

        <div class="mb-3">
            <button type="submit">SUBMIT</button>
        </div>
    </form:form>
</div>
</body>
</html>

