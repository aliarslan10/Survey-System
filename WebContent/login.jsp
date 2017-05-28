<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Giriş Yap</title>
</head>
<body>
<div class="arkaplan">
<center>
<b> Giriş Yap </b><hr>
			<table>		
			<form method="POST" action="auth">
				<tr><td>Sicil No :</td><td> <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" name="sicilNo"></td></tr>
				<tr><td>Şifre :</td><td> <input type="password" name="pass"></td></tr>
				<tr><td></td><td><input type="submit" value="Giriş Yap">
			</form>
				<a href="signup.jsp"><button>Kayıt Ol</button></a></td></tr>
			</table>
</center>
</div>
</body>
</html>