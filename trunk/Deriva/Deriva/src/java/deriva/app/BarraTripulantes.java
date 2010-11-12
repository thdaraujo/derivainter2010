/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author thiago.araujo
 */
public class BarraTripulantes extends HttpServlet {

    private userDAO dao;
    private List<Usuario> listaAmigos;

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
        String usuarioid = request.getParameter("id");
        
        int idusuario = 0;
        try{
            idusuario = Integer.parseInt(usuarioid);
        }catch(Exception ex){}        
            
        if (idusuario == 0){
            Usuario user = (Usuario) session.getAttribute("usuario");            
            idusuario = user.getIdusuario();
        }
        
        List<Usuario> listaAmigos = dao.listarAmigos(idusuario);  
        request.setAttribute("listaAmigos", listaAmigos);

        getServletContext().getRequestDispatcher("/ListaUsuarios.jsp").forward(request, response);
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
