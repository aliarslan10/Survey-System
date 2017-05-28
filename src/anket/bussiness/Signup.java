package anket.bussiness;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anket.DB.DAO.LoginDAO;
import anket.DB.models.Personel;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("signup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Personel personel = new Personel();
		personel.setSicilNo(Integer.parseInt(request.getParameter("sicilNo")));
		personel.setIsim(request.getParameter("isim"));
		personel.setSoyisim(request.getParameter("soyisim"));
		personel.setUnvanID(Integer.parseInt(request.getParameter("unvanID")));
		try {
			LoginDAO.signup(personel, request.getParameter("sifre"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
