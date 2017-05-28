package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import anket.DB.DBConnection;
import anket.DB.models.Personel;
import oracle.jdbc.OracleTypes;

public class PersonelDAO {

	private PersonelDAO() {
		// TODO Auto-generated constructor stub
	}
	
	private static LinkedList<Personel> resultSettoList(ResultSet set) throws SQLException
	{
		LinkedList<Personel> list = new LinkedList<Personel>();
		while (set.next()) {
			list.add(new Personel(
					set.getInt("SICILNO"),
					set.getString("ISIM"),
					set.getString("SOYISIM"),
					set.getInt("UNITE_ID"),
					set.getString("UNITE"),
					set.getInt("SEFLIK_ID"),
					set.getString("SEFLIK"),
					set.getInt("UNVAN_ID"),
					set.getString("UNVAN")
					));
		}
		return list;
	}
	
	public static Personel personelBul(int sicilNo) throws SQLException
	{
		Personel personel = new Personel();
		personel.setSicilNo(sicilNo);
		
		try
		{
			return personelBul(personel).getFirst();
		}
		
		catch(NoSuchElementException e)
		{
			return null;
		}
	}
	
	public static LinkedList<Personel> personelBul(Personel personel) throws SQLException {
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ ? = call USP_PERSONELBUL (?,?,?,?,?)}");
		statement.registerOutParameter(1, OracleTypes.CURSOR);
		statement.setInt(2, personel.getSicilNo());
		statement.setString(3, personel.getIsim()+personel.getSoyisim());
		statement.setInt(4, personel.getUniteID());
		statement.setInt(5, personel.getSeflikID());
		statement.setInt(6, personel.getUnvanID());
		statement.execute();
		ResultSet set = (ResultSet) statement.getObject(1);
		LinkedList<Personel> list =  resultSettoList(set);
		DBConnection.closeConnection(con);
		return list;
	}
	
	public static LinkedList<Personel> personelListe() throws SQLException {		
		return personelBul(new Personel());
	}
	
	public static void personelEkle(Personel personel) throws SQLException
	{
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_PERSONELEKLE (?,?,?,?)}");
		statement.setInt(1, personel.getSicilNo());
		statement.setString(2, personel.getIsim());
		statement.setString(3, personel.getSoyisim());
		statement.setInt(4, personel.getUnvanID());
		statement.execute();
		DBConnection.closeConnection(con);
	}
	
	public static void personelGuncelle(Personel personel) throws SQLException
	{
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_PERSONELGUNCELLE (?,?,?,?)}");
		statement.setInt(1, personel.getSicilNo());
		statement.setString(2, personel.getIsim());
		statement.setString(3, personel.getSoyisim());
		statement.setInt(4, personel.getUnvanID());
		statement.execute();
		DBConnection.closeConnection(con);
	}
	
	public static void personelSil(int sicilNo) throws SQLException {

		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_PERSONELSIL (?)}");
		statement.setInt(1, sicilNo);
		statement.execute();
		DBConnection.closeConnection(con);
	}
	

}
