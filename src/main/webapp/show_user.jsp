<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/css/style.css" rel="stylesheet" type="text/css"/>
    <title></title>
</head>
<body>
<%@include file="include/navigation.jsp" %>
<br>
${user.username}<br>
${user.description}<br><br>

u: reader: ${u}<br>
toRead: ${user.username}

<c:if test="${u != user.username}">
    <c:choose>
        <c:when test="${c == true}">
            <form action="/disconnect_user" method="post">

                <input type="hidden" name="reader" value="${u}">
                <input type="hidden" name="toRead" value="${user.username}">
                <input type="submit" value="disconnect">
            </form>
        </c:when>
        <c:otherwise>
            <form action="/connect_user" method="post">

                <input type="hidden" name="reader" value="${u}">
                <input type="hidden" name="toRead" value="${user.username}">
                <input type="submit" value="connect">
             </form>
        </c:otherwise>
    </c:choose>
</c:if>

    ${uvariable}
    <br>
    c: ${c}

</body>
</html>