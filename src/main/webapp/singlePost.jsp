<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../resources/js/answer.js">
    </script>
    <script type="text/javascript">
        function submitform(){
            document.deleteForm.submit();
        }
    </script>

    <title>${post.title}</title>
</head>
<body>
<%@include file="include/navigation.jsp" %>

<div class="container">

    <div style="width: 400px">

        <div>
            <a href="/post/?id=${answeredPost.id}">${answeredPost.title}</a><br>
            ${answeredPost.body}<br>
            <small>by: <a href="/user/?name=${answeredPost.username}">${answeredPost.username}</a></small>
        </div><br>

        <h4>${post.title}</h4>
        ${post.body}<br>
        <small>
            by: <a href="../user/?name=${post.username}">${post.username}</a>

            <c:if test="${u != null}">
                <c:choose>
                    <c:when test="${u.equals(post.username)}">
                        <a href="../updatePost/?id=${post.id}">update</a>
                        <a href="javascript:addForm();">answer</a>

                        <form name="deleteForm" action="../deletePost" method="post" style="display: inline">
                            <input type="hidden" name="id" value="${post.id}">
                            <a href="javascript:submitform();" onclick="alert('true? this can not be undone!')">delete</a>
                        </form>

                    </c:when>
                    <c:otherwise>
                        <a href="javascript:addForm();">answer</a>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:if test="${u == null}">
                login
            </c:if>
        </small>

        <div id="ans" style="display: none">
            <br>
            <form action="/create" method="post">
                <input type="text" name="title" placeholder="title" class="form-control"><br>
                <input type="hidden" name="answeredPostId" value="${post.id}">
                <textarea cols="40" rows="15" name="body" placeholder="Don&#39;t let that shit happen" class="form-control"></textarea><br>
                <input type="submit" value="ab die post" class="btn btn-default navbar-btn">
            </form>
        </div>

        <div class="row">
            <c:forEach items="${answers}" var="a">
                <div class="col-md-6 col-md-offset-3">
                    <a href="/post/?id=${a.id}"><b>${a.title}</b></a><br>
                    ${a.body}<br>
                    <small>by: <a href="/user/?name=${a.username}">${a.username}</a></small><br>
                </div>
            </c:forEach>
        </div>

    </div>
</div>

</body>
</html>