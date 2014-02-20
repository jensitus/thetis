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
    <input type="text" name="title"><br>
    <textarea cols="40" rows="15" name="body" placeholder="Enter Text Here"></textarea><br>
    <input type="submit">
</form>

<br>
<h4>${title}</h4>

${body}


