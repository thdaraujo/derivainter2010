/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Busca generica.
 * @author HAL
 */
public class Busca extends HttpServlet {
   
    private boolean next = false, previous = false;
    private userDAO dao;    
    
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
             
        String pag = request.getParameter("pagina");
        String termosBusca = request.getParameter("termos");
        int pageNumber = 0;
        try{
            pageNumber = Integer.parseInt(pag);
        }catch (Exception e){}
        
        //anti-usuario engraçadinho
        if (pageNumber < 0) pageNumber = 0;
        if (termosBusca == null) termosBusca = "";
        
        List<Usuario> listaPaginada = busca(pageNumber, termosBusca);
        
        request.setAttribute("listaPaginada", listaPaginada);
        request.setAttribute("hasNext", hasNext());
        request.setAttribute("hasPrevious", hasPrevious());
        request.setAttribute("numeroPagina", pageNumber);

        getServletContext().getRequestDispatcher("/busca.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public List<Usuario> busca(int numeroPagina, String termosBusca) {
        if (dao == null) dao = DAOFactory.getUserDAO();
        List<Usuario> lista = new ArrayList<Usuario>();
        List<Usuario> usuariosEncontrados = new ArrayList<Usuario>();
        lista = dao.BuscarUsuarios(numeroPagina, termosBusca);

        if (lista != null){
            if (lista.size() < 11) setNext(false);
            else setNext(true);
            if (numeroPagina < 1) setPrevious(false);
            else setPrevious(true);
        }

        /**Pega os usuarios da lista paginada.
         * Se houver 10, pega todos.
         * Se houver menos do que 10 (e.g.: é última página e tem menos do que 10 usuarios),
         * pega só esses que sobraram e dá break;
         */
        for (int i = 0; i < 10; i++) {
            if (i < lista.size()) usuariosEncontrados.add(lista.get(i));
            else break;
        }
        return usuariosEncontrados;
    }
    
    
    public boolean hasNext() {
        return next;
    }
    public boolean hasPrevious() {
        return previous;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
    public void setPrevious(boolean previous) {
        this.previous = previous;
    }
}
