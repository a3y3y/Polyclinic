<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>

<html lang="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Главная страница сервера</title>
</head>
<body>
<nav class="navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">
        <img src="${pageContext.request.contextPath}/resources/img/img_3.svg" width="30" height="30"
             class="d-inline-block align-top" alt="" loading="lazy">
    </a>
    <p>Поликлиника</p>

    <sec:authorize access="isAuthenticated()">
        <div>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/cabinet';">Личный кабинет
            </button>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/logout';">Выйти
            </button>
        </div>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <div>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/registration';">Регистрация
            </button>
            <button type="button" class="btn btn-primary"
                    onclick="location.href='${pageContext.request.contextPath}/signIn';">Вход
            </button>
        </div>
    </sec:authorize>
</nav>


</body>
</html>