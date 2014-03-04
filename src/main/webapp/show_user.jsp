<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-4" style="border: 1px solid #eee; border-radius: 5px; padding: 10px">
            <b>${user.username}</b><br>
            <small>${user.description}</small>

            <hr>
            <c:if test="${u == user.username}">
                <small><a href="../update_user/?name=${user.username}">update your profile</a></small>
            </c:if>

            <c:if test="${u != user.username}">
                <c:choose>
                    <c:when test="${c == true}">
                        <form action="/disconnect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="disconnect" class="btn btn-toolbar">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/connect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="connect" class="btn btn-info">
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <hr>
            Posts by ${user.username}: ${cp}

        </div>
    </div>


                <br>
        <c:forEach items="${posts}" var="post">
            <div style="width: 400px">
                <a href="../post/?id=${post.id}">${post.title}</a><br>
                ${post.body}<br>
                <small>by: <a href="../user/?name=${post.username}">${post.username}</a></small>
                <hr>
            </div>

        </c:forEach>


</div>

</body>
</html>