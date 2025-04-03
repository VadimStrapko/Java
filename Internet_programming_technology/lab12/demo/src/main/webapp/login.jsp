<%@ taglib prefix="customtags" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Логин</title>
    <link rel="stylesheet" href="styles.css" media="all">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <form action="login" method="post">
        <label>
            <span>Логин</span>
            <input type="text" name="login">
        </label>
        <p class="error">${loginError}</p>
        <label>
            <span>Пароль</span>
            <input type="password" name="password">
        </label>
        <p class="error">${passwordError}</p>
        <customtags:submitButtons text="вход"/>
    </form>
</div>
</body>
</html>