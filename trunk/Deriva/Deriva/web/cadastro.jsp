<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>

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
            <form method="post" action="Cadastra">
                <p><label>E-Mail:</label> <input name="email" type="text" class="input" maxlength="255">
                <p><label>Senha:</label> <input name="senha" type="password" class="input"  maxlength="10">
                <p><label>Nome:</label> <input name="nome" type="text" size="20" class="input"  maxlength="255">
                <p><label>Sobrenome:</label> <input name="sobrenome" type="text" class="input"  maxlength="255">
                <p><label>Ano de nascimento:</label> <input name="dia" type="text" class="inputDate" maxlength="2">/<input name="mes" type="text"  class="inputDate" maxlength="2">/<input name="ano" type="text" class="inputDate"  maxlength="4">
                <p><input type="submit" value="Enviar" class="button"></p>
               <div class="errorCode">
                   <%@include file="errorcode.jspf" %>
               </div>
            </form>
        </div>
    </body>
</html>
