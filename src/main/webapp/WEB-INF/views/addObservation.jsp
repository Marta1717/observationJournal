<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Observation</title>
</head>
<body>
<%--@declare id="animal"--%><h2>Add Observation</h2>
<%--@declare id="location"--%>
<%--@elvariable id="observation" type=""--%>
<form:form method="post" modelAttribute="observation">

    User<br/>
    <form:select path="user.id" id="user" items="${users}" itemLabel="username" itemValue="id"/>
    <form:errors path="user.id"/>
    <br/>

    <label for="animal">Select animal:</label>
    <form:select path="animal.id" items="${animals}" itemLabel="name" itemValue="id"/>
    <form:errors path="animal.id"/>
    <br/>

<%--    już raczej zbędne, to samo jest w animalu, tam chyba lepiej pasuje--%>
<%--    <label for="animal">Classis:</label>--%>
<%--    <form:select path="animal.classis" id="animals"--%>
<%--         items="${classisOptions}"/>--%>
<%--    <form:errors path="animal.classis"/>--%>
<%--    <br/>--%>

    <label for="location">Location:</label>
    <form:select path="location.biome" items="${locations}" itemLabel="name" itemValue="id"/>
    <form:errors path="location.biome"/>
    <br/>

    <label for="location">Biome:</label>
    <form:select path="location.id" items="${locations}" itemLabel="name" itemValue="id"/>
    <form:errors path="location.id"/>
    <br/>

    <label for="description" rows="6">Notes</label>
    <form:input path="description" id="description"/>
    <form:errors path="description"/>
    <br/><br/>

    <button type="submit">Submit</button>

</form:form>
</body>
</html>
