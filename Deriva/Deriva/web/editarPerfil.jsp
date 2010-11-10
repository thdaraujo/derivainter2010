<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="deriva.db.userDAO"%>
<%@page import="java.util.List"%>
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="pt">
    <head>
        <meta charset="utf-8" />
        <title>Deriva - Editar Perfil</title>
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
              <%@ include file="Controles/Topo.html" %>
        </header>
        <div id="content">
                <section class="hfeed">
                        <article class="hentry">
                                <hgroup>
                                        <h2 class="entry-title">Editar perfil</a></h2>
                                        <h3> <a class="author" href="#"> ${requestScope.usuario.email} </a> </h3>
                                </hgroup>
                                 <img src="${requestScope.usuario.imagemPerfil}" class="imagemPerfil">
                                <form method="post" action="SalvarPerfil">
                             <!--       <p><label>Senha:</label> <input name="senha" type="password" class="input"  maxlength="10">-->
                             <!--       <p><label>Digite a senha novamente:</label> <input name="senha2" type="password" class="input"  maxlength="10">-->
                                    <p><label>Nickname:</label> <input name="nickname" type="text" class="input"  maxlength="10" value="${requestScope.usuario.nickname}">
                                    <p><label>Mensagem Pessoal:</label> <textarea name="mensagemPessoal" rows="10" cols="30" type="text" class="input" maxlength="1000">${requestScope.usuario.mensagemPessoal}</textarea>
                                    <p><label>Imagem para o Perfil:</label> <input name="imagemPerfil" type="text" class="input" style="width:300px;"  maxlength="100" value="${requestScope.usuario.imagemPerfil}">
                                    <p><input type="submit" value="Salvar" class="button"></p>
                                   <div class="errorCode">
                                       <%@include file="errorcode.jspf" %>
                                   </div>
                                </form>
                        </article>
                </section>

                <aside>
                    <%@ include file="Controles/BarraTripulantes.jsp" %>
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
