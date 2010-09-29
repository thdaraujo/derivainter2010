<%-- 
    Document   : index
    Created on : 29/09/2010, 13:35:15
    Author     : HAL
--%>
<%@page import="Controller.Usuario"%>
<%@page import="Controller.ListaUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>



        <p>
            <%
                ListaUsuarios lista = new ListaUsuarios();
                Usuario user = new Usuario();

                user = lista.getUsuarioPorId(1);
            %>
        </p>




    </body>
</html>
