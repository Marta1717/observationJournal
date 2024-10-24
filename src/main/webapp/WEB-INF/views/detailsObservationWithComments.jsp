<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header-links.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Details Observation</title>

  <style>
    .container {
      background-color: rgba(255, 255, 255, 0.9);
      padding: 30px;
      margin-left: 15%;
      margin-right: 15%;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      width: 90%;
      max-width: 1200px;
      overflow-y: auto;
      height: 80vh;
      text-align: left;
    }

    h2 {
      text-align: center;
      color: #4CAF50;
      margin-top: 0;
    }

    h3 {
      text-align: left;
      color: #4cafaf;
    }

    table {
      width: 100%;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px; /* Zmniejsza przestrzeń wewnętrzną komórek */
      text-align: left;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    tr:hover {
      background-color: #ddd;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Details For Observation</h2>

  <p><strong>Observation Details:</strong></p>
  <p>User: ${observation.user.username}</p>
  <p>Location: ${observation.location.locationName}</p>
  <p>Animal: ${observation.animal.animalName}</p>

  <h3>Comments:</h3>
<%--  ${discussionList}--%>
  <c:if test="${not empty discussionList}">
  <c:forEach items="${discussionList}" var="discussion">
  <div class="comment">
    <p>${discussion.comment}</p>
    <p><strong>Created by:</strong> ${discussion.user.username} at ${discussion.createdAt}</p>
    <hr/>
  </div>
  </c:forEach>
  </c:if>
  <c:if test="${empty discussionList}">
  <p>No comments yet.</p>
  </c:if>

  <h3>Add New Comment:</h3>
  <form action="${pageContext.request.contextPath}/discussion/add" method="post">
    <label>
      <textarea name="comment" rows="5" cols="50" placeholder="Add your comment: "></textarea>
    </label>
    <input type="hidden" name="observationId" value="${observation.id}">
    <button type="submit">SUBMIT COMMENT</button>
  </form>

  <br/>
  <a href="${pageContext.request.contextPath}/observation/list/all"><h3>Back To Observation List</h3></a>
</div>
</body>
</html>
