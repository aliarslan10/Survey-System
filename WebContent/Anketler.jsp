<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
    <% request.setCharacterEncoding("UTF-8"); response.setCharacterEncoding("UTF-8"); %>
	<%@page import="anket.DB.models.Anket"%>
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
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Anketler</title>

<style type="text/css">

input[type="submit"]{
   width:55px;
   background-color:#f2f2f2;
   border:1px red solid;
}
</style>


</head>
<body>
<div class="arkaplan">

<%

if( request.getSession().getAttribute("personelinfo") != null) 
{

%>



<table border="1" width="630">
	<%
		Personel personel = (Personel) session.getAttribute("personelinfo");
		Anket anket = new Anket();
		anket.setSeflikID(personel.getSeflikID());
		anket.setUniteID(personel.getUniteID());
		
	try{
			
		ArrayList<Integer> cevaplanmisAnketListesi = CevapDAO.cevaplanmisAnketBul(personel.getSicilNo());
		
		if(request.getParameter("goster") == null)
		{		
			out.println("<form action = 'AnketDetay.jsp' method = 'POST'>");
			out.println("<a href='?goster=unite'> Üniteye ait anketleri göster </a><br /><br />");
			out.println("<tr><th colspan='2'> Şefliğe Ait Anketler </th><tr>");
			out.println("<tr><th>Anket ID</th><th>Anket Adı</th></tr>");

				for(Anket al : AnketDAO.anketBul(anket))
				{
					if(!cevaplanmisAnketListesi.contains(al.getAnketID()))
					{
						out.println("<tr><th><input type='submit' name='anketID' value='" + al.getAnketID() + "'></th><td>"+ al.getAnketAdi() +"</td></tr>");
					}
					
					else
					{
						out.println("<tr><td><i><center>"+al.getAnketID()+"</center></i></td><td>Cevaplanmış :<del>" + al.getAnketAdi() + "</del></td></tr>");
					}
				}
				
				out.println("</form>");
		}
		

		if(!(request.getParameter("goster") == null))
		{
			out.println("<a href='Anketler.jsp'> Şefliğe ait anketleri göster </a><br /><br />");
			out.println("<tr><th colspan='2'> Üniteye Ait Anketler </th><tr>");
			
			out.println("<form action = 'AnketDetay.jsp' method = 'POST'>");
			for(Anket al : AnketDAO.anketListeleUnite())
			{				
				if(!cevaplanmisAnketListesi.contains(al.getAnketID()))
				{
					out.println("<tr><th><input type='submit' name='anketID' value='" + al.getAnketID() + "'></th><td>"+ al.getAnketAdi() +"</td></tr>");
				}
				
				else
				{
					out.println("<tr><td><i><center>"+al.getAnketID()+"</center></i></td><td>Cevaplanmış :<del>" + al.getAnketAdi() + "</del></td></tr>");
				}
			}
			out.println("<input type='hidden' name='unite' value='unite'/>");
			out.println("</form>");
		}
			
	}
		
		
		catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	%>
</table>

<% }
	
else
{
	response.sendRedirect("login.jsp");
}

%>
</div>
</body>
</html>