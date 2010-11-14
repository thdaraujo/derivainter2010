<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="deriva.db.DAOFactory"%>
<%@page import="deriva.app.AutorizacaoApp" %>
<%@page import="deriva.neg.Usuario"%>
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.app.ListaUsuarios"%>

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
        <title>Deriva - Lista de Navegantes</title>
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
                                    <h2>Lista de Navegantes</h2>
                                    <p>
                                         <c:if test="${requestScope.hasPrevious == true}">
                                               <a href="/ListaUsuarios?pagina=${requestScope.numeroPagina - 1}"><h4>< anterior</h4></a>
                                           </c:if>
                                           <c:if test="${requestScope.hasNext == true}">
                                               <a href="/ListaUsuarios?pagina=${requestScope.numeroPagina + 1}"><h4> proxima > </h4></a>
                                           </c:if>                                          
                                    </p>

                                    <c:forEach var="usr" items="${requestScope.listaPaginada}">                                                
                                                <article class="hentry">                                                        
                                                        <hgroup>
                                                            <a href="MostraPerfil?id=${usr.idusuario}">
                                                                <img src="${usr.imagemPerfil}" class="imagemPerfil">
                                                                <h3 class="entry-title">${usr.nickname}</h3>
                                                            </a>
                                                        </hgroup>
                                                        <p class="entry-summary">${usr.mensagemPessoal}</p> 
                                                        <br /><hr /><br />
                                                </article>
                                       </c:forEach>
                                    <p>
                                        <c:if test="${requestScope.hasPrevious == true}">
                                           <a href="/ListaUsuarios?pagina=${requestScope.numeroPagina - 1}"><h4>< anterior</h4></a>
                                       </c:if>
                                       <c:if test="${requestScope.hasNext == true}">
                                           <a href="/ListaUsuarios?pagina=${requestScope.numeroPagina + 1}"><h4>proxima ></h4></a>
                                       </c:if>                                       
                                    </p>
                                
                     </section>
                <aside>
                    //TODO - Definir o que deve ser colocado na barra lateral daqui.
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