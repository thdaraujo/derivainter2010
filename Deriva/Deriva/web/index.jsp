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
        <script type="text/javascript" src="scripts/bouncebox-plugin/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="scripts/bouncebox-plugin/jquery.bouncebox.1.0.js"></script>
        <script type="text/javascript" src="scripts/script.js"></script>
        <title>Partir!</title>
    </head>
    <body>
            <div id="main">
                <b><errorCode>
                    <%@include file="errorcode.jspf" %>
                </erorCode></b>
                <a class="button" href="#">Partir!</a>
                <p>Clique para navegar no <strong>Deriva</strong>.<br />
                <h1><a href="cadastro.jsp">Ou preencha sua ficha de embarque! &raquo;</a></h1>
            </div>

           <div id="box" class="box">
            <p><b>Yarr!</b></p>
            <form method="post" action="SignIn">
                       <label>Usuario:</label> <input type="text" name="usuario" class="input" maxlength="255" /><br />
                       <label>Senha: </label> <input type="password" name="senha" class="input" maxlength="50"/><br />                           
                       <input type="submit" value="Partir!" id="botao" />
                       <errorCode>
                            <%@include file="errorcode.jspf" %>
                       </erorCode>
                </form>
            </div>
    </body>
</html>
