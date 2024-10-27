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
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    <br/><br/>
        <a href="${pageContext.request.contextPath}/observation/list"><h3>Observation List</h3></a>
    <br/><br/><br/>
    <form:form modelAttribute="observation" method="post" action="${pageContext.request.contextPath}/observation/edit/">
        <form:hidden path="id" />
        <form:hidden path="date"/>

        <div class="mb-3">
            <label for="location">Select location:</label>
            <form:select path="location.id" items="${locations}" itemLabel="LocationName" itemValue="id"/>
            <form:errors path="location"/>
        </div>

        <div class="mb-3">
            <label for="animal">Select animal:</label>
            <form:select path="animal.id" items="${animals}" itemLabel="animalName" itemValue="id"/>
            <form:errors path="animal"/>
        </div>

        <div class="mb-3">
            <label for="description" rows="6" cols="20">Notes</label>
            <form:textarea path="description"/>
            <form:errors path="description"/>
        </div>

        <div class="mb-3">
            <button type="submit">SUBMIT</button>
        </div>
    </form:form>
<%--        <!-- Wyświetlenie zapisanych danych po edycji -->--%>
<%--        <h3>Observation Details:</h3>--%>
<%--        <p><strong>Id:</strong> ${observation.id}</p>--%>
<%--        <p><strong>User:</strong> ${observation.user.username}</p>--%>
<%--        <p><strong>Location:</strong> ${observation.location.locationName}</p>--%>
<%--        <p><strong>Animal:</strong> ${observation.animal.animalName}</p>--%>

</div>
</body>
</html>

