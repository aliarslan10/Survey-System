package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Unite;
import oracle.jdbc.OracleTypes;

public class UniteDAO {

	private UniteDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static LinkedList<Unite> resultSettoList(ResultSet set) throws SQLException {
		LinkedList<Unite> list = new LinkedList<Unite>();
		while (set.next()) {
			list.add(new Unite(
						set.getInt("ID"),
						set.getString("ISIM")
					));	
		}
		
		return list;
	}
	
	public static LinkedList<Unite> uniteBul(Unite unite) throws SQLException {		
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ ? = call USP_UNITEBUL (?,?) }");
		statement.registerOutParameter(1, OracleTypes.CURSOR);
		statement.setInt(2, unite.getId());
		statement.setString(3, unite.getIsim());
		statement.execute();
		ResultSet set = (ResultSet) statement.getObject(1);
		LinkedList<Unite> list =  resultSettoList(set);
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static LinkedList<Unite> uniteListe() throws SQLException {
		return uniteBul(new Unite());
	}
	
	public static Unite uniteBul(int id) throws SQLException {
		Unite unite = new Unite();
		unite.setId(id);
		
		try
		{
			return uniteBul(unite).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static void uniteEkle(Unite unite) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_UNITEEKLE (?) }");
		statement.setString(1, unite.getIsim());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static void uniteGuncelle(Unite unite) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_UNITEGUNCELLE (? ,?) }");
		statement.setInt(1, unite.getId());
		statement.setString(2, unite.getIsim());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}

}
