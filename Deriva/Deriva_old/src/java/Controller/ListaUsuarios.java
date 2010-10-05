/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.UsuarioDAO;

/**
 *
 * @author HAL
 */
public class ListaUsuarios {


    public Usuario getUsuarioPorId(int id){
        Usuario usuario = UsuarioDAO.instance().findById(id);

        return usuario;
    }
}
