<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>

<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <sec:authentication var="error" property="error" />
    <style>
        .block{
    width:200px;
            height:200px;
            position: fixed;
            top: 50%;
            left: 50%;
            margin-top: -100px;
            margin-left: -100px;

        }
    </style>

</head>
<body class="text-center">

<div class="block">
    <form action="${pageContext.request.contextPath}/signIn" method="post">


        <c:if test="${param.auth eq 'failure'}">
            <div class="error">
                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
            </div>
        </c:if>
        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" name="username" >
            <label for="floatingInput">Email адрес</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" name="password">
            <label for="floatingPassword">Пароль</label>
        </div>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Войти</button>
        <a class="google" href="${pageContext.request.contextPath}/signIn-google" title="Войти через Google">Войти через Google</a>
        <p class="mt-5 mb-3 text-muted">© 2021</p>
    </form>
</div>


</body>
</html>