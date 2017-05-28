package anket.bussiness;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anket.DB.DAO.LoginDAO;
import anket.DB.DAO.YetkiDAO;
import anket.DB.models.Personel;
import anket.DB.models.Yetki;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
				int sicilNo = Integer.parseInt(request.getParameter("sicilNo"));
				String pass = request.getParameter("pass");
				Personel personel =LoginDAO.login(sicilNo, pass);
			
			if (personel != null) 
			{
				request.getSession().invalidate();
				request.getSession().setAttribute("personelinfo", personel);
				Yetki yetki = YetkiDAO.yetkiBul(personel.getSicilNo());
				
				if(yetki != null)
				{
					request.getSession().setAttribute("yetki", yetki.getYetki());
				}
				
				else
				{
					request.getSession().setAttribute("yetki", 0);
				}
				
				response.sendRedirect("index.jsp");
			}
			
			else 
			{
				response.sendRedirect("login.jsp");
			}	
		}
		
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
