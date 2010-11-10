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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Servlet que salva um novo amigo.
 * 
 */
public class SalvaTripulante extends HttpServlet {

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
        
       if (dao == null) dao = DAOFactory.getUserDAO();
       HttpSession session = request.getSession();
       Usuario usuario = (Usuario) session.getAttribute("usuario");
       
       if (usuario != null){
           int idusuario = usuario.getIdusuario();
           String amigo = request.getParameter("idusuario");

           int idamigo = 0;
           try{
               idamigo = Integer.parseInt(amigo);
               if (idamigo > 0){
                    dao.cadastrarAmigo(idusuario, idamigo);
               }
           }catch(Exception e){ }
       }
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

}
