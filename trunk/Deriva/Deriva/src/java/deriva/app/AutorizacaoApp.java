package deriva.app;

import deriva.neg.Autorizacao;
import deriva.neg.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AutorizacaoApp {

    public static boolean autoriza(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        boolean autorizado = true;

        if (session != null) {
                Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario == null || usuario.getSenha() == null || usuario.getEmail() == null
                        || usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()){
                autorizado = false;
            }
            else {
                Autorizacao aut = new Autorizacao(usuario);
                 if (!aut.PossuiAutorizacao(usuario.getEmail(), usuario.getSenha())){
                    autorizado = false;
                }
            }           
        } else {
            autorizado = false;
        }

        if (!autorizado)
            response.sendRedirect("index.jsp");

        return autorizado;
    }
}
