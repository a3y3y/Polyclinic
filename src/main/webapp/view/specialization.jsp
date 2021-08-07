<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Специальность</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">

    <sec:authentication var="user" property="principal"/>
    <style>
        .btn{
            display: block;
            width: 100px;
        }
        .form{
            width: 800px;
            float: up;
            padding: 20px;
        }
        .input-group{
            float: left;
            padding: 20px;

        }
        .col-md-4{
            float: left;
            padding: 20px;
        }
        .col-md-6{
            float: left;
            padding: 20px;
        }
        .col-md-3{
            float: up;
            padding: 20px;
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
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/users">Пользователи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/validate">Валидация</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/doctor">Врачи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/specializations">Специальности</a>
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

<form class="form" id="form">
    <div class="block1">
        <div class="col-md-5">
            <label for="validationDefault20" class="form-label">Специальность</label>
            <input type="text" class="form-control" id="validationDefault20" name="name" required>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">Описание</span>
            </div>
            <textarea class="form-control" aria-label="With textarea" name="description" required></textarea>
        </div>
    </div>
    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="post" id="post" form="form">Добавить</button>
    </div>
</form>

<table id="table">
    <thead>
    <tr>
        <th width="20%" align="center">Специальность</th>
        <th width="70%" align="center">Описание</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
        function postButton(){
        document.querySelector('.form').addEventListener('submit', handleFormSubmit);
        }



$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/specializations',
        type: 'get',
        success: function(response){
            var tbodyEl = $('tbody');

                tbodyEl.html('');

                response.forEach(function(specialization) {
                    tbodyEl.append('\
                        <tr>\
                            <td class="name">' + specialization.name + '</td>\
                            <td class="id" hidden>' + specialization.id + '</td>\
                            <td><textarea rows="4" id="formId" type="text" class="form-control">"' + specialization.description + '"</textarea></td>\
                            <td>\
                                <div class="btn">\
                                <button id="update-button" type="button" class="btn btn-primary">Обновить</button>\
                                <button id="delete-button" type="button" class="btn btn-outline-primary btn-sm">Удалить</button>\
                                </div>\
                            </td>\
                        </tr>\
                    ');
                });
        }
    });
});

        function handleFormSubmit(event) {
  event.preventDefault();

  var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/specializations';

  const data = new FormData(event.target);

  const formJSON = Object.fromEntries(data.entries());
                console.log(formJSON);
                 $.ajax({
         url:  url,
         type: 'post',
         data: formJSON,
         statusCode: {
         201: function() {
            alert('Специальность добавлена');
            window.location.reload();
         },
         406: function() {
             alert('Такая специальность уже существует')
         }
        }
    });
}
document.getElementById('post').addEventListener('click', postButton);



   $('table').on('click', '#update-button', function(event) {
        event.preventDefault();

        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();
        var newDesc = rowEl.find('#formId').val();
        const formJSON = ({description: newDesc});

        $.ajax({
            url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/specializations/' + id,
            method: 'PUT',
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify(formJSON),
            statusCode: {
                200: function() {
                    alert('Обновлено');
                    window.location.reload();
                    }
                }
        });
    });

    $('table').on('click', '#delete-button', function() {
        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();
        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/specializations/' + id;

        $.ajax({
                url: url,
                type: 'delete',
                statusCode: {
                    200: function() {
                        alert("Успешно удалено");
                        window.location.reload();
                    }
                }
            });
    });

        </script>

</body>
</html>