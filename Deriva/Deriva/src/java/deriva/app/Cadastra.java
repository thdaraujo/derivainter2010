package deriva.app;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import deriva.neg.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Cadastra extends HttpServlet {
    private userDAO dao1 = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{

        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        
        if (dao1 == null) dao1 = DAOFactory.getUserDAO();
        
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

        if(!strAno.equals("") && !strMes.equals("") && !strDia.equals("")){
            datanasc = new java.sql.Date((Integer.parseInt(strAno) - 1900), Integer.parseInt(strMes), Integer.parseInt(strDia));
        }


        if (!usuario.matches(emailRegex) || usuario.isEmpty()){
            request.setAttribute("errorCode", 3);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if(!senha.equals(senha2) || senha.isEmpty() || senha2.isEmpty()){
            request.setAttribute("errorCode", 4);
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
            dispatcher.forward(request, response);
            return;
        }

        
        /* verifica autenticação */
        if (usuario != null && senha != null && nome != null && sobrenome != null && datanasc != null) {
            
            Usuario user = new Usuario(usuario, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, datanasc);
            try {
                //verifica se já existe
                Usuario temp = dao1.FindLoginByEmail(usuario);
                if (temp != null) {
                     request.setAttribute("errorCode", 9);
                     RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                
                dao1.Cadastrar(user);
                user = dao1.FindUsuarioByEmail(user.getEmail());

                //se nao houver session, cria uma nova e adiciona o usuario.
                AdicionarSession(user, request.getSession(true));
            /* redireciona (client-side) */
            response.sendRedirect("/home.jsp");                
                
            } catch (SQLException ex) {
                Logger.getLogger(Cadastra.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
	
        else {
            /* define código de erro */
            request.setAttribute("errorCode", 1);
            /* redireciona (server-side) */
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastro.jsp");
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

    /***
     * Adiciona o usuario à sessão.
     * @param usuario
     * @param session
     */
    public void AdicionarSession(Usuario usuario, HttpSession session){
        /* cria uma sessão e adiciona o usuário */
       if (session != null && usuario != null){
            session.setAttribute("usuario", usuario);           
       }
    }
}
