<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="kdn" uri="customtags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p><script>alert('hello')</script></p>
    <p><c:out value="<script>alert('hello')</script>"/></p>
    <c:if test="${9 > 12}">
        <p>9 > 12</p>
    </c:if>
    <c:if test="${9 < 12}">
        <p>9 < 12</p>
    </c:if>
    <c:import url="data.xml" var="url"/>
    <x:parse var="doc" xml="${url}"/>
    <x:forEach var="n" select="$doc/books/book">
        <p>
            <x:out select="$n/title"/>
            <x:out select="$n/author"/>
            <x:set var="a" select="$n/author"/>
            <c:set var="b" value="privet"/>
            ${fn:contains(b, "pr")}
            ${fn:substring(b, 0, 3)}
        </p>
    </x:forEach>
    <kdn:submitButtons text="кнопка"/>
</body>
</html>
