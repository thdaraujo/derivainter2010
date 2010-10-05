package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn extends HttpServlet {

    private boolean login(String usuario, String senha) {
        userDAO dao1 = DAOFactory.getUserDAO();
        Usuario user = new Usuario(usuario, senha);
		return dao1.validaLogin(user);

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
            

			/* redireciona (client-side) */
			response.sendRedirect("home.jsp");
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
