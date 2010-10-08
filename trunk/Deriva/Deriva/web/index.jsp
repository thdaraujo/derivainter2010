<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.List"%>
<%@page import="deriva.app.Storage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body><center>
        <h1>LOGIN</h1>

		<!-- formulário de login -->
		Entre com seus dados:
		<form method="post" action="SignIn">
			Usuario: <input type="text" name="usuario" /><br/>
			Senha: <input type="password" name="senha" /><br/>
			<input type="submit" value="Sign In" />
			<%@include file="errorcode.jspf" %>
		</form>
        <a href="cadastro.jsp">Novo por aqui? Cadastre-se</a>
    </center>
    </body>
</html>
