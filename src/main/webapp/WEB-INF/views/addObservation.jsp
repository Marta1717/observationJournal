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
    <br/><br/>
    <%--@declare id="location"--%>
    <%--@elvariable id="observation" type=""--%>
    <form:form method="post" modelAttribute="observation" action="${pageContext.request.contextPath}/observation/add/">
    <a href="${pageContext.request.contextPath}/animal/list"><h3>Animal List</h3></a>
    <br/><br/><br/>

<%--    <label for="user">Select User</label><br/>--%>
<%--        <form:input path="user.id" id="user"/>--%>
<%--        <form:errors path="user.id"/>--%>
<%--    <br/>--%>

    <label for="animal">Select Animal</label>
        <form:select path="animal.id" id="animal" items="${animals}" itemLabel="animalName" itemValue="id"/>
        <form:errors path="animal.id"/>
    <br/>

    <label for="animal">Select Category:</label>
        <form:select path="animal.category" id="animals" items="${category}"/>
        <form:errors path="animal.category"/>
    <br/>

    <div class="mb-3">Select location<br/>
        <form:select path="location.id" id="location" items="${locations}" itemLabel="locationName" itemValue="id"/>
        <form:errors path="location.id"/>
    </div>

    <div class="mb-3">Select Biome<br/>
        <form:select path="location.biome" id="location" items="${locations}" itemLabel="biome" itemValue="id"/>
        <form:errors path="location.biome"/>
    </div>

    <div class="mb-3">Additional description <br/>
        <form:textarea path="description" rows="4"/>
        <form:errors path="description"/>
    </div>

    <button type="submit">Submit</button>

    </form:form>
</body>
</html>
