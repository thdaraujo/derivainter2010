package deriva.db;

import deriva.neg.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class userDAO {

    Connection conn = null;
    PreparedStatement ps = null;

	private static final Logger LOG = Logger.getLogger(userDAO.class.getName());
	private ConnectionFactory connectionFactory;

	protected userDAO() {
	}

	protected userDAO(ConnectionFactory cf) {
		this.connectionFactory = cf;
	}

	protected ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

    public boolean validaLogin(Usuario usuario){
        ResultSet rs = null;
        try{
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select senha from Usuario where email = ?");
            ps.setString(1, usuario.getEmail());
            rs = ps.executeQuery();
            if(rs.next()){
                if (usuario.getSenha().equals(rs.getString("senha")));
                return true;
            }

        }catch(SQLException e){

        }
        return false;
    }
	public void cadastra(Usuario usuario) {
		

		try {
			conn = connectionFactory.getConnection();

			ps = conn.prepareStatement("insert into Usuario(email, senha, nome, sobrenome, dtnasc) values (?, ?, ?, ?, ?)");
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getSenha());
			ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getSobrenome());
            ps.setDate(5, usuario.getDtnasc());

			ps.executeUpdate();

		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, null, ex);
		} finally {
			if (ps != null)
			{
				try {
					ps.close();
				} catch (SQLException e) { }
			}
			if (conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) { }
			}
		}
	}
}
