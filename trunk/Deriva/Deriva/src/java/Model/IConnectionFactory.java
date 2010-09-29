package Model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Luiz, Marcio, Thiago.
 */
public interface IConnectionFactory {
    public Connection getConnection() throws SQLException;
}
