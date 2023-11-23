<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>

</head>
<body>
    <c:choose>
        <c:when test="${state == '1'}">
            <h1>아이디가 존재하지 않습니다.</h1>
            <a href="/hw/s-login-form">다시 로그인하기</a>
        </c:when>
        <c:when test="${state == '2'}">
            <h1>비밀번호가 틀립니다.</h1>
            <a href="/hw/s-login-form">다시 로그인하기</a>
        </c:when>
        <c:otherwise>
            <h1>어서오세요 ${id}님</h1>
            <a href="/hw/s-login-form">다시 로그인하기</a>
        </c:otherwise>
    </c:choose>
    
</body>
</html>