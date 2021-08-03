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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <title>Аэропорты</title>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/medical_card">Моя карта</a>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/validate">Валидация пользователя</a>
            </li>
        </ul>
    </div>
    <div>
        <a><sec:authentication property="principal.username" /> </a>
    </div>
</nav>

<div>
    <p class="greeting-id">The e-mail is </p>
    <p class="greeting-content">The phone number is </p>
</div>

<div class="container">
    <table id="userTable" border="1" >
        <thead>
        <tr>
            <th width="25%">Email</th>
            <th width="25%">Phone number</th>
            <th width="25%">Last name</th>
            <th width="25%">First name</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<script>
$(document).ready(function() {
    $.ajax({
        url: "${pageContext.request.contextPath}/users/1"
    }).then(function(data) {
       $('.greeting-id').append(data.eMail);
       $('.greeting-content').append(data.phoneNumber);
    });
});

$(document).ready(function(){
    $.ajax({
        url: '${pageContext.request.contextPath}/users',
        type: 'get',
        success: function(response){
            var len = response.length;
            for(var i=0; i<len; i++){

                var eMail = response[i].eMail;
                var phoneNumber = response[i].phoneNumber;
                var lastName = response[i].passport.lastName;
                var firstName = response[i].passport.firstName;

                var tr_str = "<tr>" +
                    "<td align='center'>" + eMail + "</td>" +
                    "<td align='center'>" + phoneNumber + "</td>" +
                    "<td align='center'>" + lastName + "</td>" +
                    "<td align='center'>" + firstName + "</td>" +
                    "</tr>";

                $("#userTable tbody").append(tr_str);
            }

        }
    });
});
</script>

</body>
</html>