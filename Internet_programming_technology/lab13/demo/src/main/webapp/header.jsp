<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <%
        String user = (String) session.getAttribute("Name");
    %>
    <a style="color:black" href="controller?command=sign_out">Выйти</a>
    <p><%= user %></p>
</div>
