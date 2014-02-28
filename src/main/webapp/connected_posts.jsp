<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>
<br>


<br><br>
<c:forEach items="${contactPosts}" var="cp">
    <a href="post/?id=${cp.id}">${cp.title}</a><br>
    ${cp.body}<br>
    <small>by: <a href="../user/?name=${cp.username}">${cp.username}</a></small><br><br>

</c:forEach>

</body>
</html>
