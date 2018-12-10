<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Изменить данные пользователя</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <br>
    <br>
    <div class="span12 pagination-centered" style="text-align: center">
        <h5>Редактировать пользователя</h5>
        <br>
    </div>
        <div class="row centered">

            <%--@elvariable id="getUserById" type="java.util.List"--%>
            <c:forEach items="${getUserById}" var="p">
<tr>
<form class="form-inline" action="UpdateServlet" method="post">
    <input class="form-control mb-2 mr-sm-2" type="hidden" name = "id" value="${p.id}">
    <input class="form-control mb-2 mr-sm-2" type="text" name="login" value="${p.login}" placeholder=${p.login}>
    <input class="form-control mb-2 mr-sm-2" type="password" name="password" value="${p.password}" placeholder=${p.password}>
    <input class="form-control mb-2 mr-sm-2" type="text" name="name" value="${p.name}" placeholder=${p.name}>
    <input class="form-control mb-2 mr-sm-2" type="number" name="age" value="${p.age}" placeholder=${p.age}>
    <form action="UpdateUserServlet" method="post">
    <p><select size="1" name="role">
        <option value="user">User</option>
        <option value="admin">Admin</option>
    </select></p>
    <input class="btn btn-primary mb-2" type="submit" value="Обновить">
</form>
    </c:forEach>
</div>
</div>
</body>
</html>