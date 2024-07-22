<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Location</title>
</head>
<body>
<h2>Add new location</h2>
<%--@elvariable id="location" type=""--%>
<form:form modelAttribute="location" method="post">

    Select user<br/>
    <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>
    <form:errors path="user.id"/>
    <br/><br/>

    Name <br/>
    <form:input path="name"/>
    <form:errors path="name"/>
    <br/><br/>

    Biome <br/>
    <form:input path="biome"/>
    <form:errors path="biome"/>
    <br/><br/>

    Additional description <br/>
    <form:textarea path="locationDescription" rows="4"/>
    <form:errors path="locationDescription"/>
    <br/>

    <br/><br/>
    <button type="submit">Add location</button>
</form:form>
</body>
</html>
