<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../resources/js/answer.js">
    </script>
    <title>create post</title>
    <script type="text/javascript">

        //Edit the counter/limiter value as your wish
        var count = "1000";   //Example: var count = "175";
        function limiter(){
            var tex = document.createForm.body.value;
            var len = tex.length;
            if(len > count){
                tex = tex.substring(0,count);
                document.createForm.body.value =tex;
                return false;
            }
            document.createForm.limit.value = count-len;
        }

    </script>
</head>
<body>
<%@include file="include/navigation.jsp" %>


<div class="container">

    <h4>create a new post</h4>
    <br><span style="font-size: 13px; color: #ff072e">${error}</span>
    <div class="form-group" style="width: 400px">

        <form name="createForm" action="create" method="post">
            <input type="text" name="title" placeholder="title" class="form-control"><br>
            <textarea cols="40" rows="15" name="body" onkeyup=limiter() placeholder="Here you can free yourself from the junk that weighs down your soul" class="form-control"></textarea><br>
            <script type="text/javascript">
                document.write("<input type=text name=limit size=4 readonly value="+count+">");
            </script> <br>
            <input type="submit" value="ab die post" class="btn btn-default navbar-btn">
        </form>

    </div>

    <!-- Script by hscripts.com
    <form name="myform" METHOD=POST>
        <textarea name=comment wrap=physical rows=3 cols=40 onkeyup=limiter()></textarea><br>
        <script type="text/javascript">
            document.write("<input type=text name=limit size=4 readonly value="+count+">");
        </script>
    </form>
    Script by hscripts.com -->

    <br>


    ${title}
    ${body}
    ${answeredPostId}

</div>


<!-- Bootstrap Core Javascript  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>
