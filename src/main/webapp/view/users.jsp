<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>

<html lang="ru">

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <title>Пользователи</title>
    <style>
                .block {
                    border: 3px solid #fff;
                    padding: 20px;
                }

                .container1 {
                    width: 50%;
                    float: left;
                    padding: 20px;
                }

                .container2 {
                    width: 50%;
                    float: right;
                    padding: 20px;
                }

            </style>
</head>
<sec:authentication var="user" property="principal" />

<body>

<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">На главную</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <sec:authorize access="hasAnyAuthority('PATIENT', 'DOCTOR', 'REGISTRATION_MANAGER')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/medical_card">Мед. карта</a>
                </li>
            </sec:authorize>


            <sec:authorize access="hasAuthority('PATIENT')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/my_tickets">Мои талоны</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/ticket_order_patient">Заказ талона</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('PATIENT', 'DOCTOR')">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/users">Личные данные</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/redact_user">Редактировать профиль</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('DOCTOR', 'REGISTRATION_MANAGER')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/ticket_order">Заказ талона</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('REGISTRATION_MANAGER')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/tickets">Новые талоны</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('ADMIN', 'REGISTRATION_MANAGER')">
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
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/change_user_role">Редакторовать профили</a>
                </li>

            </sec:authorize>
        </ul>
    </div>
    <div>
        <a>
            <sec:authentication property="principal.username" />
        </a>
        <a hidden id="principalId">
            <sec:authentication property="principal.id" />
        </a>
        <a hidden id="principalRole">
            <sec:authentication property="principal.roles" />
        </a>
    </div>
</nav>

<div class="block">
    <div class="container1" id="container1">
    </div>
    <div class="container2" id="container2">
    </div>
</div>
<sec:authorize access="hasAuthority('DOCTOR')">
    <div class="container3" id="container3">
        <p>Врач</p>
    </div>
</sec:authorize>


<script>
                $(document).ready(function() {
                    var principalId = $('#principalId').text();
                    var principalRole = $('#principalRole').text();
                    console.log(principalRole);
                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/' + principalId,
                        type: 'get',
                        success: function(response) {

                            var eMail = response.user.eMail;
                            var phoneNumber = response.user.phoneNumber;

                            var lastName = response.passport.lastName;
                            var firstName = response.passport.firstName;
                            var patronymic = response.passport.patronymic;
                            var number = response.passport.number;
                            var personalId = response.passport.personalId;
                            var dateOfBirth = response.passport.dateOfBirth;
                            var issueDate = response.passport.issueDate;
                            var expireDate = response.passport.expireDate;
                            var nationality = response.passport.nationality;
                            var sex = response.passport.sex;

                            var country = response.address.country;
                            var region = response.address.region;
                            var city = response.address.city;
                            var street = response.address.street;
                            var houseNumber = response.address.houseNumber;
                            var apartmentNumber = response.address.apartmentNumber;
                            var index = response.address.index;

                            var str1 =
                                "<p>E-mail: " + eMail + "</p>" +
                                "<p>Телефон: " + phoneNumber + "</p>" +
                                "<p>Фамилия: " + lastName + "</p>" +
                                "<p>Имя: " + firstName + "</p>" +
                                "<p>Отчество: " + patronymic + "</p>" +
                                "<p>Пол: " + sex + "</p>" +
                                "<p>Дата рождения: " + dateOfBirth + "</p>" +
                                "<p>Номер паспорта: " + number + "</p>" +
                                "<p>Личный номер: " + personalId + "</p>" +
                                "<p>Дата выдачи паспорта: " + issueDate + "</p>" +
                                "<p>Срок действия паспорта: " + expireDate + "</p>" +
                                "<p>Национальность: " + nationality + "</p>";
                            var str2 =
                                "<p>Адрес</p>" +
                                "<p>Страна: " + country + "</p>" +
                                "<p>Область: " + region + "</p>" +
                                "<p>Город: " + city + "</p>" +
                                "<p>Улица, дом: " + street + ", " + houseNumber + " " + apartmentNumber + "</p>" +
                                "<p>Почтовый индекс: " + index + "</p>";



                            $("#container1").append(str1);
                            $("#container2").append(str2);
                        }
                    });
                });

                $(document).ready(function() {
                    var principalId = $('#principalId').text();
                    var principalRole = $('#principalRole').text();

                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/doctor_info/user_id/' + principalId,
                        type: 'get',
                        success: function(response) {

                            var lastPlaceOfWork = response.lastPlaceOfWork;
                            var lastPosition = response.lastPosition;
                            var education = response.education;
                            var experience = response.experience;


                            var str3 =
                                "<p>Последнее место работы: " + lastPlaceOfWork + "</p>" +
                                "<p>Занимаемая должность: " + lastPosition + "</p>" +
                                "<p>Образование: " + education + "</p>" +
                                "<p>Стаж работы: " + experience + "</p>";
                            $("#container3").append(str3);
                        }
                    });

                });

            </script>

</body>

</html>
