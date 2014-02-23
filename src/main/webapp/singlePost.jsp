<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>

<h4>${post.title}</h4>
${post.body}<br>
<small>
    by: <a href="../posts/?user=${post.username}">${post.username}</a>

    <c:if test="${u != null}">
        <c:choose>
            <c:when test="${u.equals(post.username)}">
                <a href="../updatePost/?id=${post.id}">update</a>
                <!--
                <form action="../updatePost" method="post">
                    <input type="hidden" name="id" value="${post.id}">
                    <input type="submit" value="update">
                </form>
                -->
                <form action="../deletePost" method="post">
                    <input type="hidden" name="id" value="${post.id}">
                    <input type="submit" value="delete" class="deletebutton">
                </form>
            </c:when>
            <c:otherwise>
                not authorized
            </c:otherwise>
        </c:choose>
    </c:if>

    <c:if test="${u == null}">
        login
    </c:if>
</small>

</body>
</html>