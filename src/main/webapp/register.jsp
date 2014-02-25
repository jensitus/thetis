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

<form action="register" method="post">

    <input type="text" name="username" placeholder="username" class="input"><br>
    <input type="password" name="password" placeholder="password" class="input"><br>
    <textarea cols="31" rows="3" name="description" placeholder="Say few words about you, e.g. 'survived childhood' or something like that" class="input"></textarea><br>
    <input type="submit" value="press" class="button">

</form>

</body>
</html>
