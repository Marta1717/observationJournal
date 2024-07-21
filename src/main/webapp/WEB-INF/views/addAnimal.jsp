<%--
  Created by IntelliJ IDEA.
  User: marta-ol
  Date: 19.07.2024
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Animal</title>
</head>
<body>
<h2>Add Animal</h2>
<%--@elvariable id="animal" type=""--%>
<form:form modelAttribute="animal" method="post">
    
    
<%--    User<br/>--%>
<%--    <form:select path=""--%>

    Classis:<br/>
    <form:select path="animalClassis" items="${classis}"/>
    <form:errors path="animalClassis"/>

    <br/>
    Name <br/>
    <form:input path="name"/>
    <form:errors path="name"/>
    <br/>
    Additional description <br/>
    <form:textarea path="animalDescription" rows="4"/>
    <form:errors path="animalDescription"/>
    <br/>

    <br/><br/>
    <button type="submit">Submit</button>
</form:form>
</body>
</html>