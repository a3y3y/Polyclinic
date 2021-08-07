<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="ru">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <title>Пользователи</title>
    <style>
     .block{
    width:1200px;

            position:absolute;
            top: 30%;
            left: 40%;
            margin-top: -100px;
            margin-left: -100px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">На главную</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/medical_card">Мед. карта</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Анализы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Прививки</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Личные данные</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/users">Пользователи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/validate">Валидация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/doctor">Врачи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/specializations">Специальности</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/departments">Отделения</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/tickets">Талоны</a>
            </li>
        </ul>
    </div>
    <div>
        <a><sec:authentication property="principal.username" /></a>
    </div>
</nav>

<div>
    <p class="greeting-id">Your e-mail is </p>
    <p class="greeting-content">Your phone number is </p>
</div>

<div class="container">
    <table id="userTable" border="1" >
        <thead>
        <tr>
            <th width="20%" align="center">Email</th>
            <th width="20%" align="center">Телефон</th>
            <th width="20%" align="center">Фамилия</th>
            <th width="20%" align="center">Имя</th>
            <th width="20%" align="center">Паспорт</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<script>


$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users',
        type: 'get',
        success: function(response){
            var len = response.length;
            for(var i=0; i<len; i++){

                var eMail = response[i].eMail;
                var phoneNumber = response[i].phoneNumber;
                if(response[i].passport != null){
                var lastName = response[i].passport.lastName;
                var firstName = response[i].passport.firstName;
                var number = response[i].passport.number;
                } else {
                    var lastName = "";
                    var firstName = "";
                    var number = "";
                }
                var tr_str = "<tr>" +
                    "<td align='center'>" + eMail + "</td>" +
                    "<td align='center'>" + phoneNumber + "</td>" +
                    "<td align='center'>" + lastName + "</td>" +
                    "<td align='center'>" + firstName + "</td>" +
                    "<td align='center'>" + number + "</td>" +
                    "</tr>";

                $("#userTable tbody").append(tr_str);
            }

        }
    });
});
</script>

</body>
</html>