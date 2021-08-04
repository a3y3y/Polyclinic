<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">

    <sec:authentication var="user" property="principal"/>
    <style>
        .block1{
            border: 3px solid #fff;
            padding: 20px;
            width: 50%;
        }
        .passport-form{
            width: 60%;
            float: left;
            padding: 20px;
            border-right: 1px solid #0D6EFD;
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
            float: right;
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
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/doctor">Врачи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/specializations">Специальности</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/departments">Отделения</a>
            </li>
        </ul>
    </div>
    <div>
        <a><sec:authentication property="principal.username" /></a>
    </div>
</nav>


<div class="col-md-3">
    <button class="btn btn-primary" type="submit" name="post-address" id="post-info" form="address">Добавить врача</button>
</div>
<div class="col-md-3">
    <button class="btn btn-primary" type="submit" name="put-address" id="put-info" form="address">Обновить данные</button>
</div>

<div class="col-md-4">
    <input class="form-control1" list="datalistOptions" id="exDataList" placeholder="e-mail пользователя" name="eMail"
           required>
    <datalist id="datalistOptions">
    </datalist>
</div>



<form class="doctor-form" id="form">
    <div class="block1">
        <div class="col-md-6">
            <label for="validationDefault20" class="form-label">Последнее место работы</label>
            <input type="text" class="form-control" id="validationDefault20" name="doctorInfo.lastPlaceOfWork" required>
        </div>
        <div class="col-md-6">
            <label for="validationDefault21" class="form-label">Занимаемая должность</label>
            <input type="text" class="form-control" id="validationDefault21" name="doctorInfo.lastPosition" required>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">Опыт</span>
            </div>
            <input type="text" class="form-control" name="doctorInfo.experience">
            <div class="input-group-append">
                <span class="input-group-text">лет</span>
            </div>
        </div>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">Образование</span>
            </div>
            <textarea class="form-control" aria-label="With textarea" name="doctorInfo.education"></textarea>
        </div>
        <div class="col-md-4">
            <label for="validationDefault01" class="form-label"></label>
            <input type="text" class="form-control" id="validationDefault01" name="rating" required value="0" hidden>
        </div>
        <input type="text" class="form-hidden" id="userId" name="userId" value="0" hidden>
    </div>
</form>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
        function putButton2(){
        document.querySelector('.form').addEventListener('submit', handleFormSubmit4);
        }
        function postButton1(){
        document.querySelector('.form').addEventListener('submit', handleFormSubmit1);
        }



$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/eMails',
        type: 'get',
        success: function(response){
            var len = response.length;
            for(var i=0; i<len; i++){
                var eMail = response[i].eMail;
                var id = response[i].id;
                var str = "<option id=email-id data-id=" + id + " value=" + eMail + "></option>";
                $("#datalistOptions").append(str);
            }

        }
    });
});

        document.getElementById('exDataList').addEventListener('input', function () {
    var g = $('#exDataList').val();
    var id = $('#datalistOptions').find('option[value="' +g + '"]').attr('data-id');
    var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/' + id;
    $.ajax({
        url:  url,
        type: 'get',
        success: function(response){
            if(response.doctorInfo != null){
                    $("[name='lastPlaceOfWork']").val(response.doctorInfo.lastPlaceOfWork);
                    $("[name='lastPosition']").val(response.doctorInfo.lastPosition);
                    $("[name='experience']").val(response.doctorInfo.experience);
                    $("[name='education']").val(response.doctorInfo.education);
                    $("[name='rating']").val(response.doctorInfo.rating);
                    $("[name='id']").val(response.doctorInfo.id);
            }
             else {
                    $("[class='form-control']").val(null);
                    $("[class='form-hidden']").val("0");
                    $("[class='form-select']").val(null);

            }

                }

        })
});



        function handleFormSubmit1(event) {
  event.preventDefault();

  var g = $('#exDataList').val();
  var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
            if(id == null){
                alert("Введите e-mail пользователя")
            } else {
  var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/doctor_info';
  $("[id='userId']").val(id);

  const data = new FormData(event.target);

  const formJSON = Object.fromEntries(data.entries());
                console.log(formJSON);
                 $.ajax({
         url:  url,
         type: 'post',
         data: formJSON,
         statusCode: {
         201: function() {
            alert('Доктор добавлен');
         },
         406: function() {
             alert('Доктор с такой информацией уже существует')
         }
        }
    });
    }
}
document.getElementById('post-info').addEventListener('click', postButton1);







        function handleFormSubmit4(event) {
        event.preventDefault();

  var g = $('#exDataList').val();
  var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
  var passportId = document.getElementById('passportId').value;
            if(id == null){
                alert("Введите e-mail пользователя")
            } else {
  var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/doctor_info/' + passportId;
  $("[name='passportUserId']").val(id);

  const data = new FormData(event.target);

  const formJSON = Object.fromEntries(data.entries());

  const stringJson = JSON.stringify(formJSON);
                $.ajax({
        contentType: 'application/json',
        url:  url,
        type: 'put',
        dataType: "json",
        data: stringJson,
        statusCode: {
         200: function() {
            alert('Информация обновлена');
         }
        }
    });
}
        }
document.getElementById('put-info').addEventListener('click', putButton2);

        </script>

</body>
</html>