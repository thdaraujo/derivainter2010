package deriva.db;

import deriva.neg.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class userDAO {

// <editor-fold defaultstate="collapsed" desc="Campos">
    Connection conn = null;
    PreparedStatement ps = null;
    private static final Logger LOG = Logger.getLogger(userDAO.class.getName());
    private ConnectionFactory connectionFactory;
  //</editor-fold>

// <editor-fold defaultstate="collapsed" desc="Construtores">
    protected userDAO() {
    }

    protected userDAO(ConnectionFactory cf) {
            this.connectionFactory = cf;
    }
      //</editor-fold>

// <editor-fold defaultstate="collapsed" desc="ConnFactory">
    protected ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    protected void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }// </editor-fold>

/**
 *  Metodos
 */

// <editor-fold defaultstate="collapsed" desc="INSERT">

    /**
     * Cadastra Usuario
     * @param usuario
     * @throws SQLException
     * @throws Exception
     */
    public void Cadastrar(Usuario usuario) throws SQLException {
        if (usuario.getEmail() != null && usuario.getSenha() != null
                && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()) {

            try {
                conn = connectionFactory.getConnection();
                ps = conn.prepareStatement("INSERT INTO Usuario"
                        + "(email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc)"
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);");

                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getSenha());
                ps.setString(3, usuario.getNickname());
                ps.setString(4, usuario.getNome());
                ps.setString(5, usuario.getSobrenome());
                ps.setString(6, String.valueOf(usuario.getSexo()));
                ps.setString(7, usuario.getmensagemPessoal());
                ps.setString(8, usuario.getImagemPerfil());
                ps.setDate(9, usuario.getDtnasc());
                ps.executeUpdate();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        } else {
            System.out.println("email ou senha não podem ser nulos!");
        }
    }

    /**
     * Cadastra amigo na entidade RelAmigo
     * @param idusuario = id do usuario
     * @param idamigo = id do usuario amigo
     */
    public void cadastrarAmigo(int idusuario, int idamigo) throws SQLException {

        if (idusuario > 0 && idamigo > 0) {
            try {
                conn = connectionFactory.getConnection();
                ps = conn.prepareStatement("INSERT INTO RelAmigo ([idusuario] ,[idamigo])" +
                        " VALUES (?, ?);");
                
                ps.setInt(1, idusuario);
                ps.setInt(2, idamigo);
                ps.executeUpdate();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        } else {
            System.out.println("ids invalidos!");
        }
    }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="SELECT">

    /**
     * Verifica se a senha é válida para o email passado.
     * @param usuario
     * @return true, se a senha estiver correta.
     * @throws SQLException
     */
    public boolean validaLogin(Usuario usuario) throws SQLException {
        ResultSet rs = null;
        try {
            conn = connectionFactory.getConnection();
            ps = conn.prepareStatement("select senha from Usuario where email = ?");
            ps.setString(1, usuario.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (usuario.getSenha().equals(rs.getString("senha")));
                return true;
            }

        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

    /**
     * SELECT Usuario pelo id.
     * @param id
     * @return Usuario ou null, se não existir.
     * @throws SQLException
     */
    public Usuario FindLoginById(int id) throws SQLException {
        Usuario u = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select idusuario, email, senha, nickname from Usuario where idusuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String nickname = rs.getString("nickname");

                u = new Usuario(idusuario, email, senha, nickname);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return u;
    }

    /**
     * SELECT Campos de login do usuario pelo email.
     * @param Email
     * @return Usuario com seu idusuario, email, senha e nickname.
     * @throws SQLException
     */
    public Usuario FindLoginByEmail(String Email) throws SQLException {
        Usuario u = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select idusuario, email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc from Usuario where email = ?");
            ps.setString(1, Email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");


                char sexo = ' ';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);


                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");                

                u = new Usuario(idusuario, email, nome, senha, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                u.setIdusuario(idusuario);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return u;
    }

    /**
     * SELECT Usuario por id.
     * @param id
     * @return Uusario.
     * @throws SQLException
     */
    public Usuario FindUsuarioById(int id) throws SQLException {
        Usuario u = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select idusuario, email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc from Usuario"
                    + " where idusuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);


                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return u;
    }

    /**
     * SELECT Usuario por email.
     * @param Email
     * @return Usuario.
     * @throws SQLException
     */
    public Usuario FindUsuarioByEmail(String Email) throws SQLException {
        Usuario u = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select idusuario, email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc from Usuario"
                    + " where email = ?");
            ps.setString(1, Email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);


                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return u;
    }

    /**
     * Lista todos os usuarios cadastrados.
     * @return
     */
    public List<Usuario> ListarUsuarios(){
       List<Usuario> listaUsuarios = new ArrayList<Usuario>();
       Usuario u = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("select idusuario, email, senha, nickname, nome, sobrenome, sexo, mensagemPessoal, imagemPerfil, dtnasc from Usuario");
            rs = ps.executeQuery();

            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);
                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                listaUsuarios.add(u);
            }
            return listaUsuarios;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }


    /**
     * Lista todos os usuarios cadastrados de forma paginada.
     * @return
     */
    public List<Usuario> ListarUsuariosPaginado(int numeroPagina){
       List<Usuario> listaUsuarios = new ArrayList<Usuario>();
       Usuario u = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       int inicio = 0;
       int fim = 11;

       //multiplo de 11
       if (numeroPagina > 0){
           inicio = numeroPagina * 10;
           fim = inicio + 11;
       }



        try {
            conn = connectionFactory.getConnection();

            //Seleciona os usuarios de forma paginada - de 11 em 11.
            ps = conn.prepareStatement("SELECT  * FROM (" +
                                                        " SELECT ROW_NUMBER() OVER (ORDER BY idusuario ASC) AS row_number, " +
                                                        " idusuario, email, senha, nickname, nome, sobrenome, sexo, " +
                                                        " mensagemPessoal, imagemPerfil, dtnasc " +
                                                        " FROM Usuario" +
                                                        ") foo " +
                                        " WHERE row_number > ? and row_number <= ?;");

            ps.setInt(1, inicio);
            ps.setInt(2, fim);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);
                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                listaUsuarios.add(u);
            }
            return listaUsuarios;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }

    /**
     * Lista todos os amigos de um usuario de forma paginada.
     * @return
     */
    public List<Usuario> ListarTripulantesPaginado(int numeroPagina,int idusuariologado){
       List<Usuario> listaUsuarios = new ArrayList<Usuario>();
       Usuario u = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       int inicio = 0;
       int fim = 11;

       //multiplo de 11
       if (numeroPagina > 0){
           inicio = numeroPagina * 10;
           fim = inicio + 11;
       }



        try {
            conn = connectionFactory.getConnection();

            //Seleciona os usuarios de forma paginada - de 11 em 11.
            ps = conn.prepareStatement("SELECT  * FROM (" +
                    " SELECT ROW_NUMBER() OVER (ORDER BY RelAmigo.idusuario ASC) AS row_number," +
                    " Usuario.idusuario, Usuario.email, Usuario.senha, Usuario.nickname, Usuario.nome, Usuario.sobrenome, Usuario.sexo, Usuario.mensagemPessoal, Usuario.imagemPerfil, Usuario.dtnasc " +
                    " FROM RelAmigo join USUARIO on RelAmigo.idamigo = Usuario.idusuario " +
                    " where RelAmigo.idusuario = ?) foo " +
                    " WHERE row_number > ? and row_number <= ?;");

            ps.setInt(1, idusuariologado);
            ps.setInt(2, inicio);
            ps.setInt(3, fim);



            rs = ps.executeQuery();

            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);
                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                listaUsuarios.add(u);
            }
            return listaUsuarios;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }

    public List<Usuario> listarAmigos(int idusuario){
        List<Usuario> listaAmigos = new ArrayList<Usuario>();

       Usuario u = null;
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

        try {
            conn = connectionFactory.getConnection();

            ps = conn.prepareStatement("SELECT Usuario.idusuario, Usuario.email, Usuario.senha, Usuario.nickname, Usuario.nome, Usuario.sobrenome, Usuario.sexo, Usuario.mensagemPessoal, Usuario.imagemPerfil, Usuario.dtnasc " +
                    " FROM RelAmigo join USUARIO on RelAmigo.idamigo = Usuario.idusuario " +
                    " where RelAmigo.idusuario = ?;");
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();

             while (rs.next()) {
                int id = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);
                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(id, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                listaAmigos.add(u);
            }
            return listaAmigos;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
        return null;   
    }

    /**
     * Lista os usuarios segundo uma lista de ids. Útil para quando for preciso listar os usuarios amigos ou
     * cadastrados em uma comunidade.
     * @param ids
     * @return
     * @throws SQLException
     */
    public List<Usuario> ListarUsuarios(int[] ids) throws SQLException{
        List<Usuario> listaUsuarios = null;
        Usuario u = null;

        for (int id : ids) {
            if (id > 0){
                u = FindUsuarioById(id);
                if (u != null) listaUsuarios.add(u);
            }
        }
        return listaUsuarios;
    }

    public List<Usuario> BuscarUsuarios(int numeroPagina, String termosBusca){
       List<Usuario> listaUsuarios = new ArrayList<Usuario>();
       Connection conn = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       int inicio = 0;
       int fim = 11;
       Usuario u = null;

       //multiplo de 11
       if (numeroPagina > 0){
           inicio = numeroPagina * 10;
           fim = inicio + 11;
       }

        try {
            conn = connectionFactory.getConnection();

            //Seleciona os usuarios de forma paginada - de 11 em 11.
            ps = conn.prepareStatement("SELECT  * FROM (" +
                    " SELECT ROW_NUMBER() OVER (ORDER BY id ASC) AS row_number," +
                    " Usuario.idusuario, Usuario.email, Usuario.senha, Usuario.nickname, Usuario.nome, Usuario.sobrenome, Usuario.sexo, Usuario.mensagemPessoal, Usuario.imagemPerfil, Usuario.dtnasc " +
                    " where where upper(email) like upper('%?%')" +
                    " or upper(nickname) like upper('%?%')" +
                    " or upper(nome) like upper('%?%')" +
                    " or upper(sobrenome) like upper('%?%')" +
                    " ) foo " +
                    " WHERE row_number > ? and row_number <= ?;");

            ps.setString(1, termosBusca);
            ps.setString(2, termosBusca);
            ps.setString(3, termosBusca);
            ps.setString(4, termosBusca);
            ps.setInt(5, inicio);
            ps.setInt(6, fim);



            rs = ps.executeQuery();

            while (rs.next()) {
                int idusuario = rs.getInt("idusuario");
                String email = rs.getString("email");
                String nickname = rs.getString("nickname");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");

                char sexo = '\0';
                if (rs.getString("sexo") != null) {
                    sexo = rs.getString("sexo").charAt(0);
                }
                String mensagemPessoal = rs.getString("mensagemPessoal");
                String imagemPerfil = rs.getString("imagemPerfil");
                Date dtnasc = rs.getDate("dtnasc");
                String senha = rs.getString("senha");

                u = new Usuario(idusuario, email, senha, nome, sobrenome, nickname, mensagemPessoal, sexo, imagemPerfil, dtnasc);
                listaUsuarios.add(u);
            }
            return listaUsuarios;

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { }
            }
        }
        return null;
    }


     public List<Usuario> ListarUsuariosAtivos(){
         //TODO adicionar coluna ativo na tabela usuarios e modificar classe Usuario.
         return null;
     }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="UPDATE">

    /**
     * UPDATE dos dados do usuario.
     * @param usuario
     */
    public void UpdateUsuario(Usuario usuario) throws Exception {

        if (usuario.getEmail() != null && usuario.getSenha() != null
                && !usuario.getEmail().isEmpty() && !usuario.getSenha().isEmpty()) {

            try {
                conn = connectionFactory.getConnection();

                StringBuilder sb = new StringBuilder();

                //Constroi a string que vai se tornar a query de update
                sb.append("UPDATE Usuario \n");
                sb.append("SET ");
                sb.append("email = ?");
                sb.append(",senha = ?");
                sb.append(",nickname = ?");
                sb.append(",nome = ?");
                sb.append(",sobrenome = ?");
                sb.append(",sexo = ?");
                sb.append(",mensagemPessoal = ?");
                sb.append(",imagemPerfil = ?");
                sb.append(",dtnasc = ? \n");
                sb.append("WHERE idusuario = ?;");

                ps = conn.prepareStatement(sb.toString());

                ps.setString(1, usuario.getEmail());
                ps.setString(2, usuario.getSenha());
                ps.setString(3, usuario.getNickname());
                ps.setString(4, usuario.getNome());
                ps.setString(5, usuario.getSobrenome());
                ps.setString(6, String.valueOf(usuario.getSexo()));
                ps.setString(7, usuario.getmensagemPessoal());
                ps.setString(8, usuario.getImagemPerfil());
                ps.setDate(9, usuario.getDtnasc());
                //WHERE idusuario = ?
                ps.setInt(10, usuario.getIdusuario());
                ps.executeUpdate();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        } else {
           
        }
    }
    
    public void UpdatePerfil(Usuario usuario) throws Exception {
     

            try {
                conn = connectionFactory.getConnection();

                final String query = "UPDATE Usuario " +
                        "SET " +
                        "nickname = ?, " +
                        "mensagemPessoal = ?, " +
                        "imagemPerfil = ? " +
                        "WHERE idusuario = ?";

                ps = conn.prepareStatement(query);

                ps.setString(1, usuario.getNickname());
                ps.setString(2, usuario.getmensagemPessoal());
                ps.setString(3, usuario.getImagemPerfil());
                //WHERE idusuario = ?
                ps.setInt(4, usuario.getIdusuario());
                ps.executeUpdate();

            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
                throw ex;
            } finally {
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }    
             
    }


// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="DELETE">
    /**
     * DELETE usuario por id.
     * @param id
     */
    public void DeleteUsuario(int id) throws SQLException {
        try {
            conn = connectionFactory.getConnection();
            ps = conn.prepareStatement("DELETE from Usuario where idusuario = ?;");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void DeleteTripulante(int idusuario, int idamigo) throws SQLException {
        try {
            conn = connectionFactory.getConnection();
            ps = conn.prepareStatement("DELETE from RelAmigo where idusuario = ? and idamigo = ?;");
            ps.setInt(1, idusuario);
            ps.setInt(2, idamigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

// </editor-fold>

//Fim dos metodos.
}