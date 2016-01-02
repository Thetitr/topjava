<%--
  Created by IntelliJ IDEA.
  User: Thetitr
  Date: 02.01.2016
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>MealList</title>
</head>
<body>
<h2>User list</h2>

<table  border="2" >
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Превышено</th>
    </tr>
    <c:forEach items="${list}" var="meal">

        <c:choose>
            <c:when test="${meal.isExceed()}">
                <c:set var="color" value="red"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="color" value="green"></c:set>
            </c:otherwise>
        </c:choose>
        <tr bgcolor="${color}">
            <td>${meal.getDateTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
            <td >${meal.isExceed()}</td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
