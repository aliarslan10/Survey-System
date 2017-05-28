package anket.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	/* Ali ARSLAN 
	 * Computer Engineer
	 * aliarslan10@yandex.com.tr
	 * */
	
	
	private DBConnection() {
	}

	// B�t�n program bu metodu kullanarak ba�lant�y� al�r.Connection yoksa yarat�l�r.
	public static Connection getConnection() throws SQLException
	{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","","");
				con.setAutoCommit(false);
				return con;
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found!");
			}
			
		return null;
	}
	
	public static boolean closeConnection(Connection con) throws SQLException {
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			con.rollback();
			return false;
		}
	}
	
}
