<%@page import="anket.DB.models.Anket"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); response.setCharacterEncoding("UTF-8"); %>
    <%@include file="style/header.jsp" %>
    <%@include file="style/menu.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Anket Olustur</title>
</head>
<body>
	<% 
		Anket anket = (Anket) session.getAttribute("anket");
	%>
	
	<div class="arkaplan">
	<%

	if(request.getParameter("secenekSayisi") == null)
	{
		response.sendRedirect("SoruEkle.jsp");
		
	}
	
	if(request.getParameter("soru") != null && request.getParameter("secenekSayisi") != null)
	{
		int secenekSayisi = Integer.parseInt(request.getParameter("secenekSayisi"));
		out.println("<b> Anket Adı : </b>" + anket.getAnketAdi());
		out.println("<br /> <b>Soru : </b>" +request.getParameter("soru"));
		
		out.println("<form method='POST' action='soruekle' />");


		for(int i=0; i<secenekSayisi; i++)
		{
			out.println("Bir şık girin : <input type='text' name='sec" + i + "'/><br/>");
		}
		
		out.println("<input type='hidden' name='secenekSayisi' value='"+secenekSayisi+"'/>");
		out.println("<input type='hidden' name='soru' value='"+request.getParameter("soru")+"'/>");
		out.println("<br /><input type='submit' value='Yeni Soru Ekle' />");
		out.println("<br/> <input type = 'submit' name = 'bitir' value ='Anketi Yayınla'/> </form>'");
	}
%>

</div>
</body>
</html>