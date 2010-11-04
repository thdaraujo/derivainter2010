/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiago.araujo
 */
public class ListaUsuarioOld {

    private userDAO dao;
    private boolean HasNext;
    private boolean HasPrevious;
    private List<Usuario> listaUsuario;


    /**
     * Pega uma lista de 10 usuarios e seta os valores de HasNext e HasPrevious.
     * @param numeroPagina
     * @return
     */

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    public void busca(int numeroPagina) {
        if (dao == null) dao = DAOFactory.getUserDAO();
        List<Usuario> lista = null;
        listaUsuario = new ArrayList<Usuario>();
        lista = dao.ListarUsuariosPaginado(numeroPagina);

        if (lista != null){
            if (lista.size() < 11) setHasNext(false);
            else setHasNext(true);
            if (numeroPagina < 1) setHasPrevious(false);
            else setHasPrevious(true);
        }

        for (int i = 0; i < 10; i++) {
            listaUsuario.add(lista.get(i));
        }
    }

    public boolean getHasNext() {
        return HasNext;
    }
    public boolean getHasPrevious() {
        return HasPrevious;
    }

    public void setHasNext(boolean HasNext) {
        this.HasNext = HasNext;
    }

    public void setHasPrevious(boolean HasPrevious) {
        this.HasPrevious = HasPrevious;
    }
}
