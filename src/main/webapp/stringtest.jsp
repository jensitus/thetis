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

<form action="stringtest" method="post">

    <input type="text" name="title" class="input"><br>
    <input type="text" name="body" class="input"><br>
    <input type="submit" value="OK" class="button">

</form>

<hr>
<br>
${title}<br>
${body}<br>
${b}

</body>
</html>