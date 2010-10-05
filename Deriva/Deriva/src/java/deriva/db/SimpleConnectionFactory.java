package deriva.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class SimpleConnectionFactory implements ConnectionFactory {

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		} catch (ClassNotFoundException e) { }
	}

	private static final String DEFAULT_DS_NAME = "jdbc/dslab2";

	private final DataSource dataSource;

	public SimpleConnectionFactory(String dsName) throws NamingException {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		dataSource = (DataSource) envContext.lookup(dsName);
	}

	public SimpleConnectionFactory() throws NamingException {
		this(DEFAULT_DS_NAME);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}


}
