<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta content="text/html; charset=utf-8">
    <title>Список пользователей</title>
</head>
<body>
<table border="1">
        <tr>
            <td>Id</td>
            <td>Login</td>
            <td>Password</td>
            <td>Name</td>
            <td>Age</td>
            <td>Role</td>
            <td>Действия</td>
        </tr>

        <%--@elvariable id="allUser" type="java.util.List"--%>
        <c:forEach items="${allUser}" var = "user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getName()}</td>
                <td>${user.getAge()}</td>
                <td>${user.getRole()}</td>
                <td>
                    <form action = "UpdateServlet" method="get">
                        <input type="hidden" name="id" value="${user.getId()}">
                        <input type="submit" value="Изменить" style="float:left">
                    </form>
                    <form action="DeleteServlet" method="get">
                        <input type="hidden" name="id" value="${user.getId()}">
                        <input type="submit" value="Удалить" style="float:left">
                    </form>
                    </td>
                </tr>
            </c:forEach>
    </table>
        <form action = "AddUserServlet" method="get">
            <input type="submit" value="Добавить нового пользователя">
        </form>
</body>
</html>
