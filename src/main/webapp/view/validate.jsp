<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">

    <sec:authentication var="user" property="principal" />
    <style>
                .block {
                    border: 3px solid #fff;
                    padding: 20px;
                }

                .passport-form {
                    width: 60%;
                    float: left;
                    padding: 20px;
                    border-right: 1px solid #0D6EFD;
                }

                .address-form {
                    width: 40%;
                    float: left;
                    padding: 20px;
                }

                .col-md-4 {
                    float: left;
                    padding: 20px;
                }

                .col-md-5 {
                    float: left;
                    padding: 20px;
                }

                .col-md-3 {
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
                    <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/validate">Валидация</a>
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

    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="post-address" id="post-address" form="address">Добавить адрес</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="put-address" id="put-address" form="address">Обновить адрес</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="post-passport" id="post-passport" form="passport">Добавить паспорт</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="put-passport" id="put-passport" form='passport' onclick="submit">Обновить паспорт</button>
    </div>
    <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="delete-user" id="delete-user">Удалить пользователя</button>
    </div>


    <div class="col-md-5">
        <input class="form-control1" list="datalistOptions" id="exDataList" placeholder="e-mail пользователя" name="eMail" required>
        <datalist id="datalistOptions">
        </datalist>
    </div>

</div>
<div class="block">
    <form class="passport-form" id="passport">
        <p>Паспортные данные</p>

        <div class="col-md-4">
            <label for="validationDefault20" class="form-label">Фамилия</label>
            <input type="text" class="form-control" id="validationDefault20" name="lastName" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault21" class="form-label">Имя</label>
            <input type="text" class="form-control" id="validationDefault21" name="firstName" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault22" class="form-label">Отчество</label>
            <input type="text" class="form-control" id="validationDefault22" name="patronymic" required>
        </div>


        <div class="col-md-4">
            <label>Выберите пол</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault1" name="sex" value="муж">
                <label class="form-check-label">
                    Мужской пол
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" id="flexRadioDefault2" name="sex" value="жен">
                <label class="form-check-label">
                    Женский пол
                </label>
            </div>
        </div>


        <div class="col-md-4">
            <label for="validationDefault09" class="form-label">Национальность</label>
            <input type="text" class="form-control" id="validationDefault09" name="nationality" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault01" class="form-label">Код страны выдачи</label>
            <input type="text" class="form-control" id="validationDefault01" name="codeOfIssuingState" required>
        </div>

        <div class="col-md-4">
            <label for="validationDefault07" class="form-label">Номер паспорта</label>
            <input type="text" class="form-control" id="validationDefault07" name="number" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault08" class="form-label">Личный номер</label>
            <input type="text" class="form-control" id="validationDefault08" name="personalId" required>
        </div>
        <div class="col-md-4">
            <label for="date1" class="form-label">Дата рождения</label>
            <input type="date" class="form-control" id="date1" name="dateOfBirth" placeholder="Дата рождения">
        </div>
        <div class="col-md-4">
            <label for="date2" class="form-label">Дата выдачи</label>
            <input type="date" class="form-control" id="date2" name="issueDate" placeholder="Дата выдачи">
        </div>
        <div class="col-md-4">
            <label for="date3" class="form-label">Срок действия</label>
            <input type="date" class="form-control" id="date3" name="expireDate" placeholder="Срок действия">
        </div>
        <input type="text" class="form-hidden" id="passportUserId" name="userId" value="0" hidden>
        <input type="text" class="form-hidden" id="passportId" name="id" value="0" hidden>
    </form>


    <form class="address-form" id=address>
        <p>Адрес</p>
        <div class="col-md-5">
            <label for="validationDefault03" class="form-label">Страна</label>
            <input type="text" class="form-control" id="validationDefault03" required name="country">
        </div>
        <div class="col-md-5">
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
        <div class="col-md-5">
            <label for="validationDefault12" class="form-label">Город</label>
            <input type="text" class="form-control" id="validationDefault12" name="city" required>
        </div>
        <div class="col-md-5">
            <label for="validationDefault10" class="form-label">Улица</label>
            <input type="text" class="form-control" id="validationDefault10" name="street" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault14" class="form-label">Дом</label>
            <input type="text" class="form-control" id="validationDefault14" name="houseNumber" required>
        </div>
        <div class="col-md-4">
            <label for="validationDefault15" class="form-label">Квартира</label>
            <input type="text" class="form-control" id="validationDefault15" name="apartmentNumber" required>
        </div>


        <div class="col-md-4">
            <label for="validationDefault05" class="form-label">Индекс</label>
            <input type="text" class="form-control" id="validationDefault05" name="index" required>
        </div>
        <input type="text" class="form-hidden" id="userId" name="userId" value="0" hidden>
        <input type="text" class="form-hidden" id="addressId" name="id" value="0" hidden>
    </form>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<script>
                function putButton2() {
                    document.querySelector('.passport-form').addEventListener('submit', handleFormSubmit4);
                }

                function postButton1() {
                    document.querySelector('.passport-form').addEventListener('submit', handleFormSubmit1);
                }

                function putButton1() {
                    document.querySelector('.address-form').addEventListener('submit', handleFormSubmit3);
                }

                function postButton2() {
                    document.querySelector('.address-form').addEventListener('submit', handleFormSubmit2);
                }



                $(document).ready(function() {
                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users',
                        type: 'get',
                        success: function(response) {
                            var len = response.length;
                            for (var i = 0; i < len; i++) {
                                var eMail = response[i].eMail;
                                var id = response[i].id;
                                var str = "<option id=email-id data-id=" + id + " value=" + eMail + "></option>";
                                $("#datalistOptions").append(str);
                            }

                        }
                    });
                });

                document.getElementById('exDataList').addEventListener('input', function() {
                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    $.ajax({
                        url: 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/' + id,
                        type: 'get',
                        success: function(response) {
                            if (response.passport != null || response.registrationAddress != null) {
                                $("[name='lastName']").val(response.passport.lastName);
                                $("[name='firstName']").val(response.passport.firstName);
                                $("[name='patronymic']").val(response.passport.patronymic);
                                $("[name='nationality']").val(response.passport.nationality);
                                $("[name='codeOfIssuingState']").val(response.passport.codeOfIssuingState);
                                $("[name='phoneNumber']").val(response.passport.phoneNumber);
                                $("[name='number']").val(response.passport.number);
                                $("[name='personalId']").val(response.passport.personalId);
                                $("[name='dateOfBirth']").val(response.passport.dateOfBirth);
                                $("[name='issueDate']").val(response.passport.issueDate);
                                $("[name='expireDate']").val(response.passport.expireDate);
                                $("[id='passportId']").val(response.passport.id);
                                if (response.passport.sex == "муж") {
                                    $("input[id='flexRadioDefault1']").prop('checked', true);
                                } else {
                                    $("input[id='flexRadioDefault2']").prop('checked', true);
                                }

                                $("[name='country']").val(response.address.country);
                                $("[name='region']").val(response.address.region);
                                $("[name='city']").val(response.address.city);
                                $("[name='street']").val(response.address.street);
                                $("[name='houseNumber']").val(response.address.houseNumber);
                                $("[name='apartmentNumber']").val(response.address.apartmentNumber);
                                $("[name='index']").val(response.address.index);
                                $("[id='addressId']").val(response.address.id);
                            } else {
                                $("[class='form-control']").val(null);
                                $("[class='form-hidden']").val("0");
                                $("[class='form-select']").val(null);
                                $("input[id='flexRadioDefault1']").prop('checked', false);
                                $("input[id='flexRadioDefault2']").prop('checked', false);
                            }

                        }

                    })

                });



                function handleFormSubmit1(event) {
                    event.preventDefault();

                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    if (id == null) {
                        alert("Введите e-mail пользователя")
                    } else {
                        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/passports';
                        $("[id='passportUserId']").val(id);

                        const data = new FormData(event.target);

                        const formJSON = Object.fromEntries(data.entries());
                        console.log(formJSON);
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: formJSON,
                            statusCode: {
                                201: function() {
                                    alert('Пасспорт добавлен');
                                },
                                406: function() {
                                    alert('У данного пользователя уже добавлен пасспорт')
                                },
                                500: function() {
                                    alert('Что-то пошло не так, возможно такой номер паспорта уже существует. Обратитесь к администратору.')
                                }
                            }
                        });
                    }
                }



                document.getElementById('post-passport').addEventListener('click', postButton1);



                function handleFormSubmit2(event) {
                    event.preventDefault();

                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    if (id == null) {
                        alert("Введите e-mail пользователя")
                    } else {
                        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/addresses';
                        $("[id='userId']").val(id);

                        const data = new FormData(event.target);

                        const formJSON = Object.fromEntries(data.entries());
                        $.ajax({
                            url: url,
                            type: 'post',
                            data: formJSON,
                            statusCode: {
                                201: function() {
                                    alert('Адрес добавлен');
                                },
                                406: function() {
                                    alert('У данного пользователя уже добавлен адрес')
                                }
                            }
                        });
                    }
                }

                document.getElementById('post-address').addEventListener('click', postButton2);




                function handleFormSubmit3(event) {
                    event.preventDefault();

                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    var addressId = document.getElementById('addressId').value;
                    if (id == null) {
                        alert("Введите e-mail пользователя")
                    } else {
                        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/addresses/' + addressId;
                        $("[name='userId']").val(id);

                        const data = new FormData(event.target);

                        const formJSON = Object.fromEntries(data.entries());

                        const stringJson = JSON.stringify(formJSON);
                        $.ajax({
                            url: url,
                            type: 'put',
                            dataType: "json",
                            data: stringJson,
                            contentType: 'application/json',
                            statusCode: {
                                200: function() {
                                    alert('Адрес обновлен');
                                }
                            }
                        });
                    }
                }

                document.getElementById('put-address').addEventListener('click', putButton1);


                function handleFormSubmit4(event) {
                    event.preventDefault();

                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    var passportId = document.getElementById('passportId').value;
                    if (id == null) {
                        alert("Введите e-mail пользователя")
                    } else {
                        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/passports/' + passportId;
                        $("[name='passportUserId']").val(id);

                        const data = new FormData(event.target);

                        const formJSON = Object.fromEntries(data.entries());

                        const stringJson = JSON.stringify(formJSON);
                        $.ajax({
                            contentType: 'application/json',
                            url: url,
                            type: 'put',
                            dataType: "json",
                            data: stringJson,
                            statusCode: {
                                200: function() {
                                    alert('Пасспорт обновлен');
                                }
                            }
                        });
                    }
                }

                document.getElementById('put-passport').addEventListener('click', putButton2);



                document.getElementById("delete-user").addEventListener('click', function() {
                    var g = $('#exDataList').val();
                    var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                    var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/' + id;
                    $.ajax({
                        url: url,
                        type: 'delete',
                        statusCode: {
                            200: function() {
                                alert("Пользователь удален");
                                window.location.reload();
                            }
                        }
                    });
                });

            </script>

</body>

</html>
