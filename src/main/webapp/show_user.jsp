<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">
    <div class="row" style="border: 0px solid #ccc; padding: 2px">
        <div class="col-md-6" style="border: 0px solid #999; padding: 2px">

            <h4 style="color: #000">${user.username}</h4>
            <span>${user.description}</span>

            <hr>

            <c:if test="${u == user.username}">
                <small>
                    <a href="../update_user/?name=${user.username}">update profile</a><br>
                    <a href="../create">create post</a>
                </small>
            </c:if>

            <c:if test="${u != user.username}">
                <c:choose>
                    <c:when test="${c == true}">
                        <form action="../disconnect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="disconnect" class="btn btn-group">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="../connect_user" method="post">
                            <input type="hidden" name="reader" value="${u}">
                            <input type="hidden" name="toRead" value="${user.username}">
                            <input type="submit" value="connect" class="btn btn-group-lg">
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <hr>

            <small>
                Posts by ${user.username}: ${cp}<br>
                connected with:
                ${connectedWith}
                <br>
                connected by:
                ${connectedBy}
            </small>

        </div>

        <div class="col-md-5" style="border: 0px solid #999; padding: 15px">

            <c:forEach items="${posts}" var="post">
                <c:set var="pbody" value="${fn:split(post.body, '.')}" />
                <div style="width: 400px">
                    <h4><a href="../post/?id=${post.id}">${post.title}</a></h4>
                    <c:choose>
                        <c:when test="${fn:length(pbody) > 6}">
                            ${pbody[0]}.
                            ${pbody[1]} ...
                            <a href="../post/?id=${post.id}">read more ...</a><br>
                        </c:when>
                        <c:otherwise>
                            ${post.body}<br>
                        </c:otherwise>
                    </c:choose>

                    <small>by: <a href="../user/?name=${post.username}">${post.username}</a></small>
                    <hr>
                </div>
            </c:forEach>

        </div>

        <div class="col-md-12" style="border: 0px solid #999; padding: 4px">

        </div>

    </div>

        <br>



      ...

</div>
<!-- Bootstrap Core Javascript  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>

</body>
</html>