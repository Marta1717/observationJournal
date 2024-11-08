<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Animal</title>

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
    </style>

</head>
<body>
<div class="container">
    <h2>Add Observation</h2>
    <br/>
    <%--@declare id="location"--%>
    <%--@elvariable id="observation" type=""--%>
    <form:form method="post" modelAttribute="observation" action="${pageContext.request.contextPath}/observation/add/">
    <a href="${pageContext.request.contextPath}/animal/list"><h3>Observation List</h3></a>
    <br/><br/>

    <div class="mb-3">Select location<br/>
        <form:select path="location.id" id="location" items="${locations}" itemLabel="locationName" itemValue="id"/>
        <form:errors path="location.id"/>
    </div>

    <label for="animal">Select animal</label>
        <form:select path="animal.id" id="animal" items="${animals}" itemLabel="animalName" itemValue="id"/>
        <form:errors path="animal.id"/>
    <br/>

    <div class="mb-3">Additional description <br/>
        <form:textarea path="description" rows="4"/>
        <form:errors path="description"/>
    </div>

    <button type="submit">SUBMIT</button>

    </form:form>
</body>
</html>
