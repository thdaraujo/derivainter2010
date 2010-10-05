/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luiz.perez
 */
public class Cadastra extends HttpServlet {

    private void cadastra(String usuario, String senha, String nome, String sobrenome, Date dtnasc) {
        userDAO dao1 = DAOFactory.getUserDAO();
        Usuario user = new Usuario(usuario, senha, nome, sobrenome, dtnasc);
		dao1.cadastra(user);

	}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ParseException {
        Date dtnasc = null;
		/* obtém parâmetros do request */
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        try{
            String nascStr = request.getParameter("dtnasc");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dtnasc = sdf.parse(nascStr);
        }catch(ParseException e){

        }

		/* verifica autenticação */
        if (usuario != null && senha != null && nome != null && sobrenome != null && dtnasc != null) {
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
