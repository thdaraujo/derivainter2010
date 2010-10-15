package deriva.neg;

import deriva.db.DAOFactory;
import deriva.db.userDAO;
import java.sql.SQLException;

/** Maneja autorização de acesso.
 *
 */
public class Autorizacao {

    private userDAO dao = DAOFactory.getUserDAO();
    private boolean Logado = false;
    private boolean ExisteUsuarioAutorizado = false;
    private Usuario usuario;

    public Autorizacao(Usuario usuario){
        if (usuario != null) this.usuario = usuario;
        try {
            Logado = dao.validaLogin(usuario);
        } catch (Exception ex) {}
    }

    public boolean IsLogado (){
        return Logado;
    }

    public void LogOut(){
        usuario = null;
        ExisteUsuarioAutorizado = false;
        Logado = false;
    }

  public boolean PossuiAutorizacao(String email, String senha){
      if (Logado){
         if (email.equals(usuario.getEmail())
                 && senha.equals(usuario.getSenha())){
             ExisteUsuarioAutorizado = true;
          }
      }
      return ExisteUsuarioAutorizado;
  }

    public Usuario getUsuarioSession() throws SQLException {
        Usuario u = null;
        u = dao.FindLoginByEmail(this.usuario.getEmail());
        return u;
    }
}


