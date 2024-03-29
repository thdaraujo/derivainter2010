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
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Mostra o perfil de um navegante qualquer.
 *
 */
public class MostraPerfil extends HttpServlet {
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
           String idnavegante = request.getParameter("id");
           Usuario u = null;
           Usuario eu = null;

           int id = 0;
           try{
               id = Integer.parseInt(idnavegante);
               if (id > 0){
                   u = dao.FindUsuarioById(id);
               }
           }catch(Exception e){ }

           //verifica se o cara atual sou eu mesmo.
           eu = (Usuario) session.getAttribute("usuario");
           if (eu != null && eu.getIdusuario() == id){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
                dispatcher.forward(request, response);
                return;
           }

           if (u != null) request.setAttribute("usuario", u);           


          request.setAttribute("IsAmigo", IsAmigo(u.getIdusuario(), session));

           RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mostraPerfil.jsp");
           dispatcher.forward(request, response);
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


    /***
     * Verifica se o usuario do perfil consta da lista de tripulantes da session
     * @param idusuario
     * @param session
     * @return
     */
    private boolean IsAmigo(int idusuario, HttpSession session) {
        boolean isAmigo = false;
        if (session != null){            
            List<Usuario> tripulantes = (List<Usuario>)session.getAttribute("tripulantes");

            //fancy foreach
            for (Iterator<Usuario> iterator = tripulantes.iterator(); iterator.hasNext();) {
                Usuario current = iterator.next();
                if (current.getIdusuario() == idusuario) {
                    isAmigo = true;
                }
            }           
        }
        return isAmigo;
    }
}
