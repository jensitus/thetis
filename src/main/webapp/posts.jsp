<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

<c:forEach items="${posts}" var="post">

        <a href="post/?id=${post.id}">${post.title}</a><br>
        ${post.body}<br>
        <small>by: <a href="user/?name=${post.username}">${post.username}</a></small><br><br>

</c:forEach><br><br>
</div>

</body>
</html>