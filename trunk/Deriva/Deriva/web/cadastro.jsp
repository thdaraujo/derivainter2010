<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <style type="text/css" media="screen">
            @import url("/css/stylesLogin.css");
        </style>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.js"></script>
        <script type="text/javascript">

            function VerificaEmail()
            {
                email = $('#emailid').val();
                regexp = /[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}/;
                if(!email.match(regexp))
                    {
                       alert(email + " é um e-mail inválido");
                    }
            };

            function VerificaSenha()
            {
                senha1 = $('#senha1id').val();
                senha2 = $('#senha2id').val();
                if (senha1 != null && senha2 != null)
                    {
                        if(senha1 == senha2)
                            {
                                alert("teste");
                            }
                    }
            }

        </script>
        <title>Ficha de Embarque</title>
    </head>
    <body>      

        <div class="box cadastro">
                <h1><p>Preencha sua ficha de embarque no <strong>Deriva</strong>!<br /></h1>
               <form method="post" action="Cadastra">                           
                    <label>E-Mail:</label> <input id ="emailid" name="email" type="text" class="input" maxlength="255" onchange="VerificaEmail();"><br /><br />
                        <label>Senha:</label> <input id="senha1id" name="senha" type="password" class="input"  maxlength="10" onchange="VerificaSenha();"><br />
                        <label>Digite a senha novamente:</label> <input id="senha2id" name="senha2" type="password" class="input"  maxlength="10" onchange="VerificaSenha();"><br /><br />
                    <label>Nickname:</label> <input name="nickname" type="text" class="input"  maxlength="10"><br />
                    <label>Nome:</label> <input name="nome" type="text" size="20" class="input"  maxlength="20"><br />
                    <label>Sobrenome:</label> <input name="sobrenome" type="text" class="input"  maxlength="100"><br />
                    <label>Sexo:</label> M<input type="radio" name="sexo" VALUE="M" class="radiobutton"> F<input type="radio" name="sexo" VALUE="F" class="radiobutton"><br />
                    <label>Mensagem Pessoal:</label> <input name="mensagemPessoal" type="text" class="input" maxlength="1000"><br />
                    <label>Imagem para o Perfil:</label> <input name="imagemPerfil" type="text" class="input"  maxlength="100"><br />
                    <label>Ano de nascimento:</label> <input name="dia" type="text" class="input Date" maxlength="2">/<input name="mes" type="text"  class="input Date" maxlength="2">/<input name="ano" type="text" class="input Date ano"  maxlength="4">

                       <input type="submit" value="Enviar" id="botao" accesskey="Enter"/>
                      <errorCode>
                          <%@include file="errorcode.jspf" %>
                      </errorCode>
                </form>             
         </div>
       <%-- 
            <center><h1>Deriva</h1></center>
            <div class="campoLogin">
                <center> <h1>Cadastre-se!</h1></center>
                <form method="post" action="Cadastra">
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
                    <p><input type="submit" value="Enviar" class="button" accesskey="Enter"></p>

                </form>
            </div>
        --%>
    </body>
</html>
