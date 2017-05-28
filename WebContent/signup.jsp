<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Kayıt Ol</title>
</head>
<body>
<script src="js/jquery.min.js"></script>
<script src="js/signup.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_tr.min.js"></script>

<div class="arkaplan">
<center>
<b> Personel Kayıt Sayfası </b> <hr>
<table border="0">
<form id="signupform">
 	<tr><td><label for="isim">İsim :</label></td>
 	<td><input id="isim" name="isim" type="text"></td></tr>
 
 	<tr><td><label for="soyisim">Soyisim :</label></td>
 	<td><input id="soyisim" name="soyisim" type="text"></td></tr>
 	
 	<tr><td><label for="sicilNo">Sicil No :</label></td>
 	<td><input id="sicilNo" type="text" name="soyisim"></td></tr>
 
 	<tr><td><label for="sifre">Şifre :</label></td>
 	<td><input id="sifre" type="password" name="sifre"></td></tr>
 	
 	<tr><td><label for="sifredogrulama">Şifre Doğrulama :</label></td>
 	<td><input id="sifredogrulama" type="password" name="sifredogrulama"> </td></tr>
 	
 	<tr><td><label for="unite">Üniteniz :</label></td>
	<td><select id="unite" onchange="uniteChange()"></select></td></tr>
	
	<tr><td><label for="seflik">Şefliğiniz :</label></td>
	<td><select id="seflik" onchange="seflikChange()"></select></td></tr>
	
	<tr><td><label for="unvan">Ünvanınız :</label></td>
	<td><select id="unvan"></select></td></tr>
 </form>

 	 <tr><td></td><td><button id="signup" onclick="signup()">Kayıt Ol</button></th></tr>
 	 </table>
 	</center>
 	</div>
</body>
</html>