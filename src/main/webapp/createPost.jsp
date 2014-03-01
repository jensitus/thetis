<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">

    <div class="form-group" style="width: 400px">

        <form action="create" method="post">
            <input type="text" name="title" placeholder="title" class="form-control"><br>
            <textarea cols="40" rows="15" name="body" placeholder="Here you can free yourself from the junk that weighs down your soul" class="form-control"></textarea><br>
            <input type="submit" value="ab die post" class="btn btn-default navbar-btn">
        </form>

    </div>

    <br>
    ${error}

    ${body}

</div>


</body>
</html>
