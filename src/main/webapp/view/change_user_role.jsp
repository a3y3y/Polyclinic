<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>

<html lang="ru">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="theme-color" content="#7952b3">
    <title>Страница регистрации</title>
    <style>
                .block {
                    width: 250px;
                    height: 200px;
                    position: fixed;
                    top: 50%;
                    left: 50%;
                    margin-top: -100px;
                    margin-left: -100px;
                }

            </style>
</head>

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
                    <a class="nav-link active" href="${pageContext.request.contextPath}/cabinet/change_user_role">Редакторовать профили</a>
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
    <form id="form" name="form">
        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" name="eMail" required>
            <label for="floatingInput">e-mail</label>
        </div>
        <div class="form-floating">
            <input type="tel" class="form-control" id="floatingTel" name="phoneNumber" required>
            <label for="floatingTel">Телефон</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit" id="put-button" form="form">Сохранить</button>

        <div class="col-md-5">
            <input class="form-control1" list="datalistOptions" id="exDataList" placeholder="e-mail пользователя" name="eMail">
            <datalist id="datalistOptions">
            </datalist>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="roles" id="flexRadioDefault1" value="PATIENT" checked>
            <label class="form-check-label" for="flexRadioDefault1">
                Пациент
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="roles" id="flexRadioDefault2" value="DOCTOR">
            <label class="form-check-label" for="flexRadioDefault2">
                Доктор
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="roles" id="flexRadioDefault3" value="REGISTRATION_MANAGER">
            <label class="form-check-label" for="flexRadioDefault2">
                Регистратор
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="roles" id="flexRadioDefault4" value="ADMIN">
            <label class="form-check-label" for="flexRadioDefault2">
                Админинстратор
            </label>
        </div>
    </form>




    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <script>
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

                                $("[name='eMail']").val(response.user.eMail);
                                $("[name='phoneNumber']").val(response.user.phoneNumber);
                            }

                        })

                    });

                    function handleFormSubmit(event) {
                        event.preventDefault();
                        var g = $('#exDataList').val();
                        var id = $('#datalistOptions').find('option[value="' + g + '"]').attr('data-id');
                        var url = 'http://localhost:8080/polyclinic-0.0.1-SNAPSHOT/users/' + id;
                        var eMail = $("[name='eMail']").val();
                        var password = $("[name='password']").val();
                        var phoneNumber = $("[name='phoneNumber']").val();
                        var roles = $("input[name='roles']:checked").val();

                        const formJSON = {
                            eMail: eMail,
                            password: password,
                            phoneNumber: phoneNumber,
                            roles: [roles]
                        };

                        const stringJson = JSON.stringify(formJSON);
                        $.ajax({
                            url: url,
                            type: 'put',
                            dataType: "json",
                            data: stringJson,
                            contentType: 'application/json',
                            statusCode: {
                                200: function() {
                                    alert('Аккаунт обновлен');
                                    window.location.reload();
                                }
                            }
                        });
                    }

                    document.getElementById('form').addEventListener('submit', handleFormSubmit);

                </script>
</div>
</body>

</html>
