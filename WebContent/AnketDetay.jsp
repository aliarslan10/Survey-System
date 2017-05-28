<%@ page import="anket.DB.DAO.AnketDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); response.setCharacterEncoding("UTF-8"); %>
<%@ page import="anket.DB.models.*" %>
<%@ page import="anket.DB.DAO.*" %>
<%@ include file="style/header.jsp" %>
<%@ include file="style/menu.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/style.css">
<title>Anket Detay</title>
</head>
<body>

<div class="arkaplan">
<form method='post' action='cevapekle'>
	<% 

	if( request.getSession().getAttribute("personelinfo") != null) 
	{

			try
			{
				int anketid = Integer.parseInt(request.getParameter("anketID"));
				Anket anket = null; 
				
				if(request.getParameter("unite")==null)
				{
					anket=AnketDAO.anketBul(anketid);
				}
				
				else
				{
					anket=AnketDAO.anketBulUnite(anketid);
				}
				
				out.println("<b>" + anket.getAnketAdi() + "</b>");
				
				Soru soru = new Soru();
				soru.setAnketID(anketid);
						
					int i = 0;
					for(Soru soruAl : SoruDAO.soruBul(soru))
					{
						out.println("<br /><br />" +soruAl.getSoruNO() + ")" + soruAl.getSoru());
						
						Secenek secenek = new Secenek();
						secenek.setSoruID(soruAl.getSoruID());
						
						for(Secenek secenekAl : SecenekDAO.secenekBul(secenek))
						{
							out.println("<br /><input type='radio' name='sec"+ i +"' value='" + secenekAl.getSecenekID() + "'/>" + secenekAl.getSecenek());
						}
		
						i++;
					}
					out.println("<input type='hidden' name='secenekSayisi' value='"+ i +"'/>");
					out.println("<input type='hidden' name='anketID' value='"+ anketid +"'/>");
					out.println("<br /><br/><input type='submit' value='Anketi Bitir' />");
			}
		
			catch(Exception ex)
			{
				ex.printStackTrace();
				response.sendRedirect("Anketler.jsp");
			}
	}
	
	else
	{
		response.sendRedirect("login.jsp");
	}


%>
</form>
</div>
</body>
</html>