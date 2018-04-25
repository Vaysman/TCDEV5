<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body{
            padding-top: 15px;
        }
        .logo{
            float: left;
        }
        .menu{
            float: right;
        }
        .profile-menu{
            color: #000;
        }
        .photo-user{
            font-size: 200px;
        }
        .edit-info{
            float: right;
            background-color: #444;
            color: white;
        }

    </style>

</head>
<body>
<header>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand logo" href="${contextPath}/">Netcracker Bet</a>
        </div>
        <div class="collapse navbar-collapse js-navbar">
            <ul class="nav navbar-nav menu">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <sec:authentication property="principal.username" />
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${contextPath}/user/<sec:authentication property="principal.username" />">Profile</a></li>
                        <li><a href="${contextPath}/admin/users">Display all users</a></li>
                        <li class="divider"></li>
                        <li>
                            <a href="#" onclick="document.forms['logoutForm'].submit()">Logout</a>
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
</header>
<section>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <ul class="nav nav-tabs nav-stacked">
                    <li><a class="profile-menu" href="#"><span class="glyphicon glyphicon-user"></span> Info</a></li>
                    <li><a class="profile-menu" href="#"><span class="glyphicon glyphicon-book"></span> History</a></li>
                    <li><a class="profile-menu" href="#"><span class="glyphicon glyphicon-time"></span> Active bets</a></li>
                    <li><a class="profile-menu" href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                    <li><a class="profile-menu" href="#"><span class="glyphicon glyphicon-refresh"></span> Change password</a></li>
                </ul>
            </div>
            <div class="col-md-9">
                <p><span class="glyphicon glyphicon-user photo-user"></span></p>
                <p><h4>Username: ${user.username}</h4></p>
                <p><h4>Email: ${user.email}</h4></p>
                <p><h4>Full name: ${user.fullname}</h4></p>
                <p><h4>Role: ${user.role}</h4></p>
                <p><h4>State: ${user.state}</h4></p>
            </div>
        </div>
    </div>
</section>



<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>