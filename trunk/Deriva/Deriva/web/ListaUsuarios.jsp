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
                                <li><a href="/ListaUsuarios">Lista de Usuarios</a></li>
                                <li><a href="/EditarPerfil">Editar Perfil</a></li>
                                <li><a href="/SignOut">Logout</a></li>
                                <li><a href="#">123t</a></li>
                        </ul>
                </nav>
        </header>

        <div id="content">
                <section class="hfeed">                        
                                    <h2>Lista de Usuarios</h2>                                
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
                                                        <a href="mostraPerfil?id=${usr.idusuario}">
                                                        <hgroup>
                                                             <a href="#"><img src="${usr.imagemPerfil}">
                                                            <h3 class="entry-title">${usr.nickname}</h3>
                                                            ${usr.email}</a>
                                                        </hgroup>
                                                        <p class="entry-summary">${usr.mensagemPessoal}</p>
                                                        </a>
                                                        <footer></footer>
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