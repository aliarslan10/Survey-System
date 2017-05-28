<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cevaplar</title>
</head>
<body>
<div class="arkaplan">
		<%
		if( request.getSession().getAttribute("personelinfo") != null) 
		{
			try
			{
				int anketID = Integer.parseInt(request.getParameter("anketID"));
		
				Anket anket = null;
				
				if(request.getParameter("unite") == null)
				{
					anket = AnketDAO.anketBul(anketID);
				}
				
				else
				{
					anket = AnketDAO.anketBulUnite(anketID);
				}
				
				out.println("<b>" + anket.getAnketAdi() + "</b><br />");
				
				Soru soru = new Soru();
				soru.setAnketID(anketID);
				
				for(Soru listele : SoruDAO.soruBul(soru))
				{
						out.println("<br />" + listele.getSoruNO() + ")" + listele.getSoru()  + "<br />");
						
						Secenek secenek = new Secenek();
						secenek.setSoruID(listele.getSoruID());
						
						double toplamCevapSayisi = 0;
						double oran = 0;
						LinkedList<Secenek> secenekListesi = SecenekDAO.secenekBul(secenek);
						HashMap<Integer,Integer> secenekMap = new HashMap<>();
						for(Secenek secenekListele : secenekListesi )
						{

							Cevap cevap = new Cevap();
							cevap.setSecenekID(secenekListele.getSecenekID());
							for(Cevap cevapSayisi : CevapDAO.cevapBul(cevap))
							{
								toplamCevapSayisi = cevapSayisi.getSayac() + toplamCevapSayisi;	
								secenekMap.put(secenekListele.getSecenekID(), cevapSayisi.getSayac());
							}
							
						}
						
						oran = (100.0/toplamCevapSayisi);
						
						for(Secenek s : secenekListesi)
						{
						/*	double percentage;
							int sayac = secenekMap.get(s.getSecenekID());
							if(toplamCevapSayisi == sayac)
							{
								percentage = 100;
							}
							else
							{
								percentage = sayac*oran;
							} */
							
							out.println(s.getSecenek() + " : <b> %" + Math.round((secenekMap.get(s.getSecenekID())*oran)) +"</b><br />");
						}
				}
			}
			
			catch(Exception ex)
			{
				response.sendRedirect("AnketIstatistik.jsp");
			}
		}
		
		else
		{
			response.sendRedirect("login.jsp");
		}
		
	%>
</div>
</body>
</html>