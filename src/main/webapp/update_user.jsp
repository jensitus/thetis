<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">

    <h4>update your profile</h4>
    <br>
    <div class="form-group" style="width: 250px">
        <form action="../update_user" method="post" class="form-signin" role="form">

            <input type="text" name="username" value="${user.username}" class="form-control"><br>
            <textarea cols="31" rows="3" name="description" class="form-control">${user.description}</textarea><br>
            <input type="submit" value="submit" class="btn btn-default">

        </form>

        <br><br>
        ${error}
    </div>
</div>

</body>
</html>
