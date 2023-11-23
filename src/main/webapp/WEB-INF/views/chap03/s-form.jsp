<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Web study</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        h1, a, .bottomNav {
            font-family: "NEXON Football Gothic", serif;
        }
        .idpw {
            border: black 1px solid;
            display: flex;
            flex-direction: column;
            padding: 30px;
            border-radius: 10px;
        }
        form {
            display: flex;
            justify-content: center;
            flex-direction: column;
        }
        .bottomNav {
            margin-top: 10px;
            border-radius: 5px;
            height: 40px;
            background-color: cyan;
            border: none;
        }
    </style>
</head>
<body>
    <h1>Login</h1>
    <form action="/hw/s-login-check" method="post">
        <div class="idpw">
            <a>ID </a> <label>
            <input type="text" name="id">
        </label><br>
            <a>PASSWORD </a> <label>
            <input type="password" name="password">
        </label><br>
        </div>
        <input type="submit" class="bottomNav" value="로그인">
    </form>
</body>
</html>