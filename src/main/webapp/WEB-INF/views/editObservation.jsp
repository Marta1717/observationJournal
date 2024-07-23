<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Observation</title>
</head>
<body>
<%--@declare id="animal"--%>
<%--@declare id="location"--%>
<%--@elvariable id="observation" type=""--%>
<h2>Edit Observation</h2>
<form:form method="post" modelAttribute="observation">

    <a href="${pageContext.request.contextPath}/observation/list">Observation List</a>
    <br/><br/><br/>

    Select user<br/>
    <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>
    <form:errors path="user.id"/>
    <br/>

    <label for="animal">Select animal:</label>
    <form:select path="animal.id" items="${animals}" itemLabel="name" itemValue="id"/>
    <form:errors path="animal.id"/>
    <br/>

    <label for="location">Location:</label>
    <form:select path="location.id" items="${location}" itemLabel="name" itemValue="id"/>
    <form:errors path="location.id"/>
    <br/>

    <label for="location">Biome:</label>
    <form:select path="location.id" items="${location}" itemLabel="biome" itemValue="id"/>
    <form:errors path="location.id"/>
    <br/>

    <label for="description" rows="6" cols="20">Notes</label>
    <form:textarea path="description" id="description"/>
    <form:errors path="description"/>
    <br/><br/>

    <button type="submit">Submit</button>

</form:form>
</body>
</html>

