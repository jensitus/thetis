<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>
<br>
${user.username}<br>
<small>${user.description}</small><br><br>

<c:if test="${u == user.username}">
    <a href="../update_user/?name=${user.username}">update</a>
</c:if>

<c:if test="${u != user.username}">
    <c:choose>
        <c:when test="${c == true}">
            <form action="/disconnect_user" method="post">

                <input type="hidden" name="reader" value="${u}">
                <input type="hidden" name="toRead" value="${user.username}">
                <input type="submit" value="disconnect">
            </form>
        </c:when>
        <c:otherwise>
            <form action="/connect_user" method="post">

                <input type="hidden" name="reader" value="${u}">
                <input type="hidden" name="toRead" value="${user.username}">
                <input type="submit" value="connect">
             </form>
        </c:otherwise>
    </c:choose>
</c:if>
<br><br>
<c:forEach items="${posts}" var="post">

        <a href="../post/?id=${post.id}">${post.title}</a><br>
        ${post.body}<br>
        <small>by: <a href="../user/?name=${post.username}">${post.username}</a></small><br><br>

</c:forEach><br><br>

</body>
</html>