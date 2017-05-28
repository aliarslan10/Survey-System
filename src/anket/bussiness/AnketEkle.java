package anket.bussiness;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anket.DB.models.Anket;
import anket.DB.models.Personel;
import anket.DB.models.Secenek;
import anket.DB.models.Soru;

@WebServlet("/anketekle")
public class AnketEkle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("AnketOlustur.jsp");
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try
		{
			Personel personel = new Personel();
			personel = (Personel) request.getSession().getAttribute("personelinfo");
			
			Anket anket = new Anket();
			anket.setAnketAdi(request.getParameter("anket_adi"));
			anket.setSeflikID(personel.getSeflikID());
			anket.setUniteID(personel.getUniteID());
			
			request.getSession().setAttribute("anket",anket);
			
			LinkedList<Soru> soruListesi = new LinkedList<Soru>();
			request.getSession().setAttribute("soruListesi", soruListesi);
			
			HashMap<String,LinkedList<Secenek>> secenekListesi = new HashMap<String,LinkedList<Secenek>>();
			request.getSession().setAttribute("secenekListesi", secenekListesi);
			
			response.sendRedirect("SoruEkle.jsp");	
		}
		
		catch(Exception ex){
			//System.out.println("Anket Ekleme Baþarýsýz : " + ex.toString());
			ex.printStackTrace(System.out);
		}
	}
}
