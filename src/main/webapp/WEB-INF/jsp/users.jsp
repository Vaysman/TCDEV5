<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Users</h1>
    <h2>
        <ul>
            <c:forEach var="user" items="${users_list}">
                <li><a href="/user/${user.username}">${user.username}</a></li>
            </c:forEach>
        </ul>
    </h2>
</div>

</body>
</html>
