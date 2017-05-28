package anket.DB.DAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import anket.DB.DBConnection;
import anket.DB.DAO.SecenekDAO;
import anket.DB.models.Cevap;
import anket.DB.models.Secenek;
import oracle.jdbc.driver.OracleTypes;

public class CevapDAO {

	public CevapDAO(){
		
	}			
	
	private static LinkedList<Cevap> resultSetToList(ResultSet set) throws SQLException{
		
		LinkedList<Cevap> cevapList = new LinkedList<Cevap>();
		while(set.next()){
			cevapList.add(new Cevap(
					set.getInt("SECENEK_ID"),
					set.getInt("SAYAC")
					));
		}
		
		return cevapList;
		
	}
	
	public static LinkedList<Cevap> cevapBul(Cevap cevap) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ ? = call USP_CEVAPBUL(?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, cevap.getSecenekID());
		st.executeQuery();
		ResultSet set = (ResultSet) st.getObject(1);
		LinkedList<Cevap> cevapList = resultSetToList(set);
		DBConnection.closeConnection(con);
		return cevapList;
	}
	
	public static LinkedList<Cevap> cevapListele() throws SQLException{
		return cevapBul(new Cevap());
	}
	
	public static void cevapEkle(Cevap cevap) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_CEVAPEKLE(?) }");
		st.setInt(1, cevap.getSecenekID());
		st.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	public static String secenek(int secenekID) throws SQLException{
		Secenek sec = SecenekDAO.secenekBul(secenekID);
		return sec.getSecenek();
	}
	
	public static void CevaplanmisAnketEkle(Cevap cevap) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ call USP_CEVAPLANMIS_ANKET_EKLE(?,?) }");
		st.setInt(1, cevap.getSicilNO());
		st.setInt(2, cevap.getAnketID());
		st.executeQuery();
		DBConnection.closeConnection(con);
	}
	
	
	
	public static ArrayList<Integer> cevaplanmisAnketBul(int sicilNo) throws SQLException{
		Connection con = DBConnection.getConnection();
		CallableStatement st = con.prepareCall("{ ? = call USP_CEVAPLANMIS_ANKET_BUL(?) }");
		st.registerOutParameter(1, OracleTypes.CURSOR);
		st.setInt(2, sicilNo);
		st.executeQuery();
		ResultSet set = (ResultSet) st.getObject(1);
		ArrayList<Integer> list = new ArrayList<Integer>(50);
		while(set.next())
		{
			int value = set.getInt("ANKETID");
			list.add(value);
		}
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static void main(String[] args) throws SQLException{
		
		Cevap cevap = new Cevap();
		cevap.setSecenekID(61);
		
		for(Cevap bul : CevapDAO.cevapBul(cevap))
		{
			System.out.println(bul.getSayac());
			System.out.println(bul.getSecenekID());
		}
	}
	
}
