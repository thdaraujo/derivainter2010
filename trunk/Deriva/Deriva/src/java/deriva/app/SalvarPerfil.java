package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
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

public class SalvarPerfil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String mensagemPessoal = request.getParameter("mensagemPessoal");
        String imagemPerfil = request.getParameter("imagemPerfil");
        HttpSession session = request.getSession();
        userDAO dao1 = DAOFactory.getUserDAO();
        Usuario user = null;

        if (nickname != null && session != null) {
            try{
            user = (Usuario) session.getAttribute("usuario");
            user.setNickname(nickname);
            user.setmensagemPessoal(mensagemPessoal);
            user.setImagemPerfil(imagemPerfil);

                dao1.UpdatePerfil(user);
            } catch (Exception ex) {
                Logger.getLogger(SalvarPerfil.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user != null) session.setAttribute("usuario", user);

        /* redireciona (client-side) */
        response.sendRedirect("/home.jsp");
        }
	
        else {
            /* define código de erro */
            request.setAttribute("errorCode", 2);

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
