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
    ${success}
    <h4>sign in</h4>
    <br>
    <div class="form-group" style="width: 200px">
        <form action="login" method="post" class="form-horizontal" role="form">

            <input type="text" name="username" placeholder="username" class="form-control"><br>
            <input type="password" name="password" placeholder="password" class="form-control"><br>
            <input type="submit" value="press" class="btn btn-default">

        </form>
    </div>

</div>

<!-- Bootstrap Core Javascript  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
