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



public class Cadastra extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
		/* obtém parâmetros do request */
        String usuario = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String strAno = request.getParameter("ano");
        String strMes = request.getParameter("mes");
        String strDia = request.getParameter("dia");
        java.sql.Date datanasc = null;
        datanasc = new java.sql.Date((Integer.parseInt(strAno) - 1900), Integer.parseInt(strMes), Integer.parseInt(strDia));
        



		/* verifica autenticação */
        if (usuario != null && senha != null && nome != null && sobrenome != null && datanasc != null) {
            userDAO dao1 = DAOFactory.getUserDAO();
            Usuario user = new Usuario(usuario, senha, nome, sobrenome, datanasc);
            dao1.cadastra(user);
            


			/* redireciona (client-side) */
			response.sendRedirect("index.jsp");
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
