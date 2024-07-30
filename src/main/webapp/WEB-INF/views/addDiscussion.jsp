<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add discussion</title>

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

        /*h2 {*/
        /*    text-align: center;*/
        /*    color: #4CAF50;*/
        /*}*/

        /*h3 {*/
        /*    text-align: left;*/
        /*    color: #4cafaf;*/
        /*}*/

        /*a {*/
        /*    color: #4CAF50;*/
        /*    text-decoration: none;*/
        /*    margin-right: 10px;*/
        /*}*/

        /*a:hover {*/
        /*    text-decoration: underline;*/
        /*}*/

        /*label {*/
        /*    display: block;*/
        /*    margin: 10px 0 5px;*/
        /*}*/

        /*input, select, textarea, button {*/
        /*    width: 100%;*/
        /*    padding: 10px;*/
        /*    margin: 5px 0 15px;*/
        /*    border: 1px solid #ccc;*/
        /*    border-radius: 5px;*/
        /*}*/

        /*button {*/
        /*    background-color: #4CAF50;*/
        /*    color: white;*/
        /*    border: none;*/
        /*    cursor: pointer;*/
        /*}*/

        /*button:hover {*/
        /*    background-color: #45a049;*/
        /*}*/
    </style>

</head>
<body>
<div class="container">
    <h2>Add new comment</h2>
    <br/><br/><br/>
    <a href="${pageContext.request.contextPath}/discussion/list"><h3>Discussion List</h3></a>
    <br/><br/><br/>
    <%--@declare id="location"--%>
    <%--@elvariable id="discussion" type=""--%>
    <form:form modelAttribute="discussion" method="post">

<%--        zbędne, do komentarza wchodzi się przez obserwację--%>
<%--        <div class="mb-3">--%>
<%--            Select observation:<br/>--%>
<%--            <form:select path="observation.id" id="location" items="${observations}" itemLabel="id" itemValue="id"/>--%>
<%--            <form:errors path="observation.id"/>--%>
<%--        </div>--%>

        <div class="mb-3">
            Comment: <br/>
            <form:textarea path="comment" rows="7"/>
            <form:errors path="comment"/>
        </div>

        <div class="mb-3">
            <button type="submit">Add comment</button>
        </div>
    </form:form>
</div>
</body>
</html>
