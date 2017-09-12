
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

	public static final String CONFIG_ALLTABLES = "SELECT * FROM CONFIG_TABLES";
	public static final String CONFIG_NODATA_TABLES = "SELECT * FROM CONFIG_TABLES WHERE COPY_DATA ='N'";
	public static final String DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";

	  public static Connection getFromDB(String url) throws SQLException, ClassNotFoundException {
		PropertyReader lVPropertyFile = PropertyReader.getInstance();   
		String USERNAME = lVPropertyFile.getProperty("UserName");
	    String PASSWORD = lVPropertyFile.getProperty("Password");
	    //Class.forName(DRIVER);
		Connection lVConnection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		return lVConnection;
	}
	  
	  
	public static void AddColSynRecord(String url, String TableName, String ColName, String RealColName){
		String SynSQLStatement ="INSERT INTO COLUMN_SYNONYM" + "VALUES (TableName, ColName, RealColNam)";
		
		// statement.executeUpdate
		
		PropertyReader lVPropertyFile = PropertyReader.getInstance();    					// entries
		String USERNAME = lVPropertyFile.getProperty("UserName");  							// from
		String PASSWORD = lVPropertyFile.getProperty("Password");  							// table

		//Class.forName(DRIVER);
		try (Connection lVConnection = DriverManager.getConnection(url, USERNAME, PASSWORD);) {
			
			try(Statement SqlStatement = lVConnection.createStatement();){
				SqlStatement.executeQuery(SynSQLStatement);
			}
			catch (SQLException e) {
				System.out.println(e);
			}
			
		} catch (SQLException e) {
			System.out.println(e + " - Cannot commit to " + url + " Please check your ODBC settings.");
		}
		
	}


	public static void DeleteValues(String url, String DBQuery) throws SQLException, ClassNotFoundException { 
																							// Deletes
		PropertyReader lVPropertyFile = PropertyReader.getInstance();    					// entries
		String USERNAME = lVPropertyFile.getProperty("UserName");  							// from
		String PASSWORD = lVPropertyFile.getProperty("Password");  							// table

		//Class.forName(DRIVER);
		try (Connection lVConnection = DriverManager.getConnection(url, USERNAME, PASSWORD);) {
			
			try(Statement SqlStatement = lVConnection.createStatement();){
				SqlStatement.executeQuery(DBQuery);
			}
			catch (SQLException e) {
				System.out.println(DBQuery + " completed");
			}
			
		} catch (SQLException e) {
			System.out.println(e + " - Cannot commit to " + url + " Please check your ODBC settings.");
		}
	}

}
