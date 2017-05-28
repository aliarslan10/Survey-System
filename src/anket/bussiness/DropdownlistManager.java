package anket.bussiness;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import anket.DB.DAO.SeflikDAO;
import anket.DB.DAO.UniteDAO;
import anket.DB.DAO.UnvanDAO;
import anket.DB.models.Seflik;
import anket.DB.models.Unite;
import anket.DB.models.Unvan;

/**
 * Servlet implementation class DropdownlistManager
 */
@WebServlet("/ddm")
public class DropdownlistManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DropdownlistManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int dropdownID;
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			if(request.getParameter("dropdownID")!=null)
			{
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				dropdownID = Integer.parseInt(request.getParameter("dropdownID"));
				switch (dropdownID) {
				case 1:
					LinkedList<Unite> unitelist = UniteDAO.uniteListe();
					for(Unite u : unitelist)
					{
						json.put(Integer.toString(u.getId()), u.getIsim());
					}
					out.print(json.toString());
					break;
				case 2:
					int uniteID = Integer.parseInt(request.getParameter("uniteID")); 
					Seflik seflik = new Seflik();
					seflik.setUniteID(uniteID);
					LinkedList<Seflik> sefliklist = SeflikDAO.seflikBul(seflik);
					for(Seflik s : sefliklist)
					{
						json.put(Integer.toString(s.getId()),s.getIsim());
					}
					out.print(json.toString());
					break;
				case 3:
					int seflikID = Integer.parseInt(request.getParameter("seflikID"));
					Unvan unvan = new Unvan();
					unvan.setSeflikID(seflikID);
					LinkedList<Unvan> unvanlist = UnvanDAO.unvanBul(unvan);
					for(Unvan u : unvanlist )
					{
						json.put(Integer.toString(u.getId()), u.getIsim());
					}
					out.println(json.toString());
				default:
					break;
				}
			}
		} catch (NumberFormatException e) {
			out.print(json.toString());
		} catch (SQLException e) {
			out.print(json.toString());
		}
	}

}
