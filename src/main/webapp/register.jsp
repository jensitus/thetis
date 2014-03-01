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


        <div class="form-group" style="width: 250px">
            <form action="register" method="post" class="form-signin" role="form">

                <input type="text" name="username" placeholder="username" class="form-control"><br>
                <input type="password" name="password" placeholder="password" class="form-control"><br>
                <textarea cols="31" rows="3" name="description" placeholder="Say few words about you, e.g. 'survived childhood' or something like that" class="form-control"></textarea><br>
                <input type="submit" value="press" class="btn btn-default">

            </form>
        </div>

    </div>




</body>
</html>
