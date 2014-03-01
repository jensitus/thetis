<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>
<div class="container">
    <c:choose>
        <c:when test="${u.equals(post.username)}">
            <div class="form-group" style="width: 400px">
                <form action="../updatePost" method="post" class="form-horizontal">

                    <input type="hidden" name="id" value="${post.id}">
                    <input type="text" name="title" value="${post.title}" class="form-control"><br>
                    <textarea cols="40" rows="15" name="body" class="form-control">${post.body}</textarea><br>
                    <input type="submit" value="update" class="btn btn-default">

                </form>
            </div>
        </c:when>
        <c:otherwise>
            ${u}, cocksucker, your advantage to mess around lead nowhere
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>