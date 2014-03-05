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
    <div class="row" style="width: 700px; border-bottom: 1px solid #ccc; padding: 1px">
        <div class="col-md-6" style="border: 0px solid #999; padding: 2px">
                <!--
                style="border: 1px solid #eee;
                    border-radius: 5px;
                    padding: 10px;
                    width: 350px;
                    ">
                -->
            <h4 style="color: #000">${user.username}</h4>
            <span>${user.description}</span>
        </div>

        <div class="col-md-5" style="border: 0px solid #999; padding: 15px">

            <c:if test="${u == user.username}">
                <small>
                    <a href="../update_user/?name=${user.username}">update profile</a><br>
                    <a href="../create">create post</a>
                </small>
            </c:if>

            <c:if test="${u != user.username}">
                <c:choose>
                    <c:when test="${c == true}">
                        <form action="/disconnect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="disconnect" class="btn btn-group">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/connect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="connect" class="btn btn-group-lg">
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:if>

        </div>
    </div>
    <div class="row" style="width: 700px;border-bottom: 1px solid #ccc; padding-top: 0px">
        <div class="col-md-12" style="border: 0px solid #999; padding: 4px">
            <small>
                Posts by ${user.username}: ${cp}<br>
                Connected with:
                <c:forEach items="${connectedWith}" var="cW">
                    <a href="../user/?name=${cW.username}">${cW.username}</a>
                </c:forEach><br>
                Connected By:
                <c:forEach items="${connectedBy}" var="cB">
                    <a href="../user/?name=${cB.username}">${cB.username}</a>
                </c:forEach>
            </small>
        </div>

    </div>

        <br>

        <c:forEach items="${posts}" var="post">
            <div style="width: 400px">
                <h4><a href="../post/?id=${post.id}">${post.title}</a></h4>
                ${post.body}<br>
                <small>by: <a href="../user/?name=${post.username}">${post.username}</a></small>
                <hr>
            </div>
        </c:forEach>



</div>

</body>
</html>