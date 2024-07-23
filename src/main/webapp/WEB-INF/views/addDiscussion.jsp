<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add discussion</title>
</head>
<body>
<h2>Add new discussion</h2>
<%--@declare id="location"--%>
<%--@elvariable id="discussion" type=""--%>
<form:form modelAttribute="discussion" method="post">

    <br/><br/>
    Select observation:<br/>
    <form:select path="observation.id" id="location" items="${observations}" itemLabel="id" itemValue="id"/>
    <form:errors path="observation.id"/>
    <br/>

    <br/>
    Comment: <br/>
    <form:textarea path="comment" rows="7"/>
    <form:errors path="comment"/>
    <br/><br/>

    <button type="submit">Add comment</button>
</form:form>

</body>
</html>
