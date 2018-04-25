<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>
    <style>
        body {
            padding-top: 15px;
        }

        .logo {
            float: left;
        }

        .menu {
            float: right;
        }

        .load-gif {
            padding: 10px;
            display: block;
            margin: 0 auto;
        }

    </style>

</head>
<body>


<header>
    <sec:authorize access="isAnonymous()">
        <div id="Registration" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">x</button>
                        <h4 class="modal-title"><spring:message code="label.registration"/> </h4>
                    </div>
                    <div class="modal-body">
                        <form:form method="POST" action="${contextPath}/registration" modelAttribute="userForm" class="form-horizontal" onsubmit="return checkSubmitBtn()">
                            <div class="form-group">
                                <label class="control-label col-md-3" for="lastName"><spring:message code="label.username"/>:</label>
                                <div class="col-md-9">
                                    <form:input path="username" class="form-control" id="lastName" placeholder="Enter username" type="text"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-md-3" for="fullName"><spring:message code="label.fullname"/>:</label>
                                <div class="col-md-9">
                                    <form:input path="fullname" class="form-control" id="fullName" placeholder="Enter full name"
                                           type="text"/>
                                </div>
                            </div>
                            <div class="form-group" id="formEmailMatch">
                                <label class="control-label col-md-3" for="inputEmail">
                                    <spring:message code="label.email"/>:</label>
                                <div class="col-md-9">
                                    <form:input path="email" class="form-control" id="inputEmail" onkeyup="checkEmailMatch();"
                                           placeholder="Email" type="email"/>
                                </div>
                            </div>
                            <script>
                                function checkEmailMatch() {
                                    var email = $('#inputEmail').val();
                                    var regex = email.match(/^[0-9a-z-_\.]+\@[0-9a-z-]{2,}\.[a-z]{2,}$/i);
                                    if (!regex)
                                        document.getElementById("formEmailMatch").classList.add("has-error");
                                    else
                                        document.getElementById("formEmailMatch").classList.remove("has-error");
                                }
                            </script>
                            <div class="form-group" id="formPassword">
                                <label class="control-label col-md-3" for="inputPassword">
                                    <spring:message code="label.password"/>:</label>
                                <div class="col-md-9">
                                    <form:password path="password" class="form-control" id="inputPassword"
                                                   placeholder="Enter password"/>
                                </div>
                            </div>
                            <div class="form-group" id="formConfirmPassword">
                                <label class="control-label col-md-3" for="confirmPassword">
                                    <spring:message code="label.confirmpassword"/>:</label>
                                <div class="col-md-9">
                                    <input class="form-control" id="confirmPassword" onkeyup="checkPasswordMatch();"
                                           placeholder="Retype password" type="password">
                                </div>
                            </div>
                            <script>
                                function checkPasswordMatch() {
                                    var password = $('#inputPassword').val();
                                    var confirmPassword = $('#confirmPassword').val();
                                    if (password != confirmPassword)
                                        document.getElementById("formConfirmPassword").classList.add("has-error");
                                    else
                                        document.getElementById("formConfirmPassword").classList.remove("has-error");
                                }
                            </script>
                            <br>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <input class="btn btn-primary" id="submitRegistration" value=<spring:message code="label.registration"/>
                                           type="submit">
                                    <input class="btn btn-default" value=<spring:message code="label.clear.form"/> type="reset">
                                </div>
                            </div>
                            <script>
                                function checkSubmitBtn() {
                                    var userName = $('#lastName').val();
                                    var fullName = $('#fullName').val();
                                    var password = $('#inputPassword').val();
                                    var confirmPassword = $('#confirmPassword').val();
                                    var email = $('#inputEmail').val();
                                    var confirmPasswordClass = document.getElementById("formConfirmPassword").classList;
                                    var emailClass = document.getElementById("formEmailMatch").classList;
                                    if (password == "" || confirmPassword == "" || email == ""
                                        || emailClass.contains("has-error")
                                        || confirmPasswordClass.contains("has-error")
                                        || userName == "" || fullName == "") {
                                        alert("Please, fill in or check the fields!");
                                        return false;
                                    }
                                }
                            </script>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
        <div id="Login" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button class="close" data-dismiss="modal">x</button>
                        <h4 class="modal-title"><spring:message code="label.login"/> </h4>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="${contextPath}/login" class="form-horizontal"
                              onsubmit="return checkLoginBtn()">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="username"><spring:message code="label.username"/> :</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" id="username" placeholder="Username"
                                           name="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="password"><spring:message code="label.password"/>:</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" id="password"
                                           placeholder="Password" name="password">
                                </div>
                            </div>
                            <br/>
                            <div class="form-group">
                                <div class="col-md-offset-3 col-md-9">
                                    <input type="submit" class="btn btn-primary">
                                    <input type="reset" class="btn btn-default" value="Clear form">
                                </div>
                            </div>
                            <script>
                                function checkLoginBtn() {
                                    var password = $('#password').val();
                                    var username = $('#username').val();
                                    if (password == "" || username == "") {
                                        alert("Please, enter your details!");
                                        return false;
                                    }
                                }
                            </script>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </sec:authorize>

    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand logo" href="${contextPath}/">Netcracker Bet</a>
            </div>
            <div class="collapse navbar-collapse js-navbar">
                <ul class="nav navbar-nav menu">
                    <sec:authorize access="isAnonymous()">
                        <li><a href="#" data-toggle="modal" data-target="#Login"><spring:message code="label.login"/></a></li>
                        <li><a href="#" data-toggle="modal" data-target="#Registration"><spring:message code="label.registration"/></a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <sec:authentication property="principal.username"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${contextPath}/user/<sec:authentication property="principal.username" />"><spring:message code="label.profile"/></a>
                                </li>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <li><a href="${contextPath}/admin/users"><spring:message code="label.displayusers"/></a></li>
                                </sec:authorize>
                                <li class="divider"></li>
                                <li>
                                    <a href="#" onclick="document.forms['logoutForm'].submit()"><spring:message code="label.logout"/></a>
                                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="selectChampionship"><spring:message code="select.championship"/></label>
                    <select class="form-control" id="selectChampionship">
                        <option selected disabled><spring:message code="your.choice"/>:</option>
                        <option>League of Champions</option>
                        <option>Europa League</option>
                        <option>Premier League</option>
                        <option>LaLiga Santander</option>
                        <option>League 1 Conforama</option>
                        <option>Bundesliga</option>
                        <option>Calcio A</option>
                    </select>
                    <br>
                    <label class="control-label"><spring:message code="matches.date"/>:</label>
                    <div class="row">
                        <div class="col-md-1">
                            <p><spring:message code="matches.date.first"/></p>
                        </div>
                        <div class="col-md-10">
                            <input id="dateStart" type="date" class="form-control"/>
                        </div>
                        <br><br>
                        <div class="col-md-1">
                            <p><spring:message code="matches.date.end"/></p>
                        </div>
                        <div class="col-md-10">
                            <input id="dateEnd" type="date" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div id="addMatch" class="modal fade" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button class="close" data-dismiss="modal">x</button>
                                <h4 class="modal-title"><spring:message code="label.addmatch"/></h4>
                            </div>
                            <div class="modal-body">
                                <form:form class="form-horizontal" action="/addMatch" modelAttribute="matchForm">

                                    <div class="form-group">
                                        <label class="control-label col-md-3"><spring:message code="label.dateofmatch"/>:</label>
                                        <div class="col-md-6">
                                            <form:input path="date" type="date" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3"><spring:message code="label.stateofmatch"/>:</label>
                                        <div class="col-md-6">
                                            <form:select  path="matchState" class="form-control" id="State">
                                                <option value="OPENED">Opened</option>
                                                <option value="CLOSED">Closed</option>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3"><spring:message code="label.competitions"/>:</label>
                                        <div class="col-md-6">
                                            <form:select path="competition" class="form-control" id="Competitions">
                                                <option selected disabled><spring:message code="your.choice"/>:</option>
                                                <option>League of Champions</option>
                                                <option>Europa League</option>
                                                <option>Premier League</option>
                                                <option>LaLiga Santander</option>
                                                <option>League 1 Conforama</option>
                                                <option>Bundesliga</option>
                                                <option>Calcio A</option>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3"><spring:message code="label.teams"/>:</label>
                                        <div class="col-md-6">
                                            <select class="form-control" multiple="multiple" id="Teams">
                                                <option><spring:message code="label.teams"/></option>
                                            </select>
                                        </div>
                                    </div>
                                    <br />
                                    <div class="form-group">
                                        <div class="col-md-offset-3 col-md-9">
                                            <input type="submit" class="btn btn-primary" value="Create match">
                                            <input type="reset" class="btn btn-default" value="Clear form">
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a style="text-decoration: none" href="#" data-toggle="modal" data-target="#addMatch"><h1>+</h1></a>
                </sec:authorize>
            </div>
            <div class="col-md-9">
                <div id="wait"></div>
                <table class="table" id="matches">

                </table>
            </div>
        </div>
    </div>
</section>
<footer>
    <span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=de">ru</a>
</span>
</footer>
<script>
    $(document).ready(function () {
        $.ajax({
            url: 'GetAllMatchesServlet',
            beforeSend: function () {
                $('#wait').append($("<p>Please, wait</p>"));
            },
            success: function (array) {
                $('#wait').css('display', 'none');
/*                var json = '{"date":"11.03.2018","team1":"Real Madrid","team2":"Juventus"}';
                var json2 = '{"date":"11.03.2018","team1":"Sevilla","team2":"FC Bayern Munchen"}';
                var json3 = '{"date":"24.03.2018","team1":"Roma","team2":"Sevilla"}';
                var json4 = '{"date":"28.03.2018","team1":"Inter","team2":"Dinamo"}';
                var json5 = '{"date":"28.03.2018","team1":"Egypt","team2":"Spain"}';
                var array = [json, json2, json3, json4, json5];*/
                for (var i = 0; i < array.length; i++) {
                    $('#matches').append('<tr id="match' + i + '">');
                    var json = JSON.parse(array[i]);
                    for (var id in json) {
                        $('#match' + i).append($("<td>" + json[id] + "</td>"));
                    }
                    $('#match' + i).append('</tr>');
                }
            }
        });
    });
    $(document).ready(function () {
        $('#selectChampionship').change(function () {
            $.ajax({
                url: 'GetSelectedMatchesServlet',
                data: {
                    championship: $('#selectChampionship').val(),
                    date_1: $('#dateStart').val(),
                    data_2: $('#dateEnd').val()
                },
                beforeSend: function () {
                    $('#matches tr').remove();
                },
                success: function (array) {
                    for (var i = 0; i < array.length; i++) {
                        $('#matches').append('<tr id="match' + i + '">');
                        var json = JSON.parse(array[i]);
                        for (var id in json) {
                            $('#match' + i).append($("<td>" + json[id] + "</td>"));
                        }
                        $('#match' + i).append('</tr>');
                    }
                }
            });
        });
    });
    $(document).ready(function () {
        $('#dateStart').change(function () {
            $.ajax({
                url: 'GetSelectedMatchesServlet',
                data: {
                    championship: $('#selectChampionship').val(),
                    date_1: $('#dateStart').val(),
                    data_2: $('#dateEnd').val()
                },
                beforeSend: function () {
                    $('#matches tr').remove();
                },
                success: function (array) {
                    for (var i = 0; i < array.length; i++) {
                        $('#matches').append('<tr id="match' + i + '">');
                        var json = JSON.parse(array[i]);
                        for (var id in json) {
                            $('#match' + i).append($("<td>" + json[id] + "</td>"));
                        }
                        $('#match' + i).append('</tr>');
                    }
                }
            });
        });
    });
    $(document).ready(function () {
        $('#dateEnd').change(function () {
            $.ajax({
                url: 'GetSelectedMatchesServlet',
                data: {
                    championship: $('#selectChampionship').val(),
                    date_1: $('#dateStart').val(),
                    data_2: $('#dateEnd').val()
                },
                beforeSend: function () {
                    $('#matches tr').remove();
                },
                success: function (array) {
                    for (var i = 0; i < array.length; i++) {
                        $('#matches').append('<tr id="match' + i + '">');
                        var json = JSON.parse(array[i]);
                        for (var id in json) {
                            $('#match' + i).append($("<td>" + json[id] + "</td>"));
                        }
                        $('#match' + i).append('</tr>');
                    }
                }
            });
        });
    });
</script>


<!-- jQuery library
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

Latest compiled JavaScript-->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</body>
</html>
