/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import deriva.neg.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcio.stabile
 */
public class ListaTripulantes extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String pag = request.getParameter("pagina");
        int pageNumber = 0;
        try{
            pageNumber = Integer.parseInt(pag);
        }catch (Exception e){}
        
        //anti-usuario engraçadinho
        if (pageNumber < 0) pageNumber = 0;

        ListaTripulanteFacade luf = new ListaTripulanteFacade();

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        luf.busca(pageNumber,usuario.getIdusuario());
        
        List<Usuario> listaPaginada = luf.getListaUsuario();

        request.setAttribute("listaPaginada", listaPaginada);
        request.setAttribute("hasNext", luf.getHasNext());
        request.setAttribute("hasPrevious", luf.getHasPrevious());
        request.setAttribute("numeroPagina", pageNumber);

        getServletContext().getRequestDispatcher("/ListaTripulantes.jsp").forward(request, response);
        
    } 

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
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

}
