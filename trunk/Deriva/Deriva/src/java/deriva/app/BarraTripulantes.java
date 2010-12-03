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
        if (dao == null) dao = DAOFactory.getUserDAO();
        HttpSession session = request.getSession();
        String usuarioid = request.getParameter("id");
        List<Usuario> listaAux;
        listaAmigos = new ArrayList<Usuario>();
        int idusuario = 0;
        String title = "";

        try{
            idusuario = Integer.parseInt(usuarioid);
        }catch(Exception ex){}        
            
        if (idusuario == 0){
            //Usuario user = (Usuario) session.getAttribute("usuario");
            //idusuario = user.getIdusuario();
            listaAux = (List<Usuario>)session.getAttribute("tripulantes");
            title = "Tripulantes";
        }
        else{


             try{
                 Usuario user = dao.FindUsuarioById(idusuario);
                 Usuario eu = (Usuario) session.getAttribute("usuario");
                 
                 //por questão de clareza, vou adicionar isso ao título da barra só se não for o meu perfil a ser visto.
                 if (user.getIdusuario() != eu.getIdusuario()){
                     title = "Tripulantes de " + user.getNickname();
                 }
             }catch(Exception ex){}
        }

        listaAux = dao.listarAmigos(idusuario);
        if (listaAux != null){
                 for (int i = 0; i < 4; i++) {
                    if (i < listaAux.size()) listaAmigos.add(listaAux.get(i));
                else break;
             }
                 request.setAttribute("listaAmigos", listaAmigos);
        }
        
        request.setAttribute("title", title);
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
