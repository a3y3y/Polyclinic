<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <style>
        .block{
            width:400px;
            height:200px;
            padding: 20px;
            float: inherit;

        }

    </style>

</head>

<body class="text-center">
<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">На главную</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/medical_card">Мед. карта</a>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/users">Пользователи</a>
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

<div class="container">
    <table id="medical-note-table" border="1" >
        <thead>
        <tr>
            <th width="20%" class="text-center">Дата</th>
            <th width="20%" class="text-center">Запись</th>
            <th width="20%" class="text-center">Название</th>
            <th width="20%" class="text-center">Заболевание</th>
            <th width="40%" class="text-center">Описание</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>



    <div class="block">
        <form class="form">
            <div class="form-floating">
                <input type="date" class="form-control" id="floatingInput" name="date" required>
            </div>
            <select class="form-select" aria-label="Тип записи" name="type" id="type">
                <option>Диагноз</option>
                <option>Лечение</option>
                <option>Анализ</option>
                <option>Рецепт</option>
                <option>Больничный</option>
                <option>Прививка</option>
            </select>


            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput2" name="name" required>
                <label for="floatingInput2">Название записи</label>
            </div>

            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput1" name="illness" required>
                <label for="floatingInput1">Заболевание</label>
            </div>

            <div class="form-floating">
                <textarea type="text" class="form-control" id="floatingInput3" name="description" required></textarea>
                <label for="floatingInput3">Описание</label>
            </div>
            <label for="exampleDataList" class="form-label">Имя пациента</label>
            <input class="form-control" list="datalistOptions" id="exampleDataList" placeholder="Type to search..." name="passport" required>
            <datalist id="datalistOptions">
            </datalist>

            <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить запись</button>
            <p class="mt-5 mb-3 text-muted">© 2021</p>
        </form>
    </div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>

$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/medical_notes',
        type: 'get',
        success: function(response){
            var len = response.length;
            for(var i=0; i<len; i++){
                var date = response[i].date;
                var type = response[i].type;
                var name = response[i].name;
                var illness = response[i].illness;
                var description = response[i].description;
                var tr_str = "<tr>" +
                    "<td align='center'>" + date + "</td>" +
                    "<td align='center'>" + type + "</td>" +
                    "<td align='center'>" + name + "</td>" +
                    "<td align='center'>" + illness + "</td>" +
                    "<td align='center'>" + description + "</td>" +
                    "</tr>";

                $("#medical-note-table tbody").append(tr_str);
            }
        }
    });

    $.ajax({
        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/passports',
        type: 'get',
        success: function(response){
            var len = response.length;
            for(var i=0; i<len; i++){
                var lastName = response[i].lastName;
                var firstName = response[i].firstName;
                var number = response[i].number;

                 var str = "<option id=passport value=" + number + ">" + lastName + " " + firstName + "</option>";
                $("#datalistOptions").append(str);
            }
        }
    });
});



function handleFormSubmit(event) {
  event.preventDefault();
  var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/medical_notes';

  const data = new FormData(event.target);

  const formJSON = Object.fromEntries(data.entries());
                console.log(formJSON);
                 $.ajax({
         url:  url,
         type: 'post',
         data: formJSON,
         statusCode: {
         201: function() {
            alert('Запись добавлена');
         },
        }
    });
    }

document.querySelector('.form').addEventListener('submit', handleFormSubmit);
</script>

</body>
</html>