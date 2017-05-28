package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Unvan;
import oracle.jdbc.OracleTypes;

public class UnvanDAO {

	private UnvanDAO() {
		// TODO Auto-generated constructor stub
	}
	private static LinkedList<Unvan> resultSettoList(ResultSet set) throws SQLException {
		LinkedList<Unvan> list = new LinkedList<Unvan>();
		while (set.next()) {
			list.add(new Unvan(
					set.getInt("ID"),
					set.getString("ISIM"),
					set.getInt("SEFLIK_ID"), 
					set.getString("SEFLIK_ISIM"),
					set.getInt("UNITE_ID"),
					set.getString("UNITE")
					));
		}
		
		return list;
	}
	
	public static LinkedList<Unvan> unvanBul (Unvan unvan) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ ? = call USP_UNVANBUL (?,?,?,?,?,?) }");
		statement.registerOutParameter(1, OracleTypes.CURSOR);
		statement.setInt(2, unvan.getId());
		statement.setString(3, unvan.getIsim());
		statement.setInt(4, unvan.getSeflikID());
		statement.setString(5, unvan.getSeflikIsim());
		statement.setInt(6, unvan.getUniteID());
		statement.setString(7, unvan.getUniteIsim());
		statement.execute();
		ResultSet set = (ResultSet) statement.getObject(1);
		LinkedList<Unvan> list =  resultSettoList(set);
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static LinkedList<Unvan> unvanListe() throws SQLException {
		return unvanBul(new Unvan());
	}
	
	public static Unvan unvanBul(int id) throws SQLException {
		Unvan unvan = new Unvan();
		unvan.setId(id);
		
		try
		{
			return unvanBul(unvan).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static void unvanEkle(Unvan unvan) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_UNVANEKLE (?,?) }");
		statement.setString(1, unvan.getIsim());
		statement.setInt(2, unvan.getSeflikID());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static void unvanGuncelle(Unvan unvan) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_UNVANGUNCELLE (?,?,?) }");
		statement.setInt(1, unvan.getId());
		statement.setString(2, unvan.getIsim());
		statement.setInt(3, unvan.getSeflikID());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}

}
