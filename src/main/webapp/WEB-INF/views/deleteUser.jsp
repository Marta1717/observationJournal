<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete User</title>

    <style>

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            margin-left: 35%;
            margin-right: 35%;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }

        a:hover {
            text-decoration: underline;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }
    </style>

</head>
<body>
<div class="container">
    <h2>Delete User</h2>
    <h3>Are you sure you want to delete the user with username: <strong>${user.username}</strong>?</h3>
    <%--@elvariable id="user" type=""--%>
    <form:form method="post" action="/user/delete/">
        <input type="hidden" name="id" value="${user.id}"/>
        <p>Username:<br/> ${user.username}</p>
        <p>Email:<br/> ${user.email}</p>
        <br/><br/><br/>
        <strong><a href="${pageContext.request.contextPath}/user/list">CANCEL</a></strong><br/><br/>
        <strong>
            <button type="submit" name="action">DELETE</button>
        </strong>
    </form:form>
</div>
</body>
</html>
