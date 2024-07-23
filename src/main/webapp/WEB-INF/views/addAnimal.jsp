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


<br/><br/>
<%--@elvariable id="animal" type=""--%>
<form:form modelAttribute="animal" method="post">

<%--    <a href="${pageContext.request.contextPath}/animal/edit/form/{id}">Edit Animal</a>--%>
<%--    <a href="${pageContext.request.contextPath}/animal/delete/form{id}">Delete Animal</a>--%>
    <a href="${pageContext.request.contextPath}/animal/list">Animal List</a>
    <br/><br/><br/>

    Select user<br/>
    <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>
    <form:errors path="user.id"/>
    <br/><br/>

    Select classis:<br/>
    <form:select path="animalClassis" items="${classis}"/>
    <form:errors path="animalClassis"/>
    <br/><br/>

    Name <br/>
    <form:input path="name"/>
    <form:errors path="name"/>
    <br/><br/>

    Additional description <br/>
    <form:textarea path="animalDescription" rows="4"/>
    <form:errors path="animalDescription"/>
    <br/><br/>

    <button type="submit">Submit</button>

</form:form>
</body>
</html>