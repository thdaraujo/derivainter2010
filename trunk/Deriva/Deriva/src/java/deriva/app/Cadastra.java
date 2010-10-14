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

public class Cadastra extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
       
		/* obtém parâmetros do request */
        String usuario = request.getParameter("email");
        String senha = request.getParameter("senha");
        String senha2 = request.getParameter("senha2");

        String nickname = request.getParameter("nickname");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        
        char sexo = ' ';
        if (request.getParameter("sexo") != null){
            String sex = request.getParameter("sexo");            
            sexo = sex.charAt(0);
        }

        String mensagemPessoal = request.getParameter("mensagemPessoal");
        String imagemPerfil = request.getParameter("imagemPerfil");
        String strAno = request.getParameter("ano");
        String strMes = request.getParameter("mes");
        String strDia = request.getParameter("dia");
        java.sql.Date datanasc = null;
        if(!(strAno.equals("")) && (strMes.equals("")) && strDia.equals("")){
            datanasc = new java.sql.Date((Integer.parseInt(strAno) - 1900), Integer.parseInt(strMes), Integer.parseInt(strDia));
        }


        if (!usuario.contains("@")){
            request.setAttribute("errorCode", 3);
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if(!senha.equals(senha2)){
            request.setAttribute("errorCode", 4);
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }


        /* verifica autenticação */
        if (usuario != null && senha != null && nome != null && sobrenome != null && datanasc != null) {
            userDAO dao1 = DAOFactory.getUserDAO();
            Usuario user = new Usuario(usuario, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, datanasc);
            try {
                dao1.Cadastrar(user);
            } catch (SQLException ex) {
                Logger.getLogger(Cadastra.class.getName()).log(Level.SEVERE, null, ex);
            }

            //se nao houver session, cria uma nova.
            AdicionarSession(usuario, nome, senha, request.getSession(true));

            // cria cookies
            Cookie ckEmail = new Cookie("email", usuario);
            Cookie ckSenha = new Cookie("senha", senha);
            Cookie ckNome = new Cookie("nome", nome);

           // Expiram em 1 dia.
           ckEmail.setMaxAge(60*60*24);
           ckSenha.setMaxAge(60*60*24);
           ckNome.setMaxAge(60*60*24);

           response.addCookie(ckEmail);
           response.addCookie(ckSenha);
           response.addCookie(ckNome);

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


    public void AdicionarSession(String usuario, String senha, String nome, HttpSession session){
        /* cria uma sessão e adiciona o login do usuário */
       if (session != null){
            session.setAttribute("usuario", usuario);
            session.setAttribute("senha", senha);
       }
    }
}
