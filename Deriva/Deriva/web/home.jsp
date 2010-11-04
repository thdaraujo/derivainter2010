
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.neg.Usuario" %>
<%@page import="deriva.app.AutorizacaoApp" %>

<%
    if (!AutorizacaoApp.autoriza(request, response))
        return;
%>


  <%
    session = request.getSession();
    Usuario usuario = null;
    String ola = "usuário";
    String mensagemPessoal = "escreva uma mensagem pessoal!";
    String pathImage = application.getContextPath()+ "/images/";

    String imagemPerfil = pathImage + "logo.gif";

    if (session != null && session.getAttribute("usuario") != null){
            usuario = (Usuario) session.getAttribute("usuario");       
       
        
            if (usuario.getNickname() != null && !usuario.getNickname().isEmpty()) ola = usuario.getNickname();
            else if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) ola = usuario.getEmail();
            if (usuario.getmensagemPessoal() != null && !usuario.getmensagemPessoal().isEmpty()) mensagemPessoal = usuario.getmensagemPessoal();
            if (usuario.getImagemPerfil() != null && !usuario.getImagemPerfil().isEmpty()){
                //imagem vem da pasta images se não vier da web (se não for um caminho de imagem, não vai conter / no nome)
                if (!usuario.getImagemPerfil().contains("/"))
                    imagemPerfil = pathImage + usuario.getImagemPerfil();
                else imagemPerfil = usuario.getImagemPerfil();
            }
    }    
  %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="pt">
    <head>
        <meta charset="utf-8" />
        <title>Deriva - Home</title>
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
                        <a href="/home.jsp"><img src="images/logo.gif" alt="Logo" /></a>
                        <!--<hgroup>
                                <h1>Deriva</h1>
                                <h2>Rede Social</h2>
                        </hgroup>-->
                </div>
                <nav>
                        <ul>
                                <li><a href="/home.jsp">Home</a></li>                                
                                <li><a href="/ListaUsuarios.jsp">Lista de Usuarios</a></li>
                                <li><a href="/EditarPerfil">Editar Perfil</a></li>
                                <li><a href="/SignOut">Logout</a></li>
                                <li><a href="#">123t</a></li>
                        </ul>
                </nav>
        </header>

        <div id="content">
                <section class="hfeed">
                        <article class="hentry">
                                <hgroup>
                                        <h2 class="entry-title"><a href="/home.jsp">Bem Vindo, <%= ola %>!</a></h2>
                                        <h3> <a class="author" href="#"> <%= usuario.getEmail() %></a> </h3>
                                </hgroup>
                                <img src="<%=imagemPerfil %>" class="imagemPerfil">
                                <p class="entry-summary"> <%= mensagemPessoal %>  </p>
                                <footer><a href="#">Comment on this (5)</a>&emsp;&bull;&emsp;<a href="#">Tweet this</a>&emsp;&bull;&emsp;<a href="#">Stumble Upon</a></footer>
                                <br /><hr /><br />
                        </article>
                        <article class="hentry">
                                <hgroup>
                                        <h2 class="entry-title"><a href="#">The Title</a></h2>
                                        <h3>Posted by <a class="author" href="#">Johnny</a> on <abbr class="updated published" title="20100228T15:08:00">February 28th</a></time></h3>
                                </hgroup>
                                <p class="entry-summary">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque feugiat hendrerit ante ut sagittis. Fusce blandit interdum tellus, non ornare massa luctus id. Proin lectus libero, dignissim sit amet dignissim in, facilisis sit amet tellus. Aenean sed felis a justo ultrices facilisis. Sed vehicula sagittis consequat. Donec iaculis lacinia augue eu aliquam. Vestibulum aliquet erat quis felis venenatis a ullamcorper diam semper. Donec vel neque quis sem fermentum tincidunt ac in mi. Pellentesque auctor consectetur justo, eu fermentum urna volutpat sit amet. Suspendisse lacus tellus, porta sed condimentum et, elementum vel diam. Donec at massa neque. Sed lobortis feugiat metus, tincidunt dignissim quam convallis sed.</p>
                                <footer><a href="#">Comment on this (5)</a>&emsp;&bull;&emsp;<a href="#">Tweet this</a>&emsp;&bull;&emsp;<a href="#">Stumble Upon</a></footer>
                                <br /><hr /><br />
                        </article>
                        <article class="hentry">
                                <hgroup>
                                        <h2 class="entry-title"><a href="#">The Title</a></h2>
                                        <h3>Posted by <a class="author" href="#">Johnny</a> on <abbr class="updated published" title="20100228T15:08:00">February 28th</a></time></h3>
                                </hgroup>
                                <p class="entry-summary">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque feugiat hendrerit ante ut sagittis. Fusce blandit interdum tellus, non ornare massa luctus id. Proin lectus libero, dignissim sit amet dignissim in, facilisis sit amet tellus. Aenean sed felis a justo ultrices facilisis. Sed vehicula sagittis consequat. Donec iaculis lacinia augue eu aliquam. Vestibulum aliquet erat quis felis venenatis a ullamcorper diam semper. Donec vel neque quis sem fermentum tincidunt ac in mi. Pellentesque auctor consectetur justo, eu fermentum urna volutpat sit amet. Suspendisse lacus tellus, porta sed condimentum et, elementum vel diam. Donec at massa neque. Sed lobortis feugiat metus, tincidunt dignissim quam convallis sed.</p>
                                <footer><a href="#">Comment on this (5)</a>&emsp;&bull;&emsp;<a href="#">Tweet this</a>&emsp;&bull;&emsp;<a href="#">Stumble Upon</a></footer>
                                <br /><hr /><br />
                        </article>
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
