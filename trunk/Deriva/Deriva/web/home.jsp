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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>
    </head>

    <body>
        <div>
            &nbsp;
        </div>
        <header>
              <%@ include file="Controles/Topo.html" %>
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
                </section>
                <aside>
                    <!-- Gambiware: chamo o servlet e o jsp - deve haver um jeito melhor de executar o servlet E DEPOIS dar
                         include no conteudo da barra, mas não descobri ainda como. Redirect explode tudo. -->
                    <jsp:include page="/BarraTripulantes"></jsp:include>
                    <jsp:include page="/Controles/BarraTripulantes.jsp"></jsp:include>
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
