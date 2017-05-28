package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Seflik;
import oracle.jdbc.OracleTypes;

public class SeflikDAO {

	private SeflikDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static LinkedList<Seflik> resultSettoList(ResultSet set) throws SQLException {
		LinkedList<Seflik> list = new LinkedList<Seflik>();
		while (set.next()) {
			list.add(new Seflik(
					set.getInt("ID"),
					set.getInt("UNITE_ID"),
					set.getString("ISIM"),
					set.getString("UNITE")
					));
		}
		
		return list;
	}
	
	public static LinkedList<Seflik> seflikBul (Seflik seflik) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ ? = call USP_SEFLIKBUL (?,?,?,?) }");
		statement.registerOutParameter(1, OracleTypes.CURSOR);
		statement.setInt(2, seflik.getId());
		statement.setString(3, seflik.getIsim());
		statement.setInt(4, seflik.getUniteID());
		statement.setString(5, seflik.getUnite());
		statement.execute();
		ResultSet set = (ResultSet) statement.getObject(1);
		LinkedList<Seflik> list =  resultSettoList(set);
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static LinkedList<Seflik> seflikListe() throws SQLException {
		return seflikBul(new Seflik());
	}
	
	public static Seflik seflikBul(int id) throws SQLException {
		Seflik seflik = new Seflik();
		seflik.setId(id);
		
		try
		{
			return seflikBul(seflik).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static void seflikEkle(Seflik seflik) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_SEFLIKEKLE (?,?) }");
		statement.setString(1, seflik.getIsim());
		statement.setInt(2, seflik.getUniteID());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static void seflikGuncelle(Seflik seflik) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_SEFLIKGUNCELLE (?,?,?) }");
		statement.setInt(1, seflik.getId());
		statement.setString(2, seflik.getIsim());
		statement.setInt(3, seflik.getUniteID());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}

}
