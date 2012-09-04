
package start;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * <pre>
 * The SQLDataServer class helps the model class to connect and perform all necessary SQL functions.
 * </pre>
 * 
 * @author Kenneth G Wegener
 * @version 1.0 beta
 * @since 1.0 beta
 */

public class SQLDataServer {
	/**
	 * String database identifies what database the SQLDataServer is connected
	 * to.
	 */
	
	//private String devConnectionURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST =  devgrid-scan.cia)(PORT = 1415))(ADDRESS = (PROTOCOL = TCP)(HOST =  devgrid-scan.cia)(PORT = 1415))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = cet1d.cet1d.devgrid.isb.cia)))";
	private String devConnectionURL = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = XE)))";
  
	private String connectionuser = "alex";
	private String connectionpassword = "pqiw269";
	
	private String database = "";
	/**
	 * Connection connection is the current connection to the database.
	 */
	private Connection connection;
	/**
	 * Statement statement is the statement for the current database
	 */
	private Statement statement;
	/**
	 * Statement callableStatement is the statement for the current database
	 */
	private CallableStatement callableStatement;
	/**
	 * ResultSet results is the ResultsSet after a query is executed
	 */
	private ResultSet results;

	/* XXXXXXXXXXXXXXXXXXXX Constructor XXXXXXXXXXXXXXXXXXXX */
	/**
	 * The SQLDataServer constructor creates a new connection and a new
	 * statement based on the three parameters connectionurl, connectionuser,
	 * and connectionpassword.
	 * 
	 * @param connectionurl
	 *            - the database url to connect to
	 * @param connectionuser
	 *            - the user name for the database
	 * @param connectionpassword
	 *            - the database password
	 */
	public SQLDataServer(String connectionurl, String connectionuser,
			String connectionpassword) {
		try {
			database = " using database " + connectionurl + " and schema "
					+ connectionuser;
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			connection = DriverManager.getConnection(connectionurl,
					connectionuser, connectionpassword);

			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public SQLDataServer(){
		try {
			database = " using database " + this.devConnectionURL + " and schema "
					+ this.connectionuser;
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			connection = DriverManager.getConnection(this.devConnectionURL,
					this.connectionuser, this.connectionpassword);

			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	
	public boolean SQLDataServerConn(String connectionurl, String connectionuser,
			String connectionpassword) {
		try {
				database = " using database " + connectionurl + " and schema "
						+ connectionuser;
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

				connection = DriverManager.getConnection(connectionurl,
						connectionuser, connectionpassword);

				statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	/**
	 * The SQLDataServer constructor creates a new connection and a new
	 * statement based on the four parameters connectionurl, and via Properties
	 * object (connectionuser, connectionpassword, and SetBigStringTryClob )
	 * 
	 * @param connectionurl
	 * @param properties
	 */

	public SQLDataServer(String connectionurl, Properties properties) {
		try {
			database = " using database " + connectionurl + " and schema "
					+ properties.getProperty("user");

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			connection = DriverManager.getConnection(connectionurl, properties);

			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* XXXXXXXXXXXXXXXXXXXX Getter Methods XXXXXXXXXXXXXXXXXXXX */
	/**
	 * getDatabase returns the string database
	 * 
	 * @return The database the instantiated SQLDataServer is connected to.
	 */
	public String getDatabase() {
		return database;
	}

	/* XXXXXXXXXXXXXXXXXXXX Class Methods XXXXXXXXXXXXXXXXXXXX */


	public ResultSet getResults(String sql){
		
		return results = executeQuery(sql);
	}
	


	/**
	 * Vector<Model> find returns a Vector of a model subclass passed as the
	 * first parameter. Universal method to find all of the subclassed Model
	 * classes stored in a database.
	 * 
	 * wegenek-dev Jan 25, 2011
	 * 
	 * @param model
	 * @param type
	 * @param parameters
	 * @return Vector<Model>
	 * 
	 */
	






	/**
	 * ResultSet executeQuery attempts to execute a query on the connected
	 * database.
	 * 
	 * @param query
	 *            - The String query to execute
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {

			return null;
		}
	}

	/**
	 * executeUpdate attempts to update the current database using the sql
	 * statement passed by the parameter
	 * 
	 * @param sql
	 *            - The sql statement to be updated.
	 * @return -1 if error, >= 0 if ok
	 */
	public int executeUpdate(String sql) {
		int nReturn;
		try {
			nReturn = statement.executeUpdate(sql);
		} catch (SQLException e) {

			nReturn = -1;
		}
		return nReturn;
	}

	/**
	 * ResultSet execute attempts to execute the parameter sql.
	 * 
	 * @param sql
	 *            - The sql statement to execute
	 * @return The ResultSet after the statement is executed
	 */
	public ResultSet execute(String sql) {
		try {
			if (statement.execute(sql))
				return statement.getResultSet();
			else {
				return null;
			}
		} catch (SQLException e) {
			
				System.out.println(
						"execute Exception: " + e.toString() + " using "
								+ database + ".");
				System.out.println("Tried:");
				System.out.println(sql);
				

		}
		return null;
	}

	public int executeSingleValueQuery(String sql) {
		int result = -1;
		ResultSet resultSet = executeQuery(sql);
		try {
			resultSet.next();
			result = resultSet.getInt(1);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Update takes a modelArray and if the model is new will save to database.
	 * If the model is modified will perform an update on the database.
	 * 
	 * @param modelArray
	 *            - The Model[] of model objects to be updated.
	 */


	public void runSpecialAnalysisProcedure(String procedureCode) {
		String procedure = "{ call  projects.RUN_SPECIAL_ANALYSIS(?) }";
		try {
			callableStatement = connection.prepareCall(procedure);
			callableStatement.setString(1, procedureCode);
			callableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void runProcedure(String procedure, String[] procedureValues) {
		String procedureCall = "{ call  " + procedure + " }";
		try {
			callableStatement = connection.prepareCall(procedureCall);
			if (procedureValues != null) {
				for (int i = 0; i < procedureValues.length; i++) {
					callableStatement.setString(i + 1, procedureValues[i]);
				}
			}
			callableStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
