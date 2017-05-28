package anket.bussiness;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import anket.DB.DAO.AnketDAO;
import anket.DB.DAO.SecenekDAO;
import anket.DB.DAO.SoruDAO;
import anket.DB.models.Anket;
import anket.DB.models.Secenek;
import anket.DB.models.Soru;

@WebServlet("/soruekle")
public class SoruEkle extends HttpServlet{
	private static final long serialVersionUID = 1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("SoruEkle.java");
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		try{
			HttpSession session = request.getSession();
			//soru ekleme :
			Anket anket = (Anket) session.getAttribute("anket");

			int secenekSayisi = Integer.parseInt(request.getParameter("secenekSayisi"));
			
			LinkedList<Soru>  soruListesi = (LinkedList<Soru>) session.getAttribute("soruListesi");
			HashMap<String,LinkedList<Secenek>> secenekListesi = (HashMap<String,LinkedList<Secenek>>) session.getAttribute("secenekListesi");
			
			Soru soruEkle = new Soru();
			soruEkle.setSoru(request.getParameter("soru"));
			soruEkle.setSoruNO(soruListesi.size()+1);
			soruEkle.setAnket(anket.getAnketAdi());
			soruListesi.add(soruEkle);
			
			//secenekler : 
			LinkedList<Secenek> secenekEkleList = new LinkedList<>();
			
			
			for(int i=0; i < secenekSayisi; i++)
			{
				Secenek secenek = new Secenek();
				secenek.setSecenek(request.getParameter("sec" + i));
				secenek.setSecenekNO(++i);
				secenekEkleList.add(secenek);
			}
			
			secenekListesi.put(request.getParameter("soru"),secenekEkleList);
			
			if(request.getParameter("bitir")!=null)
			{
				anket.setAnketID(AnketDAO.anketEkle(anket));
				for(Soru s : soruListesi)
				{
					s.setAnketID(anket.getAnketID());
					int soruID = SoruDAO.soruEkle(s);
					for(Secenek sec : secenekListesi.get(s.getSoru()))
					{
						sec.setSoruID(soruID);
						SecenekDAO.secenekEkle(sec);
					}
				}
				
				session.removeAttribute("anket");
				session.removeAttribute("soruListesi");
				session.removeAttribute("secenekListesi");
				response.sendRedirect("Anketler.jsp");
			}
			
			else
			{
				response.sendRedirect("SoruEkle.jsp");
			}
	
		}
		
		catch(Exception ex){
			ex.printStackTrace(System.out);
		}
	}
}
