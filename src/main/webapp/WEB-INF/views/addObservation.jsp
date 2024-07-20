<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Observation</title>
</head>
<body>
<h2>Add Observation</h2>
<%--@declare id="location"--%>
<%--@elvariable id="observation" type=""--%>
<form:form method="post" modelAttribute="observation">

    <label for="user">User</label><br/>
    <form:input path="user.id" id="user"/>
    <form:errors path="user.id"/>
    <br/>

    <label for="animal">Animal</label>
    <form:input path="animal.id" id="animal"/>
    <form:errors path="animal.id"/>
    <br/>

    <label for="animal">Classis:</label>
    <form:select path="animal.classis" id="animals"
         items="${classisOptions}"/>
    <form:errors path="animal.classis"/>
    <br/>

    <label for="location">Location:</label>
    <form:input path="location.id" items="${location}" itemLabel="name" itemValue="id" required="true"/>
    <form:errors path="location.id"/>
    <br/>

    <label for="location">Biome:</label>
    <form:input path="location.biome"/>
    <form:errors path="location.biome"/>
    <br/>

    <label for="description">Description</label>
    <form:input path="description" id="description"/>
    <form:errors path="description"/>
    <br/><br/>

    <button type="submit">Submit</button>

</form:form>
</body>
</html>
