package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Secenek;
import oracle.jdbc.driver.OracleTypes;

public class SecenekDAO {

	public SecenekDAO(){
		
	}
	
	private static LinkedList<Secenek> resultSetToList(ResultSet set) throws SQLException{
		
		LinkedList<Secenek> secenekList = new LinkedList<Secenek>();
		while(set.next()){
			secenekList.add(new Secenek(
					set.getInt("ID"),
					set.getInt("SECENEK_NO"),
					set.getInt("SORULAR_ID"),
					set.getString("SECENEK")
					));
		}
		
		return secenekList;
		
	}
	
	public static LinkedList<Secenek> secenekBul(Secenek secenek) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ ? = call USP_SECENEKBUL(?,?,?,?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, secenek.getSecenekID());
		st.setString(3, secenek.getSecenek());
		st.setInt(4, secenek.getSecenekNO());
		st.setInt(5, secenek.getSoruID());
		st.execute();
		ResultSet set = (ResultSet) st.getObject(1);
		LinkedList<Secenek> secenekList = resultSetToList(set);
		DBConnection.closeConnection(con);
		return secenekList;
	}
	
	public static LinkedList<Secenek> secenekListele() throws SQLException{
		return secenekBul(new Secenek());
	}
	
	public static Secenek secenekBul(int secenekID) throws SQLException{
		Secenek sec = new Secenek();
		sec.setSecenekID(secenekID);
		
		try
		{
			return secenekBul(sec).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static void secenekEkle(Secenek secenek) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_SECENEKEKLE(?,?,?) }");
		st.setString(1,secenek.getSecenek());
		st.setInt(2, secenek.getSecenekNO());
		st.setInt(3, secenek.getSoruID());
		st.execute();
		DBConnection.closeConnection(con);
	}
	
	public static void secenekGuncelle(Secenek secenek) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_SECENEKGUNCELLE(?,?,?,?) }");
		st.setInt(1, secenek.getSecenekID());
		st.setString(2, secenek.getSecenek());
		st.setInt(3, secenek.getSecenekNO());
		st.setInt(4, secenek.getSoruID());
		st.execute();
		DBConnection.closeConnection(con);
	}
}
