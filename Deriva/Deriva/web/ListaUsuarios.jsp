<%@page import="java.util.List"%>
<%@page import="deriva.db.userDAO"%>
<%@page import="deriva.db.DAOFactory"%>
<%@page import="deriva.neg.Usuario"%>
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.neg.Usuario" %>

  <%  session = request.getSession();
    Usuario usuario = null;

    if (session != null && session.getAttribute("usuario") != null){
            usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || usuario.getSenha() == null || usuario.getEmail() == null
                    || usuario.getEmail().isEmpty() || usuario.getSenha().isEmpty()){
            response.sendRedirect("index.jsp");
            return;
        }
        else{
            Autorizacao aut = new Autorizacao(usuario);
             if (!aut.PossuiAutorizacao(usuario.getEmail(), usuario.getSenha())){
                response.sendRedirect("index.jsp");
                return;
            }
        }           
    }
    else{
        response.sendRedirect("index.jsp");
        return;
    }
  %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt">
    <head>
        <meta charset="utf-8" />
        <title>Deriva - Lista de Usuarios</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!--[if IE]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/iehacks.css" />
        <![endif]-->

        <!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="scripts/ie67hacks.css" />
        <![endif]-->
    </head>

    <body>
        <div>
            &nbsp;
        </div>
        <header>
                <div id="logo">
                        <a href="#"><img src="images/logo.gif" alt="Logo" /></a>
                        <!--<hgroup>
                                <h1>Deriva</h1>
                                <h2>Rede Social</h2>
                        </hgroup>-->
                </div>
                <nav>
                        <ul>
                                <li><a href="/home.jsp">home</a></li>                                
                                <li><a href="/ListaUsuarios.jsp">Lista de Usuarios</a></li>
                                <li><a href="/EditarPerfil">Editar Perfil</a></li>
                                <li><a href="/SignOut">Logout</a></li>
                                <li><a href="#">123t</a></li>
                        </ul>
                </nav>
        </header>

        <div id="content">
                <section class="hfeed">                        
                                    <h2>Lista de Usuarios</h2>
                                        <%
                                            userDAO dao = DAOFactory.getUserDAO();
                                            List<Usuario> lista = null;

                                            lista = dao.ListarUsuarios();
                                              if (lista != null){
                                                for (Usuario usr : lista) {
                                                    if (usr.getImagemPerfil() != null && usr.getNickname() != null && usr.getEmail() != null){
                                                %>
                                                
                                                <article class="hentry">
                                                        <hgroup>
                                                             <h3 class="entry-title"><a href="#"><img src="<%= usr.getImagemPerfil() %>">
                                                            <%= usr.getNickname() %>
                                                            <%= usr.getEmail() %></a></h3>
                                                        </hgroup>
                                                        <p class="entry-summary"><%= usr.getmensagemPessoal() %></p>                                                         
                                                        <footer></footer>
                                                        <br /><hr /><br />
                                                </article>
                                        <%           }
                                                }
                                            }%>
                                
                     </section>
                <aside>

                        <h2>Contatos</h2>
                        <ul>
                                <li><a href="#">contato 1</a></li>
                                <li><a href="#">contato 2</a></li>
                                <li><a href="#">contato 3</a></li>
                        </ul>
                        <br />
                        <h2>Comunidades</h2>
                        <ul>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                        </ul>
                        <br />
                </aside>
        </div>

        <footer id="main-footer">
                <section id="footer-1">
                        Deriva &copy; 2010
                </section>
                <section id="footer-2">

                </section>
        </footer>
    </body>
</html>