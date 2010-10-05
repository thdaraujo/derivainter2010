/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


/**
 *
 * @author HAL
 */
public class DAOFactory {

    private static IConnectionFactory cf = new SimpleConnectionFactory();

	public static UsuarioDAO getUsuarioSAO() {
		return new UsuarioDAO(cf);
	}
}
