/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import deriva.neg.CelulaUsuario;
import deriva.neg.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HAL
 */
public class MenorCaminho extends HttpServlet {

    private CelulaUsuario cell;
    private userDAO dao = DAOFactory.getUserDAO();

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
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        String id = request.getParameter("id");
        int idusuario = 0;
        List<Usuario> caminho = new ArrayList<Usuario>();

        try{
            idusuario = Integer.parseInt(id);
        }catch (Exception ex){}

        if (idusuario > 0){
            try{
                    Usuario fim = dao.FindLoginById(idusuario);
                    if (cell == null) cell = new CelulaUsuario(null, usuario);
                    if (usuario != null && fim != null){
                        cell.menorCaminho(usuario, fim);                        
                        caminho = cell.retornaCaminho();
                        request.setAttribute("caminho", caminho);
                    }
                }catch(Exception ex){}
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
