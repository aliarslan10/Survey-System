package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Soru;
import oracle.jdbc.driver.OracleTypes;

public class SoruDAO {

	public SoruDAO(){
		
	}
	
	private static LinkedList<Soru> resultSetToList(ResultSet set) throws SQLException{
		
		LinkedList<Soru> soruList = new LinkedList<Soru>();
		while(set.next()){
			soruList.add(new Soru(
					set.getInt("ID"),
					set.getInt("SORU_NO"),
					set.getInt("ANKET_ID"),
					set.getString("SORU")
					));
		}
		
		return soruList;
	}
	
	public static LinkedList<Soru> soruBul(Soru soru) throws SQLException{
		
		Connection conn = DBConnection.getConnection();
		CallableStatement st = conn.prepareCall("{ ? = call USP_SORUBUL(?,?,?,?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, soru.getSoruID());
		st.setString(3, soru.getSoru());
		st.setInt(4, soru.getSoruNO());
		st.setInt(5, soru.getAnketID());
		st.execute();
		ResultSet set = (ResultSet) st.getObject(1);
		LinkedList<Soru> soruList = resultSetToList(set);
		DBConnection.closeConnection(conn);
		return soruList;
	}
	
	public static LinkedList<Soru> soruListele() throws SQLException{
		return soruBul(new Soru());
	}
	
	public static Soru soruBul(int soruID) throws SQLException{
		Soru soru = new Soru();
		soru.setSoruID(soruID);
		
		try
		{
			return soruBul(soru).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static int soruEkle(Soru soru) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ ? = call USP_SORUEKLE(?,?,?) }");
		st.registerOutParameter(1, OracleTypes.INTEGER);
		st.setString(2,soru.getSoru());
		st.setInt(3, soru.getSoruNO());
		st.setInt(4, soru.getAnketID());
		st.executeQuery();
		int id = st.getInt(1);
		DBConnection.closeConnection(con);
		return id;
	}
	
	public static void soruGuncelle(Soru soru) throws SQLException{
		
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_SORUGUNCELLE(?,?,?,?) }");
		st.setInt(1, soru.getSoruID());
		st.setString(2, soru.getSoru());
		st.setInt(3, soru.getSoruNO());
		st.setInt(4, soru.getAnketID());
		st.execute();
		DBConnection.closeConnection(con);
	}
}
