<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
	<%@page import="anket.DB.models.Personel"%>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Hesap Bilgileri</title>
</head>
<body>
<div class="arkaplan">
<%

	if(request.getSession().getAttribute("personelinfo") != null)
	{
		Personel personel = (Personel) session.getAttribute("personelinfo");
		out.print(personel.getIsim()+" "+ personel.getSoyisim() +" hoşgeldin <hr/>");
		out.print("<b>Sicil Numaran :</b> "+personel.getSicilNo());
		out.print("<br><b>Üniten :</b>  "+personel.getUnite());
		out.print("<br><b>Şefliğin :</b>  "+personel.getSeflik());
		out.print("<br><b>Unvanın :</b>  "+personel.getUnvan());
	}

	else
	{
		response.sendRedirect("login.jsp");
	}

 %>
 </div>
</body>
</html>