package deriva.db;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class DAOFactory {

	static {
		try {
			cf = new SimpleConnectionFactory();
		} catch (NamingException ex) {
			Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static ConnectionFactory cf;

	public static userDAO getUserDAO() {
		return new userDAO(cf);
	}

}
