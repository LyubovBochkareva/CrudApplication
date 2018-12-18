<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Удалить пользователя</title>
</head>
<body>

<%--@elvariable id="getUserByIdToDelete" type="java.util.List"--%>
<c:forEach items="${getUserByIdToDelete}" var="user">

Вы действительно хотите удалить пользователя ${user.getLogin()}?

<form action="<c:url value="/admin/DeleteServlet"/>" method="post">
    <input type="hidden" name="id" value="${user.id}">
<input type="submit" value="Удалить">
</form>
</c:forEach>
</body>
</html>

