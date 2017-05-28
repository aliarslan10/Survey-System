package anket.bussiness;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anket.DB.DAO.CevapDAO;
import anket.DB.DAO.SecenekDAO;
import anket.DB.models.Cevap;
import anket.DB.models.Personel;
import anket.DB.models.Secenek;;

@WebServlet("/cevapekle")
public class CevapEkle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("AnketDetay.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		
		int anketID = Integer.parseInt(request.getParameter("anketID"));
		Personel personel = (Personel) request.getSession().getAttribute("personelinfo");
		int sicilNo = personel.getSicilNo();
		
		try{
			
			int secenekSayisi = Integer.parseInt(request.getParameter("secenekSayisi"));
			
			for(int i=0; i<secenekSayisi; i++)
			{
				int secenekID = Integer.parseInt(request.getParameter("sec"+i));
				
				Secenek secenek = new Secenek();
				secenek.setSecenekID(secenekID);
				
				for(Secenek al : SecenekDAO.secenekBul(secenek))
				{
					Cevap cevap = new Cevap();
					cevap.setSecenekID(al.getSecenekID());
					CevapDAO.cevapEkle(cevap);
				}
			}
			
			Cevap cevaplanmisAnket = new Cevap();
			cevaplanmisAnket.setAnketID(anketID);
			cevaplanmisAnket.setSicilNO(sicilNo);
			CevapDAO.CevaplanmisAnketEkle(cevaplanmisAnket);
			
			response.sendRedirect("Anketler.jsp");
		}
		
		catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	}
}
