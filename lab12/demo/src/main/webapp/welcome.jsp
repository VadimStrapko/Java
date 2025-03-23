<%@ page import="db.DbItem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="customtags" uri="customtags" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Привет</title>
    <link rel="stylesheet" media="all" href="styles.css"/>
</head>
<body>
    <sql:setDataSource var="ds" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver" url="jdbc:sqlserver://localhost;database=java;trustServerCertificate=true" user="sa" password="1111"/>
<%--    <p>${ds}</p>--%>
    <sql:query dataSource="${ds}" var="rs">
        select * from Items
    </sql:query>
    <c:import url="header.jsp"/>
<%--    <c:set var="items" value="${requestScope.items}"/>--%>
    <table>
        <c:forEach items="${rs.rows}" var="item">
            <tr>
                <td><c:out value="${item.Name}"/></td>
                <td><fmt:formatDate value="${item.DateOfFound}" type="date"/></td>
                <td><c:out value="${item.Founder}"/></td>
            </tr>
        </c:forEach>
    </table>
    <customtags:tablica/>
    <form style="width: 400px" action="welcome" method="post">
        <label>
            Название
            <input type="text" name="name">
        </label>
        <label>
            Нашедший
            <input type="text" name="founder">
        </label>
        <input style="visibility: hidden" type="text" name="type" value="add">
        <input type="submit" value="Добавить">
    </form>
    <form style="width: 400px" action="welcome" method="post">
        <label>
            Название
            <input type="text" name="name2">
        </label>
        <input style="visibility: hidden" type="text" name="type" value="remove">
        <input type="submit" value="Удалить">
    </form>
    <form style="width: 400px" action="welcome" method="post">
        <label>
            Название
            <input type="text" name="name3">
        </label>
        <label>
            Новое название
            <input type="text" name="newName">
        </label>
        <label>
            Новое описание
            <input type="text" name="newFounder">
        </label>
        <input style="visibility: hidden" type="text" name="type" value="update">
        <input type="submit" value="Обновить">
    </form>
</body>
</html>
