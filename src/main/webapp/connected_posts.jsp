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
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">

    <h4>the posts i am connected with</h4>

    <c:forEach items="${contactPosts}" var="cp">
        <div style="width: 400px">
            <hr>
            <a href="post/?id=${cp.id}">${cp.title}</a><br>
            <c:set var="pbody" value="${fn:split(cp.body, '.')}" />
            <c:choose>
                <c:when test="${fn:length(pbody) > 6}">
                    ${pbody[0]}.
                    ${pbody[1]} ...
                    <a href="post/?id=${cp.id}">read more ...</a><br>
                </c:when>
                <c:otherwise>
                    ${cp.body}<br>
                </c:otherwise>

            </c:choose>

            <small>by: <a href="user/?name=${cp.username}">${cp.username}</a></small><br>

        </div>

    </c:forEach>

</div>

<!-- Bootstrap Core Javascript  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
