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

<c:forEach items="${posts}" var="post">
        ${post.id}<br>
        ${post.title}<br>
        ${post.body}<br>
        ${post.username}<br><br>

</c:forEach><br><br>

</body>
</html>