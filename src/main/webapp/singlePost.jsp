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

    <title>${post.title}</title>
</head>
<body>
<%@include file="include/navigation.jsp" %>

<div class="container">

    <div class="row">

        <div class="col-xs-12 col-sm-10 col-md-8" style="border: 0px solid #999">


                <c:if test="${answeredPost != null}">
                    <div class="col-md-9" style="border: 1px solid #eee;
                                border-radius: 5px;
                                padding: 15px;
                                color: #567;
                                margin-left: 25px;
                                margin-top: 17px;
                                margin-bottom: 17px;">
                        ${answeredPost.title}<br>
                        ${answeredPost.body}<br>
                        <small><a href="/post/?id=${answeredPost.id}">link</a> | by: <a href="/user/?name=${answeredPost.username}">${answeredPost.username}</a></small>
                    </div><br>
                </c:if>

            <div class="col-md-10" style="border: 1px solid #eee;
                                          padding: 20px;
                                          margin: 10px;
                                          border-radius: 5px;
                                          box-shadow: 2px 1px 2px 1px #eee;
                                          ">
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
                                        <a href="javascript:submitform();" onclick="alert('you are sure? this can not be undone!')">delete</a>

                                </form>

                            </c:when>
                            <c:otherwise>
                                <a href="javascript:addForm();">answer</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>

                    <c:if test="${u == null}">
                        <span style="color: #888">please login to perform some action</span>
                    </c:if>
                </small>

            </div>

        <div id="ans" class="col-md-8" style="display: none;
                                              margin-left: 20px;
                                              padding: 10px;
                                              ">
            <br>

            <form name="createForm" action="/create" method="post">
                <input type="text" name="title" placeholder="title" class="form-control"><br>
                <input type="hidden" name="answeredPostId" value="${post.id}">
                <textarea rows="10" name="body" onkeyup=limiter() placeholder="Don&#39;t let that shit happen" class="form-control"></textarea><br>
                <script type="text/javascript">
                    document.write("<input type=text name=limit size=4 readonly value="+count+">");
                </script> <br>
                <input type="submit" value="ab die post" class="btn btn-default navbar-btn">
            </form>
        </div>

        <br><br>

            <c:forEach items="${answers}" var="a">
                <div class="col-md-9" style="border: 1px solid #eee;
                                             border-radius: 5px;
                                             padding: 15px;
                                             color: #567;
                                             margin-left: 25px;
                                             margin-top: 17px;
                                             margin-bottom: 7px;
                                             ">
                    ${a.title}<br>
                    ${a.body}<br>
                    <small><a href="/post/?id=${a.id}">link</a> | by: <a href="/user/?name=${a.username}">${a.username}</a></small><br>
                </div>
            </c:forEach>

        </div>

    </div>
</div>

<!-- Bootstrap Core Javascript  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>

</body>
</html>