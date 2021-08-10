<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="ru">
<!doctype html>


<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Талоны</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <style>
                .table {
                    width: 40%;
                    float: right;
                }

                .block {
                    width: 55%;
                    float: left;
                }

                .col-md-5 {
                    float: left;
                    padding: 20px;
                }

                .col-md-4 {
                    float: left;
                    padding: 20px;
                }

                .col-md-3 {
                    float: right;
                    padding: 20px;
                }

                #button {
                    float: right;
                    padding: 20px;
                }

            </style>

</head>

<body class="text-center">
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/users">Личные данные</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cabinet/redact_user">Редактировать профиль</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyAuthority('DOCTOR', 'REGISTRATION_MANAGER')">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/ticket_order">Заказ талона</a>
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
    <div class="col-md-5">
        <input class="form-control" list="datalistOptions1" id="docDataList" placeholder="Фамилия врача" required>
        <datalist id="datalistOptions1" name="doctorDatalist" class="doctorDatalist">
        </datalist>
    </div>

    <div class="col-md-5">
        <input class="form-control" list="datalistOptions2" id="patDataList" placeholder="Фамилия пациента" required>
        <datalist id="datalistOptions2" class="patientDatalist" name="patientDatalist">
        </datalist>
    </div>
    <div class="col-md-5" name="button">
        <button class="btn btn-primary" id="reload" onClick="window.location.reload();">Сбросить</button>
    </div>
    <div class="col-md-5" name="button">
        <button class="btn btn-primary" id="patient">Талоны пациента</button>
    </div>
</div>

<div class="container">
    <table class="table" id="table">
        <thead>
        <tr>
            <th>Талон</th>
            <th>Дата</th>
            <th>Время</th>
            <th>Доступен</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
                $(document).ready(function() {
                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/passports/doctors',
                        type: 'get',
                        success: function(response) {
                            var len = response.length;
                            for (var i = 0; i < len; i++) {
                                var userId = response[i].id;
                                var lastName = response[i].lastName;
                                var firstName = response[i].firstName;
                                var passportNumber = response[i].passportNumber;
                                var str = "<option id='userId' data-id=" + userId + " value=" + passportNumber + ">" +
                                    lastName + " " + firstName + "</option>";
                                $(".doctorDatalist").append(str);
                            }

                        }
                    });

                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/passports/patients',
                        type: 'get',
                        success: function(response) {
                            var len = response.length;
                            for (var i = 0; i < len; i++) {
                                var userId = response[i].id;
                                var lastName = response[i].lastName;
                                var firstName = response[i].firstName;
                                var passportNumber = response[i].passportNumber;
                                var str = "<option id='patientId' data-id=" + userId + " value=" + passportNumber + ">" +
                                    lastName + " " + firstName + "</option>";
                                $(".patientDatalist").append(str);
                            }

                        }
                    });

                });

                document.getElementById('docDataList').addEventListener('input', function() {
                    var g = $('#docDataList').val();
                    var patId = $('#datalistOptions1').find('option[value="' + g + '"]');
                    var doctorId = patId.attr('data-id');

                    const url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/tickets/' + doctorId;
                    $.ajax({
                        url: url,
                        type: 'get',
                        success: function(response) {
                            var tbodyEl = $('tbody');

                            tbodyEl.html('');

                            response.forEach(function(ticket) {

                                if (ticket.available) {
                                    tbodyEl.append('\
                                        <tr>\
                            <td align="center" class="id" hidden>' + ticket.id + '</td>\
                            <td align="center" class="officeNumber" hidden>' + ticket.officeNumber + '</td>\
                            <td align="center" class="number">' + ticket.number + '</td>\
                            <td align="center" class="date">' + ticket.date + '</td>\
                            <td align="center" class="time">' + ticket.time + '</td>\
                            <td>' + "да" + '</td>\
                            <td>\
                                <div class="btn">\
                                <button id="get-button" type="button" class="btn btn-outline-secondary btn-sm">Взять</button>\
                                </div>\
                            </td>\
                        </tr>\
                    ');
                                } else {
                                    tbodyEl.append('\
                        <tr>\
                            <td align="center" class="id" hidden>' + ticket.id + '</td>\
                            <td align="center" class="officeNumber" hidden>' + ticket.officeNumber + '</td>\
                            <td align="center" class="number">' + ticket.number + '</td>\
                            <td align="center" class="date">' + ticket.date + '</td>\
                            <td align="center" class="time">' + ticket.time + '</td>\
                            <td>' + "нет" + '</td>\
                        </tr>\
                    ');
                                }
                            });
                        }
                    });
                });

                document.getElementById('patient').addEventListener('click', function() {
                    var g = $('#patDataList').val();
                    var patId = $('#datalistOptions2').find('option[value="' + g + '"]');
                    var patientId = patId.attr('data-id');

                    if (patientId == null) {
                        alert("Выберите пациента");
                        return false;
                    }

                    const url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/tickets/' + patientId;
                    $.ajax({
                        url: url,
                        type: 'get',
                        success: function(response) {
                            var tbodyEl = $('tbody');

                            tbodyEl.html('');

                            response.forEach(function(ticket) {


                                tbodyEl.append('\
                        <tr>\
                            <td align="center" class="id" hidden>' + ticket.id + '</td>\
                            <td align="center" class="officeNumber" hidden>' + ticket.officeNumber + '</td>\
                            <td align="center" class="number">' + ticket.number + '</td>\
                            <td align="center" class="date">' + ticket.date + '</td>\
                            <td align="center" class="time">' + ticket.time + '</td>\
                        </tr>\
                    ');
                            });
                        }
                    });
                });

                $('table').on('click', '#get-button', function(event) {
                    event.preventDefault();
                    var g = $('#patDataList').val();
                    var patId = $('#datalistOptions2').find('option[value="' + g + '"]');
                    var patientId = patId.attr('data-id');

                    if (patientId == null) {
                        alert("Выберите пациента");
                        return false;
                    }

                    var rowEl = $(this).closest('tr');
                    var id = rowEl.find('.id').text();
                    var officeNumber = rowEl.find('.officeNumber').text();
                    var date = rowEl.find('.date').text();
                    var time = rowEl.find('.time').text();
                    var number = rowEl.find('.number').text();
                    const formJSON = ({
                        id: id,
                        officeNumber: officeNumber,
                        number: number,
                        date: date,
                        time: time,
                        available: false,
                        userId: patientId
                    });

                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/tickets/' + id,
                        method: 'PUT',
                        dataType: "json",
                        contentType: 'application/json',
                        data: JSON.stringify(formJSON),
                        statusCode: {
                            200: function() {
                                alert('Талон взят');
                                window.location.reload();
                            }
                        }
                    });
                });

            </script>

</body>

</html>
