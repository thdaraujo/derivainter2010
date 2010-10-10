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

    public boolean validaLogin(Usuario usuario) throws SQLException{
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
//           throw e;
        }
        return false;
    }
	public void cadastra(Usuario usuario) throws SQLException {

		try {
			conn = connectionFactory.getConnection();

			
                        //ps = conn.prepareStatement("insert into Usuario(email, senha, nome, sobrenome, dtnasc) values (?, ?, ?, ?, ?)");

                        ps = conn.prepareStatement("INSERT INTO Usuario"
                                + "(email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc)"
                                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);");

                        ps.setString(1, usuario.getEmail());
                        ps.setString(2, usuario.getSenha());
                        ps.setString(3, usuario.getNickname());
                        ps.setString(4, usuario.getNome());
                        ps.setString(5, usuario.getSobrenome());
                        ps.setString(6, String.valueOf(usuario.getSexo()));
                        ps.setString(7, usuario.getMensagempessoal());
                        ps.setString(8, usuario.getImagemPerfil());
                        ps.setDate(9, usuario.getDtnasc());


//			ps.setString(1, usuario.getEmail());
//			ps.setString(2, usuario.getSenha());
//			ps.setString(3, usuario.getNome());
//                        ps.setString(4, usuario.getSobrenome());
//                        ps.setDate(5, usuario.getDtnasc());

			ps.executeUpdate();

		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, null, ex);
                        throw ex;
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