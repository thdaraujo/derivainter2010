<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>


<%
        LoadCampos();

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
            <center> <h1>Cadastre-se!</h1></center>
            <form method="post" action="EditarPerfil">
                <p><label>E-Mail:</label> <input name="email" type="text" class="input" maxlength="255">
                <p><label>Senha:</label> <input name="senha" type="password" class="input"  maxlength="10">
                    <p><label>Digite a senha novamente:</label> <input name="senha2" type="password" class="input"  maxlength="10">
                <p><label>Nickname:</label> <input name="nickname" type="text" class="input"  maxlength="10">
                <p><label>Nome:</label> <input name="nome" type="text" size="20" class="input"  maxlength="20">
                <p><label>Sobrenome:</label> <input name="sobrenome" type="text" class="input"  maxlength="100">
                <p><label>Sexo:</label> M<input type="radio" name="sexo" VALUE="M" class="radiobutton"> F<input type="radio" name="sexo" VALUE="F" class="radiobutton">
                <p><label>Mensagem Pessoal:</label> <input name="mensagemPessoal" type="text" class="input" maxlength="1000">
                <p><label>Imagem para o Perfil:</label> <input name="imagemPerfil" type="text" class="input"  maxlength="100">
                <p><label>Ano de nascimento:</label> <input name="dia" type="text" class="input Date" maxlength="2">/<input name="mes" type="text"  class="input Date" maxlength="2">/<input name="ano" type="text" class="input Date ano"  maxlength="4">
                <p><input type="submit" value="Enviar" onclick="<% %>"  class="button" accesskey="Enter"></p>
               <div class="errorCode">
                   <%@include file="errorcode.jspf" %>
               </div>
            </form>
        </div>
    </body>
</html>
