<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	<%@page import="anket.DB.models.Anket"%>
	<%@page import="anket.DB.DAO.AnketDAO"%>
	<%@page import="anket.DB.models.Cevap" %>
	<%@page import="anket.DB.DAO.CevapDAO" %>
	<%@page import="anket.DB.models.Personel" %>
	<%@page import="anket.DB.DAO.PersonelDAO" %>
	<%@page import="java.util.HashMap" %>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Anketler</title>

<style type="text/css">

input[type="submit"]{
   width:55px;
   background-color:#f2f2f2;
   border:1px red solid;
}
</style>
<title>İstatistikler</title>
</head>
<body>
<div class="arkaplan">
<table border="1">
	<%
	
try
	{
	int yetki = (int) session.getAttribute("yetki");
	
	if(yetki == 1 || yetki == 2)
	{
			Personel personel = new Personel();
			personel = (Personel) session.getAttribute("personelinfo");
			
			Anket anket = new Anket();
			anket.setUniteID(personel.getUniteID());
			
			
			if(yetki == 1 || yetki == 2)
			{
				if((request.getParameter("goster")) == null)
				{
					String text = "";
					if(yetki == 1)
						{
							text = "Üniteye Ait Anketlei Göster";
						}
					
					out.println("<table border='1' width='600'><tr><th colspan='2'> Şefliğe Ait Anketler </td></tr><tr><th> İstatistik Gör </th><th>Anketler</th></tr>");
					out.println("<a href='?goster=uniteIstatistik'> " + text +  " </a>");
					out.println("<form method='POST' action='CevaplariGoster.jsp'>");
					
					for(Anket listele : AnketDAO.anketBul(anket))
					{
						out.println("<tr><th><input type='submit' name='anketID' value='" + listele.getAnketID() + "'/></th><td>"+ listele.getAnketAdi() +"</td></tr>");
					}
					
					out.println("</form>");
				}
			}
			
			if(((request.getParameter("goster")) != null) && yetki == 1)
			{
				out.println("<table border='1' width='600'><tr><th colspan='2'> Üniteye Ait Anketler </th></tr><tr><th> İstatikleri Gör </th><th>Anketler</th></tr>");
				out.println("<a href='AnketIstatistik.jsp'> Şefliğe Ait Anketleri Göster</a>");
				out.println("<form method='POST' action='CevaplariGoster.jsp'>");
				
				for(Anket listele : AnketDAO.anketBulUnite(anket))
				{
					out.println("<tr><th><input type='submit' name='anketID' value='" + listele.getAnketID() + "'></th><td>"+ listele.getAnketAdi() +"</td></tr>");
				}
				
				out.println("<input type='hidden' name='unite' value='unite'/>");
				out.println("</form>");
			}
	}
	
	else
	{
		response.sendRedirect("index.jsp");
	}
	}
	
	catch(Exception ex)
	{
		response.sendRedirect("index.jsp");
	}
	%>
</table>
</div>
</body>
</html>