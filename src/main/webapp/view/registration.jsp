<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <title>Страница регистрации</title>
    <style>
        .block{
            width:250px;
            height:200px;
            position: fixed;
            top: 50%;
            left: 50%;
            margin-top: -100px;
            margin-left: -100px;

        }
    </style>
</head>
<body>
<div class="block">
    <c:choose>
        <c:when test="${requestScope.error}">
            <p style="color:red;">${requestScope.message}</p>
        </c:when>
        <c:otherwise>
            <h1 class="h3 mb-3 fw-normal">Зарегистрируйтесь</h1>
        </c:otherwise>
    </c:choose>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="eMail">
            <label for="floatingInput">Email адрес</label>
        </div>

        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
            <label for="floatingPassword">Пароль</label>
        </div>
        <div class="form-floating">
            <input type="tel" class="form-control" id="floatingTel" placeholder="Phone number" name="tel" required>
            <label for="floatingTel">Телефон</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit">Регистрация</button>
        <p class="mt-5 mb-3 text-muted">© 2021</p>
    </form>
</div>
</body>
</html>
