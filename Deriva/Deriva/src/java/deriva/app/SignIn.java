package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Autorizacao;
import deriva.neg.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn extends HttpServlet {
    private Usuario usersession;

    private boolean login(String usuario, String senha) {      
       Usuario user = new Usuario(usuario, senha);
       Autorizacao aut = null;
       aut = new Autorizacao(user);
       try{
            /* cria uma sessão e adiciona o usuário */
       usersession = aut.getUsuarioSession();
       }catch (Exception e){}

       if (aut != null && usersession != null) return aut.IsLogado();
       else return false;
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		/* obtém parâmetros do request */

        HttpSession session = request.getSession(true);
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

            /* verifica autenticação */
        if (usuario != null && senha != null && login(usuario, senha)) {
            if (usersession != null) session.setAttribute("usuario", usersession);
           
            /* redireciona (client-side) */
            response.sendRedirect("home.jsp");
            return;
        } else {
                /* define código de erro */
                request.setAttribute("errorCode", 1);
                /* redireciona (server-side) */
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
