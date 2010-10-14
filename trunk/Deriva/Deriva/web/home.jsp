<%@page import="java.util.List"%>
<%--<%@page import="br.senac.sp.bcc.lab2.Storage"%>--%>
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.neg.Usuario" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%  session = request.getSession();
    String senha = "";
    String usuario = "";

    if (session != null && session.getAttribute("usuario") != null &&
            session.getAttribute("senha") != null){
            senha = session.getAttribute("senha").toString();
            usuario = session.getAttribute("usuario").toString();

        if (usuario.isEmpty() || senha.isEmpty()){
            response.sendRedirect("index.jsp");
            return;
        }
        else{
            Usuario u = new Usuario(usuario, senha);
            Autorizacao aut = new Autorizacao(u);
             if (!aut.PossuiAutorizacao(usuario, senha)){
                response.sendRedirect("index.jsp");
                return;
            }
        }
    }
    else response.sendRedirect("index.jsp");
  %>

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
                        <a href="#"><img src="images/logo.gif" alt="Logo" /></a>
                        <!--<hgroup>
                                <h1>Deriva</h1>
                                <h2>Rede Social</h2>
                        </hgroup>-->
                </div>
                <nav>
                        <ul>
                                <li><a href="/home.jsp">home</a></li>
                                <li><a href="/cadastro.jsp">cadastro</a></li>
                                <li><a href="/ListaUsuarios.jsp">Lista de Usuarios</a></li>
                                <li><a href="#">123</a></li>
                                <li><a href="#">123</a></li>
                                <li><a href="#">123t</a></li>
                        </ul>
                </nav>
        </header>

        <div id="content">
                <section class="hfeed">
                        <article class="hentry">
                                <hgroup>
                                        <h2 class="entry-title"><a href="/home.jsp">Bem Vindo, <%= usuario %>!</a></h2>
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
