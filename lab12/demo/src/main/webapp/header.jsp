<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="test.jsp">Тест</a>
<div class="header">
    <%
        Cookie[] cookies = request.getCookies();
        Cookie user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                user = cookie;
                break;
            }
        }
        if (user == null) {
            user = new Cookie("user", "");
        }
    %>
    <p><%= user.getValue() %></p>
</div>
