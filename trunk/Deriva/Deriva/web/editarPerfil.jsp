<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="deriva.db.userDAO"%>
<%@page import="java.util.List"%>
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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <style type="text/css" media="screen">
            @import url("/css/site.css");
        </style>
        <title>Cadastro</title>
    </head>
    <body>
        <center><h1>Deriva</h1></center>
        <div class="campoLogin">
            <center> <h1>Editar perfil</h1></center>
            <form method="post" action="SalvarPerfil">
                <p><label >E-Mail:</label> <label >${requestScope.usuario.email}</label>
         <!--       <p><label>Senha:</label> <input name="senha" type="password" class="input"  maxlength="10">-->
         <!--       <p><label>Digite a senha novamente:</label> <input name="senha2" type="password" class="input"  maxlength="10">-->
                <p><label>Nickname:</label> <input name="nickname" type="text" class="input"  maxlength="10" value="${requestScope.usuario.nickname}">
                <p><label>Mensagem Pessoal:</label> <input name="mensagemPessoal" type="text" class="input" maxlength="1000" value="${requestScope.usuario.mensagemPessoal}">
                <p><label>Imagem para o Perfil:</label> <input name="imagemPerfil" type="text" class="input"  maxlength="100" value="${requestScope.usuario.imagemPerfil}">
                <p><input type="submit" value="Salvar" class="button" accesskey="Enter"></p>
               <div class="errorCode">
                   <%@include file="errorcode.jspf" %>
               </div>
            </form>
        </div>
    </body>
</html>
