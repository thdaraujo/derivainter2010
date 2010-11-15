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
 * @author marcio.stabile
 */
public class ListaTripulanteFacade {

    private userDAO dao;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<Usuario> listaUsuario;


    /**
     * Pega uma lista de até 10 amigos e seta os valores de HasNext e HasPrevious.
     * @param numeroPagina
     * @return
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }
    public void busca(int numeroPagina,int idusuariologado) {
        if (dao == null) dao = DAOFactory.getUserDAO();
        List<Usuario> lista = null;
        listaUsuario = new ArrayList<Usuario>();
        lista = dao.ListarTripulantesPaginado(numeroPagina, idusuariologado);

        if (lista != null){
            if (lista.size() < 11) setHasNext(false);
            else setHasNext(true);
            if (numeroPagina < 1) setHasPrevious(false);
            else setHasPrevious(true);
        }

        /**Pega os usuarios da lista paginada.
         * Se houver 10, pega todos.
         * Se houver menos do que 10 (e.g.: é última página e tem menos do que 10 usuarios),
         * pega só esses que sobraram e dá break;
         */
        for (int i = 0; i < 10; i++) {
            if (i < lista.size()) listaUsuario.add(lista.get(i));
            else break;
        }
    }
    
    public boolean getHasNext() {
        return hasNext;
    }
    public boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasNext(boolean HasNext) {
        this.hasNext = HasNext;
    }
    public void setHasPrevious(boolean HasPrevious) {
        this.hasPrevious = HasPrevious;
    }
}
