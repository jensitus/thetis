<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <h4>the posts i am connected with</h4>

    <c:forEach items="${contactPosts}" var="cp">
        <div style="width: 400px">
            <hr>
            <a href="post/?id=${cp.id}">${cp.title}</a><br>
            ${cp.body}<br>
            <small>by: <a href="../user/?name=${cp.username}">${cp.username}</a></small><br>

        </div>

    </c:forEach>

</div>

</body>
</html>
