<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

<c:choose>
    <c:when test="${u.equals(post.username)}">
        <form action="../updatePost" method="post">
            <input type="hidden" name="id" value="${post.id}">
            <input type="text" name="title" value="${post.title}" class="input"><br>
            <textarea cols="40" rows="15" name="body" class="input">${post.body}</textarea><br>
            <input type="submit" value="update" class="button">
        </form>
    </c:when>
    <c:otherwise>
        ${u}, cocksucker, your advantage to mess around lead nowhere
    </c:otherwise>
</c:choose>

</body>
</html>