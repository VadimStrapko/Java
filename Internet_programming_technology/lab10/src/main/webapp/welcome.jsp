<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 06.05.2024
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Welcome page</title>
    <link rel="stylesheet" href="wel.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<h3>Добрый день, ${name}</h3>
<div class="wrapper">

    <div class="table">
        <table>
            <tr>
                <th>ID</th>
                <th>Название игры</th>
                <th>Цена</th>
            </tr>
            <c:forEach items="${myDataList}" var="myData">
                <tr>
                    <td>${myData.id}</td>
                    <td>${myData.name}</td>
                    <td>${myData.cost}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="addTeam">
        <% if (request.getParameter("error3") != null && request.getParameter("error3").equals("true")) { %>
        <p style="color: red; margin: 0">Ошибка добавления</p>
        <% } %>
        <form action="ServletAdd">
            <input type="text" name="name" placeholder="Название игры" required>
            <input type="text" name="cost" placeholder="Цена" required>
            <input type="submit" name="btn2" value="Добавить">
        </form>
    </div>

    <div class="addTeam">
        <% if (request.getParameter("error4") != null && request.getParameter("error4").equals("true")) { %>
        <p style="color: red; margin: 0">Ошибка удаления</p>
        <% } %>
        <form action="ServletRemove">
            <input type="text" name="name" placeholder="Название игры" required>
            <input type="submit" name="btn2" value="Удалить">
        </form>
    </div>

</div>

</body>
</html>