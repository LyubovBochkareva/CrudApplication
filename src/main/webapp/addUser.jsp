<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Добавить нового пользователя</title>
    <meta charset="utf-8"/>
</head>
<body>
        <form action="AddUserServlet" method="post">
        <table>
            <tr>
                <td>Логин:</td>
                <td><label>
                    <input type="text" name="login">
                </label></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><label>
                    <input type="password" name="password">
                </label></td>
            </tr>
            <tr>
                <td>Имя:</td>
                <td><label>
                    <input type="text" name="name">
                </label></td>
            </tr>
            <tr>
                <td>Возраст:</td>
                <td><label>
                    <input type="number" name="age">
                </label></td>
            </tr>
            <tr>
                <td>Роль</td>
                <td><label>
                    <form action="AddUserServlet" method="post">
                        <select name="role">
                            <option>Выберите роль</option>
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </form>
                </label></td>
            </tr>
            <tr>
                <td align="right" colspan="2"><input type="submit" value="Отправить"></td>
            </tr>
        </table>
    </form>
</body>
</html>
