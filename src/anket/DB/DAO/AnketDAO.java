package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Anket;
import oracle.jdbc.driver.OracleTypes;

public class AnketDAO {

	public AnketDAO(){
		
	}
	
	private static LinkedList<Anket> resultSetToList(ResultSet set) throws SQLException{
		
		LinkedList<Anket> anketList = new LinkedList<Anket>();
		while(set.next()){
			anketList.add(new Anket(
					set.getInt("ID"),
					set.getInt("SEFLIK_ID"),
					set.getInt("UNITE_ID"),
					set.getString("ADI"),
					set.getString("SEFLIK"),
					set.getString("UNITE")
					));
		}
		
		return anketList;
	}
	
	public static LinkedList<Anket> anketBul(Anket anket) throws SQLException{
		
		Connection conn = DBConnection.getConnection();
		CallableStatement st = conn.prepareCall("{ ? = call USP_ANKETBUL(?,?,?,?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, anket.getAnketID());
		st.setString(3, anket.getAnketAdi());
		st.setInt(4, anket.getSeflikID());
		st.setInt(5, anket.getUniteID());
		st.execute();
		ResultSet set = (ResultSet) st.getObject(1);
		LinkedList<Anket> anketList = resultSetToList(set);
		DBConnection.closeConnection(conn);
		return anketList;
	}
	
	public static LinkedList<Anket> anketBulUnite(Anket anket) throws SQLException{
		
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ ? = call USP_ANKETBUL_UNITE(?,?,?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, anket.getAnketID());
		st.setString(3, anket.getAnketAdi());
		st.setInt(4, anket.getUniteID());
		st.execute();
		ResultSet set = (ResultSet) st.getObject(1);
		
		LinkedList<Anket> anketListUnite = new LinkedList<Anket>();
		while(set.next())
		{
			anketListUnite.add(new Anket(
					set.getInt("ID"),
					0,
					set.getInt("UNITE_ID"),
					set.getString("ADI"),
					null,
					set.getString("UNITE")
					));
		}
		
		DBConnection.closeConnection(con);
		return anketListUnite;
	}
	
	public static Anket anketBul(int anketID) throws SQLException{
		Anket anket = new Anket();
		anket.setAnketID(anketID);
		
		try
		{
			return anketBul(anket).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static Anket anketBulUnite(int anketID) throws SQLException{
		
		Anket anket = new Anket();
		anket.setAnketID(anketID);
		
		try
		{
			return anketBulUnite(anket).getFirst();
		}
		
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static LinkedList<Anket> anketListele() throws SQLException{
		return anketBul(new Anket());
	}
	
	public static LinkedList<Anket> anketListeleUnite() throws SQLException{
		return anketBulUnite(new Anket());
	}
	
	public static int anketEkle(Anket anket) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st  = con.prepareCall("{ ? = call USP_ANKETEKLE(?,?,?) }");
		st.registerOutParameter(1, OracleTypes.INTEGER);
		st.setString(2, anket.getAnketAdi());
		st.setInt(3, anket.getSeflikID());
		st.setInt(4, anket.getUniteID());
		st.execute();
		int id = st.getInt(1);
		DBConnection.closeConnection(con);	
		return id;
	}
	
	
	public static void anketGuncelle(Anket anket) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_ANKETGUNCELLE(?,?,?,?)}");
		st.setInt(1, anket.getAnketID());
		st.setString(2, anket.getAnketAdi());
		st.setInt(3, anket.getSeflikID());
		st.setInt(4, anket.getUniteID());
		st.execute();
		DBConnection.closeConnection(con);
	}
	
/*	public static void main(String[] args) throws SQLException{
		for(Anket al : AnketDAO.anketListeleUnite()) 
		{
			System.out.println(al.getAnketAdi());
		}
	}*/
}
