<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns:th="https://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <sec:authentication var="user" property="principal"/>
    <style>
        .block{
    width:400px;
            height:200px;
            position: fixed;
            top: 30%;
            left: 40%;
            margin-top: -100px;
            margin-left: -100px;

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
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/validate">Валидация</a>
            </li>
        </ul>
    </div>

</nav>


<form class="row g-3" action="${pageContext.request.contextPath}/cabinet/validate" th:action="@{/validate}"
      th:object="${user1}"
      th:object="${passport}" th:object="${address}" method="post">
    <div class="col-md-2">
        <input class="form-control" list="datalistOptions" id="exampleDataList" placeholder="e-mail" name="eMail" required>
        <datalist id="datalistOptions">
            <c:forEach items="${users}"
                       var="user">
                <option>${user.eMail}</option>
            </c:forEach>
        </datalist>
    </div>

    <div class="col-md-2">
        <button class="btn btn-primary" type="submit">Отправить форму</button>
    </div>

    <div class="input-group">
        <span class="input-group-text">Фамилия Имя Отчество</span>
        <input type="text" aria-label="Last name" class="form-control" name="lastName">
        <input type="text" aria-label="First name" class="form-control" name="firstName">
        <input type="text" aria-label="Patronymic" class="form-control" name="patronymic">
    </div>

    <div>
        <label>Выберите пол</label>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="flexRadioDefault1" name="sex"
                   value="муж">
            <label class="form-check-label">
                Мужской пол
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" id="flexRadioDefault2" name="sex"
                   value="жен">
            <label class="form-check-label">
                Женский пол
            </label>
        </div>
    </div>
    <div class="col-md-2">
        <label for="validationDefault09" class="form-label">Национальность</label>
        <input type="text" class="form-control" id="validationDefault09" name="nationality" required>
    </div>
    <div class="col-md-2">
        <label for="validationDefault01" class="form-label">Код страны выдачи</label>
        <input type="text" class="form-control" id="validationDefault01" name="codeOfIssuingState" required>
    </div>
    <div class="col-md-2">
        <label for="validationDefault17" class="form-label">Номер телефона</label>
        <input type="tel" class="form-control" id="validationDefault17" name="phoneNumber" path="phoneNumber" required>
    </div>
    <div class="col-md-2">
        <label for="validationDefault07" class="form-label">Номер паспорта</label>
        <input type="text" class="form-control" id="validationDefault07" name="number" required>
    </div>
    <div class="col-md-2">
        <label for="validationDefault08" class="form-label">Личный номер</label>
        <input type="text" class="form-control" id="validationDefault08" name="personalId" required>
    </div>
    <div class="col-md-2">
        <label for="date1" class="form-label">Дата рождения</label>
        <input type="date" class="form-control" id="date1" name="dateOfBirth" placeholder="Дата рождения">
    </div>
    <div class="col-md-2">
        <label for="date2" class="form-label">Дата выдачи</label>
        <input type="date" class="form-control" id="date2" name="issueDate" placeholder="Дата выдачи">
    </div>
    <div class="col-md-2">
        <label for="date3" class="form-label">Срок действия</label>
        <input type="date" class="form-control" id="date3" name="expireDate" placeholder="Срок действия">
    </div>
    <div class="col-md-6">
        <label for="validationDefault03" class="form-label">Страна</label>
        <input type="text" class="form-control" id="validationDefault03" required name="country">
    </div>
    <div class="col-md-3">
        <label for="validationDefault04" class="form-label">Область</label>
        <select class="form-select" id="validationDefault04" name="region" required>
            <option selected disabled value="">Выберите...</option>
            <option>Гомельская область</option>
            <option>Брестская область</option>
            <option>Гродненская область</option>
            <option>Витебская область</option>
            <option>Могилевская область</option>
            <option>Минская область</option>
        </select>
    </div>
    <div class="col-md-2">
        <label for="validationDefault12" class="form-label">Город</label>
        <input type="text" class="form-control" id="validationDefault12" name="city" required>
    </div>
    <div class="col-md-2">
        <label for="validationDefault10" class="form-label">Улица</label>
        <input type="text" class="form-control" id="validationDefault10" name="street" required>
    </div>
    <div class="col-md-1">
        <label for="validationDefault14" class="form-label">Номер дома</label>
        <input type="text" class="form-control" id="validationDefault14" name="houseNumber" required>
    </div>
    <div class="col-md-1">
        <label for="validationDefault15" class="form-label">Номер квартиры</label>
        <input type="text" class="form-control" id="validationDefault15" name="apartmentNumber" required>
    </div>


    <div class="col-md-3">
        <label for="validationDefault05" class="form-label">Индекс</label>
        <input type="text" class="form-control" id="validationDefault05" name="index" required>
    </div>


</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>