    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>
    <% request.setCharacterEncoding("UTF-8"); response.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Anket Olustur</title>
</head>
<body>
<div class="arkaplan">
<% 

	if(session.getAttribute("personelinfo") != null)
	{

%>
	<form method="POST" action="anketekle">
	Ankete bir isim ver : <input type="text" name="anket_adi"/>
	<input type="submit" value="Tamam"/>
	</form>

<% 
}
	else
	{
		response.sendRedirect("login.jsp");
	}


	%>
</div>
</body>
</html>