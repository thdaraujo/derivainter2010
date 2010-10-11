/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import deriva.neg.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 *
 * @author HAL
 */
public class Autorizacao extends HttpServlet {

    private Usuario usuario;
    private deriva.neg.Autorizacao aut;
    private String email, senha;
    private boolean IsAutorizado;

    public boolean isIsAutorizado() {
        return IsAutorizado;
    }

    public void setIsAutorizado(boolean IsAutorizado) {
        this.IsAutorizado = IsAutorizado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(String email, String senha) {
        this.usuario = new Usuario(email, senha);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        if (IsAutorizado) return;
        else{
           Cookie cookie = null;
           Cookie[] cookies = null;
           // Get an array of Cookies associated with this domain
           cookies = request.getCookies();
           if( cookies != null ){
              for (int i = 0; i < cookies.length; i++){
                 cookie = cookies[i];
                 if (cookie.getName() == null ? "email" == null : cookie.getName().equals("email"))
                     email = cookie.getValue();
                 if (cookie.getName() == null ? "senha" == null : cookie.getName().equals("senha"))
                     senha = cookie.getValue();
              }
           }
          if (email != null && senha != null){
              setUsuario(email, senha);
              setIsAutorizado(ValidarAutorizacao(usuario));
              if (IsAutorizado) return;
           }

           else if (!IsAutorizado) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
          }
        }
    }
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

    private boolean ValidarAutorizacao(Usuario usuario) {
        aut = new deriva.neg.Autorizacao(usuario);
        boolean b = aut.PossuiAutorizacao(email, senha);
        return b;
    }
    private void goPage(HttpServletRequest httpRequest, ServletResponse response, String page) throws IOException, ServletException {
		debug("...trying to go to: " + page);
		RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher(page);
		requestDispatcher.forward(httpRequest, response);
	}
    private void debug(String msg) {
    	System.out.println(msg);
    }
}
