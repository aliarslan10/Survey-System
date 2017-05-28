package anket.DB.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import anket.DB.DBConnection;
import anket.DB.models.Personel;
import oracle.jdbc.OracleTypes;

public class LoginDAO {

	private LoginDAO() {
	}

	public static boolean auth(int sicilNo , String pass) {
		try {
			Connection con = DBConnection.getConnection();
			CallableStatement statement = con.prepareCall("{ ? = call USP_AUTH (?,?) }");
			statement.registerOutParameter(1, OracleTypes.INTEGER);
			statement.setInt(2, sicilNo);
			statement.setString(3, pass);
			statement.execute();
			if (statement.getInt(1) != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static Personel login(int sicilNo , String pass){
		try {
			if (auth(sicilNo,pass)) {
				return PersonelDAO.personelBul(sicilNo);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static void signup(Personel personel, String pass) throws SQLException
	{
		PersonelDAO.personelEkle(personel);
		Connection con = DBConnection.getConnection();
		CallableStatement statement = con.prepareCall("{ call USP_SIGNUP(?,?) }");
		statement.setInt(1, personel.getSicilNo());
		statement.setString(2, pass);
		statement.executeQuery();
		DBConnection.closeConnection(con);
	}
	
}
