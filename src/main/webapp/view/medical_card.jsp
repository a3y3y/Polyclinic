<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import = "by.it_academy.polyclinic.model.patient_info.MedicalNoteType" %>

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
                <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/medical_card">Моя карта</a>
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
        </ul>
    </div>
    <div>
        <a>${user.eMail}</a>
    </div>
</nav>

<table class="block" border="1">
    <tbody>
    <c:forEach items="${requestScope.medicalNotes}"
               var="medicalNote">
        <tr>
            <td width="20%">${medicalNote.date}</td>
            <td width="20%">${medicalNote.type}</td>
            <td width="20%">${medicalNote.illness}</td>
            <td width="20%">${medicalNote.name}</td>
            <td width="20%">${medicalNote.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>




<div class="block">
    <form action="${pageContext.request.contextPath}/cabinet/medical_card" method="post">
        <div class="form-floating">
            <input type="date" class="form-control" id="floatingInput" name="date">
        </div>
        <select class="form-select" aria-label="Тип записи" name="type">
            <c:forEach items="<%= MedicalNoteType.getNames() %>"
                       var="noteType">
            <option value="${noteType}">${noteType}</option>
            </c:forEach>
        </select>


        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput2" name="name">
            <label for="floatingInput2">Название записи</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput1" name="illness">
            <label for="floatingInput1">Заболевание</label>
        </div>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput3" name="description">
            <label for="floatingInput3">Описание</label>
        </div>
        <label for="exampleDataList" class="form-label">Имя пациента</label>
        <input class="form-control" list="datalistOptions" id="exampleDataList" placeholder="Type to search..." name="fio">
        <datalist id="datalistOptions">
            <c:forEach items="${passports}"
                       var="passport">
                <option>${passport.lastName} ${passport.firstName} ${passport.patronymic} ${passport.number}</option>
            </c:forEach>
        </datalist>

        <button class="w-100 btn btn-lg btn-primary" type="submit">Добавить запись</button>
        <p class="mt-5 mb-3 text-muted">© 2021</p>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>