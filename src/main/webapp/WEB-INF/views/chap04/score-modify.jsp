<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <style>
        li {
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 1.4em;
        }

        form.score-main {
            width: 50%;
            margin: 0 auto 150px;
            padding: 20px;
            border: 2px solid orange;
            border-radius: 10px;
            box-shadow: 2px 2px 5px orangered;
            transform: translateY(200px);
        }

        a.list-btn {
            display: block;
            width: fit-content;
            text-decoration: none;
            background: rgb(83, 189, 83);
            color: white;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
        }
        .modify-btn {
            display: block;
            width: fit-content;
            text-decoration: none;
            background: rgb(225, 237, 100);
            color: black;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
        }
    </style>

</head>

<body>

<div class="wrap">
    <form action="/score/modifyScore/" class="score-main" method="post">
        <h1>${s.name}님 성적 정보 수정하기~</h1>
        <ul>
            <input type="hidden" name="stuNum" value="${s.stuNum}">
            <input type="hidden" name="name" value="${s.name}">
            <li># 국어 : <input type="text" name="kor" value="${s.kor}"></li>
            <li># 영어 : <input type="text" name="eng" value="${s.eng}"></li>
            <li># 수학 : <input type="text" name="math" value="${s.math}"></li>

        </ul>
        <div class="btn-group">
            <a class="list-btn" href="/score/list">목록</a>
            <button type="submit" class="modify-btn">수정</button>
        </div>
    </form>

</div>
</body>

</html>