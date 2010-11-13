<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.neg.Usuario" %>
<%@page import="deriva.app.AutorizacaoApp" %>

<%
    if (!AutorizacaoApp.autoriza(request, response))
        return;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="pt">
    <head>
        <meta charset="utf-8" />
        <title>Deriva - Usuario</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!--[if IE]>
        <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <link rel="stylesheet" type="text/css" href="scripts/iehacks.css" />
        <![endif]-->

        <!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="scripts/ie67hacks.css" />
        <![endif]-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
	<script src="scripts/jquery.ae.image.resize.min.js"></script>
	<script>
		$(function() {
			$(".imagemPerfilBarra").aeImageResize({height: 50, width: 50});
                        $(".imagemPerfil").aeImageResize({height: 150, width: 150});
		});
	</script>
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
                                        <h3> <a class="author" href="#">${requestScope.usuario.email}</a> </h3>
                                </hgroup>
                                <img src="${requestScope.usuario.imagemPerfil}" class="imagemPerfil">
                                <p class="entry-summary"> ${requestScope.usuario.mensagemPessoal}  </p>
                                <footer><a href="/SalvaTripulante?idusuario=${requestScope.usuario.idusuario}">Adicionar tripulante!</a>&emsp;&bull;&emsp;<a href="#">Enviar torpedo</a>&emsp;&bull;&emsp;</footer>
                                <br /><hr /><br />
                        </article>
                </section>
                <aside>
                    <!-- Gambiware: chamo o servlet e o jsp - deve haver um jeito melhor de executar o servlet E DEPOIS dar
                         include no conteudo da barra, mas não descobri ainda como. Redirect explode tudo. -->
                    <jsp:include page="/BarraTripulantes?id=${requestScope.usuario.idusuario}"></jsp:include>
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
