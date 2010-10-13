<%@page import="java.util.List"%>
<%--<%@page import="br.senac.sp.bcc.lab2.Storage"%>--%>
<%@page import="deriva.neg.Autorizacao" %>
<%@page import="deriva.neg.Usuario" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%  session = request.getSession();
    String senha = "";
    String usuario = "";

    if (session != null && session.getAttribute("usuario") != null &&
            session.getAttribute("senha") != null){
            senha = session.getAttribute("senha").toString();
            usuario = session.getAttribute("usuario").toString();

        if (usuario.isEmpty() || senha.isEmpty()){
            response.sendRedirect("index.jsp");
            return;
        }
        else{
            Usuario u = new Usuario(usuario, senha);
            Autorizacao aut = new Autorizacao(u);
             if (!aut.PossuiAutorizacao(usuario, senha)){
                response.sendRedirect("index.jsp");
                return;
            }
        }
    }
    else response.sendRedirect("index.jsp");
  %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <style type="text/css" media="screen">
            @import url("/css/site.css");
        </style>
        <title>Home</title>

    </head>
    <body>
        <h1>Bem vindo, <%= usuario%>!</h1>
        <p><a href="SignOut" class="linkSimples">Sign out</a></p>

        <div class="navigation">
            <ul id="nav">
            <li><a href="#nowhere" title="Lorum ipsum dolor sit amet">Lorem</a></li>
            <li><a href="#nowhere" title="Aliquam tincidunt mauris eu risus">Aliquam</a></li>
            <li><a href="#nowhere" title="Morbi in sem quis dui placerat ornare">Morbi</a></li>
            <li><a href="#nowhere" title="Praesent dapibus, neque id cursus faucibus">Praesent</a></li>
            <li><a href="#nowhere" title="Pellentesque fermentum dolor">Pellentesque</a></li>
            </ul>
        </div>

        <div class="content">
            <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan porttitor, facilisis luctus, metus</p>
        </div>


    </body>
</html>
