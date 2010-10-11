package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Autorizacao;
import deriva.neg.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn extends HttpServlet {


    private boolean login(String usuario, String senha) {
        userDAO dao1 = DAOFactory.getUserDAO();
        Usuario user = new Usuario(usuario, senha);
        Autorizacao aut = null;

           // b = dao1.validaLogin(user);
           aut = new Autorizacao(user);
        if (aut != null) return aut.IsLogado();
        else return false;
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		/* obtém parâmetros do request */
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

		/* verifica autenticação */
            if (usuario != null && senha != null && login(usuario, senha)) {
                /* cria uma sessão e adiciona o login do usuário */
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setAttribute("senha", senha);

                Cookie ckEmail = new Cookie("email", usuario);
                Cookie ckSenha = new Cookie("senha", senha);

               // Expira em 1 dia.
               ckEmail.setMaxAge(60*60*24);
               ckSenha.setMaxAge(60*60*24);

               response.addCookie(ckEmail);
               response.addCookie(ckSenha);

                /* redireciona (client-side) */
                response.sendRedirect("home.jsp");

//               /* redireciona (server-side) */
//               RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");
//                dispatcher.forward(request, response);
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
