<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exam</title>
</head>
<body>
<h1>Вычисление ИКТ</h1>
<div class="registration" style="display: flex; flex-direction: column">
    <form action="Servletik">
        <p><input type="text" name="weight" placeholder="Введите вес (кг)" size="18" required/></p>
        <p><input type="text" name="height" placeholder="Введите рост(см)" size="18" required/></p>
        <p>
            <button type="submit" name="action" value="login">Отправить</button>
        </p>
    </form>
</div>
</body>
</html>