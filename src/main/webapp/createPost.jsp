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

<form action="create" method="post">
    <input type="text" name="title" placeholder="title" class="input"><br>
    <textarea cols="40" rows="15" name="body" placeholder="Here you can free yourself from the junk that weighs down your soul" class="input"></textarea><br>
    <input type="submit" value="ab die post" class="button">
</form>

<br>
${error}

${body}


