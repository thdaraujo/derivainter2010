/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author HAL
 */
public class SimpleConnectionFactory implements IConnectionFactory {

    static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) { }
	}  

    @Override
    public Connection getConnection() throws SQLException {
		Properties info = new Properties();
		info.setProperty("user", "DB1380_Inter2010");
		info.setProperty("password", "campeao123");
		return DriverManager.getConnection("jdbc:sqlserver://174.37.19.98:25000/DB1380_Inter2010", info);
		//jdbc:sqlserver://localhost;user=MyUserName;password=*****
	}

}
