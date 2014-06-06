<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	${requestScope.error}
	<form action="" method="post">
		用户名:		<input type="text" name="username" />
		密    码:		<input type="password" name="password" />
		 下次自动登录：<input type="checkbox" name="rememberMe" value="true"><br/>
		<input type="submit" value="登陆">
	</form>
	<a href="success.jsp">成功</a>
	<a href="role.jsp">角色</a>
</body>
</html>