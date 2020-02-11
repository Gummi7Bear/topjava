<%--
  Created by IntelliJ IDEA.
  User: Galina777
  Date: 11.02.2020
  Time: 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealsToList}" var="meal">
        <tr style="background-color:${meal.excess ? 'greenyellow' : 'red'}">
            <td>${meal.dateTime.format( DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"))}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
