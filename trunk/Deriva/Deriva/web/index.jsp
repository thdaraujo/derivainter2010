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
            @import url("/css/site.css");
        </style>

        <title>Login</title>
    </head>
    <body>
        <center><h1>Deriva</h1></center>
        <!-- formulário de login -->
        <div class="campoLogin">
            <h1>Entre com seus dados:</h1>
            <form method="post" action="SignIn">
                <p><label>Usuario:</label> <input type="text" name="usuario" class="input" maxlength="255" /></p>
                <p><label>Senha:</label> <input type="password" name="senha" class="input" maxlength="50"/></p>
                    <p><input type="submit" value="Sign In" class="button" /></p>
                    <div class="errorCode">
                            <%@include file="errorcode.jspf" %>
                    </div>
            </form>
        </div>
        <center><a href="cadastro.jsp" class="linkSimples">Novo por aqui? Cadastre-se!</a></center>

</body>
</html>
