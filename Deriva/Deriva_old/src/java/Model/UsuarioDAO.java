/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Controller.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HAL
 */
public class UsuarioDAO {

    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());
	private IConnectionFactory connectionFactory;

	protected UsuarioDAO() {
	}

	protected UsuarioDAO(IConnectionFactory cf) {
		this.connectionFactory = cf;
	}

	protected IConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	protected void setConnectionFactory(IConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public static UsuarioDAO instance() {
		return new UsuarioDAO(new SimpleConnectionFactory());
	}

	public Usuario findById(int id) {
		Usuario usuario = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = connectionFactory.getConnection();

			ps = conn.prepareStatement("select profileid, email, nome, sobrenome, from Perfil where profileidid = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");				
				String sobrenome = rs.getString("sobrenome");

				usuario = new Usuario(nome, sobrenome);

			}
		} catch (SQLException ex) {
			LOG.log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) { }
			}
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
		return usuario;
	}
}
