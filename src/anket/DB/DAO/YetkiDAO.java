package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Yetki;
import oracle.jdbc.OracleTypes;

public class YetkiDAO {

	private YetkiDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static LinkedList<Yetki> resultSettoList(ResultSet set) throws SQLException {
		LinkedList<Yetki> list = new LinkedList<Yetki>();
		while (set.next()) {
			list.add(new Yetki(
						set.getInt("SICILNO"),
						set.getInt("YETKI"),
						set.getString("ISIM"),
						set.getString("SOYISIM"),
						set.getString("UNITE"),
						set.getString("SEFLIK"),
						set.getString("UNVAN")						
					));	
		}
		
		return list;
	}
	
	public static LinkedList<Yetki> yetkiBul(Yetki yetki) throws SQLException {		
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ ? = call USP_YETKIBUL (?,?) }");
		statement.registerOutParameter(1, OracleTypes.CURSOR);
		statement.setInt(2, yetki.getSicilNo());
		statement.setInt(3, yetki.getYetki());
		statement.execute();
		ResultSet set = (ResultSet) statement.getObject(1);
		LinkedList<Yetki> list =  resultSettoList(set);
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static LinkedList<Yetki> yetkiListe() throws SQLException {
		return yetkiBul(new Yetki());
	}
	
	public static Yetki yetkiBul(int sicilNo) throws SQLException  {
		Yetki yetki = new Yetki();
		yetki.setSicilNo(sicilNo);
		
		try
		{
			return yetkiBul(yetki).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static void yetkiEkle(Yetki yetki) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_YETKIEKLE (?,?) }");
		statement.setInt(1, yetki.getSicilNo());
		statement.setInt(2, yetki.getYetki());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static void yetkiGuncelle(Yetki yetki) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_YETKIGUNCELLE (? ,?) }");
		statement.setInt(1, yetki.getSicilNo());
		statement.setInt(2, yetki.getYetki());
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static void yetkiSil(int sicilNo) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_YETKISIL (?) }");
		statement.setInt(1,sicilNo);
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
}
