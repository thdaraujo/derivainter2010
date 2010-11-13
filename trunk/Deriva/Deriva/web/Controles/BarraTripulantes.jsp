<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="deriva.app.BarraTripulantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
      <!--  <h2>Contatos</h2>
        <ul>
                <li><a href="#">contato 1</a></li>
                <li><a href="#">contato 2</a></li>
                <li><a href="#">contato 3</a></li>
        </ul>
        <br /> -->
      
        <h2>Contatos</h2>
        <ul>
           <c:forEach var="usr" items="${requestScope.listaAmigos}">
                <li><a href="mostraPerfil?id=${usr.idusuario}">
                        <img src="${usr.imagemPerfil}">
                        <h3 class="entry-title">${usr.nickname}</h3>
                    </a>
                </li>
           </c:forEach>
       </ul>
       <br />

        <%--
        <h2>Comunidades</h2>
        <ul>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
        </ul>
        <br /> --%>
    </body>
</html>
