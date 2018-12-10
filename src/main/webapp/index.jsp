<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Здравствуйте!</title>
</head>
<body>
WELCOME!

<form action="AuthUserServlet" method="get">
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
        <td align="right" colspan="2"><input type="submit" value="Войти"></td>
    </tr>
</table>
</form>
</body>
</html>
